package getrequest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
 
public class RestAssuredAPITest {
 
 @Test
public void GetBooksDetails() { 
	// Specify the base URL to the RESTful web service 
	RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books"; 
	// Get the RequestSpecification of the request to be sent to the server. 
	RequestSpecification httpRequest = RestAssured.given(); 
	// specify the method type (GET) and the parameters if any. 
	//In this case the request does not take any parameters 
	Response response = httpRequest.request(Method.GET, "");
	// Get the status code of the request. 
    //If request is successful, status code will be 200
   int statusCode = response.getStatusCode();
	// Print the status and message body of the response received from the server 
	System.out.println("Status received => " + response.getStatusLine()); 
	// to print response body as string or pretty
	System.out.println("Response=>" + response.prettyPrint());
	System.out.println("Response Body is =>  " + response.asString());
	// Assert that correct status code is returned.
	Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
	
	// Get the status line from the Response in a variable called statusLine
    String statusLine = response.getStatusLine();
    Assert.assertEquals(statusLine /*actual value*/, "HTTP/1.1 200 OK"  /*expected value*/, "Correct status code returned");
    
    		
}

	
	
@Test
public void queryParameter() {
	//Defining the base URI
	RestAssured.baseURI= "https://bookstore.toolsqa.com/BookStore/v1";
	RequestSpecification httpRequest = RestAssured.given();
	//Passing the resource details
	Response res = httpRequest.queryParam("ISBN","9781449325862").get("/Book");
	//Retrieving the response body using getBody() method
	ResponseBody body = res.body();
	//Converting the response body to string object
	String rbdy = body.asString();
	//Creating object of JsonPath and passing the string response body as parameter
	JsonPath jpath = new JsonPath(rbdy);
	//Storing publisher name in a string variable
	String title = jpath.getString("title");
	System.out.println("The book title is - "+title);
}

}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*public void testgetresponse()
	{
		RestAssured.baseURI="https://reqres.in/api/";
	Response	response=RestAssured.get("users/2");
	System.out.println(response.asString());
	int code=response.getStatusCode();
	System.out.println("status code is "+code);
	Assert.assertEquals(code, 200); //https://www.freeformatter.com/json-escape.html#before-output
	 String actualResponseBody = response.getBody().asString();
     
     // define the expected response body
     String expectedResponseBody = "{\"data\":{\"id\":2,\"email\":\"janet.weaver@reqres.in\",\"first_name\":\"Janet\",\"last_name\":\"Weaver\",\"avatar\":\"https://reqres.in/img/faces/2-image.jpg\"},\"support\":{\"url\":\"https://reqres.in/#support-heading\",\"text\":\"To keep ReqRes free, contributions towards server costs are appreciated!\"}}";
     
    // validate the actual response body with the expected response body
    Assert.assertEquals(actualResponseBody, expectedResponseBody, "Response body validation failed");
 }
	}*/


