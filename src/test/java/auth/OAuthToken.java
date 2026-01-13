package auth;

import static io.restassured.RestAssured.given;
import utils.ConfigReader;

import io.restassured.response.Response;
import routes.Routes;
import utils.ConfigReader;

public class OAuthToken {
	
	public static String getAccessToken() {
		Response response=
				given()
		.contentType("application/x-www-form-urlencoded")
		.formParam("grant_type", "client_credentials")
		.formParam("client_id", ConfigReader.getProperty("YOUR_CLIENT_ID"))
        .formParam("client_secret", ConfigReader.getProperty("YOUR_CLIENT_SECRET"))
//        .formParam("client_id", "365mrjnnlu35mf3f77t29fkk7j")
//        .formParam("client_secret", "4lmkf9j4o9dsoglc441uo9ttdnjhqfgp2fdvufae03eq1v1s763")
		.when()
		.post(Routes.TOKEN_URL)
		.then()
		.statusCode(200)
		.extract().response();
		
		String Token= response.jsonPath().getString("access_token");
		return Token;
	}

}
