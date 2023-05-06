package com.factoApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

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
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.i18n.phonenumbers.AsYouTypeFormatter;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestApiTest {
	String jsontoken;

	private static final String EXCEL_FILE_PATH = "C:\\Users\\desktop\\eclipse\\RestAssured\\excel\\data.xlsx";
	private static final String SHEET_NAME = "Sheet1";

	@DataProvider(name = "excelData")
	public Iterator<Object[]> readExcelData() throws IOException {
		FileInputStream fileInputStream = new FileInputStream(EXCEL_FILE_PATH);
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		Sheet sheet = workbook.getSheet(SHEET_NAME);
		List<Object[]> data = new ArrayList<Object[]>();

		for (Row row : sheet) {
			List<Object> rowData = new ArrayList<Object>();
			System.out.println(rowData);
			// Object index=rowData.get(0);
			// System.out.println("this index "+index);
			{
				for (Cell cell : row) {
					switch (cell.getCellType()) {
					case STRING:
						rowData.add(cell.getStringCellValue());
						// System.out.println(rowData);
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
				// System.out.println(rowData);
			}
		}

		workbook.close();
		fileInputStream.close();

		return data.iterator();
	}

	@Test()
	public void LoginjwtToken() {

		//String payload = "{ \"data\": { \"user_email\": \"anish.reddy@rhibhus.com\", \"user_password\": \"Anish@123\" } }";
String payload="{\r\n"
		+ "        	    \"data\": {\r\n"
		+ "        	        \"client_name\": \"kavya\",\r\n"
		+ "        	        \"entity_name\": \"My New Entity3\",\r\n"
		+ "        	        \"sub_category\": \"My New Subcategory3\",\r\n"
		+ "        	        \"client_address\": \"My New Address3\",\r\n"
		+ "        	        \"primary_contact_details\": \"909090909\",\r\n"
		+ "        	        \"secondary_contact_details\": \"9999999991\",\r\n"
		+ "        	        \"billing_address\": \"My New Billing Address3\",\r\n"
		+ "        	        \"sla_agreement\": \"\",\r\n"
		+ "        	        \"client_components\": [\r\n"
		+ "        	            \"Address\"\r\n"
		+ "        	        ],\r\n"
		+ "        	        \"pricing\": \"900000001\"\r\n"
		+ "        	    }\r\n"
		+ "        	}"
		Response response = RestAssured.given().contentType(ContentType.JSON).body(payload)
				.post("https://91fjgvixl9.execute-api.ap-south-1.amazonaws.com/testing/v1/login");

		System.out.println("Response body: " + response.getBody().asString());
		System.out.println("Status code: " + response.getStatusCode());
		String jwttoken = response.getBody().asString();
		System.out.println(jwttoken);

		int stringlength = jwttoken.length();
		System.out.println(stringlength);
		jsontoken = jwttoken.substring(9, stringlength - 3);
		System.out.println(jsontoken);
	}

	@Test(priority = 1, dataProvider = "excelData")

	public void ApiTestMethod(String Method, String URL, String payload, double Expectedstatuscode,String name)

	{
//String jsontoken ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiN2MyMWM4YzEtM2VlMC01MGViLThlMDItNGRhZWExYjQ4YzEyIiwidXNlcl9jYXRlZ29yeSI6IkFkbWluIiwidXNlcl9jbGllbnRfaWQiOiJOLkEuIiwidXNlcl90eXBlIjoiTi5BLiIsImlhdCI6MTY4MzI4MTU4OH0.ho2oWwKlY5_N5jXGnAPxKGNfwPuuDmTVk6fTx5rYVs8";

		Response response = RestAssured.given().header("Authorization", jsontoken).body(payload).post(URL);
		System.out.println("payload" + payload);
		System.out.println("header token -----------------" + jsontoken);
		//String expectedresponsebody = Expectedresbody;
		int Actualstatuscode = response.getStatusCode();
		int statuscode = (int) Expectedstatuscode;
		Reporter.log("status code is " + Actualstatuscode + "expected" + Expectedstatuscode);
		Assert.assertEquals(Actualstatuscode, statuscode);
		String actualresponsebody = response.getBody().asString();
		// System.out.println("Response body: " + response.getBody().asString());
		System.out.println("Status code: " + response.getStatusCode());
	//	Assert.assertEquals(actualresponsebody, expectedresponsebody);

	}

}
