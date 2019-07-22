package restApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DepartamentoApiTest {
    String hostUrl = "http://umgapitests.azurewebsites.net";
    String departamentoPath = "/api/departamentos";


    @Test
    public void TestGetDepartamentos(){
        RestAssured.baseURI = hostUrl;
        RestAssured.port = 80;
        System.out.println("Testing");
        given().
                when().
                get(departamentoPath).
                then().
                assertThat().
                statusCode(200).
                and().
                contentType(ContentType.JSON);

    }

    @Test
    public void TestGetDepartamentosById(){
        RestAssured.baseURI = hostUrl;
        RestAssured.port = 80;
        System.out.println("Testing");
        given().
                when().
                get(departamentoPath + "/1").
                then().
                assertThat().
                statusCode(200).
                and().
                contentType(ContentType.JSON).
                body("nombre", equalTo("Test"));
    }
}
