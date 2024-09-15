package demo_RestAssured;

import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Bugtest {

	public static void main(String[] args) {
		String EncodedToken = "Basic Z2FuZXNobTQwODdAZ21haWwuY29tOkFUQVRUM3hGZkdGME1ZcWk3THplRlN2V2I5cy1aMEVVYW95dFRhTklrajlreUZmcVd5QkhCdXV6RnFIRkp4TWhlQXFyeTlobkhiODlaenI0ODUxTm1kWEliNGFZaEN3Rm5OSnhrZUI1QTVSMVZEbVZYbnJwcjBkQmtERFRud3lVODYybmstdE1sb2Z4eF9KQm5LLTlxR0hfOUMwSG84Zk5MdjhLU1BNVGYxTDFoY3NMTGtRVzFTWT0xRTNFRjQxQg==";	
		
RestAssured.baseURI="https://ganeshm4087-1725942549417.atlassian.net";
	
	
String response=given()
		.header("Content-Type", "application/json")
		.header("Authorization", EncodedToken)
		.body("{\n"
				+ "    \"fields\": {\n"
				+ "       \"project\":\n"
				+ "       {\n"
				+ "          \"key\": \"SCRUM\"\n"
				+ "       },\n"
				+ "       \"summary\": \"pic links not displayed\",\n"
				+ "       \"issuetype\": {\n"
				+ "          \"name\": \"Bug\"\n"
				+ "       }\n"
				+ "   }\n"
				+ "}\n"
				+ "")
		.log().all()
	.when()
		.post("/rest/api/2/issue")
	.then()
		.assertThat().statusCode(201)
		.log().all()
		.extract().response().asString();
	
	JsonPath js=new JsonPath(response);
	String issueId=js.getString("id");
	
	System.out.println("IssueID :"+issueId);
	
	//attach photo
	given()
		.header("Content-Type","multipart/form-data")
		.header("Authorization", EncodedToken)
		.header("X-Atlassian-Token","nocheck")
		.pathParam("key", issueId)
		.multiPart("file",new File("/Users/msai/Downloads/DummyImage.png"))
		.log().all()
	.when()
		.post("/rest/api/2/issue/{key}/attachments")
	.then()
		.log().all()
		.assertThat().statusCode(200);


	}

}
