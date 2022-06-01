package org.stem.moviedb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
//Movie Details POJO
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {


	@JsonProperty
	private String Title;
	@JsonProperty
	private String Year;
	@JsonProperty
	private String Rated;
	@JsonProperty
	private String Released;
	@JsonProperty
	private String Runtime;
	@JsonProperty
	private String Genre;
	@JsonProperty
	private String Director;
	@JsonProperty
	private String Writer;
	@JsonProperty
	private String Actors;
	@JsonProperty
	private String Plot;
	@JsonProperty
	private String Language;
	@JsonProperty
	private String Country;
	@JsonProperty
	private String Awards;
	@JsonProperty
	private String Poster;
	@JsonProperty
	private Object Ratings;
	@JsonProperty
	private String Metascore;
	@JsonProperty
	private String imdbRating;
	@JsonProperty
	private String imdbVotes;
	@JsonProperty
	private String imdbID;
	@JsonProperty
	private String Type;
	@JsonProperty
	private String totalSeasons;
	@JsonProperty
	private String Response;
	
	
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		Year = year;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getRated() {
		return Rated;
	}
	public void setRated(String rated) {
		Rated = rated;
	}
	public String getReleased() {
		return Released;
	}
	public void setReleased(String released) {
		Released = released;
	}
	public String getRuntime() {
		return Runtime;
	}
	public void setRuntime(String runtime) {
		Runtime = runtime;
	}
	public String getGenre() {
		return Genre;
	}
	public void setGenre(String genre) {
		Genre = genre;
	}
	public String getDirector() {
		return Director;
	}
	public void setDirector(String director) {
		Director = director;
	}
	public String getWriter() {
		return Writer;
	}
	public void setWriter(String writer) {
		Writer = writer;
	}
	public String getActors() {
		return Actors;
	}
	public void setActors(String actors) {
		Actors = actors;
	}
	public String getPlot() {
		return Plot;
	}
	public void setPlot(String plot) {
		Plot = plot;
	}
	public String getLanguage() {
		return Language;
	}
	public void setLanguage(String language) {
		Language = language;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getAwards() {
		return Awards;
	}
	public void setAwards(String awards) {
		Awards = awards;
	}
	public String getPoster() {
		return Poster;
	}
	public void setPoster(String poster) {
		Poster = poster;
	}
	public String getMetascore() {
		return Metascore;
	}
	public void setMetascore(String metascore) {
		Metascore = metascore;
	}
	public void setRatings(Object ratings) {
		Ratings = ratings;
	}
	public Object getRatings() {
		return Ratings;
	}
	public String getImdbRating() {
		return imdbRating;
	}
	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}
	public String getImdbVotes() {
		return imdbVotes;
	}
	public void setImdbVotes(String imdbVotes) {
		this.imdbVotes = imdbVotes;
	}
	public String getImdbID() {
		return imdbID;
	}
	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getTotalSeasons() {
		return totalSeasons;
	}
	public void setTotalSeasons(String totalSeasons) {
		this.totalSeasons = totalSeasons;
	}
	public String getResponse() {
		return Response;
	}
	public void setResponse(String response) {
		Response = response;
	}



}
