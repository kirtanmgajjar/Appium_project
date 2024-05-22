package utilities;

import java.io.File;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONReader {
	private ObjectMapper mapper = new ObjectMapper();;
	private Map<String,Map<String,Object>> allData;
		
		//Class Constructor open read the data from the input stream of the properties file
		public JSONReader(File file) throws Exception
		{
			allData = mapper.readValue(file, new TypeReference<Map<String,Map<String,Object>>>(){});
		}
		
		
		public Map<String,Object> getObjectData(String object)
		{
			return allData.get(object);
		}
		
}
