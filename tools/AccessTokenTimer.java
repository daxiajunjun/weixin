package net.org.nercita.mushroom.wx.tools;

import java.util.TimerTask;

import net.org.nercita.mushroom.wx.bean.AccessToken;
import net.org.nercita.mushroom.wx.dao.AccessTokenDao;
import net.org.nercita.mushroom.wx.util.WeiXinUtil;

import org.springframework.beans.factory.annotation.Autowired;

public class AccessTokenTimer extends TimerTask{
	
	@Autowired
	private AccessTokenDao atDao;
	
	@Override
	public void run() {
		
		String  appId= "";
		String 	appSecret= "";
		
		AccessToken accessToken = WeiXinUtil.getAccessToken(appId, appSecret);
		atDao.addAccessToken(accessToken);
		
	}
	

}
