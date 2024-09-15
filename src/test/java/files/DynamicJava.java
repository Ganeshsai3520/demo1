package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class DynamicJava {
	@Test(dataProvider="Books data")
	public void addbook(String isbn,String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
Response response=given()
			.header("Content-Type","application/json")
			.body(payloads.Addbook(isbn,aisle))
		.when()
			.post("/Library/Addbook.php")
		.then()
			.assertThat().statusCode(200)
			.extract().response();
		
		JsonPath js = ReusableMethods.rawToJson(response);
		String id = js.get("ID");
		System.out.println("Book Id : "+id);
		
		
	//Delete Books
		given()
			.header("Content-Type","application/json")
			.body(id)
		.when()
			.delete("/Library/DeleteBook.php")
		.then()
			.assertThat().statusCode(200);
			

	}
	@DataProvider(name ="Books data")
	public Object[][] getData() {
	
		return	new Object[][] {{"mgs213","2213"},
							{"mgs214","2214"},
							{"sdhjg","6737"},
							{"ojfwty","9363"},
							{"cwetee","4253"},
							{"okmfet","533"}};
	}
	

}
