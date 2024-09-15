package demo_RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class BasicAPI {

	public static void main(String[] args) throws IOException {
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
	//Add place or Creating 
	String response =	given()
			.log().all()
			.queryParam("key","qaclick123")
			.header("Content-Type","application/json")
			.body(new String(Files.readAllBytes(Paths.get("/Users/msai/Downloads/PlaceLocation.json.txt"))))
		//	.body(payloads.Addplace())
		.when()
			.post("/maps/api/place/add/json")
		.then()
			.assertThat().statusCode(200)
			.body("scope",equalTo("APP"))
			.header("Server","Apache/2.4.52 (Ubuntu)")
			.extract().response().asString();
	
	System.out.println(response);
	
	JsonPath js =new JsonPath(response); //for parsing Json
	
	String placeId=js.getString("place_id");
	System.out.println("place_id:"+placeId);
	
	//update place
	String newAddress = "70,Summer Walk,Texas,USA";
	given()
		.log().all()
		.queryParam("key", "qaclick123")
		.header("Content-Type","application/json")
		.body("{\n"
				+ "\"place_id\":\""+placeId+"\",\n"
				+ "\"address\":\""+newAddress+"\",\n"
				+ "\"key\":\"qaclick123\"\n"
				+ "}\n"
				+ "")
	.when()
		.put("/maps/api/place/update/json")
	.then()
		.assertThat().statusCode(200)
		.body("msg",equalTo("Address successfully updated") )
		.log().all();
	//get place
String getPlaceResponse =	given()
		.queryParam("key","qaclick123")
		.queryParam("place_id",placeId)
	.when()
		.get("/maps/api/place/get/json")
	.then()
		.assertThat().log().all().statusCode(200)
		.extract().response().asString();
	
	JsonPath js1=ReusableMethods.rawToJson(getPlaceResponse);
	String actualAddress = js1.getString("address");
	System.out.println("ActualAddress:"+actualAddress);
	//Assertion
	Assert.assertEquals(actualAddress, newAddress);
	//Delete
		given()
			.queryParam("key","qaclick123")
			.header("Content-Type","application/json")
			.body("{\n"
					+ "    \"place_id\": \""+placeId+"\"\n"
					+ "}")
		.when()
			.delete("/maps/api/place/delete/json")
		.then()
			.assertThat().statusCode(200);
			
	
	}

}
