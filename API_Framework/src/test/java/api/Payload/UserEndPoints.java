package api.Payload;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.Enpoints.Routes;
import api.Enpoints.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserEndPoints {
	
	
	
	public static Response create_user(User payload)
	{
		return RestAssured.given() 
		.contentType("application/json; charset=utf-8")
			.body(payload)
		.when()
		.post(Routes.creat_url);
	
	}
	
	public static Response findUser(String userName)
	{
		 return RestAssured.given() 
				.pathParam("username", userName)
		.contentType("application/json; charset=utf-8")
			
		.when()
		.get(Routes.find_url);
		
		 
	}
	

}
