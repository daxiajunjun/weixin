package net.org.nercita.mushroom.wx.main;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.org.nercita.mushroom.wx.bean.AccessToken;
import net.org.nercita.mushroom.wx.bean.JsapiTicket;
import net.org.nercita.mushroom.wx.util.WeiXinUtil;

public class SignatureManager {
	
	public static void  main(String args[]) {
		  // 第三方用户唯一凭证  
        String appId = "wxd6e51ca436bd6907";  
        // 第三方用户唯一凭证密钥  
        String appSecret = "f11bd9418290dfeb5e88ee75c8b69b6b";  
  
        // 调用接口获取access_token  
        AccessToken at = WeiXinUtil.getAccessToken(appId, appSecret);
        JsapiTicket jt = WeiXinUtil.getJsapiTicket(at);
       
        String jsapi_ticket=jt.getTicket();
        String url = "http://wangjun5132.6655.la/mushroomWX/loader.html";
        Map<String, String> ret = sign(jsapi_ticket, url);
        for (Map.Entry entry : ret.entrySet()) {
            System.out.println(entry.getKey() + "======== " + entry.getValue());
        }
        System.out.println("signature:  "+ret.get("signature") +  ": timestamp " +ret.get("timestamp"));
        System.out.println(createLinkString(ret));
        
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
