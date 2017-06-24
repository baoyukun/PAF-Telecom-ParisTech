package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

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
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.util.FileManager;

import jena.turtle;
import main.PAF;


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

	/** Update the rdf file.**/
	private void update() throws FileNotFoundException, IOException{
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
	public boolean addAuthor(Author author) throws FileNotFoundException, IOException{
		Resource authorRes = checkAuthor(author.getFamilyName(), author.getGivenName());
		if(authorRes == null) return false;
		authorRes = 
					this.model.createResource(PAF.BASE 
							+ author.getFamilyName().replaceAll(" ", "") + "_"
							+author.getGivenName().replaceAll(" ", ""));
			
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
		
		update();
		
		return true;
	}

	/** This method will check whether the author resource specified by arguments.
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
		System.out.println(queryString);
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
	
	
	public boolean addArticle(Article article){
		
		 try {
			 Resource articleRes = 
					model.createResource(PAF.ARTICLE + article.getTitle().replaceAll("\\W", "_"));
			
			articleRes.addProperty("", arg1)
			authorRes.addProperty(FOAF.family_name, author.getFamilyName());
			authorRes.addProperty(FOAF.givenname, author.getGivenName());
			
			if(author.getGroupe() != null){
				System.out.println(author.getGroupe());
				authorRes.addProperty(PAF.GROUP, author.getGroupe());
			}
			if(author.getAffiliation() != null){
				System.out.println(author.getDepartement());
				authorRes.addProperty(PAF.ORGANISATION, author.getAffiliation());
			}
			if(author.getDepartement()!= null){
				System.out.println(author.getAffiliation());
				authorRes.addProperty(PAF.DEPARTEMENT, author.getAffiliation());
			}

			
		 } finally { 
		   dataset.end() ; 
		 }
		return false;	
	}
	
	/** This method use to test programme, don't use it**/
	@Deprecated
	public Dataset getModel(){
		return this.model; 
	}
	
	
	
}
