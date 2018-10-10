package com.hx.core.mongo.provider;

import com.hx.core.mongo.utils.JsonUtils;
import com.hx.core.mongo.value.Sort;
import com.hx.core.utils.PropertiesUtils;
import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * mongoDB数据库操作类
 * 注：	1、新版本所有操作对象均用Document 实现了Map(包括添加，修改，查询条件，返回结果等)
 * 		2、组装查询条件用Filters类 (例:Filters.ne("字段名","匹配数据"))
 * 		3、mongo条件关联的操作符对照:
 * 			$gt：>;  	$gte：>=; 	$lte:<=;	$nin:not in(后面i的值为bson对象数组);
 * 			$eq:=; 		$ne:!=;		$lt:<;		$in:in(后面的值为bson对象数组);
 * 		4、以下组装条件表示查询 (name=AAA and age=30 ) or (name=xxx and age=22 ) 
 * 		Filters.or(Filters.and(Filters.eq("name", "AAA"),Filters.eq("age", 30)),
 * 										Filters.and(Filters.eq("name", "xxx"),Filters.eq("age", 22)));
 * 
 * 
 * @author WJ
 */
@SuppressWarnings("all")
@Component
public class MongoProvider {

    @Autowired
    private Environment env;

	private final static Logger logger = LoggerFactory.getLogger(MongoProvider.class);
    /** 
     * MongoClient的实例代表数据库连接池，是线程安全的，可以被多线程共享，客户端在多线程条件下仅维持一个实例即可 
     * Mongo是非线程安全的，目前mongodb API中已经建议用MongoClient替代Mongo 
     */  
    private MongoClient mongoClient = null;  
  
    private final static String KEY_HOST = "HOST";
    private final static String KEY_PORT = "PORT";
    
    /** 
     * 构造方法 
     * 
     * @param host   127.0.0.1 
     * @param port 27107 
     */  
    
    private MongoProvider() {
        if (mongoClient == null) {
            //env.getProperty("xx.mongo.host")
            //Integer.valueOf(env.getProperty("xx.mongo.port"))
        	
        	
        	//String host =  "192.168.1.247";
        	// Integer port = 27017;
			
        	Properties properties = PropertiesUtils.getProperties("my-config.properties");
        	String host = properties.getProperty("xx.mongo.host");
			Integer port = Integer.parseInt(properties.getProperty("xx.mongo.port"));
			System.err.println(host+"==========="+port);
            MongoClientOptions.Builder build = new MongoClientOptions.Builder();  
            /**
             * 一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间为2分钟 
             * 这里比较危险，如果超过maxWaitTime都没有获取到这个连接的话，该线程就会抛出Exception 
             * 故这里设置的maxWaitTime应该足够大，以免由于排队线程过多造成的数据库访问失败 
             */  
            build.maxWaitTime(1000 * 60 * 2);
            build.connectTimeout(1000 * 60 * 1);    //与数据库建立连接的timeout设置为1分钟
            build.socketTimeout(0);// 套接字超时时间，0无限制
            build.connectionsPerHost(300);   //连接池设置为300个连接,默认为100
            build.threadsAllowedToBlockForConnectionMultiplier(5000);// 线程队列数，如果连接线程排满了队列就会抛出“Out of semaphores to get db”错误。
            build.writeConcern(WriteConcern.ACKNOWLEDGED);
  
            MongoClientOptions myOptions = build.build();  
            try {  
                //数据库连接实例  
                mongoClient = new MongoClient(new ServerAddress(host, port), myOptions);
            } catch (MongoException e) {  
            	System.err.println(1111111);
                e.printStackTrace();  
            }  
  
        }  
    }  
  
  
    private static MongoProvider mongoProvider = null;  
  
    /** 
     * 获取实例 
     * 
     * @param host   127.0.0.1 
     * @param port 27107 
     * @return 
     */  
    public synchronized static MongoProvider getInstance() {  
        if (mongoProvider == null) {  
        	mongoProvider = new MongoProvider();  
        }  
        return mongoProvider;  
    }  
  
    /**
     * 查询是否存在
     * @param dbName
     * @param collectionName
     * @param filter
     * @return
     */
    public boolean isExits(MongoCollection conllection, Bson filter) {
        if (filter != null) {
            FindIterable<Document> docs = conllection.find(filter);
            Document doc = docs.first();  
            if (doc != null) {  
                return true;  
            } else {  
                return false;  
            }  
        }  
        return false;  
    }  
  
    /**
     * 获得数据库链接对象
     * @param dbName	数据库名称
     * @param collectionName	表名	
     * @return
     */
	public MongoCollection getCollection(String dbName, String collectionName) {  
        return mongoClient.getDatabase(dbName).getCollection(collectionName);  
    }  
  
    /**
     * 获得数据源
     * @param dbName 数据库名称
     * @return
     */
    public MongoDatabase getDatabase(String dbName) {  
        return mongoClient.getDatabase(dbName);  
    }  
  
    /**
     * 查询总数
     * @param dbName
     * @param collectionName
     * @return
     */
    public long getCount(String dbName, String collectionName) {  
        return getDatabase(dbName).getCollection(collectionName).count();  
    } 
    
    /**
     * 添加
     * @param dbName
     * @param collectionName
     * @param insertMap
     * @return
     */
    public void insert(MongoCollection conllection, Map<String, Object> insertMap) {
        if (insertMap != null) {
            String json = JsonUtils.Bean2Json(insertMap);
        	conllection.insertOne(Document.parse(json));
        }
    }

    /**
     * 添加
     * @param dbName
     * @param collectionName
     * @param t
     * @return
     */
    public <T> void insert(MongoCollection conllection, T t) {
        if (t != null) {
            String json = JsonUtils.Bean2Json(t);
            conllection.insertOne(Document.parse(json));
        }
    }

    /**
     * ID删除
     * @param conllection
     * @param _id
     * @return
     */
    public boolean deleteById(MongoCollection conllection, String _id) {  
        ObjectId objectId = new ObjectId(_id);  
        Bson filter = Filters.eq("_id", objectId); 
        
        DeleteResult deleteResult = conllection.deleteOne(filter);  
        long deletedCount = deleteResult.getDeletedCount();  
  
        return deletedCount > 0 ? true : false;  
    }  
  
  
    /**
     * 条件删除
     * @param conllection
     * @param map
     * @return
     */
    public boolean delete(MongoCollection conllection, Bson bson) {  
        if (bson != null) {  
            DeleteResult result = conllection.deleteMany(bson);  
            long deletedCount = result.getDeletedCount();  
            return deletedCount > 0 ? true : false;  
        }  
        return false;  
    }

    /**
     * 删除所有数据
     * @param conllection
     */
    public void deleteAll(MongoCollection conllection) {
        conllection.deleteMany(null);
    }
  
    /**
     * 多条件 更新一条数据
     * @param conllection
     * @param filterMap			条件map
     * @param updateMap			更新map
     * @return
     */
    public boolean updateOne(MongoCollection conllection, Bson filterMap, Map<String, Object> updateMap) {  
        if (filterMap != null && updateMap != null) {  
            UpdateResult result = conllection.updateOne(filterMap, new Document("$set", new Document(updateMap)));
            long modifiedCount = result.getModifiedCount();  
            return modifiedCount > 0 ? true : false;  
        }  
  
        return false;  
    }  
  
    /**
     * ID更新
     * @param conllection
     * @param _id
     * @param updateDoc
     * @return
     */
    public boolean updateById(MongoCollection conllection, String _id, Document updateDoc) {  
        ObjectId objectId = new ObjectId(_id);  
        Bson filter = Filters.eq("_id", objectId);  
        
        UpdateResult result = conllection.updateOne(filter, new Document("$set", updateDoc));  
        long modifiedCount = result.getModifiedCount();  
        return modifiedCount > 0 ? true : false;  
    }

    /**
     * 查询所有
     * @param conllection
     * @param sort
     * @param projection
     * @return
     */
    public List<Map<String,Object>> findAll(MongoCollection conllection, Sort sort, Bson projection) {
        List<Map<String,Object>> resultList = new ArrayList<>();
        FindIterable<Document> docs = conllection.find();
        if(projection != null)
            docs.projection(projection);
        if(sort != null)
            docs.sort(new Document(sort));
        docs.forEach(new Block<Document>() {
            public void apply(Document document) {
                resultList.add((Map<String,Object>)document);
            }
        });
        return resultList;
    }
    
    
    /**
     * 查询所有 --多排序
     * @param conllection
     * @param sorts
     * @param projection
     * @return
     */
    public List<Map<String, Object>> findALLSorts(MongoCollection conllection, Sort[] sorts, Bson projection) {
    	List<Map<String,Object>> resultList = new ArrayList<>();
        FindIterable<Document> docs = conllection.find();
        if(projection != null)
            docs.projection(projection);
//        if(sort != null)
//            docs.sort(new Document(sort));
        if (sorts!=null&&sorts.length>0){
        	Document document = new Document();
        	for (Sort sort : sorts) {
        		docs.sort(sort);
			}
        }
        docs.forEach(new Block<Document>() {
            public void apply(Document document) {
                resultList.add((Map<String,Object>)document);
            }
        });
        return resultList;
	}

    /**
     * 条件查询 
     * 默认的AND连接查询
     * @param conllection
     * @param filter	查询条件
     * @param sort	    排序
     * @param projection	指定查询的字段集合
     * @return
     */
    public List<Map<String,Object>> findAnd(MongoCollection conllection, Bson filter,Sort sort,Bson projection) {
        List<Map<String,Object>> resultList = new ArrayList<>();  
        FindIterable<Document> docs;
        if(filter != null)
            docs = conllection.find(filter);
        else
            docs = conllection.find();
        if(projection != null)
            docs.projection(projection);
        if(sort != null)
            docs.sort(new Document(sort));
        docs.forEach(new Block<Document>() {
            public void apply(Document document) {
                resultList.add((Map<String,Object>)document);
            }
        });

        return resultList;
    }  
    
    /**
     * 条件查询    多排序
     * 默认的AND连接查询
     * @param conllection
     * @param filter	查询条件
     * @param sort	    排序
     * @param projection	指定查询的字段集合
     * @return
     */
    public List<Map<String, Object>> findAndSorts(MongoCollection conllection, Bson filter, Sort[] sorts,
    		Bson projection) {
    	 List<Map<String,Object>> resultList = new ArrayList<>();
    	 
    	 
    	 
         FindIterable<Document> docs;
         if(filter != null)
             docs = conllection.find(filter);
         else
             docs = conllection.find();
         if(projection != null)
             docs.projection(projection);
//         if(sort != null)
//             docs.sort(new Document(sort));
         
         if (sorts!=null&&sorts.length>0){
          	Document document = new Document();
          	for (Sort sort : sorts) {
          		docs.sort(sort);
  			}
          }
         
         docs.forEach(new Block<Document>() {
             public void apply(Document document) {
                 resultList.add((Map<String,Object>)document);
             }
         });

         return resultList;
	}

  
    /**
     * 条件查询 
     * 自定义查询 创建Bson对象未查询条件 
     * @param conllection
     * @param filter		查询条件
     * @param sort		
     * @param projection	指定查询的字段集合
     * @return
     */
    public List<Map<String,Object>> findCustom(MongoCollection conllection, Bson filter, Sort sort,Bson projection) {
        List<Map<String,Object>> resultList = new ArrayList<>();  
        FindIterable<Document> docs;
        if (filter != null) {  
            docs = conllection.find(filter);  
        } else {
        	docs = conllection.find(); 
        }
        if(projection != null)
            docs.projection(projection);
        if(sort != null)
        	docs.sort(new Document(sort));
        docs.forEach(new Block<Document>() {  
            public void apply(Document document) {  
                resultList.add((Map<String,Object>)document);  
            }  
        });
        return resultList;  
    }

    /**
     * 获取自增长ID
     * @param conllection
     * @param sort      排序
     * @param idName    自增长ID名
     * @return
     */
    public Long getIncId(MongoCollection conllection,String idName) {
        FindIterable<Document> docs;
        docs = conllection.find().sort(new Sort(idName,Sort.DESC));
        if(docs != null) {
            Document d = docs.first();
            if(d!= null && d.get(idName) != null) {
                Long incId = Long.parseLong(d.get(idName).toString());
                return 1+incId;
            }
        }
        return 1L;
    }

    /**
     * 查询一条记录
     * @param conllection
     * @param filter        查询条件
     * @param projection    指定查询的字段集合
     * @return
     */
    public Map<String,Object> findOne(MongoCollection conllection, Bson filter,Bson projection,Sort sort) {
        FindIterable<Document> docs;
        if (filter != null) {
            docs = conllection.find(filter);
            if(sort != null) docs.sort(sort);
            if(projection != null) docs.projection(projection);
            return docs.first();
        } else {
            return null;
        }
    }

    /**
     * 分组查询
     * @param conllection
     * @param groups
     * @param projection
     * @param sort
     * @return
     */
    public List<Map<String,Object>> findGroup(MongoCollection conllection,List<Document> groups,Document projection,Sort sort) {
        if(sort != null)  groups.add(sort);
        if(projection != null)  groups.add(projection);
        AggregateIterable<Document> docs = conllection.aggregate(groups);
        if(docs != null) {
            List<Map<String,Object>> resultList = new ArrayList<>();
            docs.forEach(new Block<Document>() {
                public void apply(Document document) {
                    resultList.add((Map<String, Object>) document);
                }
            });
            return resultList;
        }
        return null;
    }

    /**
     * ID查询
     * @param dbName
     * @param collectionName
     * @param _id
     * @param projection	指定查询的字段集合
     * @return
     */
    public Map<String,Object> findById(MongoCollection conllection, String _id,Bson projection) {
        ObjectId objectId = new ObjectId(_id);
        FindIterable<Document> docs = conllection.find(Filters.eq("_id", objectId));
        if(projection != null)
            docs.projection(projection);
        Document doc = (Document) docs.first();
        return doc;  
    }

    /**
     * 分页查询
     * @param dbName
     * @param collectionName
     * @param filter 			条件
     * @param sort
     * @param pageIndex     	页码 从1开始
     * @param pageSize 			每页条数
     * @return
     */
    public List<Map<String,Object>> findByPage(MongoCollection conllection, 
    		Bson filter,Sort sort, int pageIndex, int pageSize,Bson projection) {
        List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();  
        FindIterable<Document> docs;
        if(filter != null)		
        	docs = conllection.find(filter);
        else
        	docs = conllection.find(); 
        if(sort!=null) docs.sort(new Document(sort));

        docs.skip((pageIndex - 1) * pageSize).limit(pageSize);
        if(projection != null) docs.projection(projection);
        docs.forEach(new Block<Document>() {  
            public void apply(Document document) {
                resultList.add(document);
            }
        });  
  
        return resultList;  
    }  
    
    /**
     * 分页查询  ---多排序
     * @param dbName
     * @param collectionName
     * @param filter 			条件
     * @param sorts
     * @param pageIndex     	页码 从1开始
     * @param pageSize 			每页条数
     * @return
     */
    public List<Map<String, Object>> findByPageSorts(MongoCollection conllection, Bson filter, Sort[] sorts,
			int pageIndex, int pageSize,Bson projection) {
    	List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();  
        FindIterable<Document> docs;
        if(filter != null)		
        	docs = conllection.find(filter);
        else
        	docs = conllection.find(); 
        //if(sort!=null) docs.sort(new Document(sort));
        if (sorts!=null&&sorts.length>0){
        	Document document = new Document();
        	for (Sort sort : sorts) {
        		docs.sort(sort);
			}
        }

        docs.skip((pageIndex - 1) * pageSize).limit(pageSize);
        if(projection != null) docs.projection(projection);
        docs.forEach(new Block<Document>() {  
            public void apply(Document document) {
                resultList.add(document);
            }
        });  
  
        return resultList; 
	}  
  
    /**
     * 条件查询总数
     * @param conllection
     * @param filter			查询条件
     * @return
     */
    public long findPageCount(MongoCollection conllection, Bson filter) {  
        return conllection.count(filter);  
    }
    
    /** 
     * 查询dbName下的所有表名 
     * @param dbName 
     * @return 
     */  
    public List<String> getAllCollections(String dbName) {  
        MongoIterable<String> cols = getDatabase(dbName).listCollectionNames();  
        List<String> _list = new ArrayList<String>();  
        for (String s : cols) {  
            _list.add(s);  
        }  
        return _list;  
    }

    /**
     * 查询dbName下包含指定名称的表名
     * @param dbName
     * @return
     */
    public List<String> getContainsCollections(String dbName, String tableName) {
        MongoIterable<String> cols = getDatabase(dbName).listCollectionNames();
        List<String> _list = new ArrayList<String>();
        for (String s : cols) {
            if(s.contains(tableName))
                _list.add(s);
        }
        return _list;
    }

    /** 
     * 获取所有数据库名称列表 
     * 
     * @return 
     */  
    public MongoIterable<String> getAllDatabaseName() {  
        MongoIterable<String> s = mongoClient.listDatabaseNames();  
        return s;  
    }  
  
    /** 
     * 删除一个数据库 
     * 
     * @param dbName 
     */  
    public void dropDatabase(String dbName) {  
        getDatabase(dbName).drop();  
    }  
  
    /** 
     * 删除collection 
     * 
     * @param dbName 
     * @param collectionName 
     */  
    public void dropCollection(String dbName, String collectionName) {  
        getDatabase(dbName).getCollection(collectionName).drop();  
    }  
  
    public void close() {  
        if (mongoClient != null) {  
            mongoClient.close();  
            mongoClient = null;  
        }  
    }

	
	

	

	
}  