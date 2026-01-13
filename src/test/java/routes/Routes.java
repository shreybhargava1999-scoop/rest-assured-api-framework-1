package routes;

public class Routes {
	
	//BaseUrl
	public static final String BASE_URL="https://api-dt-qa.orientalbank.com/ob-idmapi/admin";
	
	//APIs
	public static final String GET_USERS_UUID="/users?crit=loginName+eq+{id}";
	public static final String GET_USERS_LOGIN_NAME="/users/{loginName}";
	public static final String POST_AUTHENTICATE_USER="/credentials";
	public static final String UPDATE_AUTO_SUSPENDED_STATUS="/users/suspend/{id}";
	
	//Token
	public static final String TOKEN_URL="https://idm-ofg-usepool-qa.auth.us-east-1.amazoncognito.com/oauth2/token";
	
	
	

}
