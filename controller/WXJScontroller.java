package net.org.nercita.mushroom.wx.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.org.nercita.mushroom.controller.BaseController;
import net.org.nercita.mushroom.wx.service.WXJSService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WXJScontroller extends BaseController{
	
	
	@Autowired
	private WXJSService jsService;
	
	@RequestMapping("parseLocation")
	@ResponseBody
	public void parseLocation(@RequestParam("latitude") String latitude,@RequestParam("longitude") String longitude,HttpServletResponse response){
		System.out.println(latitude+"----------"+longitude);
		
		returnWithString(response, jsService.parseLocation(latitude, longitude));
	}
	
	@RequestMapping("downloadMediaFromWX")
	@ResponseBody
	public String downloadMediaFromWX(@RequestParam("media_id") String media_id){
		System.out.println(media_id);
		return jsService.downloadMediaFromWX(media_id);
	}
}
