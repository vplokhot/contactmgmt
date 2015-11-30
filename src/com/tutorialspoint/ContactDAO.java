package com.tutorialspoint;
import java.util.List;

import javax.sql.DataSource;

public interface ContactDAO {
	public void setDataSource(DataSource ds);
	   /** 
	    * This is the method to be used to create
	    * a record in the Student table.
	    */
	   public void create(String firstName, String lastName, String address, String email, String lon, String lat);
	   /** 
	    * This is the method to be used to list down
	    * a record from the Student table corresponding
	    * to a passed student id.
	    */
	   public Contact getContact(Integer id);
	   /** 
	    * This is the method to be used to list down
	    * all the records from the Student table.
	    */
	   public List<Contact> listContacts();
	   /** 
	    * This is the method to be used to delete
	    * a record from the Student table corresponding
	    * to a passed student id.
	    */
	   public void delete(Integer id);
	   /** 
	    * This is the method to be used to update
	    * a record into the Student table.
	    */
	   public void update(Integer id, String firstName, String lastName, String address, String email, String lon, String lat);
}
