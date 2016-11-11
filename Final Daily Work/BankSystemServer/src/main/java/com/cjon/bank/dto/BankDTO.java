package com.cjon.bank.dto;

public class BankDTO {
	private String member_id;
	private String member_name;
	private int member_balance;
	private String member_account;
	
	public String getMember_account() {
		return member_account;
	}

	public void setMember_account(String member_account) {
		this.member_account = member_account;
	}

	public BankDTO() {
		
	}

	public void BankDTO(String member_id, String member_name, int member_balance, String member_account) {
		this.member_id = member_id;
		this.member_name = member_name;
		this.member_balance = member_balance;
		this.member_account = member_account;
	}
	
	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public int getMember_balance() {
		return member_balance;
	}

	public void setMember_balance(int member_balance) {
		this.member_balance = member_balance;
	}
}
