package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.playload.User;
import io.restassured.response.Response;

public class UserTest {
	// test caes for user module
	// we need to create put delete post
	Faker faker; // to generate a data
	User userpayload;

	@BeforeClass
	public void setdata() {
		faker = new Faker();
		userpayload = new User();
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setPassword(faker.internet().password(5, 10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		userpayload.setUsername(faker.name().firstName());

	}

	@Test(priority = 1)
	public void testpostuser() {
		Response response = UserEndPoints.createuser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 2)
	public void testgetuserbyusername() {
		Response response = UserEndPoints.readuser(this.userpayload.getUsername());
		response.then().log().all();

		// when we are using status code we need to specify getstatus code
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 3)
	public void testgetupdatebyusername() {
		// update data using pay loads
		// don't update username

		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());

		Response response = UserEndPoints.updateuser(this.userpayload.getUsername(), userpayload);
		response.then().log().all();
		// one more type we do assertion response.then().log().body().statuscode(200)
		// =restassured

		Assert.assertEquals(response.getStatusCode(), 200);// testng assertion
		// checking data if it updated or not
		Response responseafterupdate = UserEndPoints.readuser(this.userpayload.getUsername());
		response.then().log().all();

		// when we are using status code we need to specify getstatus code
		Assert.assertEquals(responseafterupdate.getStatusCode(), 200);

	}

	@Test(priority = 4)

	public void testdeleteuserbyname() {
		Response response = UserEndPoints.deleteuser(this.userpayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
