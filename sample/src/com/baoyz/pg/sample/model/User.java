package com.baoyz.pg.sample.model;

import java.util.List;

import com.baoyz.pg.Parcelable;

@Parcelable
public class User extends Person {

	private long id;
	private boolean vip;
	private double balance;
	private Long id2;
	private Boolean vip2;
	private Double balance2;
	private List<String> alias;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id2 = id;
		this.id = id;
	}

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip2 = vip;
		this.vip = vip;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance2 = balance;
		this.balance = balance;
	}

	public List<String> getAlias() {
		return alias;
	}

	public void setAlias(List<String> alias) {
		this.alias = alias;
	}

	public Long getId2() {
		return id2;
	}

	public void setId2(Long id2) {
		this.id2 = id2;
	}

	public Boolean getVip2() {
		return vip2;
	}

	public void setVip2(Boolean vip2) {
		this.vip2 = vip2;
	}

	public Double getBalance2() {
		return balance2;
	}

	public void setBalance2(Double balance2) {
		this.balance2 = balance2;
	}

	@Override
	public String toString() {
		return "User [name=" + getName() + ", age=" + getAge() + ", id=" + id
				+ ", vip=" + vip + ", balance=" + balance + ", id2=" + id2
				+ ", vip2=" + vip2 + ", balance2=" + balance2 + ", alias="
				+ alias + "]";
	}

}
