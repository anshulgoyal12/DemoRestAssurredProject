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

public class basics8 {
	
	
	Properties prop = new Properties();
	
	@BeforeTest
	public void envData() throws IOException{
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\Files\\env.properties");
		prop.load(fis);
	}
	
	@Test
	public void jiraApi1(){
		
		RestAssured.baseURI = "http://localhost:8080";
		
		Response res = given().header("Content-Type", "application/json").
		header("cookie", "JSESSIONID="+ReusableMethods.sessionKey()).
		pathParams("commentid","10304").
		body("{"+
      "\"body\": \"Updating my latest Credit card comment3\","+
      "\"visibility\": {"+
        "\"type\": \"role\","+
        "\"value\": \"Administrators\""+
      "}"+
    "}").when().
		put("/rest/api/2/issue/10200/comment/{commentid}").then().statusCode(200).
		extract().response();
		
		
	}

}
