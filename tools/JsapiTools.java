package net.org.nercita.mushroom.wx.tools;

import java.util.TimerTask;

import net.org.nercita.mushroom.wx.bean.AccessToken;
import net.org.nercita.mushroom.wx.bean.JsapiTicket;
import net.org.nercita.mushroom.wx.dao.AccessTokenDao;
import net.org.nercita.mushroom.wx.dao.JsapiTicketDao;
import net.org.nercita.mushroom.wx.util.WeiXinUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("taskjob")
public class JsapiTools extends TimerTask{
	
	@Autowired
	private JsapiTicketDao jTdao;
	@Autowired
	private AccessTokenDao atDao;
	
	@Override
	public void run() {
		
		
		/*Properties p = new Properties();
		try {
			p.load(new FileInputStream(new File("wx.properties")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		String  appId= "wxd6e51ca436bd6907";
		String 	appSecret= "f11bd9418290dfeb5e88ee75c8b69b6b";
		
		AccessToken accessToken = atDao.getAccessToken();
		JsapiTicket jsTicket = WeiXinUtil.getJsapiTicket(accessToken);
		
		jTdao.addJsapiTicket(jsTicket);
	}
	
}
