import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class basics6 {
	
	
	Properties prop = new Properties();
	
	@BeforeTest
	public void envData() throws IOException{
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\Files\\env.properties");
		prop.load(fis);
	}
	
	@Test
	public void jiraApi(){
		
		RestAssured.baseURI = "http://localhost:8080";
		
		Response res = given().header("Content-Type", "application/json").
		header("cookie", "JSESSIONID="+ReusableMethods.sessionKey()).
		body("{"+
"\"fields\": {"+
"\"project\":"+
"{"+ 
"\"key\": \"SP\""+
"},"+
"\"summary\": \"Debit Card Issue1\","+
"\"description\": \"Creating of an Debite Card issue using project keys and issue type names using the REST API1\","+
"\"issuetype\": {"+
"\"name\": \"Bug\""+
"}"+
"}"+
"}"+
"").when().
		post("/rest/api/2/issue").then().statusCode(201).
		extract().response();
		
		JsonPath js = ReusableMethods.rawToJSON(res);
		String issueId = js.get("id");
		System.out.println(issueId);
		
		
	}

}
