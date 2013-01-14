package br.caelum.clauvane.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static ConnectionFactory instance;
	
	private ConnectionFactory() {
	}
	
	public static ConnectionFactory getInstance(){
		if (instance == null) 
			instance = new ConnectionFactory();
		return instance;
	}
	
	public Connection getConnection(){
		Connection connetion = null;
		try {
			connetion =  DriverManager.getConnection("jdbc:mysql://localhost/caelumfj21","root","");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connetion;
	}
}
