package com.lyplay.sflow.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sflow_oauth")
public class OtherAuthenticate implements Serializable {

	private static final long serialVersionUID = -656706522343811867L;

	@Id
	@Column(length = 20)
	private String id;

	@Column(length = 20, nullable = false)
	private String userId;
	
	@Column(length = 20, nullable = false)
	private String oauthName;
	
	@Column(length = 200, nullable = false)
	private String oauthId;

	
	@Column(length = 200, nullable = false)
	private String oauthAccessToken;
	
	@Column(length = 20, nullable = false)
	private String oauthExpires;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOauthName() {
		return oauthName;
	}

	public void setOauthName(String oauthName) {
		this.oauthName = oauthName;
	}

	public String getOauthId() {
		return oauthId;
	}

	public void setOauthId(String oauthId) {
		this.oauthId = oauthId;
	}

	public String getOauthAccessToken() {
		return oauthAccessToken;
	}

	public void setOauthAccessToken(String oauthAccessToken) {
		this.oauthAccessToken = oauthAccessToken;
	}

	public String getOauthExpires() {
		return oauthExpires;
	}

	public void setOauthExpires(String oauthExpires) {
		this.oauthExpires = oauthExpires;
	}

	
}
