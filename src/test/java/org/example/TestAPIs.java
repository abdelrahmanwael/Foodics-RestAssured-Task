package org.example;

import io.restassured.path.json.exception.JsonPathException;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;



import static org.hamcrest.core.IsEqual.equalTo;

public class TestAPIs {

    // Define the base URL for the API
    private static final String BASE_URL = "https://pay2.foodics.dev/cp_internal";

    // Define the credentials for the merchant
    private static final String USERNAME = "merchant@foodics.com";
    private static final String PASSWORD = "123456";

    @Test
    public void testLoginEndpoint() {
        // Send a POST request to the login endpoint with the credentials
        Response response = RestAssured.given()
                .contentType("application/json")
                .body("{ \"username\": \"" + USERNAME + "\", \"password\": \"" + PASSWORD + "\"}")
                .post(BASE_URL + "/login");

        // Assert that the response code is 302 Found
        response.then().statusCode(302);

    }

    @Test
    public void testWhoAmIEndpoint() {
        // Send a GET request to the whoami endpoint after logging in
        String token = loginAndGetToken();
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .get(BASE_URL + "/whoami");

        // Assert that the response code is 200 OK
        response.then().statusCode(200);

        // Assert that the response code is 200 OK and the response body contains the merchant's email
        response.then().statusCode(200).assertThat().body("email", equalTo(USERNAME));

    }

    @Test
    public void testWhoAmIEndpointWithoutToken() {
        // Send a GET request to the whoami endpoint without providing an access token
        Response response = RestAssured.given()
                .get(BASE_URL + "/whoami");

        // Assert that the response code is 401 Unauthorized
        response.then().statusCode(401);
    }

    // Helper method to log in and retrieve the access token
    private String loginAndGetToken() {
        Response response = RestAssured.given()
                .contentType("application/json")
                .body("{ \"username\": \"" + USERNAME + "\", \"password\": \"" + PASSWORD + "\"}")
                .post(BASE_URL + "/login");

        // Extract the access token from the response body and return it
        String accessToken = null;
        try {
            accessToken = response.jsonPath().getString("access_token");
            System.out.println("Access token: " + accessToken);
        } catch (JsonPathException e) {
            System.err.println("Failed to extract access token from response body: " + e.getMessage());
        }
        return accessToken;
    }
}