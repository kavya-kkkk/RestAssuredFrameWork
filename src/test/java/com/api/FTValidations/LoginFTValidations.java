package com.api.FTValidations;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.Dataprovider.ExcelReader.DataProviderExcelReader;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginFTValidations {

	@Test(dataProvider = "RestApi", dataProviderClass = DataProviderExcelReader.class)
	public void RestApiLoginValidations(String Method, String EndPoint, String Payload, double ExpectedStatusCodeDouble,
			String ExpectedResponseBody, String ExpectedHeaderContentType)

	{

		Response response = RestAssured

				.given()

				.baseUri("https://91fjgvixl9.execute-api.ap-south-1.amazonaws.com/testing")
				.contentType(ContentType.JSON).body(Payload)

				.when() 

				.post(EndPoint)

				.then()

				.extract()

				.response();
		// To ActualStatusCode from response body and To Print
		int ActualStatusCode = response.getStatusCode();
		

		// To ActualResponseBody from response body and To print
		String ActualResponseBody = response.getBody().asString();
		
		// To ActualHeaderContentType from response body and To print
		String ActualHeaderContentType = response.header("Content-Type");
		

		// To convert from double to integer (while fetching will get integer as double)
		// and to print
		int ExpectedStatusCode = (int) ExpectedStatusCodeDouble;
		

		
		

		
				// To print and To validate both (exp and act status code)
				Reporter.log("Actual status code is " + "=>" + ActualStatusCode + "=>" + "-AND-" + "expected status code"
						+ "=>" + ExpectedStatusCode);
				Assert.assertEquals(ActualStatusCode, ExpectedStatusCode, "Expected status code is  Incorrect");
		
				
			
		

			// To print response body
			Reporter.log("Actual Response body" + " =>" + ActualResponseBody + "=>" + "Expected Response Body is" + "=>"
					+ ExpectedResponseBody);
			Assert.assertEquals(ActualResponseBody.contains(ExpectedResponseBody), true, "Incorrect Expeted body");

			// To print headers ActualHeaderContentType
			Reporter.log("Actual Response body" + " =>" + ActualHeaderContentType + "=>" + "Expected Response Body is"
					+ "=>" + ExpectedHeaderContentType);
			Assert.assertEquals(ActualHeaderContentType.contains(ExpectedHeaderContentType), true,
					"Incorrect  Expected Header ContentType");
			System.out.println(" status code is "+ ActualStatusCode);

		

	}

}
