/**
 * 
 */
package com.kelvem.common.utils;

/**
 * @author kelvem
 *
 */
public class KLog {

	private static boolean flagDebug = false;
	public static void info(String msg){
		System.out.println(msg);
	}
	
	public static void debug(String msg){
		if (flagDebug) {
			System.out.println(msg);
		}
	}
	
	public static void error(String msg){
		System.err.println(msg);
	}
}
