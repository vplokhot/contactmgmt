package com.tutorialspoint;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

public class ContactJDBCTemplate implements ContactDAO{
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	private String tableName = "sample_contacts_table10";
	@Override
	public void setDataSource(DataSource ds) {
		// TODO Auto-generated method stub
		this.dataSource = ds;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(String firstName, String lastName, String address, String email, String lon, String lat) {
		// TODO Auto-generated method stub
	      String SQL = "insert into " + tableName + " (firstName, lastName, address, email, lon, lat) values (?, ?, ?, ?, ?, ?)";
	      
	      jdbcTemplateObject.update( SQL, firstName, lastName, address, email, lon, lat);
	      System.out.println("Created Record Name = " + firstName + " " + lastName + " Address = " + address + " Email = " + email + " LONG : " + lon + " LAT : " + lat );
	      return;
	}

	@Override
	public Contact getContact(Integer id) {
		// TODO Auto-generated method stub
	      String SQL = "select * from " + tableName + " where contact_Id = ?";
	      Contact contact = jdbcTemplateObject.queryForObject(SQL, 
	                        new Object[]{id}, new ContactMapper());
	      return contact;
	}

	@Override
	public List<Contact> listContacts() {
		// TODO Auto-generated method stub
		String SQL = "select * from " + tableName;
	      List <Contact> contacts = jdbcTemplateObject.query(SQL, new ContactMapper());
	      return contacts;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
	      String SQL = "delete from " + tableName + " where contact_Id = ?";
	      jdbcTemplateObject.update(SQL, id);
	      System.out.println("Deleted Record with contact_Id = " + id );
	      return;
	}

	@Override
	public void update(Integer id, String firstName, String lastName, String address, String email, String lon, String lat ) {
		// TODO Auto-generated method stub
		String SQL = "update " + tableName + " set firstName = ?, lastName = ?, address = ?, email = ?, lon = ?, lat = ? where contact_Id = ?";
	      jdbcTemplateObject.update(SQL, firstName, lastName, address, email, lon, lat, id);
	      System.out.println("Updated Record with contact_Id = " + id );
	      return;
	}

	public List<Contact> matchingContacts(String query, String fieldName) {
		// TODO Auto-generated method stub
		String SQL = "select * from " + tableName + " where " + fieldName + " like '%" + query + "%'";
	    List <Contact> contacts = jdbcTemplateObject.query(SQL, new ContactMapper());
	    return contacts;
	}

	public Contact getDistance(Search search, Contact contact){
		JSONObject jsonObject=null;
     	Integer dist;
		String url = "";
		
		try {
			url = "http://maps.googleapis.com/maps/api/distancematrix/json?origins="+search.getCurrentLon() + "," + search.getCurrentLat() + "&destinations="+ contact.getAddress();

			System.out.println(url);
				
			jsonObject = readJsonFromUrl(url.replaceAll("\\s",""));
			
			if(((JSONArray)jsonObject.get("rows")).getJSONObject(0).getJSONArray("elements").getJSONObject(0).getString("status").equals("OK")){
				dist = ((JSONArray)jsonObject.get("rows")).getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("distance").getInt("value");		

				System.out.println(((JSONArray)jsonObject.get("rows")).toString());
				System.out.println(dist.toString());
				   
				contact.setDistance(dist/1000);
			}else{
				contact.setDistance(-1);
			}
			

			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return contact;
	}

	public Contact getGeo(Contact contact){
		JSONObject json=null;
	
		try {
			json = readJsonFromUrl("http://maps.googleapis.com/maps/api/geocode/json?address=" + contact.getAddress().replaceAll("\\s",""));
			   json.get("results");
			
			   double lng = ((JSONArray)json.get("results")).getJSONObject(0)
			            .getJSONObject("geometry").getJSONObject("location").getDouble("lng");

			   double lat = ((JSONArray)json.get("results")).getJSONObject(0)
			            .getJSONObject("geometry").getJSONObject("location").getDouble("lat");

			   String lngStr = new Double(lng).toString();
			   String latStr = new Double(lat).toString();


			   System.out.println(lngStr);
			   System.out.println(latStr);
			   
			   contact.setLon(lngStr);
			   contact.setLat(latStr);
			   
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return contact;
	}
	 public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException 
	 {
	    InputStream is = new URL(url).openStream();
	    try 
	    {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    }
	    finally 
	    {
	      is.close();
	    }
	 }
	 private static String readAll(Reader rd) throws IOException 
	 {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) 
	    {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	 }

}
