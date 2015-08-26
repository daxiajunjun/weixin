package net.org.nercita.mushroom.wx.service;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.org.nercita.mushroom.wx.dao.AccessTokenDao;
import net.org.nercita.mushroom.wx.tools.ParseLocation;
import net.org.nercita.mushroom.wx.util.WeiXinUtil;

@Service
public class WXJSService {
	
	@Autowired
	AccessTokenDao acDao;
	
	public String parseLocation(String latitude, String longitude) {
		return ParseLocation.getLocation(latitude, longitude);
	}

	public String downloadMediaFromWX(String media_id) {
		
		try {
			WeiXinUtil.downWeiXinMedia(acDao.getAccessToken(), media_id);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
