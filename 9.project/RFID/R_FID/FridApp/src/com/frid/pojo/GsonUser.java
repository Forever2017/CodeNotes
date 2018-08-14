package com.frid.pojo;

/**登录时返回用户信息*/
public class GsonUser extends GsonState {
	/*{
	    "token": "1AF6CF8F9422411FA0086FFDB351AB27",
	    "tokenExpireTime": "2018-03-20T13:51:46.077",
	    "refreshToken": "A8D20F62362B43A4843E7F9A0D9CD0EC",
	    "refreshTokenExpireTime": "2018-04-19T13:51:46.077",
	    "permissions": []
	}*/
	
	private String token;
	private String tokenExpireTime;
	private String refreshToken;
	private String refreshTokenExpireTime;
	private String[] permissions;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTokenExpireTime() {
		return tokenExpireTime;
	}
	public void setTokenExpireTime(String tokenExpireTime) {
		this.tokenExpireTime = tokenExpireTime;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getRefreshTokenExpireTime() {
		return refreshTokenExpireTime;
	}
	public void setRefreshTokenExpireTime(String refreshTokenExpireTime) {
		this.refreshTokenExpireTime = refreshTokenExpireTime;
	}
	public String[] getPermissions() {
		return permissions;
	}
	public void setPermissions(String[] permissions) {
		this.permissions = permissions;
	}
	
}
