package com.api.FTValidations;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiFunctionalValidation {
	@Test
	public void RestApiValidations() {
	 String jsontoken="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiN2MyMWM4YzEtM2VlMC01MGViLThlMDItNGRhZWExYjQ4YzEyIiwidXNlcl9jYXRlZ29yeSI6IkFkbWluIiwidXNlcl9jbGllbnRfaWQiOiJOLkEuIiwidXNlcl90eXBlIjoiTi5BLiIsImlhdCI6MTY4MzY5MDU0N30.1XtI12UFwiEH7JakkmM6JfaY9lxYAsb0qJT0AFYPbYE";
     String   ExceptedResponsebody="[]";
     String   ExceptedResponsebody4="[Client Name Already Exists]";
     int Expectedstatuscode2=200; 
     int Expectedstatuscode4=400;
   /* String requestBody ="{\r\n"
     		+ "    \"data\": {\r\n"
     	 	+ "        \"client_name\": \"k781234\",\r\n"
     		+ "        \r\n"
     		+ "        \"pricing\": \"90000001\"\r\n"
     		+ "    }\r\n"
     		+ "}";*/
    String requestBody =" {\r\n"
     		+ "        \"client_name\": \"k781234\",\r\n"
     		+ "        \r\n"
     		+ "        \"pricing\": \"90000001\"\r\n" //502 error
     		+ "}";
     Response response = RestAssured
     		
             .given()
             .baseUri("https://91fjgvixl9.execute-api.ap-south-1.amazonaws.com/testing")
             .header("Authorization", jsontoken)
                 .contentType(ContentType.JSON)
                 .body(requestBody)
             .when()
                 .post("/v1/insertClients")
             .then()
                 .extract()
                 .response();
     int Actualstatuscode = response.getStatusCode();
     Reporter.log("Actual status code is =====>"+Actualstatuscode);
     String ActualresponseBody = response.getBody().asString();
     Reporter.log("Header token is =====>" + jsontoken);
     String actualContentType = response.header("Content-Type");
     Reporter.log("Actual Content Type is =====>"+ actualContentType);
     Reporter.log("Actual Response Body is =====>"+ ActualresponseBody);
     
     
     if(Actualstatuscode==200) {
     	
     	
      assertEquals(actualContentType, "application/json; charset=utf-8", "Content-Type header is Incorrect");
      Reporter.log("Actual status code is " +"=" + Actualstatuscode +"---AND---"+"expected status code"+"=" + Expectedstatuscode2);
      System.out.println("ActualResponse body=====" + ActualresponseBody+"ExpectedResponseBody====="+ExceptedResponsebody);
 	 Assert.assertEquals(Actualstatuscode,Expectedstatuscode2 ,"Expected status code is 200");
 	  Assert.assertTrue(ActualresponseBody.contains("Client Name Already Exists"));
 	 Assert.assertEquals(ActualresponseBody, ExceptedResponsebody);
 	    
	}
	 
     
     else if(Actualstatuscode==400 ) 
     {
     	
     	if(ActualresponseBody.contains("Client Name Already Exists")) {
     	 
          assertEquals(actualContentType, "application/json; charset=utf-8", "Content-Type header is Incorrect");
          System.out.println("header token -----------------" + jsontoken);
     	 Reporter.log("Actual status code is " +"=" + Actualstatuscode +"---AND---"+"expected status code"+"=" + Expectedstatuscode4);
          System.out.println("ActualResponse body=====" + ActualresponseBody+"ExpectedResponseBody====="+ ExceptedResponsebody4);
     	 Assert.assertEquals(Actualstatuscode,Expectedstatuscode4 ,"Expected status code is 400");
     
     	  
     	}
     	
     

     	else if(ActualresponseBody.contains("Client Name Already Exists")) {
     	 
          assertEquals(actualContentType, "application/json; charset=utf-8", "Content-Type header is Incorrect");
          System.out.println("header token -----------------" + jsontoken);
     	 Reporter.log("Actual status code is " +"=" + Actualstatuscode +"---AND---"+"expected status code"+"=" + Expectedstatuscode4);
          System.out.println("ActualResponse body=====" + ActualresponseBody+"ExpectedResponseBody====="+ ExceptedResponsebody4);
     	 Assert.assertEquals(Actualstatuscode,Expectedstatuscode4 );
     	}
     	
     	
     	 else if(ActualresponseBody.contains("Unexpected string in JSON at position ")) //if we miss payload format
     	  
     	 {
         	 
              assertEquals(actualContentType, "application/json; charset=utf-8", "Content-Type header is Incorrect");
              System.out.println("header token -----------------" + jsontoken);
         	 Reporter.log("Actual status code is " +"=" + Actualstatuscode +"---AND---"+"expected status code"+"=" + Expectedstatuscode4);
         	 Reporter.log("ActualResponse body=====" + ActualresponseBody+"ExpectedResponseBody=====>"+ "Unexpected string in JSON at position");
         	 Assert.assertEquals(Actualstatuscode,Expectedstatuscode4 );
     	 }
     	
         else if(ActualresponseBody.contains("Invalid Token")) //if token is expried or wrong jwt 
         
         {
         	 
              assertEquals(actualContentType, "application/json; charset=utf-8", "Content-Type header is Incorrect");
              System.out.println("header token -----------------" + jsontoken);
         	 Reporter.log("Actual status code is " +"=" + Actualstatuscode +"---AND---"+"expected status code"+"=" + Expectedstatuscode4);
              System.out.println("ActualResponse body=====" + ActualresponseBody+"ExpectedResponseBody====="+ ExceptedResponsebody4);
         	 Assert.assertEquals(Actualstatuscode,Expectedstatuscode4 );
     	 }
     	//"Unexpected token } in JSON at position
     	
     
      
     
               	
     else if(ActualresponseBody.contains("Unexpected token } in JSON at position")) //if miss {} TO open/closed flower bracket
         {
         	 
              assertEquals(actualContentType, "application/json; charset=utf-8", "Content-Type header is Incorrect");
              System.out.println("header token -----------------" + jsontoken);
         	 Reporter.log("Actual status code is " +"=" + Actualstatuscode +"---AND---"+"expected status code"+"=" + Expectedstatuscode4);
              System.out.println("ActualResponse body=====" + ActualresponseBody+"ExpectedResponseBody====="+ ExceptedResponsebody4);
         	 Assert.assertEquals(Actualstatuscode,Expectedstatuscode4 );
     	 }
	}
     	//"message": "Internal server error"

         	 else if(Actualstatuscode==502 ) 
         	 { 
         	
          if(ActualresponseBody.contains("Internal server error")) //if miss to given content Ex: {data:}
         	
         {
         	 int excptedstatuscode=502;
         	 System.out.println("sataus code is above 500");
              assertEquals(actualContentType, "application/json", "Content-Type header is Incorrect");
              System.out.println("header token -----------------" + jsontoken);
         	 Reporter.log("Actual status code is " +"=" + Actualstatuscode +"---AND---"+"expected status code"+"=" + Expectedstatuscode4);
         	 Reporter.log("ActualResponse body=====" + ActualresponseBody+"ExpectedResponseBody====="+ "message: Internal server error");
         	Assert.assertEquals(Actualstatuscode, excptedstatuscode);
     	 }
     	
         	 
     	

} 
	}
	
}



