package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import javax.activation.UnsupportedDataTypeException;
import javax.print.attribute.standard.RequestingUserName;
import javax.swing.border.TitledBorder;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Seq;
import org.apache.jena.riot.RiotException;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.util.FileManager;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;
import com.sun.org.apache.xpath.internal.compiler.Keywords;
import com.sun.prism.shader.FillPgram_LinearGradient_REFLECT_AlphaTest_Loader;
import com.sun.webkit.graphics.Ref;

import jena.turtle;
import main.PAF;
import sun.net.www.content.audio.x_aiff;


public class RDFUtils{
	
	public static RDFUtils rdf = null;
	private static final String rdfFile = "res/database.rdf";
	private static String TDB_DIR= "res/Dataset";
	

	private Model model = null;
	private Property Author = null;
	private Property group = null;
	private Property Departement = null;
	
	
	private RDFUtils() throws IOException{			
		model =  ModelFactory.createDefaultModel();
		model.setNsPrefix("foaf", FOAF.NS);
		model.setNsPrefix("paf", PAF.NS);
		
		File file = new File(rdfFile);
		if(!file.getParentFile().isDirectory()) file.getParentFile().mkdirs();
		if(!file.exists()){
			file.createNewFile();
			update();
		}
		 
		InputStream in = FileManager.get().open(file.getAbsolutePath());		
		model.read(file.getAbsolutePath(), "RDFXML") ;
	}

	
	/** Update the rdf file. This method will update the data in the rdf file**/
	public void update() throws FileNotFoundException, IOException{
		try(OutputStreamWriter or = new OutputStreamWriter(new FileOutputStream(rdfFile) , Charset.forName("UTF-8"));){
			model.write(or,"RDFXML");
		}
	}
	
	
	public static RDFUtils getInstance() throws IOException{
		if(rdf == null) rdf = new RDFUtils();
		return rdf;
	}
	
	
	/** Add author resource denoted by argument in rdf model.
	 * First of all, it will check whether the author has existed in rdf.
	 * For now, we judge it by the family name and given name.
	 *  @param author 
	 *  @return returns true if the author don't exists in rdf false if not. 
	 * **/
	public Resource addAuthor(Author author) throws FileNotFoundException, IOException{
		if(author == null) throw new NullPointerException("Can not add a null author");
		
		Resource authorRes = checkAuthor(author.getFamilyName().replaceAll("\\W+", "_")
				, author.getGivenName().replaceAll("\\W+", "_"));
		if(authorRes == null){
		authorRes = 
					this.model.createResource(PAF.AUTHOR
							+ author.getFamilyName().replaceAll("\\W+", "_") + "_"
							+ author.getGivenName().replaceAll("\\W+", "_"));
		}
		
			
		authorRes.addProperty(FOAF.family_name, author.getFamilyName());
		authorRes.addProperty(FOAF.givenname, author.getGivenName());
		
		if(author.getGroupe() != null){
			authorRes.addProperty(PAF.GROUP, author.getGroupe());
		}
		if(author.getAffiliation() != null){
			authorRes.addProperty(PAF.ORGANISATION, author.getAffiliation());
		}
		if(author.getDepartement()!= null){
			authorRes.addProperty(PAF.DEPARTEMENT, author.getDepartement());
		}
		
		return authorRes;
	}

	/** This method will check whether the author resouce specified by arguments.
	 * 
	 * @param	family_name the family name of author
	 * @param	given_name the given name of author
	 * @return	the resource representing the author if this author has exited in rdf.
	 * 			Returns null if not
	 *  */
	public Resource checkAuthor(String family_name, String given_name){
		String queryString = "SELECT ?x ?fname "
				+ "WHERE {?x  <" + FOAF.family_name.getURI() +">  \"" + family_name + "\". "
						+ "?x  <" + FOAF.givenname.getURI() +">  ?g."
								+ "FILTER regex(?g,\"" + given_name.replaceAll("\\.", "") +"\")}";
		Query query = QueryFactory.create(queryString);
		 try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
			    ResultSet results = qexec.execSelect() ;
			    if(results.hasNext())
			    	return results.nextSolution().getResource("x");
			    else{
			    	return null;
			    }
			  }
	}
	
	/** Add article resource denoted by argument in rdf model.
	 *  @param article 
	 *  @return returns true if the article don't exists in rdf false if not. 
	 * **/
	public void addArticle(Article article) throws IllegalArgumentException, IllegalAccessException, FileNotFoundException, IOException{
		Resource articleRes = 
				this.model.createResource(PAF.ARTICLE + article.getTitle().replaceAll("\\W+", "_") + "_" + article.getId().trim());
		//Check if this resource has existed.

		// Add simple property
		Field[] fields = article.getClass().getDeclaredFields();
		for (Field field : fields) {
			if(field.getType().equals(List.class)) continue;
			field.setAccessible(true);
			Property property = model.createProperty(PAF.NS + field.getName());
			if(field.get(article) != null){
				articleRes.addProperty(property, (String)field.get(article));
			}				
		}
		
		//Add author list
		for(Author author: article.getAuthorsList()){
			Resource authorRes = checkAuthor(author.getFamilyName(), author.getGivenName());
			if(authorRes == null) authorRes = addAuthor(author);
			
			model.add(articleRes,PAF.WRITTENBY,authorRes);
		}
		try{
		}catch (RiotException e) {
			System.out.println(article.getTitle());
		}
		
		
		//Add KeyWord		
		List<String> keywords = article.getKeywords();
		if(keywords != null){
			for(String keyword: article.getKeywords()){
				articleRes.addProperty(PAF.HASKEYWORD, keyword.trim().replace("\\s+", " ").toLowerCase());
			}
		}
		
	}
	
	/** This method will check whether the article resource specified by arguments.
	 * 
	 * @param	article
	 * @return	the resource representing the author if this author has exited in rdf.
	 * 			Returns null if not
	 *  */
	public Resource checkArticle(Article article){
		String queryString = "SELECT ?x "
					+ "WHERE {?x  <" + PAF.NS + "title" +">  \"" + article.getTitle() + "\"}";
		System.out.println(queryString);	
		Query query = QueryFactory.create(queryString);
		try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
			ResultSet results = qexec.execSelect() ;
			if(results.hasNext()){
				return results.nextSolution().getResource("x");
			}else{
				return null;
			}
		}
	}

	
	/** This method use to test programme, don't use it**/
	@Deprecated
	public Dataset getModel(){
		return this.model; 
	}

	
	
	
}
