package main;


import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class OntologyTest {
	public static void main(String[] args) {
		OntModel ontModel = ModelFactory.createOntologyModel();
		ontModel.read("res/paf.owl");
		Model base = ontModel.getBaseModel();
		
		//base.write(System.out);
		System.out.println(ontModel.listAllOntProperties());
		
	}
}
