package parser;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by zhufa on 22/06/2017.
 */
public class JsonUtils {

    /** This method is used to deserialize the author list into a ArrayList
     * @param path the path of json file
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException **/
    public static List<Author> parserAuthorsList(String path) throws JsonParseException, JsonMappingException, IOException{
    	
    	List<Author> authorList = new ArrayList<>();
        File file = new File(path);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        
         JsonNode root = mapper.readTree(file);
         // Parcourir tous les departement
         for (JsonNode departement : root) {
        	 for (JsonNode  group: departement.findValue("groups")) {
        		 for (JsonNode people : group.findValue("people")) {
					Author author = mapper.readValue(people.traverse(), Author.class);
					author.setDepartement(departement.findValue("name").asText());
					author.setGroupe(group.findValue("name").asText());
 					author.setInstitution("Telecom P"
 							+ "aristech");
 					authorList.add(author);
				}
			}
		}
         return authorList;
    }
    
    /** This method is used to deserialize the article list into a ArrayList
     * @throws IOException 
     * @throws JsonProcessingException **/
    public static List<Article> parserArticleList(String path) throws JsonProcessingException, IOException{
    	File file = new File(path);

    	ObjectMapper mapper = new ObjectMapper();
    	mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS,true);
    	
    	//mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
       	
    	JsonNode root = mapper.readTree(file).findValue("publis");
    	if(root == null) root = mapper.readTree(file).findValue("articles");
    	
    	return  mapper.readValue(root.traverse(), new TypeReference<List<Article>>(){} );
    }
    
    
    public static Author parserAuthor(String path) throws JsonProcessingException, IOException{
    	File file = new File(path);

    	ObjectMapper mapper = new ObjectMapper();
    	mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS,true);
    	
    	//mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
       	
    	JsonNode root = mapper.readTree(file);
    	
    	return  mapper.readValue(root.traverse(), Author.class );
    }
    
}