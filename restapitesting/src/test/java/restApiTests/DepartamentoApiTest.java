package restApiTests;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

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

    @Test//(dataProvider="ObtenerDepartamentos")
    public void TestGetDepartamentoById(/*String idDepartamento, String nombreDepartamento*/){
        String idDepartamento = "1";
        String nombreDepartamento = "Departamento 1";
        RestAssured.baseURI = hostUrl;
        RestAssured.port = 80;
        System.out.println("Testing get departamento by Id " + idDepartamento);
        given().
                when().
                get(departamentoPath + "/" + idDepartamento).
                then().
                assertThat().
                statusCode(200).
                and().
                contentType(ContentType.JSON).
                body("nombre", equalTo(nombreDepartamento));
    }

    @Test//(dataProvider="InsertarDepartamentos")
    public void InsertDepartamento(/*String nombreDepartamento*/) {
        String nombreDepartamento = "Departamento 1";
        JsonObject jsonData = getJsonInsertData(nombreDepartamento);
        RestAssured.baseURI = hostUrl;
        RestAssured.port = 80;
        System.out.println("Testing insert departamento: " + nombreDepartamento);

        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(jsonData.toString());

        Response response = httpRequest.post(departamentoPath);
        System.out.println("Response: " + response.asString());
        System.out.println("Status Code: " + response.getStatusCode());
        assertEquals("El departamento "+ nombreDepartamento +" fue guardado correctamente", response.asString());
        assertEquals(200, response.getStatusCode());
    }


    @DataProvider(name="ObtenerDepartamentos")
    public Object[][] getDataFromDataproviderGet(){
        return new Object[][]
                {
                        { "1", "Departamento 1" },
                        { "2", "Departamento 2" },
                        { "3", "Departamento 3" }
                };

    }

    @DataProvider(name="InsertarDepartamentos")
    public Object[][] getDataFromDataproviderInsert(){
        return new Object[][]
                {
                        { "Departamento 1" },
                        { "Departamento 2" },
                        { "Departamento 3" }
                };

    }

    private JsonObject getJsonInsertData(String nombreDepartamento){
        JsonObject departamento = new JsonObject();
        departamento.addProperty("nombre", nombreDepartamento);
        return departamento;
    }
}
