package tests;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import Files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class basics4 {
	
	@Test
	public void postData() throws IOException{
		
		String postData = GenerateStringFromResource("C:\\Users\\indianic\\Desktop\\postData.xml");
		
		RestAssured.baseURI = "https://maps.googleapis.com";
		
		Response resp = given().
		
		queryParam("key","AIzaSyBfU_WlcnPDTI1rdjYW0WqQOdVhQqx8Q2w").
		body(postData).
		when().
		post("/maps/api/place/add/xml").
		then().assertThat().contentType(ContentType.XML).
		extract().response();
		
		//String respon = resp.asString();
		//System.out.println(resp);
		//XmlPath x = new XmlPath(respon);
		
		XmlPath x = ReusableMethods.rawToXML(resp);
		System.out.println(x.get("PlaceAddResponse.place_id"));
		
		//XmlPath x =ReusableMethods.rawToXML(resp);
		
		
	
		
		//System.out.println(x.getBoolean("PlaceAddResponse.status"));
		
		
		
		

	}
	
	public static String GenerateStringFromResource(String path) throws IOException{
		
		return new String(Files.readAllBytes(Paths.get(path)));
		
		
	}

}
