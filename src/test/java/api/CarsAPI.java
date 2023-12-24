package api;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import dto.AddNewCarDTO;

import static com.jayway.restassured.RestAssured.given;

public class CarsAPI extends BaseAPI{
    Response responseAddNewCar = null;

    private Response getResponseAddNewCar(AddNewCarDTO addNewCarDTO, String token) {
        responseAddNewCar = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addNewCarDTO)
                .when()
                .post(baseUrl + "/v1/cars")
                .thenReturn();
        return responseAddNewCar;
    }

    public String getMessageResponseAddNewCar(AddNewCarDTO addNewCarDTO, String token) {
        if(responseAddNewCar == null) {
            getResponseAddNewCar(addNewCarDTO, token);
        }
        return responseAddNewCar.then().extract().path("message");
    }

    public int getStatusCodeResponseAddNewCar(AddNewCarDTO addNewCarDTO, String token) {
        if(responseAddNewCar == null) {
            getResponseAddNewCar(addNewCarDTO, token);
        }
        return responseAddNewCar.getStatusCode();
    }

    public void setResponseAddNewCarNull() {
        responseAddNewCar = null;
    }
}
