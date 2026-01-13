package testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Map;

import org.testng.annotations.Test;

import auth.OAuthToken;
//import pojo.Product;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Payload;
import pojo.AdminTests;
import routes.Routes;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainBaseClass extends BaseClass{
	
	String Token = OAuthToken.getAccessToken();
	long id;
	
	@Test(priority=3)
	public void verifyGetAPI_UUID() {
		
		//long Userid= configReader.getLongProperty("id");
		
		given()
		.auth().oauth2(Token)
		.header("x-api-key",configReader.getProperty("x_api_key"))
		.header("Accept-Language",configReader.getProperty("Accept_Language"))
		.header("appid",configReader.getProperty("appid"))
		.pathParam("id", id)
		
		.when()
		.get(Routes.GET_USERS_UUID)
		.then()
		.statusCode(200);
		//.log().body();
		
	}
	
	
	@Test(priority=1)
	public void verifyGetAPI_loginName() {
		
		String loginName= configReader.getProperty("loginName");
		
		given()
		.auth().oauth2(Token)
		.header("x-api-key",configReader.getProperty("x_api_key"))
		.header("Accept-Language",configReader.getProperty("Accept_Language"))
		.header("appid",configReader.getProperty("appid"))
		.pathParam("loginName", loginName)
		
		.when()
		.get(Routes.GET_USERS_LOGIN_NAME)
		.then()
		.statusCode(200);
		//.log().body();
		
	}
	
	
	@Test(priority=2)
	public void verifyPostAPI_authenticateUser() {
		
		AdminTests newAdminTests = Payload.AuthPayload();
				
				
		id = given()
		.auth().oauth2(Token)
		.contentType(ContentType.JSON)
		.header("x-api-key",configReader.getProperty("x_api_key"))
		.header("Accept-Language",configReader.getProperty("Accept_Language"))
		.header("appid",configReader.getProperty("appid"))
		.body(newAdminTests)
		
		.when()
		.post(Routes.POST_AUTHENTICATE_USER)
		
		.then()
		.statusCode(200)
		.body("id", notNullValue())
		.extract().jsonPath().getLong("id");
		
		System.out.println(id);
		//.log().body();
		
	}
	
	@Test(priority=4)
	public void verifyUpdateAPI_TrueAutoSuspendedStatus() {
		
		//long Userid= configReader.getLongProperty("id");
		
		given()
		.auth().oauth2(Token)
		.header("x-api-key",configReader.getProperty("x_api_key"))
		.header("Accept-Language",configReader.getProperty("Accept_Language"))
		.header("appid",configReader.getProperty("appid"))
		.pathParam("id", id)
		.queryParam("status", "1")
		
		.when()
		.put(Routes.UPDATE_AUTO_SUSPENDED_STATUS)
		.then()
		.statusCode(200)
		.body(equalTo("User status updated successfully"));
		//.log().body();
		
	}
	
		
		@Test(priority=5)
		public void verifyGetAPI_CheckTrue() {
			
			//long Userid= configReader.getLongProperty("id");
			
			int value=given()
			.auth().oauth2(Token)
			.header("x-api-key",configReader.getProperty("x_api_key"))
			.header("Accept-Language",configReader.getProperty("Accept_Language"))
			.header("appid",configReader.getProperty("appid"))
			.pathParam("id", id)
			
			.when()
			.get(Routes.GET_USERS_UUID)
			.then()
			.statusCode(200)
			.extract().jsonPath().get("data[0].autoSuspended");
			
			if(value==1)
			{
				System.out.println("User is Auto-Suspneded");
			}
			else
			{
				System.out.println("Normal User");
			}
			//.log().body();
			
		}
		
		
		
		@Test(priority=6)
		public void verifyUpdateAPI_FalseAutoSuspendedStatus() {
			
			//long Userid= configReader.getLongProperty("id");
			
			given()
			.auth().oauth2(Token)
			.header("x-api-key",configReader.getProperty("x_api_key"))
			.header("Accept-Language",configReader.getProperty("Accept_Language"))
			.header("appid",configReader.getProperty("appid"))
			.pathParam("id", id)
			.queryParam("status", "0")
			
			.when()
			.put(Routes.UPDATE_AUTO_SUSPENDED_STATUS)
			.then()
			.statusCode(200)
			.body(equalTo("User status updated successfully"));
			//.log().body();
			
		}
		
			
			@Test(priority=7)
			public void verifyGetAPI_CheckFalse() {
				
				//long Userid= configReader.getLongProperty("id");
				
				int value=given()
				.auth().oauth2(Token)
				.header("x-api-key",configReader.getProperty("x_api_key"))
				.header("Accept-Language",configReader.getProperty("Accept_Language"))
				.header("appid",configReader.getProperty("appid"))
				.pathParam("id", id)
				
				.when()
				.get(Routes.GET_USERS_UUID)
				.then()
				.statusCode(200)
				.extract().jsonPath().get("data[0].autoSuspended");
				
				if(value==1)
				{
					System.out.println("User is Auto-Suspneded");
				}
				else
				{
					System.out.println("Normal User");
				}
				//.log().body();
				
			}
			
			
			@Test(dataProvider="jsonDataProvider", dataProviderClass=utils.DataProviders.class, priority=8)
			public void testJSONAddNewUser(Map<String,String> data)
			{
				String loginName=data.get("loginName");
				String password=data.get("password");
				
				AdminTests newAdminTest=new AdminTests(loginName,password);
				
				
				given()
						.auth().oauth2(Token)
						.contentType(ContentType.JSON)
						.header("x-api-key",configReader.getProperty("x_api_key"))
						.header("Accept-Language",configReader.getProperty("Accept_Language"))
						.header("appid",configReader.getProperty("appid"))
						.body(newAdminTest)
						
						.when()
						.post(Routes.POST_AUTHENTICATE_USER)
						
						.then()
						.statusCode(200);
				
			}
			
			
			@Test(dataProvider="csvDataProvider", dataProviderClass=utils.DataProviders.class, priority=9)
			public void testCSVAddNewUser(String loginName, String password) {

			    AdminTests newAdminTest = new AdminTests(loginName, password);

			    given()
			        .auth().oauth2(Token)
			        .contentType(ContentType.JSON)
			        .header("x-api-key", configReader.getProperty("x_api_key"))
			        .header("Accept-Language", configReader.getProperty("Accept_Language"))
			        .header("appid", configReader.getProperty("appid"))
			        .body(newAdminTest)
			    .when()
			        .post(Routes.POST_AUTHENTICATE_USER)
			    .then()
			        .statusCode(200);
			}
			
	

}
