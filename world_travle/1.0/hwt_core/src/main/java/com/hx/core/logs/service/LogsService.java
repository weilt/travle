package com.hx.core.logs.service;

import com.hx.core.logs.annotation.InsertIncId;
import com.hx.core.logs.entity.parent.Logs;
import com.hx.core.logs.exception.LogsException;
import com.hx.core.mongo.service.MongoService;
import com.hx.core.mongo.value.MongoQueryCondition;
import com.hx.core.mongo.value.MongoQueryValue;
import com.hx.core.mongo.value.Sort;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * Created by RO on 2017/11/7.
 */
@Service
public class LogsService {

    private final static Logger log = LoggerFactory.getLogger(LogsService.class);

    private final static String MONGO_DB_NAME = "hwt_logs";

    /**
     * 单表最大保存条数
     */
    private static long tableMaxCount = 5000000;

    /**
     * 添加自增长ID的日志 表名默认为类名
     * @param t
     * @throws LogsException
     */
    @Async
    public void addInsertIncId(Logs t) throws LogsException {
        insertIncId(t,null);
    }

    /**
     * 添加自增长ID的日志
     * @param t
     * @param tableName 表名
     * @throws LogsException
     */
    @Async
    public void addInsertIncId(Logs t, String tableName) {
        try {
            insertIncId(t,tableName);
        } catch (LogsException e) {
            log.error("LogsService method addInsertIncId LogsException msg : {}",e.getMessage());
        }
    }

    /**
     * 非自增长ID的日志添加
     * @param t
     * @throws LogsException
     */
    @Async
    public void add(Logs t) {
        try {
            insert(t,null);
        } catch (LogsException e) {
            log.error("LogsService method add LogsException msg : {}",e.getMessage());
        }

    }

    /**
     * 非自增长ID的日志添加
     * @param t
     * @param tableName
     * @throws LogsException
     */
    @Async
    public void add(Logs t, String tableName) throws LogsException {
        insert(t,tableName);
    }


    /**
     * 自增长ID的添加
     * @param t
     * @param tableName
     * @throws LogsException
     */
    private void insertIncId(Logs t, String tableName) throws LogsException {
        String className = StringUtils.isBlank(tableName) ? t.getClass().getSimpleName().toLowerCase() : tableName;

        //如果分表最后一个表大于2000000条数据 则添加到新分表
        //查询最后一个分表的表名 分表名称为 className + 索引(1,2,3,.......N )
        String tname = MongoService.getMaxTableName(MONGO_DB_NAME,className);
        if(StringUtils.isNotBlank(tname)) {
            if(MongoService.getLastTableCount(MONGO_DB_NAME,tname) > tableMaxCount) {
                className += Integer.valueOf(tname.substring(className.length())) + 1;
            }
        }

        String incidName = null;
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field f : fields) {
            InsertIncId insertIncId = f.getAnnotation(InsertIncId.class);
            if(insertIncId != null) {
                incidName = StringUtils.isBlank(insertIncId.name()) ? f.getName() : insertIncId.name();
                break;
            }
        }
        if(StringUtils.isBlank(incidName)) throw new LogsException( "class "+className+" is not find annotation InsertIncId;");
        else {
            try {
                MongoService.insertIncId(MONGO_DB_NAME,className,t,incidName);
            } catch (Exception e) {
                log.error("LogsService method insertIncId Exception msg : {}",e.getMessage());
                throw new LogsException("adding "+className+" log failed");
            }
        }
    }

    /**
     * 非自增长ID的添加
     * @param t
     * @param tableName
     * @throws LogsException
     */
    private void insert(Logs t, String tableName) throws LogsException {
        String className = StringUtils.isBlank(tableName) ? t.getClass().getSimpleName().toLowerCase() : tableName;

        //如果分表最后一个表大于2000000条数据 则添加到新分表
        //查询最后一个分表的表名 分表名称为 className + 索引(1,2,3,.......N )
        String tname = MongoService.getMaxTableName(MONGO_DB_NAME,className);
        if(StringUtils.isNotBlank(tname)) {
            if(MongoService.getLastTableCount(MONGO_DB_NAME,tname) > tableMaxCount) {
                className += Integer.valueOf(tname.substring(className.length())) + 1;
            } else {
                className = tname;
            }
        } else {
            className = className + "0";
        }

        try {
            MongoService.insert(MONGO_DB_NAME,className,t);
        } catch (Exception e) {
            throw new LogsException("adding "+className+" log failed");
        }
    }


    /**
     * 分页查询
     * 该方法只支持 AND连接的查询条件
     * @param queryValues   查询条件数组
     * @param tableName     表名
     * @param pageIndex     开始条数
     * @param pageSize      查询条数
     * @param sort          排序
     * @return
     */
    public List<Map<String,Object>> getPageToAnd(String tableName, int pageIndex, int pageSize, Sort sort, MongoQueryValue...queryValues) {
        return MongoService.findByPage(MONGO_DB_NAME,tableName,null
                ,new MongoQueryCondition(MongoQueryCondition.LinkKey.and,queryValues),sort,pageIndex,pageSize);
    }
}
