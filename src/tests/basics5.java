package tests;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import Files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class basics5 {

	@Test
	public void test1(){
		
		RestAssured.baseURI = "https://maps.googleapis.com";
		
	Response res = given().log().ifValidationFails().
				param("location","23.0295345,72.5639128").
				param("radius","500").
				param("type","restaurant").
				param("key","AIzaSyBfU_WlcnPDTI1rdjYW0WqQOdVhQqx8Q2w").
				when().
				get("/maps/api/place/nearbysearch/json").
				then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
				body("results[1].name", equalTo("Atithi The Hotel")).and().
				body("results[1].place_id",equalTo("ChIJuVUPN_iEXjkRgOfXzR5g8RQ")).and().
				header("server",equalTo("scaffolding on HTTPServer2")).
				extract().response();
	
				JsonPath js = ReusableMethods.rawToJSON(res);
				int count = js.get("results.size");
				System.out.println(count);
				for(int i=0;i<count;i++){
					
					System.out.println(js.get("results["+i+"].name"));
					
				}
	}


}
