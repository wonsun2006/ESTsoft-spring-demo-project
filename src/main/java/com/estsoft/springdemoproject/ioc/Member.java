package com.estsoft.springdemoproject.ioc;

public class Member {
	int id;
	String name;
	String address;

	public Member(String address, String name, int id) {
		this.address = address;
		this.name = name;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}
}
