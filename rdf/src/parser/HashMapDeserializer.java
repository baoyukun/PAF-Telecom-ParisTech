package parser;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HashMapDeserializer extends JsonDeserializer<Map<String, String>>{  
    @Override  
    public Map<String, String> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {  
    	ObjectMapper mapper = new ObjectMapper();
    	TreeNode node = mapper.readTree(jp);
    	Map<String,String> map = mapper.convertValue(node, new TypeReference<HashMap<String, String>>(){});
		return map; 
    }
 
}  
