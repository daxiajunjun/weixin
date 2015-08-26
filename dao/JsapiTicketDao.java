package net.org.nercita.mushroom.wx.dao;

import java.util.List;

import net.org.nercita.mushroom.dao.BaseDao;
import net.org.nercita.mushroom.wx.bean.JsapiTicket;

import org.springframework.stereotype.Repository;

@Repository
public class JsapiTicketDao extends BaseDao<JsapiTicket>{

	public void addJsapiTicket(JsapiTicket jsTicket) {
		
		List jList= this.getHibernateTemplate().find("from JsapiTicket");
		this.getHibernateTemplate().deleteAll(jList);
		this.getHibernateTemplate().save(jsTicket);
	}
	
	
	public JsapiTicket getJsapiTicket() {
		
		List<JsapiTicket> jList= this.getHibernateTemplate().find("from JsapiTicket");
		return jList.get(0);
	}	
}
