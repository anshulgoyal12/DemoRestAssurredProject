import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class basics9 {
	
	String ConsumerKey = "F9vhEVIROOP6SDnxX6wtzURBQ";
	String ConsumerSecret = "wm4pg20xTuqldNXj52UkI4rgx7Wwfl3BtIBqk3YBGY55EGHBSX";
	String Token = "248647382-yBFxjMLldZqWCn8epFbnFcVvCDRlrqH079Yts1xA";
	String TokenSecret = "IQ5g1uIyiHb7AeRkHtMCexi87UVbzTCcg1FEPGQmtRnRP";
	String id;
	
	
	@Test
	public void getLatestTweet(){
		
		
		
		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		
		Response res = given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret).
		queryParam("count", "1").
		when().
			get("/home_timeline.json").then().extract().response();
		
		String response = res.asString();
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		System.out.println(js.get("text"));
		System.out.println(js.get("id"));
	}
	
	@Test
	public void createTweet(){
		
		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		
		Response res = given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret).
		queryParam("status", "I am tweeting from restapi3").
		when().
			post("/update.json").then().extract().response();
		
		String response = res.asString();
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		//System.out.println(js.get("text"));
		System.out.println(js.get("id"));
		id = js.get("id").toString();
		
	}
	
	@Test
	public void deleteTweet(){
		
		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		
		Response res = given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret).
		//	queryParam("text", "Hi this is test tweet").
		when().
			post("/destroy/"+id+".json").then().extract().response();
		
		String response = res.asString();
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		System.out.println(js.get("text"));
		System.out.println(js.get("truncated"));
		
	}

}
