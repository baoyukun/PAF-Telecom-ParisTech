package main;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.junit.Test;

public class RDFUtilsTest {

	@Test
	public void testGetInstance() throws IOException {
		List<Author> authors = JsonParser.parserAuthorsList("chercheurstpt.json");
		
		RDFUtils rdfUtils = RDFUtils.getInstance();
		for (Author author : authors) {
			rdfUtils.addAuthor(author);
		}
		
		Dataset dataset = rdfUtils.getDataSet();
		dataset.begin(ReadWrite.READ);
		Model model = dataset.getDefaultModel();
		model.write(System.out);
		StmtIterator sm = model.listStatements();
		while (sm.hasNext()) {
			System.out.println(sm.next());
		}
		System.out.println(model.getResource("http://givingsense.eu/sembib/data/tpt/paf2017/author#ZHUFangda").getProperty(FOAF.family_name));
		dataset.end();
	}

}
