package joker.run.data;

import java.io.UnsupportedEncodingException;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonTool {
	public static StringEntity createJson(String[]...strings) {
		StringEntity strEntity = null;
		JSONObject jsonbject = new JSONObject();

		for (String[] string : strings) {
			try { 
				jsonbject.put(string[0], string[1]);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}		
		try {
			strEntity = new StringEntity(jsonbject.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			try { strEntity = new StringEntity("{\"state\", \"json error\"}");} catch (UnsupportedEncodingException e1) {}
		}

		return strEntity;
	}
}
