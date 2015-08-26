package net.org.nercita.mushroom.wx.tools;

import java.io.UnsupportedEncodingException;

import net.org.nercita.mushroom.tools.HttpClientTool;
import net.sf.json.JSONObject;


public class ParseLocation {
	private static String parse_location_url = "http://lbs.juhe.cn/api/getaddressbylngb?lngx=longitude&lngy=latitude";
	
	public static String getLocation(String latitude,String longitude){
		
		if(latitude != null && longitude != null){
			parse_location_url = parse_location_url.replace("longitude", longitude).replace("latitude", latitude);
		}else{
			return "地址解析错误";
		}
		
		String result = null;
		try {
			result = HttpClientTool.getHttpClientValue(parse_location_url);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		JSONObject jobject = JSONObject.fromObject(result);
		jobject = JSONObject.fromObject(jobject.get("row"));
		jobject = JSONObject.fromObject(jobject.get("result"));
		System.out.println(jobject.get("formatted_address").toString());
		return jobject.get("formatted_address").toString();
	}
	
	public static void main(String args[]){
		ParseLocation.getLocation(null, null);
	}
}
