package main;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.jena.ext.com.google.common.annotations.VisibleForTesting;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import parser.Article;
import parser.Author;
import parser.JsonUtils;

public class JsonUtilsTest {


	@Ignore
	public void testParserAuthorsList() throws JsonParseException, JsonMappingException, IOException {
		List<Author> authors = JsonUtils.parserAuthorsList("res/chercheurstpt.json");
		for (Author author : authors) {
			//System.out.println(author);
		}

	}

	
	@Ignore
	public void testParserArticleList() throws JsonParseException, JsonMappingException, IOException {
		List<Article> articles = JsonUtils.parserArticleList("res/18112015.5ans.json");
		for (Article article : articles) {
			if(article.getCitedList()!= null){
				ObjectMapper mapper = new ObjectMapper();
				System.out.println("ITIEDlIST:\t" + mapper.writeValueAsString(article.getCitedList()));
			
			}
		}
		
		
		System.out.println(articles.size());
	}
	
	/*** This method will test JsonUtils.parserArcticleList with res/jsonSample.json
	 * @throws IOException 
	 * @throws JsonProcessingException */
	@Ignore
	public void testParserArticleListYUKUN() throws JsonProcessingException, IOException {
		List<Article> articles = JsonUtils.parserArticleList("res/paperJson.json");
		for (Article article : articles) {
			if(article.getCitedList() != null){
				ObjectMapper mapper = new ObjectMapper();
				System.out.println("ITIEDlIST:\t" + mapper.writeValueAsString(article.getCitedList()));
			
			}
		}
	}
	
	@Ignore
	public void test() throws ClassNotFoundException {
		Class<?> class1 = Class.forName("parser.Article");
		Field[] x_aiff = class1.getDeclaredFields();
		
		System.out.println(class1.getName());
		
		for (Field field : x_aiff) {
			field.getType();
			System.out.println(field.getName() + "" + field.getGenericType());
		}
	
	}
	
	@Test
	public void testString() throws IOException{
		String xString = StringEscapeUtils.unescapeXml("&amp;");
		System.out.println("I # $$hahaha$$$ ;".replaceAll("[^a-zA-Z]+", ""));
		System.out.println(xString);
		JsonUtils.escapeHTML("res/paperJson.json", "res/paperJsonNew.json");
	}
}
