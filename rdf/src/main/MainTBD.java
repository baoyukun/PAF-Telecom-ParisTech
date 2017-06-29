package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;

import parser.Article;
import parser.Author;
import parser.JsonUtils;
import parser.RDFUtils;
import parser.RDFUtilsTDB;

public class MainTBD {
	
	public static void main(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException {
		RDFUtilsTDB rdfUtils = RDFUtilsTDB.getInstance();
		
		System.out.println("Get author information from chercheurstpt.json....");
		List<Author> authors = JsonUtils.parserAuthorsList("res/chercheurstpt.json");
		Dataset dataset = rdfUtils.getDataset();
		// Get all information about author
		for (Author author : authors) {
			dataset.begin(ReadWrite.WRITE);
			rdfUtils.setModle(dataset.getDefaultModel());
			rdfUtils.addAuthor(author);
			dataset.commit();
			dataset.end();
		}
		
		System.out.println("Get article information from paperJson.json...");
		List<Article> articles = JsonUtils.parserArticleList("res/paperJson.json...");
		int count = 0;
		for (Article article : articles) {
			System.out.println(++count + "." + article.getTitle());
			dataset.begin(ReadWrite.WRITE);
			rdfUtils.setModle(dataset.getDefaultModel());
			rdfUtils.addArticle(article,false);
			dataset.commit();
			dataset.end();
		}
		
		System.out.println("Get article information from 18112015.5ans.json...");
		articles = JsonUtils.parserArticleList("res/18112015.5ans.json");
		count = 0;
		for (Article article : articles) {
			System.out.println(++count + "." + article.getTitle());
			dataset.begin(ReadWrite.WRITE);
			rdfUtils.setModle(dataset.getDefaultModel());
			rdfUtils.addArticle(article,true);
			dataset.commit();
			dataset.end();
		}
		
		

	}

}
