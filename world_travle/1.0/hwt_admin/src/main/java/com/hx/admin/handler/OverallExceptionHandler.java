package com.hx.admin.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hx.admin.base.ResultCode;
import com.hx.admin.base.ResultEntity;
import com.hx.admin.validate.ValidateException;
import com.hx.core.exception.BaseException;
import com.hx.core.utils.ObjectHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by Administrator on 2017/8/5.
 * 全局异常处理
 * @ControllerAdvice  中的 @ExceptionHandler、@InitBinder、@ModelAttribute
 * @ExceptionHandler ： 应用到所有@RequestMapping注解的方法，在其抛出UnauthenticatedException异常时执行
 * @InitBinder ：应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
 * @ModelAttribute ： 应用到所有@RequestMapping注解方法，在其执行之前把返回值放入Model
 *
 */
@ControllerAdvice
public class OverallExceptionHandler{
	
    private static final Logger logger = LoggerFactory.getLogger(OverallExceptionHandler.class);
    
    /**
     * 所有的异常信息
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK) //200 自己处理错误
    public Object exception(HttpServletRequest request,HttpServletResponse response, Exception e) {
    	if(e.getMessage() == null || "".equals(e.getMessage())){
        	logger.error(e.getMessage(),e);
        }else{
        	logger.error("Exception:" + e.getMessage());
        }
    	//同步请求
		/*if(!ObjectHelper.x_requested_with(request)){
			try {
				request.getRequestDispatcher("/404/404.html").forward(request,response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		}*/
    	ResultEntity resultEntity = null;
    	String msg = formatException(e);  //友好提示
    	if (e instanceof BaseException){ 
    		//自定义异常，用于处理自己的异常数据信息
    		resultEntity = new ResultEntity(ResultCode.ERROR.code,msg);
    	} else if (e instanceof ValidateException){
    		//请求参数错误异常
    		resultEntity = new ResultEntity(ResultCode.REQUEST_PRAM.code,msg);
    	} else {
    		ResultCode resultCode = ResultCode.getResultCode(msg);
    		 if(resultCode == null) {
    			 e.printStackTrace();
    			 resultEntity = new ResultEntity(ResultCode.SYS_ERROR.code,msg);//系统错误
    		 } else {
    			 resultEntity = new ResultEntity(resultCode);
    	     }
    	}
        return resultEntity;
    }
    
    /** 
     * 格式化异常信息，用于友好响应用户 
     * @param e 
     * @return 
     */  
    private static String formatException(Exception e){  
    	String message = null;  
        String eClassName = e.getClass().getName();  
        //一些常见异常提示  
        if (e instanceof BaseException || e instanceof ValidateException) {  //自定义异常，用于处理当前自己的异常数据信息
            message = e.getMessage();  
        } else if("java.lang.NumberFormatException".equals(eClassName)){  
            message = "请输入正确的数字";  
        } else if("java.lang.NullPointerException".equals(eClassName)){
        	message = "空指针异常,请联系开发人员！";  
        } else if("java.lang.ArithmeticException".equals(eClassName)){
        	message = "数学运算异常！";  
        } else if("java.lang.ClassCastException".equals(eClassName)){
        	message = "数据类型转换异常！";  
        } else if("java.lang.FileNotFoundException".equals(eClassName)){
	    	message = "文件未找到异常！";  
	    } else if("java.lang.OutOfMemoryException".equals(eClassName)){
	    	message = "内存不足错误！";
	    } else {
        	message = e.getMessage();  
        }
        if(ObjectHelper.isEmpty(message)){
        	message = e.toString();  
        }
        //替换特殊字符  
        message = message.replaceAll("\"", "'");  
        return message;  
    }  
}
