package com.facto.restapi;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.DoubleArray;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FactoApi {

		
	String jsontoken;

	  private static final String FILE_NAME = "C:\\Users\\desktop\\eclipse\\RestAssured\\API-Endpoints\\data.xlsx";
	    private static final String SHEET_NAME = "Sheet1";

	    @DataProvider(name = "excelData")
	    public static Object[][] readExcelData() throws IOException {
	        List<Object[]> data = new ArrayList<>();
	        File file = new File(FILE_NAME);
	        FileInputStream inputStream = new FileInputStream(file);
	        Workbook workbook = WorkbookFactory.create(inputStream);
	        Sheet sheet = workbook.getSheet(SHEET_NAME);
	        int rowCount = sheet.getLastRowNum();
	        for (int i = 1; i <= rowCount; i++) { // start from second row
	            Row row = sheet.getRow(i);
	            Object[] rowValues = new Object[row.getLastCellNum()];
	            for (int j = 0; j < row.getLastCellNum(); j++) {
	                Cell cell = row.getCell(j, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
	              //  System.out.println("cell============"+cell);
	                
	                if (cell != null) {
	                    rowValues[j] = getCellValue(cell);
	                } else {
	                    rowValues[j] = null;
	                }
	            }
	            data.add(rowValues);
	        }
	        return data.toArray(new Object[0][0]);
	    }

	    private static Object getCellValue(Cell cell) {
	        CellType cellType = cell.getCellType();
	        switch (cellType) {
	            case BOOLEAN:
	                return cell.getBooleanCellValue();
	            case NUMERIC:
	                return cell.getNumericCellValue();
	            case STRING:
	                return cell.getStringCellValue();
	            default:
	                return null;
	        }
	    }
	

   
   /* @Test(priority = 1)
	public void LoginjwtToken() {

		String payload = "{ \"data\": { \"user_email\": \"anish.reddy@rhibhus.com\", \"user_password\": \"Anish@123\" } }";

		Response response = RestAssured.given().log().all().contentType(ContentType.JSON).body(payload)
				.post("/v1/login");

		System.out.println("Response body: " + response.getBody().asString());
		System.out.println("Status code: " + response.getStatusCode());
		String jwttoken = response.getBody().asString();
	//	System.out.println(jwttoken);

		int stringlength = jwttoken.length();
		//System.out.println(stringlength);
		jsontoken = jwttoken.substring(9, stringlength - 3);
		System.out.println(jsontoken);
	}*/
    
    
@Test(dataProvider = "excelData",priority=2)
public void myTest(String Method, String EndPoint , String payload,double Expectedstatuscode) {
	
	
	String jsontoken="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiN2MyMWM4YzEtM2VlMC01MGViLThlMDItNGRhZWExYjQ4YzEyIiwidXNlcl9jYXRlZ29yeSI6IkFkbWluIiwidXNlcl9jbGllbnRfaWQiOiJOLkEuIiwidXNlcl90eXBlIjoiTi5BLiIsImlhdCI6MTY4MzYxMTcxOX0.NJaJANpn1aYY7OX99LFWZQNo3jHDI9mRC3PDcIaprd8";
    // Set base URI
   // Define request payload
   // String requestBody = "";
    
    
    // Send POST request
    Response response = given()
    		.baseUri("https://91fjgvixl9.execute-api.ap-south-1.amazonaws.com/testing")  
    		. header("Authorization", jsontoken)
            .contentType(ContentType.JSON)
            .body(payload)
            .post(EndPoint);
    // Print response body
    
    String responseBody = response.getBody().asString();
    
    System.out.println("Response body: " + responseBody);
    // Print response status code
   
    int Actualstatuscode = response.getStatusCode();
	int statuscode = (int) Expectedstatuscode;
   
    
    System.out.println("header token -----------------" + jsontoken);
	//String expectedresponsebody = Expectedresbody;
	
	Reporter.log("status code is =========" + Actualstatuscode + "expected========" + Expectedstatuscode);
	Assert.assertEquals(Actualstatuscode,statuscode);
	
	//String responseBody = response.getBody().asString();
     
    

}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	/*String jsontoken="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiN2MyMWM4YzEtM2VlMC01MGViLThlMDItNGRhZWExYjQ4YzEyIiwidXNlcl9jYXRlZ29yeSI6IkFkbWluIiwidXNlcl9jbGllbnRfaWQiOiJOLkEuIiwidXNlcl90eXBlIjoiTi5BLiIsImlhdCI6MTY4MzYxMTcxOX0.NJaJANpn1aYY7OX99LFWZQNo3jHDI9mRC3PDcIaprd8";
	Response response = RestAssured.given().log().all()
			.baseUri("https://91fjgvixl9.execute-api.ap-south-1.amazonaws.com/testing")
			//.header("Content-Type", "application/json")
            . header("Authorization", jsontoken)
            .contentType(ContentType.JSON)
            .body(payload)
            .post(EndPoint);
	
	
	System.out.println("payload" + payload);
	System.out.println("header token -----------------" + jsontoken);
	//String expectedresponsebody = Expectedresbody;
	int Actualstatuscode = response.getStatusCode();
	int statuscode = (int) Expectedstatuscode;
	Reporter.log("status code is =========" + Actualstatuscode + "expected========" + Expectedstatuscode);
	Assert.assertEquals(Actualstatuscode, statuscode);
	String actualresponsebody = response.getBody().asString();
	String responseBody = response.getBody().asString();
	JsonPath jsonPath = new JsonPath(responseBody);

	String errorMessage = jsonPath.get("error.message");
	System.out.println("Error message: " + errorMessage);
	
	System.out.println("Response body: ===============" +actualresponsebody);
	
	System.out.println("Status code:==========" +Actualstatuscode);
  //  Assert.assertEquals(actualresponsebody, expectedresponsebody);

}


}*/


