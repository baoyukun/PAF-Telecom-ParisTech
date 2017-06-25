package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.http.auth.AUTH;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import jdk.nashorn.internal.ir.annotations.Ignore;
import parser.Article;
import parser.Author;
import parser.JsonParser;
import parser.RDFUtils;

public class RDFUtilsTest {

	@Ignore
	public void testGetInstance() throws IOException {
		List<Author> authors = JsonParser.parserAuthorsList("res/chercheurstpt.json");
		
		
		RDFUtils rdfUtils = RDFUtils.getInstance();
		for (Author author : authors) {
			rdfUtils.addAuthor(author);
		}
	}
	
	@Test
	public void testAddArticle() throws JsonProcessingException, IOException, IllegalArgumentException, IllegalAccessException{
		List<Author> authors = JsonParser.parserAuthorsList("res/chercheurstpt.json");
		List<Article> articles = JsonParser.parserArticleList("res/18112015.5ans.json");
		
		RDFUtils rdfUtils = RDFUtils.getInstance();
		for (Author author : authors) {
			rdfUtils.addAuthor(author);
		}
		
		for (Article article : articles) {
			rdfUtils.addArticle(article);
		}
		
	
		
		rdfUtils.update();
	}
	
	@Ignore
	public void testCheckArticle() throws IllegalArgumentException, IllegalAccessException, FileNotFoundException, IOException{
		List<Article> articles = JsonParser.parserArticleList("res/18112015.5ans.json");
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

}
