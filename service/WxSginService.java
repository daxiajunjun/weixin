package net.org.nercita.mushroom.wx.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import net.org.nercita.mushroom.wx.bean.JsapiTicket;
import net.org.nercita.mushroom.wx.dao.JsapiTicketDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxSginService {
	
	@Autowired
	private JsapiTicketDao jtDao;
	
	public Map<String, String>  getSignatue(String url){
		

		/*Properties p = new Properties();
		try {
			p.load(new FileInputStream(new File("wx.properties")));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		String url = p.getProperty("url");*/
	    
		JsapiTicket jsapi_ticket = jtDao.getJsapiTicket();
		
	    Map<String, String> ret = sign(jsapi_ticket.getTicket(), url);
	    ret.put("appid", "wxd6e51ca436bd6907");
	    
	    System.out.println("signature:  "+ret.get("signature") +  ": timestamp " +ret.get("timestamp"));
	    System.out.println(createLinkString(ret));
	        
	     return ret;
	}
	
	//对所有待签名参数按照字段名的ASCII 码从小到大排序（字典序）后
    /** 
    * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
    * @param params 需要排序并参与字符拼接的参数组
    * @return 拼接后字符串
    */
   public static String createLinkString(Map<String, String> params) {
       List<String> keys = new ArrayList<String>(params.keySet());
       Collections.sort(keys);
       String prestr = "";
       for (int i = 0; i < keys.size(); i++) {
           String key = keys.get(i);
           String value = params.get(key);
           if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
               prestr = prestr + key + "=" + value;
           } else {
               prestr = prestr + key + "=" + value + "&";
           }
       }
       return prestr;
   }
    
   public static Map<String, String> sign(String jsapi_ticket, String url) {
   	
   	
       Map<String, String> ret = new HashMap<String, String>();
       String nonce_str = create_nonce_str();
       String timestamp = create_timestamp();
       String string1;
       String signature = "";
       // 注意这里参数名必须全部小写，且必须有序
       string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
       //System.out.println(string1);
       try {
           MessageDigest crypt = MessageDigest.getInstance("SHA-1");
           crypt.reset();
           crypt.update(string1.getBytes("UTF-8"));
           signature = byteToHex(crypt.digest());
       } catch (NoSuchAlgorithmException e) {
           e.printStackTrace();
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
       }
       ret.put("url", url);
       ret.put("jsapi_ticket", jsapi_ticket);
       ret.put("nonceStr", nonce_str);
       ret.put("timestamp", timestamp);
       ret.put("signature", signature);
       return ret;
   }

   private static String byteToHex(final byte[] hash) {
       Formatter formatter = new Formatter();
       for (byte b : hash) {
           formatter.format("%02x", b);
       }
       String result = formatter.toString();
       formatter.close();
       return result;
   }

   private static String create_nonce_str() {
       return UUID.randomUUID().toString();
   }

   private static String create_timestamp() {
       return Long.toString(System.currentTimeMillis() / 1000);
   }
	
}
