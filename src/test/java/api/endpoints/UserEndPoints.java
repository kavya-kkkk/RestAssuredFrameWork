package api.endpoints;

import api.playload.User;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {
	
	//this class for crud operation for user module

	//we are creating end oints not testcases
	
	
public static  Response createuser(User playload)
	//user is pojo class we need to create
	{
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(playload)
		.when()
		.post(Routes.Posturl);
		return response;
	}
	
	
	
public  static Response readuser(String username)//to receive parameter

{
	Response response=given()
	.pathParam("username", username)
	.when().get(Routes.geturl);
	return response;
}

public static  Response updateuser(String username,User playload)

{
	Response response=given()
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.pathParam("username", username)
	.body(playload)
	.when()
	.put(Routes.Puturl);
	return response;
}
public static Response deleteuser(String username)
{
	Response response=given()
	
	.pathParam("username", username)
	
	.when()
	.delete(Routes.Puturl);
	return response;
}
	
	
	
}
