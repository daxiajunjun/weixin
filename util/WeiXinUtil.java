package net.org.nercita.mushroom.wx.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;

import net.org.nercita.mushroom.tools.HttpClientTool;
import net.org.nercita.mushroom.util.HttpRequest;
import net.org.nercita.mushroom.wx.bean.AccessToken;
import net.org.nercita.mushroom.wx.bean.JsapiTicket;
import net.org.nercita.mushroom.wx.bean.Menu;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeiXinUtil {
private static Logger log = LoggerFactory.getLogger(WeiXinMessageUtil.class);  
	
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";  
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";  
	public static String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	public static String down_media_url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	
	
	/** 
	 * 创建菜单 
	 *  
	 * @param menu 菜单实例 
	 * @param accessToken 有效的access_token 
	 * @return 0表示成功，其他值表示失败 
	 */  
	public static int createMenu(Menu menu, String accessToken) {  
	    int result = 0;  
	  
	    // 拼装创建菜单的url  
	    String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);  
	    System.out.println(accessToken);
	    // 将菜单对象转换成json字符串  
	    String jsonMenu = JSONObject.fromObject(menu).toString();  
	    // 调用接口创建菜单  
	    JSONObject jsonObject = HttpRequest.httpRequest(url, "POST", jsonMenu);  
	  
	    if (null != jsonObject) {  
	        if (0 != jsonObject.getInt("errcode")) {  
	            result = jsonObject.getInt("errcode");  
	            log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
	        }  
	    }  
	  
	    return result;  
	}  
	/**
	 * sha1加密算法
	 * @param key需要加密的字符串
	 * @return 加密后的结果
	 */
	public static String sha1(String key) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.update(key.getBytes());
			String pwd = new BigInteger(1, md.digest()).toString(16);
			return pwd;
		} catch (Exception e) {
			e.printStackTrace();
			return key;
		}
	}
	
	public static AccessToken getAccessToken(String appid, String appsecret) {  
	    AccessToken accessToken = null;  
	  
	    String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);  
	    JSONObject jsonObject = HttpRequest.httpRequest(requestUrl, "GET", null);  
	    // 如果请求成功  
	    if (null != jsonObject) {  
	        try {  
	            accessToken = new AccessToken();  
	            accessToken.setToken(jsonObject.getString("access_token"));  
	            System.out.println(jsonObject.getString("access_token"));
	            accessToken.setExpiresIn(jsonObject.getInt("expires_in"));  
	        } catch (JSONException e) {  
	            accessToken = null;  
	            // 获取token失败  
	            log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
	        }  
	    }  
	    return accessToken;  
	} 
	
	public static JsapiTicket getJsapiTicket(AccessToken at) {  
		
		JsapiTicket jsapiTicket = null;
	    String requestUrl = jsapi_ticket_url.replace("ACCESS_TOKEN",at.getToken() );
	    JSONObject jsonObject = HttpRequest.httpRequest(requestUrl, "GET", null);  
	    // 如果请求成功  
	    if (null != jsonObject) {  
	        try {  
	        	jsapiTicket = new JsapiTicket();  
	        	jsapiTicket.setTicket(jsonObject.getString("ticket"));  
	        	jsapiTicket.setExpiresIn(jsonObject.getInt("expires_in"));  
	        } catch (JSONException e) {  
	        	jsapiTicket = null;  
	            // 获取token失败  
	            log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
	        }  
	    }  
	    return jsapiTicket;  
	} 
	
	public static void downWeiXinMedia(AccessToken at,String mediaId) throws UnsupportedEncodingException{
		
		String requestUrl = down_media_url.replace("ACCESS_TOKEN",at.getToken()).replace("MEDIA_ID", mediaId);
		System.out.println(requestUrl);
	    String result = HttpClientTool.getHttpClientValue(requestUrl);
	    System.out.println(result);
	}
	
}
