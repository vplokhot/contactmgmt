package com.tutorialspoint;

public class Search {

	
	private String query;
	private String field;
	private String currentLon;
	private String currentLat;

	public Search(){};
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	public String getCurrentLon() {
		return currentLon;
	}

	public void setCurrentLon(String currentLon) {
		this.currentLon = currentLon;
	}

	public String getCurrentLat() {
		return currentLat;
	}

	public void setCurrentLat(String currentLat) {
		this.currentLat = currentLat;
	}
}
