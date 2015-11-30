package com.tutorialspoint;

import java.util.Comparator;

public class Contact implements Comparator<Contact>{
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private Integer id;
	private String lon;
	private String lat;
	
	private Integer distance;
	
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Contact [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", email="
				+ email + "]";
	}
	@Override
    public int compare(Contact c1, Contact c2) {
		
		if(c1.getDistance() > 0 && c2.getDistance() > 0){
	        return c1.getDistance().compareTo(c2.getDistance());
		}else{
			return 1;
		}
		
    }
}
