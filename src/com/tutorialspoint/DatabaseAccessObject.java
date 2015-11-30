package com.tutorialspoint;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseAccessObject
{
  private Connection connection;
  private DatabaseMetaData databaseMetadata;
  private Statement statement;
  private ResultSet resultSet;
  private ResultSetMetaData resultSetMetadata;
  
  public void closeResultSet()
    throws SQLException
  {
    this.resultSet.close();
  }
  
  public void closeStatement()
    throws SQLException
  {
    this.statement.close();
  }
  
  public void closeConnection()
    throws SQLException
  {
    this.connection.close();
  }
  
  public void connect(String url, String userName, String passWord)
    throws SQLException
  {
    if ((userName == null) || (passWord == null)) {
      this.connection = DriverManager.getConnection(url);
    } else {
      this.connection = DriverManager.getConnection(url, userName, passWord);
    }
  }
  
  public int executeUpdateQuery(String updateQuery)
    throws SQLException
  {
    return this.statement.executeUpdate(updateQuery);
  }
  
  public ResultSet generateResultSet(String selectQuery)
    throws SQLException
  {
    this.resultSet = this.statement.executeQuery(selectQuery);
    
    return this.resultSet;
  }
  
  public Connection getConnection()
  {
    return this.connection;
  }
  
  public DatabaseMetaData getDatabaseMetadata(Connection connection)
    throws SQLException
  {
    this.databaseMetadata = connection.getMetaData();
    
    return this.databaseMetadata;
  }
  
  public ResultSet getResultSet()
  {
    return this.resultSet;
  }
  
  public ResultSetMetaData getResultSetMetadata(ResultSet resultSet)
    throws SQLException
  {
    this.resultSetMetadata = resultSet.getMetaData();
    
    return this.resultSetMetadata;
  }
  
  public Statement getStatement()
  {
    return this.statement;
  }
  
  public void initializeStatement(int resultSetType, int resultSetConcurrency)
    throws SQLException
  {
    if ((resultSetType == 1003) && 
      (resultSetConcurrency == 1007)) {
      this.statement = this.connection.createStatement();
    } else {
      this.statement = this.connection.createStatement(resultSetType, 
        resultSetConcurrency);
    }
  }
  
  public void loadDatabaseDriver(String className)
    throws ClassNotFoundException
  {
    Class.forName(className);
  }
}