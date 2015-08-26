package net.org.nercita.mushroom.wx.dao;

import java.util.List;

import net.org.nercita.mushroom.dao.BaseDao;
import net.org.nercita.mushroom.wx.bean.AccessToken;

import org.springframework.stereotype.Repository;

@Repository
public class AccessTokenDao extends BaseDao<AccessToken>{
	
	public void addAccessToken(AccessToken accessToken) {
		
		List jList= this.getHibernateTemplate().find("from AccessToken");
		this.getHibernateTemplate().deleteAll(jList);
		this.getHibernateTemplate().save(accessToken);
	}
	public AccessToken getAccessToken() {
		
		List<AccessToken> jList= this.getHibernateTemplate().find("from AccessToken");
		return jList.get(0);
	}	
}
