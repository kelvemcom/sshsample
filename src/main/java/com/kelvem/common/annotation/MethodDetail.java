package com.kelvem.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
@Inherited
/**
 * <p>记录方法运行时信息的注解</p>
 * 
 * @param description 方法作用描述
 * @author kelvem
 *
 */
public @interface MethodDetail {

	// 方法作用描述
	public String description() default "";  
}
