package parser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QueryParseException;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;

import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.tdb.TDBFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import main.PAF;


public class RDFUtilsTDB{
	static public Logger log = Logger.getLogger(RDFUtilsTDB.class);
	 
	public static RDFUtilsTDB rdf = null;
	private static final String DATABASE = "res/database";
	private Model model = null;
	private Dataset dataset;
	
	
	private RDFUtilsTDB() throws IOException{	
		log.setLevel(Level.INFO);
		model =  ModelFactory.createDefaultModel();
		model.setNsPrefix("foaf", FOAF.NS);
		model.setNsPrefix("paf", PAF.NS);
		dataset = TDBFactory.createDataset(DATABASE);
	}


	public static RDFUtilsTDB getInstance() throws IOException{
		if(rdf == null) rdf = new RDFUtilsTDB();
		return rdf;
	}
	
	
	/** Add author resource denoted by argument in rdf model.
	 * First of all, it will check whether the author has existed in rdf.
	 * For now, we judge it by the family name and given name.
	 *  @param author 
	 *  @return returns true if the author don't exists in rdf false if not. 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * **/
	public Resource addAuthor(Author author) throws FileNotFoundException, IOException, IllegalArgumentException, IllegalAccessException{
		if(author == null) throw new NullPointerException("Can not add a null author");

		Resource authorRes = checkAuthor(author);
		
		dataset.begin(ReadWrite.WRITE);
		this.model = dataset.getDefaultModel();
		
		if(authorRes == null){
			authorRes = model 
				.createResource(PAF.AUTHOR + author.getFamilyName().replaceAll("\\W+", "_") + "_"
						+ author.getGivenName().replaceAll("\\W+", "_"));
		}
		
		authorRes.addProperty(FOAF.family_name, author.getFamilyName().toLowerCase());
		authorRes.addProperty(FOAF.givenname, author.getGivenName().toLowerCase());
		if(author.getMiddleName() != null && author.getMiddleName().equals(""))
			authorRes.addProperty(PAF.MIDDLENAME, author.getMiddleName().toLowerCase());

		
		Field[] fields = author.getClass().getDeclaredFields();
		for (Field field : fields) {
			String data = null;
			if(field.getType().equals(String.class)){
				field.setAccessible(true);
				data = (String)field.get(author);
				if(data == null) continue;
				data = data.trim().replaceAll("\\s+", " ");
				if(!data.equals("")){
					Property property = model.createProperty(PAF.NS + field.getName());
					authorRes.addProperty(property, data.toLowerCase());
				}
			}
		}
		
			
		fields = author.getAffiliation().getClass().getDeclaredFields();
		
		Set<String> set = null;
		for (Field field : fields) {
			field.setAccessible(true);
			set = (Set<String>)field.get(author.getAffiliation());
			if(set == null || set.size() == 0) continue;
			for (String data : set) {		
				data = data.trim().replaceAll("\\s+", " ");
				if(data.equals("")) continue;
				Property property = model.createProperty(PAF.NS + field.getName());
				authorRes.addProperty(property, data.toLowerCase());
			}
		}
		
		dataset.commit();
		dataset.end();
		return authorRes;
	}

	/** This method will check whether the author resouce specified by arguments.
	 * 
	 * @param	author
	 * @return	the resource representing the author if this author has exited in rdf.
	 * 			Returns null if not
	 *  */
	public Resource checkAuthor(Author author){
		dataset.begin(ReadWrite.WRITE);
		model = dataset.getDefaultModel();
		
		if(author.getGivenName().equals("")) return null;
		String queryString = "SELECT ?x ?g "
				+ "WHERE {?x  <" + FOAF.family_name.getURI() +">  \"" + author.getFamilyName().toLowerCase() + "\". "
						+ "?x  <" + FOAF.givenname.getURI() +">  ?g."
								+ "FILTER regex(?g,\"^" + author.getGivenName().charAt(0) +"\")}";
		Query query = QueryFactory.create(queryString);
		 try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
			    ResultSet results = qexec.execSelect() ;

				if(results.hasNext()){
					 QuerySolution sln = results.nextSolution();
				    String givienName = sln.getLiteral("g").getString();
				    if(author.getGivenName().length() > givienName.length()){
				    	model.remove(sln.getResource("x").getProperty(FOAF.givenname));
				    	sln.getResource("x").addProperty(FOAF.givenname, author.getGivenName().toLowerCase());
				    	log.warn("Change Given name:" + givienName  + "--->" + author.getGivenName());
				    }
				   dataset.commit();
				    return sln.getResource("x");
				}else{
					return null;
				}
		 }finally {
			dataset.end();
		}
			    	
	}
	
	
	/** Add article resource denoted by argument in rdf model.
	 *  @param article 
	 *  @return returns the resource specified by argument. 
	 * **/
	public Resource addArticle(Article article, boolean check) throws IllegalArgumentException, IllegalAccessException, FileNotFoundException, IOException{
		//Check if thisResou resource has existed.
		Resource articleRes = null;
		if(check == true) articleRes = checkArticle(article);
		
		dataset.begin(ReadWrite.READ);
		model = dataset.getDefaultModel();
		
		// Create article resource 
		if(articleRes == null){
			String uri = article.getUri();
			if(uri == null || uri.equals("")) uri = PAF.ARTICLE + article.getTitle().replaceAll("\\W","_");
			articleRes = 
					this.model.createResource(uri);
		}
		
		// Add simple property
		Field[] fields = article.getClass().getDeclaredFields();

		for (Field field : fields) {
			if(!field.getType().equals(String.class)) continue;
			field.setAccessible(true);
			Property property = model.createProperty(PAF.NS + field.getName());
			if(field.get(article) != null){
				articleRes.addProperty(property, (String)field.get(article));
			}				
		}

		// add scopus
		if(article.getScopus() != null){
			if(article.getScopus().getAggregationType() != null)
				articleRes.addProperty(PAF.AggregationType, article.getScopus().getAggregationType());
			if(article.getScopus().getPaperScopusId() != null)
				articleRes.addProperty(PAF.PaperScopusId, article.getScopus().getPaperScopusId());
			if(article.getScopus().getPublicationName() != null)
				articleRes.addProperty(PAF.PublicationName, article.getScopus().getPublicationName());
		}
		

		//Add KeyWord		
		List<String> keywords = article.getKeywords();
		if(keywords != null){
			for(String keyword: article.getKeywords()){
				articleRes.addProperty(PAF.HASKEYWORD, keyword.trim().replace("\\s+", " "));
			}
		}
		
		
		//Add citationList
		if(article.getCitationList()!= null)
		{		
			for(Article citation: article.getCitationList()){
				Resource citationRes = addArticle(citation,true);			
				model.add(articleRes,PAF.CITATION, citationRes);
			}
		}
		
		dataset.commit();
		dataset.end();
		
		//Add author list
		for(Author author: article.getAuthorsList()){
			Resource authorRes = checkAuthor(author);
			if(authorRes == null) authorRes = addAuthor(author);
			
			dataset.begin(ReadWrite.WRITE);
			model = dataset.getDefaultModel();
			model.add(articleRes,PAF.WRITTENBY,authorRes);
			dataset.commit();
			dataset.end();
		}
				
		return articleRes;
	}
	
	/** This method will check whether the article resource specified by arguments.
	 * 
	 * @param	article
	 * @return	the resource representing the author if this author has exited in rdf.
	 * 			Returns null if not
	 *  */
	public Resource checkArticle(Article article){
		if(article.getTitle().equals("")) return null;
		String queryString = "SELECT ?x "
					+ "WHERE {?x  <" + PAF.NS + "title" +">  \"" + article.getTitle().toLowerCase()
					+ "\"}";	
		QueryExecution qexec = null;
		dataset.begin(ReadWrite.READ);
		try{
			Query query = QueryFactory.create(queryString);
			qexec = QueryExecutionFactory.create(query, model);
			ResultSet results = qexec.execSelect() ;
			if(results.hasNext()){
				return results.nextSolution().getResource("x");
			}else{
				return null;
			}
		}catch (QueryParseException e) {
			e.printStackTrace();
			System.err.println(queryString);
			return  null;
		}finally {
			try{
				qexec.close();
				dataset.end();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/** Remove the resource donated by arguments*/
	public static void deleteResource(Model model, Resource resource) {
	    // remove statements where resource is subject
	    model.removeAll(resource, null, (RDFNode) null);
	    // remove statements where resource is object
	    model.removeAll(null, null, resource);
	}
	

}
