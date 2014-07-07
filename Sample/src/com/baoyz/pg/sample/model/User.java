package com.baoyz.pg.sample.model;

import java.util.List;

import com.baoyz.pg.Parcelable;

@Parcelable
public class User extends Person{

	private long id;
	private boolean vip;
	private double balance;
	private List<String> alias;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<String> getAlias() {
		return alias;
	}

	public void setAlias(List<String> alias) {
		this.alias = alias;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + getName() + ", age=" + getAge() + ", vip="
				+ vip + ", balance=" + balance + ", alias=" + alias + "]";
	}

}
