package net.org.nercita.mushroom.wx.controller;


import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.org.nercita.mushroom.wx.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WeiXinController{
	
	private static final String TOKEN = "muoguquan";
	
	//接收微信公众号接收的消息，处理后再做相应的回复
	@RequestMapping(value="/weixin",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String replyMessage(HttpServletRequest request){
		//仅处理微信服务端发的请求
		if (checkWeixinReques(request)) {
			Map<String, String> requestMap = WeiXinMessageUtil.parseXml(request);
			Message message = WeiXinMessageUtil.mapToMessage(requestMap);
			String replyContent = Reply.WELCOME_CONTENT;
			String type = message.getMsgType();
			if (type.equals(Message.TEXT)) {//仅处理文本回复内容
				String content = message.getContent();//消息内容
				replyContent = content;
				
			}else if (type.equals(Message.EVENT)) {
				String event = message.getEvent();
				if (event.equals("subscribe")) {
					replyContent = "感谢您订阅本公共号,本账号还在建设中,还不能提供服务,请谅解";
				}else if(event.equals("unsubscribe")){
					
				}else if(event.equals("click")){
					
				}
					
			}
			
			//拼装回复消息
			Reply reply = new Reply();
			reply.setToUserName(message.getFromUserName());
			reply.setFromUserName(message.getToUserName());
			reply.setCreateTime(new Date());
			reply.setMsgType(Reply.TEXT);
			reply.setContent(replyContent);
			//将回复消息序列化为xml形式
			String back = WeiXinMessageUtil.replyToXml(reply);
			System.out.println(back);
			return back;
		}else{
			return "error";
		}
	}
	
	//微信公众平台验证url是否有效使用的接口
	@RequestMapping(value="/weixin",method=RequestMethod.GET)
	@ResponseBody
	public String initWeixinURL(HttpServletRequest request){
		String echostr = request.getParameter("echostr");
		if (checkWeixinReques(request) && echostr != null) {
			return echostr;
		}else{
			return "error";
		}
	}
	
	/**
	 * 根据token计算signature验证是否为weixin服务端发送的消息
	 */
	private static boolean checkWeixinReques(HttpServletRequest request){
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		if (signature != null && timestamp != null && nonce != null ) {
			String[] strSet = new String[] { TOKEN, timestamp, nonce };
			java.util.Arrays.sort(strSet);
			String key = "";
			for (String string : strSet) {
				key = key + string;
			}
			String pwd = WeiXinMessageUtil.sha1(key);
			return pwd.equals(signature);
		}else {
			return false;
		}
	}
	
}
