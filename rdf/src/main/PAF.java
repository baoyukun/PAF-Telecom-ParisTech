package main;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class PAF {
    /** <p>The RDF model that holds the vocabulary terms</p> */
    private static Model m_model = ModelFactory.createDefaultModel();
    public static final String NS = "http://givingsense.eu/sembib/data/tpt/paf2017/model#";
    public static final Resource NAMESPACE = m_model.createResource( "http://givingsense.eu/sembib/data/tpt/paf2017/model#" );
    public static final Property GROUP = m_model.createProperty(NS + "group" );
    public static final Property ORGANISATION = m_model.createProperty( NS + "organisation" );
    public static final Property DEPARTEMENT = m_model.createProperty( NS + "departement" );    
    public static final Property WRITTENBY = m_model.createProperty( NS + "written_by" );
    public static final Property MIDDLENAME = m_model.createProperty( NS + "middle_name");
    public static final String BASE = "http://givingsense.eu/sembib/data/tpt/paf2017/"; 
    public static final String AUTHOR= "http://givingsense.eu/sembib/data/tpt/paf2017/author#";
    public static final String ARTICLE= "http://givingsense.eu/sembib/data/tpt/paf2017/article#";
	public static final Property HASKEYWORD =m_model.createProperty( NS + "has_key_word" );
	public static final Property CITATION = m_model.createProperty( NS + "citation" );
	public static final Property AggregationType = m_model.createProperty( NS + "AggregationType" );
	public static final Property PaperScopusId = m_model.createProperty( NS + "PaperScopusId" );
	public static final Property PublicationName = m_model.createProperty( NS + "PublicationName" );
    
}
