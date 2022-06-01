package org.stem.moviedb.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//Search result POJO
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDB {

	@JsonProperty
	private List<Search> Search;
	
	@JsonProperty
	private String totalResults;
	
	@JsonProperty
	private String Response;
	
	@JsonProperty
	private String Error;
	
	public void setSearch(List<Search> search) {
		this.Search = search;
	}
	public List<Search> getSearch() {
		return Search;
	}
	public void setResponse(String response) {
		Response = response;
	}
	public String getResponse() {
		return Response;
	}
	public void setTotalResults(String totalResults) {
		this.totalResults = totalResults;
	}
	public String getTotalResults() {
		return totalResults;
	}
	public void setError(String error) {
		Error = error;
	}
	public String getError() {
		return Error;
	}
}
