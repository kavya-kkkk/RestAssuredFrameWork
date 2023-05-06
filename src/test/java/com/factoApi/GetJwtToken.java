package com.factoApi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetJwtToken {
   /* public static void main(String[] args) {
        RequestSpecification request = RestAssured.given()
            .baseUri("https://91fjgvixl9.execute-api.ap-south-1.amazonaws.com/testing/v1/login")
            .contentType(ContentType.JSON)
            .body("{\"data\":\"{\"user_email\": \"anish.reddy@rhibhus.com\",\"user_password\": \"Anish@123\"}\"");
        Response response = request.post("/v1/login");
     int   code=response.statusCode();
        System.out.println("status code" + code);
        System.out.println(response);
        String token = response.jsonPath().getString("token");
        System.out.println("JWT Token: " + token);*/
        
        
        
        public static void main(String[] args) {
            String payload = "{ \"data\": { \"user_email\": \"anish.reddy@rhibhus.com\", \"user_password\": \"Anish@123\" } }";
            
            Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .post("https://91fjgvixl9.execute-api.ap-south-1.amazonaws.com/testing/v1/login");
            
            System.out.println("Response body: " + response.getBody().asString());
            System.out.println("Status code: " + response.getStatusCode());
            
            
    }
}































/*String requestBody = "{\"data\":{\"user_email\": \"anish.reddy@rhibhus.com\",\"user_password\": \"Anish@123\"}}";
given().contentType("application/json").body(requestBody)
        .when()
		.post("https://91fjgvixl9.execute-api.ap-south-1.amazonaws.com/testing/v1/login").then()
		.statusCode(200);*/

/*
 * String payload =
 * "{ \"data\": { \"user_email\": \"anish.reddy@rhibhus.com\", \"user_password\": \"Anish@123\" } }"
 * ; //String contenttype="application/json";
 * 
 * 
 * RequestSpecification httpRequest = given(); RequestSpecification jsontype =
 * httpRequest.contentType(ContentType.JSON); RequestSpecification reqjsonbody =
 * jsontype.body(payload); Response response = RestAssured.post(
 * "https://91fjgvixl9.execute-api.ap-south-1.amazonaws.com/testing/v1/login");
 * int Actstatuscode = response.getStatusCode(); int EXstatuscode = 200; String
 * jwt=response.getBody().asString(); System.out.println("the jwt token is "
 * +jwt); System.out.println(Actstatuscode);
 */

 
/* Response response = RestAssured.given()
     .contentType(ContentType.JSON)
     .body(payload)
     .post("https://91fjgvixl9.execute-api.ap-south-1.amazonaws.com/testing/v1/login");
 
 System.out.println("Response body: " + response.getBody().asString());
 System.out.println("Status code: " + response.getStatusCode());*/
 


// .assertThat()
// .body("name", equalTo("John Smith"))
// .body("email", equalTo("john.smith@example.com"));

/*String jsonbody="{\"data\":{\"user_email\": \"anish.reddy@rhibhus.com\",\"user_password\": \"Anish@123\"}}";
String contenttype="application/json";

String Url ="https://91fjgvixl9.execute-api.ap-south-1.amazonaws.com/testing/v1/login";



 

String payload = jsonbody;
RequestSpecification httpRequest = given();
RequestSpecification jsontype = httpRequest.contentType(contenttype);
RequestSpecification reqjsonbody = jsontype.body(payload);

//System.out.println("Actual and expected values are equal.");
Assert.assertTrue(true);
Response response = RestAssured.post(Url);
System.out.println(response.asPrettyString());
int Actstatuscode = response.getStatusCode();
int EXstatuscode = 200;
Reporter.log("status code is " + Actstatuscode + "expected" + EXstatuscode);
Assert.assertEquals(Actstatuscode, EXstatuscode);
Reporter.log(response.asString());
// https://www.freeformatter.com/json-escape.html#before-output
String jwt = response.getBody().asString();
System.out.println(jwt);*/







