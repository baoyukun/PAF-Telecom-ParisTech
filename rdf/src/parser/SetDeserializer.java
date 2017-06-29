package parser;


import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SetDeserializer extends JsonDeserializer<Set<String>>{  
    @Override  
    public Set<String> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {  
    	ObjectMapper mapper = new ObjectMapper();
    	TreeNode node = mapper.readTree(jp);
    	
    	Set<String> set = null;
    	if(node.isArray()){
    		 set = mapper.convertValue(node, new TypeReference<HashSet<String>>(){});
    	}else{
    		set = new HashSet<>();
    		String value =  mapper.convertValue(node, String.class);
    		set.add(value);
    	}
    	
    	return set;
		
          
    }
 
}  
