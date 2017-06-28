package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import parser.Article;
import parser.Author;
import parser.JsonUtils;
import parser.RDFUtils;

public class Main {
	
	public static void main(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException {
		RDFUtils rdfUtils = RDFUtils.getInstance();
		
		System.out.println("Get author information from chercheurstpt.json....");
		List<Author> authors = JsonUtils.parserAuthorsList("res/chercheurstpt.json");
		// Get all information about author
		for (Author author : authors) {
			rdfUtils.addAuthor(author);
		}
		
		System.out.println("Get article information from paperJsonNew.json...");
		List<Article> articles = JsonUtils.parserArticleList("res/paperJson.json");
		int count = 0;
		for (Article article : articles) {
		//	System.out.println(++count + "." + article.getTitle());
			rdfUtils.addArticle(article,false);
		}
		
		System.out.println("Get article information from 18112015.5ans.json...");
		articles = JsonUtils.parserArticleList("res/18112015.5ans.json");
		count = 0;
		for (Article article : articles) {
		//	System.out.println(++count + "." + article.getTitle());
			rdfUtils.addArticle(article,true);
		}
		
		rdfUtils.update();
	}

}
