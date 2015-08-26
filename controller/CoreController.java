package net.org.nercita.mushroom.wx.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/coreCheck")
public class CoreController {
	
	static Logger logger = Logger.getLogger(CoreController.class.getName());
	
	/*@Autowired
	private CityService cityService;*/
	
	@RequestMapping()
	public void get(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		//String city = cityService.getCityAreaId("北京");
//		System.err.println(Weather.getWeather(city));
	}

	@RequestMapping(method = RequestMethod.POST)
	public void post(HttpServletRequest request, HttpServletResponse response) {
		// 暂时空着，在这里可处理用户请求
	}
}