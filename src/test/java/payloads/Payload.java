package payloads;

import pojo.AdminTests;
import utils.ConfigReader;

public class Payload {
	
	public static AdminTests AuthPayload() {
		
		AdminTests login = new AdminTests();
		
		login.setLoginName(ConfigReader.getProperty("loginName"));
		login.setPassword(ConfigReader.getProperty("Password"));
		
		return login;
		
	}

}
