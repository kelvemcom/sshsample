package com.kelvem.common.jdbc.base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public abstract class DataBaseSession {

	public DataBaseSession(){
		this.property = readProperty();
	}
	
	public DataBaseSession(ConnectionProperty property){
		this.property = property;
	}
	
	public ConnectionProperty property = null;
	
	public Connection connection = null;
	
	public abstract void open();
	
	public abstract void close();
	
	public abstract ConnectionProperty readProperty();
	
	public abstract ResultSet query(String sql);
	
	public abstract int execute(String sql);

	public abstract int[] executeBatch(List<String> listSql);
	
	public abstract String getSessionType();
	
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public ConnectionProperty getProperty() {
		return property;
	}

	public void setProperty(ConnectionProperty property) {
		this.property = property;
	}

	
}
