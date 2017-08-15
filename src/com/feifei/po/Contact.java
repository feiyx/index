package com.feifei.po;

import java.util.Date;




public class Contact {
	
	private String cNo;
	private String cName;
	private String cEmail;
	private String cSubject;
	private String cMessage;
	private Date cCurrentime;
	private String cStatus;
	private String cIP;
	private String cAdress;
	
	public String getcIP() {
		return cIP;
	}
	public void setcIP(String cIP) {
		this.cIP = cIP;
	}
	public String getcAdress() {
		return cAdress;
	}
	public void setcAdress(String cAdress) {
		this.cAdress = cAdress;
	}
	public String getcNo() {
		return cNo;
	}
	public void setcNo(String cNo) {
		this.cNo = cNo;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcEmail() {
		return cEmail;
	}
	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}
	public String getcSubject() {
		return cSubject;
	}
	public void setcSubject(String cSubject) {
		this.cSubject = cSubject;
	}
	public String getcMessage() {
		return cMessage;
	}
	public void setcMessage(String cMessage) {
		this.cMessage = cMessage;
	}
	public Date getcCurrentime() {
		return cCurrentime;
	}
	public void setcCurrentime(Date cCurrentime) {
		this.cCurrentime = cCurrentime;
	}
	public String getcStatus() {
		return cStatus;
	}
	public void setcStatus(String cStatus) {
		this.cStatus = cStatus;
	}
	@Override
	public String toString() {
		return "Contact [cNo=" + cNo + ", cName=" + cName + ", cEmail="
				+ cEmail + ", cSubject=" + cSubject + ", cMessage=" + cMessage
				+ ", cCurrentime=" + cCurrentime + ", cStatus=" + cStatus
				+ ", cIP=" + cIP + ", cAdress=" + cAdress + "]";
	}
	public Contact(String cNo, String cName, String cEmail, String cSubject,
			String cMessage, Date cCurrentime, String cStatus, String cIP,
			String cAdress) {
		super();
		this.cNo = cNo;
		this.cName = cName;
		this.cEmail = cEmail;
		this.cSubject = cSubject;
		this.cMessage = cMessage;
		this.cCurrentime = cCurrentime;
		this.cStatus = cStatus;
		this.cIP = cIP;
		this.cAdress = cAdress;
	}
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
