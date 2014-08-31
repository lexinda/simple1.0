package com.lexindasoft.model.weixin;

public class ResultModel {

	private String access_token;
	private int expiress_in;
	private int errcode;
	private String errmsg;
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	
	public int getExpiress_in() {
		return expiress_in;
	}
	public void setExpiress_in(int expiress_in) {
		this.expiress_in = expiress_in;
	}
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
}
