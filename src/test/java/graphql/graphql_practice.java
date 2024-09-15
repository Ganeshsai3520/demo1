package graphql;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;

public class graphql_practice {

	public static void main(String[] args) {
	
	String resp=	given()
			.log().all()
			.header("Content-Type","application/json")
			.body("{\n"
					+ "  \"query\": \"query($characterId:Int!,$name:String)\\n{\\n  character(characterId: $characterId) {\\n    name\\n    gender\\n  }\\n  location(locationId: 8) {\\n    name\\n    dimension\\n  }\\n  episode(episodeId: 1) {\\n    name\\n    air_date\\n    created\\n  }\\n  characters(filters: {name: $name}) {\\n    info {\\n      count\\n    }\\n    result {\\n      name\\n      type\\n      gender\\n    }\\n  }\\n  episodes(filters: {episode: \\\"hulu\\\"}) {\\n    info {\\n      pages\\n      count\\n    }\\n    result {\\n      name\\n      episode\\n    }\\n  }\\n}\",\n"
					+ "  \"variables\": {\n"
					+ "    \"characterId\": 8,\n"
					+ "    \"name\": \"rahul\"\n"
					+ "  }\n"
					+ "}")
		.when()
			.post("https://rahulshetty.com/gq/graphql")
		.then()
			.log().all()
			.extract().response().asString();
	System.out.println(resp);
	}

}
