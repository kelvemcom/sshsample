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
 * <p>��¼��������ʱ��Ϣ��ע��</p>
 * 
 * @param description ������������
 * @author kelvem
 *
 */
public @interface MethodDetail {

	// ������������
	public String description() default "";  
}
