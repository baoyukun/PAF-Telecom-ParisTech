package parser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
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
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT , true);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT , true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    	mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        
        
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
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT , true);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT , true);
    	mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    	
    	//mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
       	
    	JsonNode root = mapper.readTree(file).findValue("publis");
    	if(root == null) root = mapper.readTree(file).findValue("articles");
    	
    	
    	return  mapper.readValue(root.traverse(), new TypeReference<List<Article>>(){} );
    }
    
    
    public static Author parserAuthor(String path) throws JsonProcessingException, IOException{
    	File file = new File(path);

    	ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT , true);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT , true);
    	mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    	
    	//mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
       	
    	JsonNode root = mapper.readTree(file);
    	
    	return  mapper.readValue(root.traverse(), Author.class );
    }
    
    
    public static void escapeHTML(String src, String dst) throws IOException{
    	File srcFile = new File(src);
    	File destFile = new File(dst);
    	if(!destFile.exists()){
    		destFile.getParentFile().mkdirs();
    		destFile.createNewFile();
    	}
    	
    	try(FileInputStream fi = new FileInputStream(srcFile);
    		InputStreamReader ir= new InputStreamReader(fi, Charset.forName("utf-8"));
    		BufferedReader in = new BufferedReader(ir);
    		FileOutputStream fo = new FileOutputStream(destFile);
    		OutputStreamWriter or = new OutputStreamWriter(fo, Charset.forName("utf-8"));
    		BufferedWriter out = new BufferedWriter(or)){
    		
    		String inputLine = null;
    		String outputLine = null;
    		
    		while((inputLine =in.readLine()) != null){
    			outputLine = StringEscapeUtils.unescapeXml(inputLine);
    			outputLine = StringEscapeUtils.escapeJson(inputLine);
    			out.write(outputLine);
    			out.newLine();
    		}
    		
    		out.flush();
    		
    	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    }
    
}