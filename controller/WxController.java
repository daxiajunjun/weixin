package net.org.nercita.mushroom.wx.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.org.nercita.mushroom.wx.service.WxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("Wx")
public class WxController {
	
	
	@Autowired
	private WxService wxService;
	
	@RequestMapping(value = "WxLogin")
	@ResponseBody
	public void WxLogin(HttpServletResponse response,HttpServletRequest request){
		wxService.WxLogin();
	}
	
	@RequestMapping(value = "getWxCode")
	@ResponseBody
	public void getWxCode(HttpServletResponse response,HttpServletRequest request){
		wxService.WxLogin();
	}
}
