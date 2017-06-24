package main;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class JsonParserTest {


	@Ignore
	public void testParserAuthorsList() throws JsonParseException, JsonMappingException, IOException {
		List<Author> authors = JsonParser.parserAuthorsList("res/chercheurstpt.json");
		for (Author author : authors) {
			System.out.println(author);
		}

	}

	
	@Test
	public void testParserArticleList() throws JsonParseException, JsonMappingException, IOException {
		List<Article> articles = JsonParser.parserArticleList("res/18112015.5ans.json");
		for (Article article : articles) {
			ObjectMapper mapper = new ObjectMapper();
			
			System.out.println(mapper.writeValueAsString(article));
		}
		
		
		System.out.println(articles.size());
	}
}
