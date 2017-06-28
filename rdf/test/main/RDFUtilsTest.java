package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import parser.Article;
import parser.Author;
import parser.JsonUtils;
import parser.RDFUtils;

public class RDFUtilsTest {

	@Ignore
	public void testGetInstance() throws IOException, IllegalArgumentException, IllegalAccessException {
		List<Author> authors = JsonUtils.parserAuthorsList("res/chercheurstpt.json");

		RDFUtils rdfUtils = RDFUtils.getInstance();
		for (Author author : authors) {
			rdfUtils.addAuthor(author);
		}
	}
	
	@Ignore
	public void testAddArticle() throws JsonProcessingException, IOException, IllegalArgumentException, IllegalAccessException{
		List<Author> authors = JsonUtils.parserAuthorsList("res/chercheurstpt.json");
		List<Article> articles = JsonUtils.parserArticleList("res/18112015.5ans.json");
		
		RDFUtils rdfUtils = RDFUtils.getInstance();
//		for (Author author : authors) {
//			rdfUtils.addAuthor(author);
//		}
		
		int count = 0;
		for (Article article : articles) {
			System.out.println(++count + "." + article.getTitle());
			rdfUtils.addArticle(article,false);
		}
		
	
		
		rdfUtils.update();
	}
	
	@Ignore
	public void testCheckArticle() throws IllegalArgumentException, IllegalAccessException, FileNotFoundException, IOException{
		List<Article> articles = JsonUtils.parserArticleList("res/18112015.5ans.json");
		RDFUtils rdfUtils = RDFUtils.getInstance();
		System.out.println(rdfUtils.checkArticle(articles.get(0)));
	}
	
	@Ignore
	public void testReflect() throws ClassNotFoundException{
		Class class1 = Class.forName("parser.Article");
//		for(Field field: class1.getDeclaredFields()){
//			System.out.println(field.getType());
//			System.out.println(field.getModifiers());
//			System.out.println(class1.getMethods()[field.getModifiers()]);
//			System.out.println(field.getType().equals(List.class));
//		}
		
		String xString = " I love you";
		System.out.println(xString.trim().replaceAll("\\s+", "_"));
		
		
	}
	
	@Test
	public void testTotal() throws JsonParseException, JsonMappingException, IOException, IllegalArgumentException, IllegalAccessException{
		RDFUtils rdfUtils = RDFUtils.getInstance();
		
		System.out.println("Get author information from chercheurstpt.json....");
		List<Author> authors = JsonUtils.parserAuthorsList("res/chercheurstpt.json");
		// Get all information about author
		for (Author author : authors) {
			rdfUtils.addAuthor(author);
		}
		
		System.out.println("Get article information from paperJson.json...");
		List<Article> articles = JsonUtils.parserArticleList("res/paperJson.json");
		int count = 0;
		for (Article article : articles) {
			System.out.println(++count + "." + article.getTitle());
			rdfUtils.addArticle(article,false);
		}
		
		System.out.println("Get article information from 18112015.5ans.json...");
		articles = JsonUtils.parserArticleList("res/18112015.5ans.json");
		count = 0;
		for (Article article : articles) {
			System.out.println(++count + "." + article.getTitle());
			rdfUtils.addArticle(article,true);
		}
		
		rdfUtils.update();
		
		
	}

}
