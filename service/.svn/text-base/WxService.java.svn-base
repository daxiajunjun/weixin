package net.org.nercita.mushroom.wx.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.org.nercita.mushroom.tools.HttpClientTool;
import net.org.nercita.mushroom.util.Constants;

import org.springframework.stereotype.Service;

@Service
public class WxService {
	
	public static String  GetCodeRequest = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
			"appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&" +
			"state=STATE#wechat_redirect"; 
	
	
	public void WxLogin() {
		// TODO Auto-generated method stub
		String url = getCodeRequest();
		String value = "";
		try {
			 value = HttpClientTool.getHttpClientValue(url);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		System.out.println(value);
		
	}
	

    public static String getCodeRequest(){ 

        String result = null; 

        GetCodeRequest  = GetCodeRequest.replace("APPID", urlEnodeUTF8(Constants.appId)); 

        GetCodeRequest  = GetCodeRequest.replace("REDIRECT_URI",urlEnodeUTF8(Constants.REDIRECT_URI)); 

        GetCodeRequest = GetCodeRequest.replace("SCOPE", Constants.SCOPE); 

        result = GetCodeRequest; 

        return result; 

    } 


    public static String urlEnodeUTF8(String str){ 

        String result = str; 

        try { 
            result = URLEncoder.encode(str,"UTF-8"); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return result; 

    } 
	
}
