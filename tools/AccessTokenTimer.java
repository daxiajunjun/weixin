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
		
		String  appId= "wxd6e51ca436bd6907";
		String 	appSecret= "f11bd9418290dfeb5e88ee75c8b69b6b";
		
		AccessToken accessToken = WeiXinUtil.getAccessToken(appId, appSecret);
		atDao.addAccessToken(accessToken);
		
	}
	

}
