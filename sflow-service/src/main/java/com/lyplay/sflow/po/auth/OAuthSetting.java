package com.lyplay.sflow.po.auth;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 系统可用的OAuth settings
 * @author Swin.Liu
 *
 */

@Table(name = "sflow_oauth_setting")
public class OAuthSetting implements Serializable {

	private static final long serialVersionUID = 4022067087758356896L;
	
	@Id
	@Column(length = 20)
	private String id;
	
	/**
	 * oauth server id
	 */
	@Column(length = 50)
	private String oauthId;
	
	/**
	 * oauth name : QQ, Baidu
	 */
	@Column(length = 50, nullable = false)
	private String oauthName;
	
	/**
	 * client_id
	 */
	@Column(length = 100, nullable = false)
	private String apiKey;
	
	/**
	 * client_secret
	 */
	@Column(length = 100, nullable = false)
	private String secretKey;
	
	/**
	 * redirect url
	 */
	@Column(length = 100, nullable = false)
	private String redirectUri;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOauthId() {
		return oauthId;
	}

	public void setOauthId(String oauthId) {
		this.oauthId = oauthId;
	}

	public String getOauthName() {
		return oauthName;
	}

	public void setOauthName(String oauthName) {
		this.oauthName = oauthName;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
	
}
