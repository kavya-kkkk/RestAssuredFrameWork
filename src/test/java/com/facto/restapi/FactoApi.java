package com.facto.restapi;

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
	

   
    @Test(priority = 1)
	public void LoginjwtToken() {

		String payload = "{ \"data\": { \"user_email\": \"anish.reddy@rhibhus.com\", \"user_password\": \"Anish@123\" } }";

		Response response = RestAssured.given().contentType(ContentType.JSON).body(payload)
				.post("https://91fjgvixl9.execute-api.ap-south-1.amazonaws.com/testing/v1/login");

		System.out.println("Response body: " + response.getBody().asString());
		System.out.println("Status code: " + response.getStatusCode());
		String jwttoken = response.getBody().asString();
	//	System.out.println(jwttoken);

		int stringlength = jwttoken.length();
		//System.out.println(stringlength);
		jsontoken = jwttoken.substring(9, stringlength - 3);
		System.out.println(jsontoken);
	}
    
    
@Test(dataProvider = "excelData",priority=2)
public void myTest(String Method, String URL, String payload,double Expectedstatuscode) {
	Response response = RestAssured.given()
			.header("Content-Type", "application/json")
            . header("Authorization", jsontoken)
            .body(payload)
            .post(URL);
	
	
	System.out.println("payload" + payload);
	System.out.println("header token -----------------" + jsontoken);
	//String expectedresponsebody = Expectedresbody;
	int Actualstatuscode = response.getStatusCode();
	int statuscode = (int) Expectedstatuscode;
	Reporter.log("status code is =========" + Actualstatuscode + "expected========" + Expectedstatuscode);
	Assert.assertEquals(Actualstatuscode, statuscode);
	String actualresponsebody = response.getBody().asString();
	
	System.out.println("Response body: ===============" +actualresponsebody);
	
	System.out.println("Status code:==========" +Actualstatuscode);
  //  Assert.assertEquals(actualresponsebody, expectedresponsebody);

}

}


