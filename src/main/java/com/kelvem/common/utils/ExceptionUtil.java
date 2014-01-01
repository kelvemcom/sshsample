package com.kelvem.common.utils;

import java.io.IOException;
import java.sql.SQLException;

/**
 * <P>Title: EFTGW</P>
 * 
 * @version 1.0
 */

public class ExceptionUtil {

	public static Exception getException(Exception e) {
		
		try{
			e.printStackTrace();
			throw e;
		}catch (NullPointerException ex) {
			return new Exception("调用了未经初始化的对象或者是不存在的对象！",ex);
		} catch(IOException ex){
			return new Exception("IO异常！",ex);
		}catch(ClassNotFoundException ex){
			return new Exception("指定的类不存在！",ex);
		}catch(ArithmeticException ex){
			return new Exception("数学运算异常！",ex);
		}catch(ArrayIndexOutOfBoundsException ex){
			return new Exception("数组下标越界!",ex);
		}catch(IllegalArgumentException ex){
			return new Exception("方法的参数错误！",ex);
		}catch(ClassCastException ex){
			return new Exception("类型强制转换错误！",ex);
		}catch(SecurityException ex){
			return new Exception("违背安全原则异常！",ex);
		}catch(NoSuchMethodError ex){
			return new Exception("方法末找到异常！",ex);
		}catch(SQLException ex){
			return new Exception("数据库异常！",ex);
		}catch(Exception ex){
			return ex;
		}
	}
}

