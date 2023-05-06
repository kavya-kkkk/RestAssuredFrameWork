package api.endpoints;

public class Routes {
// this class for only url
	// https://petstore.swagger.io

	public static String baseurl = "https://petstore.swagger.io/v2/";

	// user module

	public static String Posturl = baseurl + "user/";

	public static String geturl = baseurl + "user/{username} ";
	public static String Puturl = baseurl + "user/{username} ";
	public static String deleteurl = baseurl + "user/{username}";
	// store module......
	// we can add multiple url's like this
} 
//https://petstore.swagger.io/v2/user/{username}