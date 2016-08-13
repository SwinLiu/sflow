package com.lyplay.sflow.common.mail;


public class MailInfo {

	private String from;// 发件人
	private String to;// 收件人
	private String cc;// 抄送人
	private String subject;// 邮件的主题
	private String content;// 邮件的内容
	//private List<Object> attachList;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	
}
