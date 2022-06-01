package org.stem.moviedb.client.test;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.stem.moviedb.client.MovieDBClient;
import org.stem.moviedb.model.Movie;
import org.stem.moviedb.model.Search;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import freemarker.template.utility.CollectionUtils;



public class TestMovieDBClient {
	static ExtentTest test;
	static ExtentReports report;

	
	//Initiate extent report before the test to start writing the test reports during the execution
	@BeforeClass

	public static void startTest(final ITestContext testContext)
	{
		report = new ExtentReports("test-output" + File.separator + testContext.getName()+ "TestExecutionReport.html", true);


	}

	@BeforeMethod
	public void beforeTestMethods(Method method) {
		test = report.startTest(method.getName());
		test.log(LogStatus.INFO, method.getName()+" test started.");

	}

	//Push all the test results to the extent report after the test is completed
	@AfterMethod
	public void name(ITestResult result){
		for (String group : result.getMethod().getGroups())
			test.assignCategory(group);
		test.log(LogStatus.INFO, result.getMethod().getMethodName()+" test completed.");

		report.endTest(test);
	}

	//Before every test accessthe extent report to write the status of test
	@BeforeTest
	public static void startBeforeTest()
	{
		report = new ExtentReports("test-output" + File.separator + "MovieDBClientTestReport1.html", true);
		test = report.startTest("TestMovieDBClient");
	}

	static Logger log = Logger.getLogger(TestMovieDBClient.class.getName());  
	//Assert that the result should contain at least 30 items . Number of expected items given in testng.xml

	@Test
	@Parameters(value = {"expectedCount","searchTextVal"})
	public  void AssertResultsCount(int expectedCount, String searchTextVal)
	{

		try {
			test.log(LogStatus.INFO, "Expected result count should contain atleast "+expectedCount);

			int totalCnt=MovieDBClient.search(searchTextVal).size();
			test.log(LogStatus.INFO, "Actual Result Count "+totalCnt);

			assertTrue((totalCnt >= expectedCount), "Retreived Items list does not contain "+expectedCount+" items");
			test.log(LogStatus.PASS, "Retreived Items list contain more than or equal to the  "+expectedCount+" items");
		}catch(AssertionError ae) {
			test.log(LogStatus.FAIL, ae.getMessage());
		}catch(Exception e) {
			test.log(LogStatus.FATAL, "Exception occured -> "+e.getMessage());
		}
	}
	//Assert that the result contains items titled The STEM Journals and Activision: STEM - in the Videogame Industry. Input for the searchtext is given in tesnNG.xml. We can add many number of movie title to search using comma


	@Test
	@Parameters(value = {"movieNames","searchText"})
	public  void AssertListedMovieName(String movieNames, String searchText)
	{
		try {
			test.log(LogStatus.INFO, "Expected Titles :  "+movieNames + " in the search text"+ searchText);

			List<String> movieNameLst = Arrays.asList((StringUtils.defaultIfBlank(movieNames, "")).split(","));
			List<Search> movies=MovieDBClient.search(searchText);
			List<String> titles = movies.stream().map(e->e.getTitle()).collect(Collectors.toList());
			test.log(LogStatus.INFO, " Actual list of titles :  "+titles);

			for(String expectedName : movieNameLst) {
				assertTrue((titles.contains(expectedName)), "Error - Cannot find any item with title (\""+expectedName+"\")");
			}
			test.log(LogStatus.PASS,"Assert that the result contains items titled The STEM Journals and Activision: STEM - in the Videogame Industry");
		}catch(AssertionError ae) {
			test.log(LogStatus.FAIL, ae.getMessage());
		}catch(Exception e) {
			test.log(LogStatus.FATAL, "Exception occured -> "+e.getMessage());
		}
	}

	//Assert that the movie was released on 23 Nov 2010 and was directed by Mike Feurstein

	@Test
	@Parameters(value = {"expectedReleaseDate","expectedDirectorName","searchText1","searchMovieName1"})
	public  void AssertMovieReleasedDateAndDirectorName(String expectedReleaseDate, String expectedDirectorName, String searchText1, String searchMovieName1)
	{
		try {

			test.log(LogStatus.INFO, "Expected ReleaseDate :  "+expectedReleaseDate + " and Expected DirectorName "+ expectedDirectorName);


			List<Search> movies=MovieDBClient.search(searchText1);
			Map<String, List<Search>> movieTitleMap = movies.stream().collect(Collectors.groupingBy(Search::getTitle));
			List<String> movieText = movies.stream().map(e->e.getTitle()).collect(Collectors.toList());


			//Possibility of having multiple movies with same name

			List<Search> movieLst = movieTitleMap.get(searchMovieName1);
			
			String errorMsg = "Release Date and Director Name does not match";
			boolean match = false;

			test.log(LogStatus.INFO, "Movie List found :" + movieText);

			for(Search movie : movieLst) {
				String imdbid = movie.getImdbID();
				Movie movieDet = MovieDBClient.get_by_id(imdbid);

				String releaseDate = movieDet.getReleased(); 
				String director = movieDet.getDirector();

				if((releaseDate.equalsIgnoreCase(expectedReleaseDate))&&(director.equalsIgnoreCase(expectedDirectorName))) {
					match = true;
					test.log(LogStatus.INFO, "Director Name and Release Date matched in the movie " + director + releaseDate);

					break;
				}else if((releaseDate.equalsIgnoreCase(expectedReleaseDate))&&!(director.equalsIgnoreCase(expectedDirectorName))) {
					errorMsg = "Director Name does not match";
					test.log(LogStatus.INFO, "Director Name did not match " + director);

				}else if(!(releaseDate.equalsIgnoreCase(expectedReleaseDate))&&(director.equalsIgnoreCase(expectedDirectorName))){
					errorMsg = "Release Date does not match";

					test.log(LogStatus.INFO, "Release Date did not match  " + releaseDate);

				}
			}

			assertTrue(match, errorMsg);
			test.log(LogStatus.PASS,"Asserted the Movie Release Date " + expectedReleaseDate + " and " + "expectedDirectorName" );
		}catch(AssertionError ae) {
			test.log(LogStatus.FAIL, ae.getMessage());
		}catch(Exception e) {
			test.log(LogStatus.FATAL, "Exception occured -> "+e.getMessage());
		}


	}
	//Using get_by_title method, get item by title The STEM Journals and assert that the plot contains the string Science, Technology, Engineering and Math and has a runtime of 22 minutes.

	@Test
	@Parameters(value = {"expectedPlot","expectedRunTime","searchMovieName2"})
	public  void AssertMoviePlotAndRunTime(String expectedPlot, String expectedRunTime, String searchMovieName2)
	{
		try {
			test.log(LogStatus.INFO, "Expected RunTime :  "+expectedRunTime + " and Expected Plot "+ expectedPlot +"in the movie " + searchMovieName2);

			Movie movieDet = MovieDBClient.get_by_title(searchMovieName2);
			String plot = movieDet.getPlot().toUpperCase();
			String runTime = movieDet.getRuntime().toUpperCase();
			assertTrue((plot.contains((expectedPlot).toUpperCase())), "Expected Plot (\""+expectedPlot+"\") does not match");
			assertTrue((runTime.startsWith((expectedRunTime).substring(0, 6).toUpperCase())), "Expected RunTime (\""+expectedRunTime+"\") does not match");
			test.log(LogStatus.INFO, "Actual RunTime :  "+runTime + " and Actual Plot "+ plot +"in the movie " + searchMovieName2 + "movieDet");

			test.log(LogStatus.PASS,"Test case Passed  searching moview with movie plot and Runttime filter");

		}catch(AssertionError ae) {
			test.log(LogStatus.FAIL, ae.getMessage());
		}catch(Exception e) {
			test.log(LogStatus.FATAL, "Exception occured -> "+e.getMessage());
		}

	}	


	@AfterClass
	public static void endTest()
	{
		//report.endTest(test);
		report.flush();
	}

}
