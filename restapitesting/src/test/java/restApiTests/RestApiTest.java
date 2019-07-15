package restApiTests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RestApiTest {

    String hostUrl = "http://www.omdbapi.com";
    String getMovieByIdPath = "/?i={movie}&apikey={apikey}";
    String getMoviesByNamePath = "?apikey={1}&s={2}";
    String movieName = "Batman";
    String movieId = "tt3896198";
    String apiKey = "981288";



    @BeforeMethod
    public void InitializeTest(){
        System.out.println("Initialization");

    }


    @Test
    public void TestMovieIdMatchName(){
        RestAssured.baseURI = hostUrl;
        RestAssured.port = 80;
        System.out.println("Testing");
        given().
                pathParam("movie", movieId).
                pathParam("apikey", apiKey).
                when().
                get(getMovieByIdPath).
                then().
                assertThat().
                statusCode(200).
                and().
                contentType(ContentType.JSON).
                body("Title", equalTo("Guardians of the Galaxy Vol. 2"));
    }

    @Test
    public void TestBatmanForeverYear() {
        RestAssured.baseURI = hostUrl;
        RestAssured.port = 80;

        System.out.println("Testing by Movie name, Movie: " + movieName);
        given().
                when().
                get(getMoviesByNamePath, apiKey, movieName).
                then().
                body("Search.findAll { it.Title == 'Batman Forever' }.Year", hasItem("1995"));

    }

}
