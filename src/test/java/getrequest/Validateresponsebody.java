package getrequest;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Validateresponsebody {
    
    @Test
    public void test() {
        // make the API request
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/1");
        
        // get the actual response body
        String actualResponseBody = response.getBody().asString();
        
        // define the expected response body
        String expectedResponseBody = "{\n"
                + "    \"userId\": 1,\n"
                + "    \"id\": 1,\n"
                + "    \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n"
                + "    \"body\": \"quia et suscipit\\nsuscipit\\nrecusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n"
                + "}";
        
        // validate the actual response body with the expected response body
        Assert.assertEquals(actualResponseBody, expectedResponseBody, "Response body validation failed");
    }
}


