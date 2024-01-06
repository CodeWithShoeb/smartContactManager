package com.smart.helper;


public class Message {//for manage the messages
	
	private String content;//like messages
	private String type;//like success or not
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Message(String content, String type) {
		super();
		this.content = content;
		this.type = type;
	}
	
}
