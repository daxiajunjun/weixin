package net.org.nercita.mushroom.wx.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AccessToken")
public class AccessToken {  
    // 获取到的凭证  
	@Id
	@Column(name = "token", unique = true, nullable = false, length =200)
    private String token;  
    // 凭证有效时间，单位：秒
	@Column(name = "expiresIn", length = 32)
    private int expiresIn;  
  
    public String getToken() {  
        return token;  
    }  
  
    public void setToken(String token) {  
        this.token = token;  
    }  
  
    public int getExpiresIn() {  
        return expiresIn;  
    }  
  
    public void setExpiresIn(int expiresIn) {  
        this.expiresIn = expiresIn;  
    }  
}  
