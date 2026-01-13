package testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Map;

import org.testng.annotations.Test;

import auth.OAuthToken;
import io.restassured.http.ContentType;
import pojo.AdminTests;
import routes.Routes;

public class UserDataDrivenTest extends BaseClass
{
	String Token = OAuthToken.getAccessToken();
	
	@Test(dataProvider="jsonDataProvider", dataProviderClass=utils.DataProviders.class)
	public void testAddNewUser(Map<String,String> data)
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
			    // .extract().jsonPath().get("id"); //Extracting Id from response body
		
		//System.out.println("ID======> "+ id);
		
		
	}

}
