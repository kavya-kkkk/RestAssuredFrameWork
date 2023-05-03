package api.utilies;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.Console;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.hamcrest.Matcher;
import org.mozilla.javascript.ConsString;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.model.Report;

import api.endpoints.UserEndPoints;
import api.playload.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ExcelReaderDataProviderExample {
	private static final String EXCEL_FILE_PATH = "C:\\Users\\desktop\\eclipse\\RestAssured\\testdata\\exceldata.xlsx";
	private static final String SHEET_NAME = "Sheet1";

	@DataProvider(name = "excelData")
	public Iterator<Object[]> readExcelData() throws IOException {
		FileInputStream fileInputStream = new FileInputStream(EXCEL_FILE_PATH);
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		Sheet sheet = workbook.getSheet(SHEET_NAME);
		List<Object[]> data = new ArrayList<Object[]>();

		for (Row row : sheet) {
			List<Object> rowData = new ArrayList<Object>();
			for (Cell cell : row) {
				switch (cell.getCellType()) {
				case STRING:
					rowData.add(cell.getStringCellValue());
					break;
				case NUMERIC:
					rowData.add(cell.getNumericCellValue());
					break;
				case BOOLEAN:
					rowData.add(cell.getBooleanCellValue());
					break;
				case _NONE:
		 			rowData.add(cell.getBooleanCellValue());
					break;
				default:
					rowData.add(null);
					break;
				}
			}
			data.add(rowData.toArray());
		}

		workbook.close();
		fileInputStream.close();

		return data.iterator();
	}

	/*//get
	  @Test(priority=1,dataProvider = "excelData")
	  
	  public void testgetresponse(String url) 
	  { 
		  
     Response response=RestAssured.get(url);
	  System.out.println(response.asPrettyString());
	 System.out.println("url for get "+ url);
	 int code=response.getStatusCode();
	 System.out.println("status code is "+code); 
	 Assert.assertEquals(code, 200); 
	 
	  }*/
	
	  //post
	@Test(priority=1,dataProvider = "excelData")
	  
	  public void createUser(String method ,String jsonbody, String url, String contenttype,double  status,String expectedresponsebody) 
			{ 
		
		
		Reporter.log("request body" +jsonbody +"request url "  +url + "type of the content "+contenttype );
				
				    
				if(method.equals("post"))
				{
					Reporter.log("request body" +jsonbody +"request url "  +url + "type of the content "+contenttype );
					String requestBody = jsonbody;
					RequestSpecification httpRequest = given() ;
					RequestSpecification jsontype = httpRequest.contentType(contenttype);
					RequestSpecification reqjsonbody = jsontype.body(requestBody);
				   
				    System.out.println("Actual and expected values are equal.");
				    Assert.assertTrue(true);
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
					 
				} else if(method.equals("put")) 
				
				{
					 System.out.println("Actual and expected values are equal.");

					  String  requestBody = jsonbody; given() 
					  .contentType(contenttype) 
					  .body(requestBody)
				     .when() 
				    .put(url) 
				    .then() 
				    .statusCode(200) ;
			 	  
					
					    Response response=RestAssured.put(url);
						 // System.out.println(response.asPrettyString());
						 System.out.println("url for get "+ url);
						 Assert.assertTrue(true);
						 int code=response.getStatusCode();
						 int statuscode=(int)status;
						 Reporter.log("status code is "+code+ "expected"+ statuscode);
						 Assert.assertEquals(code, statuscode);  
					     Reporter.log(response.asString());
							//https://www.freeformatter.com/json-escape.html#before-output
							 String actualResponseBody = response.getBody().asString();
						     
					 	     // define the expected response body
						      String expectedResponseBody ="expectedresponse" ;
						     
						    // validate the actual response body with the expected response body
						    Assert.assertEquals(actualResponseBody, expectedResponseBody,"Correct status code returned");
						 
				}
				
				
				else if(method.equals("get")) 
					
				{
					 Reporter.log("Actual and expected values are equal.");
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
						      //String expectedResponseBody ="expectedresponse" ;
						     
						    // validate the actual response body with the expected response body
						 //   Assert.assertEquals(actualResponseBody, expectedResponseBody);
						 
				}
				
				
        else if(method.equals("delete")) 
					
				{
					      Reporter.log("Actual and expected values are equal.");
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
						     // String expectedResponseBody ="expectedresponse" ;
						     
						    // validate the actual response body with the expected response body
						   // Assert.assertEquals(actualResponseBody, expectedResponseBody);
						 
				}
				
				
        else {
        	
        	
        Reporter.log("Please provide a correct method name ");
        }
				
				
				
				
				
				
				
				
				
				
				
				
				
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
				      String expectedResponseBody ="expectedresponse" ;
				     
				    // validate the actual response body with the expected response body
				    Assert.assertEquals(actualResponseBody, expectedResponseBody);
				 
			}
				 
				 
				//Assert.assertEquals(code, 200); 
			   
			  //.post(url) 
			//  .then()
			  //System.out.println(sta);
			 // .statusCode(200) 
			//  .assertThat();
			//.body("name", equalTo("John")) 
			//  .body("email", equalTo("john@example.com")); 
			
	 
	/*  public void updateUser(String jsonbody,String url, String contenttype,String name,String email ) 
	  { 
		 /* { \"name\": \"morpheus\", \"email\": \"zion resident\" }"
		  {
			    "name": "morpheus",
			    "job": "zion resident"
			}
		  String  requestBody = jsonbody;
		  given() 
		  .contentType(contenttype) 
		  .body(requestBody)
	  .when() 
	  .put(url) //https://reqres.in/api/users/2
	  .then() 
	  .statusCode(200) 
	  .assertThat() 
	  .body("name",equalTo(name)) 
	  .body("email", equalTo(email)); */
	
	//get

	 /* @Test(priority=1,dataProvider = "excelData") public void
	 testgetresponse(String url) { 
		  
     Response response=RestAssured.get(url);
	  System.out.println(response.asPrettyString());
	 System.out.println("url for get "+ url);
	 int code=response.getStatusCode();
	 System.out.println("status code is "+code); 
	 Assert.assertEquals(code, 200); 
	 
	  }*/
	 
	//post
	  
	/*@Test(dataProvider = "excelData") public void createUser(String jsonbody,
	  String url, String contenttype) 
	{ 
		String requestBody = jsonbody;
		given()
	  .contentType(contenttype) .
	  body(requestBody) 
	  .when() 
	  .post(url) 
	  .then()
	  .statusCode(200) 
	  .assertThat();
	// .body("name", equalTo("John")) 
	//  .body("email", equalTo("john@example.com")); 
	}
*/
	
	  
	  
//update
	
	/*  @Test(dataProvider = "excelData")
	  public void updateUser(String
	  jsonbody,String url, String contenttype,String name,String email ) 
	  { 
		 
		  String  requestBody = jsonbody; given() 
		  .contentType(contenttype) 
		  .body(requestBody)
	  .when() 
	  .put(url) 
	  .then() 
	  .statusCode(200) 
	  .assertThat() 
	  .body("name",
	  equalTo(name)) 
	  .body("email", equalTo(email)); 
		  }*/
	 

	/*
	  @Test(dataProvider = "excelData") public void testpostuser(String
	  username,String firstname,String lastname,String email,String password) {
	  
	  System.out.println(username); User userpayload=new User();
	  
	  userpayload.setUsername(username); 
	  userpayload.setFirstName(firstname);
	  userpayload.setLastName(lastname); userpayload.setEmail(email);
	  userpayload.setPassword(password);
	  
	  
	  Response response = UserEndPoints.createuser(userpayload);
	  System.out.println(response); //
	  Assert.assertEquals(response.getStatusCode(), 200);
	  
	  
	  //it will create multiple user }
	  
	  @Test(dataProvider = "excelData") public void testExcelData(String column1,
	  String column2) { System.out.println("Column 1: " + column1);
	  System.out.println("Column 2: " + column2);
	  
	  }
	  */
	 
}
