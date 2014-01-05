package com.kelvem.common.jdbc.mysql;

import com.kelvem.common.jdbc.base.DataBaseSession;

public class MysqlTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DataBaseSession session = new MysqlSession();
		
		session.open();
		
		System.out.println("Test OK");
	}

}
