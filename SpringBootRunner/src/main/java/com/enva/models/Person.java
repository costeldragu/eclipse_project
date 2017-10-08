package com.enva.models;

public class Person {

	private String name;
	private String username;
	private String sex;
	private Integer age;

	{
		name = "Unamed";
		username = "Not known";
		sex = "Not mow";
		age = 1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
