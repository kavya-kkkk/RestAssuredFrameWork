package api.test;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
public class RestApiTest{
//post
	@Test(priority=1,dataProvider = "excelData")
	  
	  public void createUser(String method ,String jsonbody, String url, String contenttype,double  status,String expectedresponsebody) 
			{ 
		
		Reporter.log("request body" +jsonbody +"request url "  +url + "type of the content "+contenttype );
				
				    
				if(method.equals("post"))
				{
					Reporter.log("request body" +jsonbody +"request url "  +url + "type of the content "+contenttype );
					String requestBody = jsonbody;
					given()
				    .contentType(contenttype) .
				    body(requestBody) ;
				    System.out.println("Actual and expected values are equal.");
				    Response response=RestAssured.post(url);
					 // System.out.println(response.asPrettyString());
					 System.out.println("url for get "+ url);
					 int code=response.getStatusCode();
					 int statuscode=(int)status;
					 Reporter.log("status code is "+code+ "expected"+ statuscode);
					 Assert.assertEquals(code, statuscode);  
				     Reporter.log(response.asString());
						//https://www.freeformatter.com/json-escape.html#before-output
						 String actualResponseBody = response.getBody().asString();
					     
					     // define the expected response body
					      String expectedResponseBody ="expectedresponsebody" ;
					     
					    // validate the actual response body with the expected response body
					    Assert.assertEquals(actualResponseBody, expectedResponseBody);
				}
			}
}