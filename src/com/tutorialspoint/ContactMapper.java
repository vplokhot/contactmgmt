package com.tutorialspoint;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ContactMapper implements RowMapper<Contact>{

	@Override
	public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
	      Contact contact = new Contact();
	      contact.setId(rs.getInt("contact_Id"));
	      contact.setFirstName(rs.getString("firstName"));
	      contact.setLastName(rs.getString("lastName"));
	      contact.setAddress(rs.getString("address"));
	      contact.setEmail(rs.getString("email"));
	      contact.setLon(rs.getString("lon"));
	      contact.setLat(rs.getString("lat"));
	      return contact;
	}

}
