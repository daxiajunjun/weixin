package net.org.nercita.mushroom.wx.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "JsapiTicket")
public class JsapiTicket {
	
	// 获取到的凭证  
	@Id
	@Column(name = "ticket", unique = true, nullable = false, length = 100)
    private String ticket;  
    // 凭证有效时间，单位：秒  
	@Column(name = "expiresIn", length = 32)
    private int expiresIn;  
	
	@Column(name = "errcode", length = 32)
    private String  errcode;
	
    @Column(name = "errmsg", length = 32)
    private String  errmsg;
    
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
    
}
