package com.baoyz.pg.sample;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.baoyz.pg.Parcelable;

public class User {

	private String name;
	private int age;
	private boolean vip;
	private double balance;
	private long id;
	private List<String> list;
	private String[] strArray;
	private HashMap<Integer, String> map;
	private int[] intArray;

	public int[] getIntArray() {
		return intArray;
	}

	public void setIntArray(int[] intArray) {
		this.intArray = intArray;
	}

	public String[] getStrArray() {
		return strArray;
	}

	public void setStrArray(String[] strArray) {
		this.strArray = strArray;
	}

	public HashMap<Integer, String> getMap() {
		return map;
	}

	public void setMap(HashMap<Integer, String> map) {
		this.map = map;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", vip=" + vip
				+ ", balance=" + balance + ", id=" + id + ", list=" + list
				+ ", strArray=" + Arrays.toString(strArray) + ", map=" + map
				+ ", intArray=" + Arrays.toString(intArray) + "]";
	}

}
