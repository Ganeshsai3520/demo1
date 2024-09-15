package demo_RestAssured;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojoClasses.LoginRequest;
import pojoClasses.LoginResponse;
import pojoClasses.Orders;
import pojoClasses.orderDetail;

public class ECommerceAPITest {

	public static void main(String[] args) {
RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
								.setContentType(ContentType.JSON).build();
	LoginRequest loginrequest = new LoginRequest();
	loginrequest.setUserEmail("rahulshetty@gmail.com");
	loginrequest.setUserPassword("Iamking@000");
	
	
RequestSpecification reqLogin=	given()
									.relaxedHTTPSValidation()
									.spec(req)
									.body(loginrequest)
									.log().all();
LoginResponse loginresponse =reqLogin.when()
							.post("/api/ecom/auth/login")
						.then()
							.extract()
							.response().as(LoginResponse.class);
	String TokenID = loginresponse.getToken();
	String UserID = loginresponse.getUserId();
	System.out.println(TokenID);
	System.out.println(UserID);
	
	//Add Product
		RequestSpecification addProduct =new RequestSpecBuilder()
											.setBaseUri("https://rahulshettyacademy.com")
											.addHeader("authorization",TokenID)
											.build();

RequestSpecification reqaddProduct=	
		given()
			.log().all()
			.spec(addProduct)
			.param("productName","Laptop")
			.param("productAddedBy",UserID)
			.param("productCategory","Electronics")
			.param("productSubCategory","Laptops")
			.param("productPrice", "13500")
			.param("productDescription","Lenova")
			.multiPart("productImage",new File("/Users/msai/Downloads/laptop.jpg"));
String addProductResponse=reqaddProduct.when()
				.post("/api/ecom/product/add-product")
			.then()
				.log().all()
				.extract().response().asString();

JsonPath js = new JsonPath(addProductResponse);
	String productid=js.get("productId");
	
	System.out.println(productid);
	
	//Create Order
		RequestSpecification createOrderBaseReq= new RequestSpecBuilder()
												.setBaseUri("https://rahulshettyacademy.com")
												.addHeader("authorization", TokenID)
												.setContentType(ContentType.JSON)
												.build();
		orderDetail od = new orderDetail();
		od.setCountry("India");
		od.setProductOrderId(productid);
		
		List<orderDetail> orderDetailsList = new ArrayList<orderDetail>();
		orderDetailsList.add(od);
		
		
		Orders orders =new Orders();
		orders.setOrders(orderDetailsList);
		
RequestSpecification createOrderReq=given()
										.log().all()
										.spec(createOrderBaseReq)
										.body(orders);

String responseAddOrder= createOrderReq.when()
											.post("/api/ecom/order/create-ordeer")
										.then()
											.log().all()
											.extract().response().asString();

	//Delete
RequestSpecification deleteOrderBaseReq= new RequestSpecBuilder()
					.setBaseUri("https://rahulshettyacademy.com")
					.addHeader("authorization", TokenID)
					.setContentType(ContentType.JSON)
					.build();
RequestSpecification deleteProdReq= given()
									.log().all()
									.spec(deleteOrderBaseReq)
									.pathParam("productId",productid);
String deleteProdResponse=deleteProdReq.when()
				.delete("/api/ecom/product/delete-product/{productid}")
			.then()
				.log().all()
				.extract().response().asString();
JsonPath js1 = new JsonPath(deleteProdResponse);
	js1.getString("productId");
	
	}

}
