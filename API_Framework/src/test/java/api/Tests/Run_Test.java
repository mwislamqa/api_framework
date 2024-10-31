package api.Tests;

import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.Enpoints.User;
import api.Payload.UserEndPoints;
import io.restassured.response.Response;

public class Run_Test {
	
	public User user;
	Faker fakeData;
	@BeforeTest
	public void Data() {
		
		fakeData = new Faker();
		user = new User();
		user.setName(fakeData.name().username());
		user.setJob(fakeData.job().position());
	}
	
	@Test(priority=1)
	public void createUser() {
		
		Response res=UserEndPoints.create_user(user);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 201);
		
	}
	
	@Test(priority=2)
	public void findUser() {
		
		Response res=UserEndPoints.findUser(this.user.getName());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		
	}

}
