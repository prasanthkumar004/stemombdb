package org.stem.moviedb.client;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.stem.moviedb.model.Movie;
import org.stem.moviedb.model.MovieDB;
import org.stem.moviedb.model.Search;

import io.restassured.response.Response;

public class MovieDBClient {

	private final static  String url="https://www.omdbapi.com/";
	private final static String apiKey="25ef8635";
	private final static String COUNT_PROPERTY = "totalResults";
	private final static String RESPONSE_PROPERTY = "Response";


	//Returns the List of All Movies based the movie name
	public static List<Search> search(String searchParam){

		Response r = getResponseBody("s", searchParam);
		int movieCount = getMovieCount(r);	
		int page =  Math.incrementExact(movieCount/10);
		List<Search> movies = new ArrayList<Search>();
		for(int i=1; i<=page; i++) {
			movies.addAll(searchByPage(searchParam, i+""));
		}
		return movies;
	}

	//Returns the Movie based on ID - If movie does not exists returns null
	public static Movie get_by_id(String searchParam){
		Response r = getResponseBody("i", searchParam);
		Movie movie = r.getBody().as(Movie.class);
		return movie;
	}

	//Returns the Movie based on title - If movie does not exists returns null
	public static Movie get_by_title(String searchParam){
		Response r = getResponseBody("t", searchParam);
		Movie movie = r.getBody().as(Movie.class);
		return movie;
	}

	//Returns response for generic query param and search text
	private static  Response getResponseBody(String queryparam,String searchText){
		Response response = given().queryParam(queryparam, searchText)
				.queryParam("apikey",apiKey)
				.when().get(url);

		return response;
	}


	//Returns the List of Movies based on page number and movie name
	public static List<Search> searchByPage(String searchParam, String pageNumber){

		MovieDB movieDB = given().queryParam("s", searchParam).queryParam("Page", pageNumber)
				.queryParam("apikey",apiKey)
				.when().get(url).getBody().as(MovieDB.class);

		List<Search> movies = new ArrayList<Search>();

		if(movieDB.getSearch()==null) {
			return movies;
		}else {
			movies.addAll(movieDB.getSearch());
		}
		return movies;

	}

// Return the count of total movies based on the search text given
	public static int getMovieCount(Response response) {
		int count = 0;
		if(response!=null) {
			String responseVal = response.then().extract().path(RESPONSE_PROPERTY).toString();
			//System.out.println(responseVal);
			if(responseVal.equalsIgnoreCase("TRUE")) {
				String totalCount= response.then().extract().path(COUNT_PROPERTY).toString();
				count = StringUtils.isNumeric(totalCount) ? Integer.parseInt(totalCount) : 0;
			}
		}
		return count;
	}

	public static void main(String args[]) {
		List<Search> movies = search(",");
		System.out.println(movies.size());

		Movie movie = get_by_id("tt0324613454353");
		System.out.println(movie.getTitle());
		
		Movie movie1 = get_by_title("prasanth");
		System.out.println(movie1.getTitle());
		
		
		movies = search("avatar");
		System.out.println(movies.size());

		movies = search("lahjfkhdajkfhdks");
		System.out.println(movies.size());

		 movie = get_by_id("tt0324613");
		System.out.println(movie.getTitle());

		 movie1 = get_by_title("avatar");
		System.out.println(movie1.getTitle());
		//getTitles(r);
		 
		
	}





}
