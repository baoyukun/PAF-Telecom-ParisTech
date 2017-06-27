package main;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jdk.nashorn.internal.ir.annotations.Ignore;
import parser.Article;
import parser.Author;
import parser.JsonParser;

public class JsonParserTest {


	@Ignore
	public void testParserAuthorsList() throws JsonParseException, JsonMappingException, IOException {
		List<Author> authors = JsonParser.parserAuthorsList("res/chercheurstpt.json");
		for (Author author : authors) {
			System.out.println(author);
		}

	}

	
	@Ignore
	public void testParserArticleList() throws JsonParseException, JsonMappingException, IOException {
		List<Article> articles = JsonParser.parserArticleList("res/18112015.5ans.json");
		for (Article article : articles) {
			ObjectMapper mapper = new ObjectMapper();
			
			System.out.println(mapper.writeValueAsString(article));
		}
		
		
		System.out.println(articles.size());
	}
	
	@Test
	public void test() throws ClassNotFoundException {
	Class<?> class1 = Class.forName("parser.Article");
	Field[] x_aiff = class1.getDeclaredFields();
	
	System.out.println(class1.getName());
	
	for (Field field : x_aiff) {
		field.getType();
		System.out.println(field.getName() + "" + field.getGenericType());
	}
	
	
	}
}
