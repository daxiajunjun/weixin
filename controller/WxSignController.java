package net.org.nercita.mushroom.wx.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.org.nercita.mushroom.controller.BaseController;
import net.org.nercita.mushroom.wx.service.WxSginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WxSignController extends BaseController{
	
	@Autowired
	private WxSginService wxSginService;
	
	@RequestMapping("getJSParam")
	@ResponseBody
    public Object getJSParam(HttpServletResponse response,HttpServletRequest request) {
		System.err.println("我来取值了");
		  // 第三方用户唯一凭证  
		String url = request.getParameter("url");
		
		System.out.println(url);
		
		Map<String, String> ret =  wxSginService.getSignatue(url);
        
        /*return "({\"url\":\""+ret.get("url")+"\",\"jsapi_ticket\":\""+ret.get("jsapi_ticket")+"\"" +
        ",\"nonceStr\":\""+ret.get("nonceStr")+"\",\"appid\":\""+ret.get("appid")+"\""+
        ",\"timestamp\":\""+ret.get("timestamp")+"\",\"signature\":\""+ret.get("signature")+"\"})";  */
		
		return ret;
    };
 
    
}
