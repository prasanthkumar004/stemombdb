package org.stem.moviedb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
//Movie Search POJO
public class Search {

	@JsonProperty
	private String Title;
	
	@JsonProperty
	private String Year;
	
	@JsonProperty
	private String ImdbID;
	
	@JsonProperty
	private String Type;
	
	@JsonProperty
	private String Poster;
	
	public void setImdbID(String imdbID) {
		this.ImdbID = imdbID;
	}
	public void setPoster(String poster) {
		this.Poster = poster;
	}
	public void setTitle(String title) {
		this.Title = title;
	}
	public void setType(String type) {
		this.Type = type;
	}
	public void setYear(String year) {
		this.Year = year;
	}
	public String getImdbID() {
		return ImdbID;
	}
	public String getPoster() {
		return Poster;
	}
	public String getTitle() {
		return Title;
	}
	public String getType() {
		return Type;
	}
	public String getYear() {
		return Year;
	}
	
}
