package net.org.nercita.mushroom.wx.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;

public class Weather {
	public static String  getWeather(String id){
		String areaid = id;
		String type = "observe";  //observe 实况;alarm  预警;index 指数;forecast1d  常规预报
		
		String short_url = "http://open.weather.com.cn/data/";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		String date = sdf.format(new Date());
		String origin_appid = "d1d93a168c71a8c6";  
		String appid = origin_appid.substring(0,6);
		String public_key = "http://open.weather.com.cn/data/?areaid="+areaid+"&type="
						+type+"&date="+date+"&appid="+origin_appid;
		
		String url = "http://open.weather.com.cn/data/?areaid="+areaid+"&type="
						+type+"&date="+date+"&appid="+appid+"&key=";
		
		
		String private_key = "7f42ac_SmartWeatherAPI_9dd1b70";		
		byte[] key = null;
		
		SecretKeySpec signingKey = new SecretKeySpec(private_key.getBytes(), "HmacSHA1");  
		byte[] rawHmac =null;
        Mac mac;
		try {
			mac = Mac.getInstance("HmacSHA1");
			 mac.init(signingKey);  
		  rawHmac = mac.doFinal(public_key.getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
         key=Base64.encodeBase64(rawHmac);
		
		url+=URLEncoder.encode(new String(key));
		System.err.println(url);
		//String data = SendGET(url);
		//String result = paraJSON(data);
		return "";
	}
	
	 public static String paraJSON(String data){
	    	
	    	Map<String, String> map = new  HashMap<String, String>();
	    	String result ="";
	    	try {
				JSONObject jsonObject =  (JSONObject) new JSONObject(data).get("l");
				
				String time = jsonObject.getString("l7");
				map.put("time", time);
				result = "时间:"+time;
				String temperature = jsonObject.getString("l1");
				map.put("temperature", temperature);
				result+="  温度:"+temperature;
				String humidity = jsonObject.getString("l2");
				map.put("humidity", humidity);
				result +="  湿度"+humidity;
				String windPow = windPower[jsonObject.getInt("l3")];
				map.put("wind", windPow);
				result+="   风力"+windPow;
				String windDir = windDirection[jsonObject.getInt("l4")];
				map.put("windDir", windDir);
				result+="	风向:"+windDir;
				System.err.println(result);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
	    }
	
	public static String SendGET(String urlAddress){
		   String result="";//访问返回结果
		   BufferedReader read=null;//读取访问结果
		    
		   try {
		    //创建url
		    URL realurl=new URL(urlAddress);
		    //打开连接
		    URLConnection connection=realurl.openConnection();
		     // 设置通用的请求属性
		             connection.setRequestProperty("accept", "*/*");
		             connection.setRequestProperty("connection", "Keep-Alive");
		             connection.setRequestProperty("user-agent",
		                     "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		             //建立连接
		             connection.connect();
		          // 获取所有响应头字段
		             Map<String,List<String>> map = connection.getHeaderFields();
		             // 遍历所有的响应头字段，获取到cookies等
		             for (String key : map.keySet()) {
		                 System.out.println(key + "--->" + map.get(key));
		             }
		             // 定义 BufferedReader输入流来读取URL的响应
		             read = new BufferedReader(new InputStreamReader(
		                     connection.getInputStream(),"UTF-8"));
		             String line;//循环读取
		             while ((line = read.readLine()) != null) {
		                 result += line;
		             }
		   } catch (IOException e) {
		    e.printStackTrace();
		   }finally{
		    if(read!=null){//关闭流
		     try {
		      read.close();
		     } catch (IOException e) {
		      e.printStackTrace();
		     }
		    }
		   }
		   
		   return result; 
		 }
	
	public static void main(String args[]) {
		Weather.getWeather("101011000");
	}
	public static String []windPower ={"微风","3-4级","4-5级","5-6级","6-7级","7-8级","8-9级","9-10级","10-11级"};
	public static String []windDirection = {"无持续风向","东北风","东风","东南风","南风","西南风","西风","西北风","北风"};
}
