package Files;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {
	
	public static XmlPath rawToXML(Response r){
		
		String respon = r.asString();
		System.out.println(respon);
		XmlPath x = new XmlPath(respon);
		return x;
	}
	
	public static JsonPath rawToJSON(Response r){
		
		String respon = r.asString();
		JsonPath x = new JsonPath(respon);
		return x;
			
	}

	public static String sessionKey(){
		
		RestAssured.baseURI = "http://localhost:8080";
		
		Response res = given().header("Content-Type", "application/json").
		body("{ \"username\": \"anshul.goyal\", \"password\": \"A@12\" }").
		when().
		post("/rest/auth/1/session").then().statusCode(200).
		extract().response();
		
		JsonPath js = ReusableMethods.rawToJSON(res);
		String sessionId = js.get("session.value");
		System.out.println(sessionId);
		return sessionId;
	}
	
	public static String issueID(){
		
		RestAssured.baseURI = "http://localhost:8080";
		
		Response res = given().header("Content-Type", "application/json").
		header("cookie", "JSESSIONID="+ReusableMethods.sessionKey()).
		body("{"+
"\"fields\": {"+
"\"project\":"+
"{"+ 
"\"key\": \"SP\""+
"},"+
"\"summary\": \"Debit Card Issue\","+
"\"description\": \"Creating of an Debite Card issue using project keys and issue type names using the REST API\","+
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
		
		return issueId;
	}
	
	public static String commentId(){
		
RestAssured.baseURI = "http://localhost:8080";
		
		Response res = given().header("Content-Type", "application/json").
		when().
		post("/rest/api/2/issue/"+ReusableMethods.issueID()+"/comment").then().statusCode(201).
		extract().response();
		
		JsonPath js = ReusableMethods.rawToJSON(res);
		String commentId = js.get("id");
		System.out.println(commentId);
		
		return commentId;
	
	
	}
}
