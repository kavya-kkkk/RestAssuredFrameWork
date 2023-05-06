package getrequest;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Validateresponsebody {

	@Test
    public void test() {
         // make the API request
        Response response = RestAssured.get("https://reqres.in/api/users/2");
        
        // get the actual response body
        String actualResponseBody = response.getBody().asString();
        
        // define the expected response body
        String expectedResponseBody = "{\r\n"
        		+ "    \"data\": {\r\n"
        		+ "        \"client_name\": \"Oracle5555555\",\r\n"
        		+ "        \"entity_name\": \"My New Entity5\",\r\n"
        		+ "        \"sub_category\": \"My New Subcategory5\",\r\n"
        		+ "        \"client_address\": \"My New Address5\",\r\n"
        		+ "        \"primary_contact_details\": \"9090909095\",\r\n"
        		+ "        \"secondary_contact_details\": \"9999999995\",\r\n"
        		+ "        \"billing_address\": \"My New Billing Address5\",\r\n"
        		+ "        \"sla_agreement\": \"pak5.jpg\",\r\n"
        		+ "        \"client_components\": [\r\n"
        		+ "            \"Address\",\r\n"
        		+ "            \"Employment\"\r\n"
        		+ "        ],\r\n"
        		+ "        \"pricing\": \"9000000015\",\r\n"
        		+ "        \"client_id\": \"4b76077f-b885-55a3-a0b1-10ba6389e469\"\r\n"
        		+ "    }\r\n"
        		+ "}"
        	   String as="{\r\n"
        	   		+ "    \"data\":\r\n"
        	   		+ "    {\r\n"
        	   		+ "         \"client_id\": \"e3f757db-de44-5fd2-bbf5-0a56fb50fab6\"\r\n"
        	   		+ "    }\r\n"
        	   		+ "}"
        // validate the actual response body with the expected response body
        Assert.assertEquals(actualResponseBody, expectedResponseBody, "Response body validation failed");
    }
        
        
        String ss=
}
	 /*@DataProvider(name = "myDataProvider")
    public Object[][] data() {
        return new Object[][] {
            {"Post", "https://91fjgvixl9.execute-api.ap-south-1.amazonaws.com/testing/v1/fetchAllClients", "", 200.0, "kavya", null},
            // add more test data here
        };
    }

 @Test(dataProvider = "myDataProvider")

 public void myTest(String arg0, String arg1, String arg2, Double arg3, String arg4, Object arg5) {
     // test code here
 }
*/