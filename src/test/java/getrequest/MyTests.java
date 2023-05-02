package getrequest;

import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class MyTests {

	@Test
		  public void createUser() {
		    String requestBody = "";
		    given()
		      .contentType("application/json")
		      .body(requestBody)
		    .when()
		      .post("https://reqres.in/api/users?page=2")
		    .then()
		      .statusCode(201)
		      .assertThat();
		      //.body("name", equalTo("John"))
		     // .body("email", equalTo("john@example.com"));
		  }
}

/*
 * public static void main(String[] args) { // Set the base URI for the API
 * endpoint RestAssured.baseURI = "https://my-api.com";
 * 
 * // Define the request payload as a JSON string String requestBody =
 * "{ \"name\": \"John\", \"email\": \"john@example.com\" }";
 * 
 * // Make the POST request using RestAssured given()
 * .contentType(ContentType.JSON) .body(requestBody) .when() .post("/users")
 * .then() .statusCode(200) .log().all(); // Print response details to the
 * console } }
 */
