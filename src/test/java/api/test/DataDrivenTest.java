package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.playload.User;
import api.utilies.DataPro;
import io.restassured.response.Response;

public class DataDrivenTest {
	@Test(priority =1,dataProvider = "Data",dataProviderClass = DataPro.class)
	//data provider will sent the data so wenned to receive the data
	public void testpostuser(String userid,	String username,String	firstname,String lastname,String	email,String password,String	phonenumber)
	{
		
		System.out.println(username);
		User userpayload=new User();
		
		userpayload.setId(Integer.parseInt(userid));
		userpayload.setUsername(username);
		userpayload.setFirstName(firstname);
		userpayload.setLastName(lastname);
		userpayload.setEmail(email); 
		userpayload.setPassword(password);
		userpayload.setPhone(phonenumber);

		Response response = UserEndPoints.createuser(userpayload);
	   System.out.println(response);
	//	Assert.assertEquals(response.getStatusCode(), 200);
		
		
		//it will create multiple user
	}
	
	
	
	//@Test(priority=2,dataProvider="username",dataProviderClass=DataProviders.class)
	//public void testdeleteuserbyname(String username) {
		
		
	//Response	response=UserEndPoints.deleteuser(username);
	
	//Assert.assertEquals(response.getStatusCode(), 200);
	
		//it will delete the user
	

	
	//}

	

}
