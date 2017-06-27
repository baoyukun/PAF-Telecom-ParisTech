package parser;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.jena.sparql.expr.E_Add;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ListDeserializer extends JsonDeserializer<List<Author>>{  
    @Override  
    public List<Author> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {  
    	ObjectMapper mapper = new ObjectMapper();
    	TreeNode node = mapper.readTree(jp);
    	List<Author> list;
    	if(node.isArray()) 	list = mapper.convertValue(node, new TypeReference<ArrayList<Author>>(){});
    	else{
    		
    		list = new ArrayList<Author>();
    		Author e = mapper.convertValue(node, new TypeReference<Author>(){});
    		list.add(e);
    	}
    	
    	return list;
    }
 
}  
