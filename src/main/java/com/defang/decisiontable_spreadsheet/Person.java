package com.defang.decisiontable_spreadsheet;

/**
 * This class was automatically generated by the data modeler tool.
 */

public class Person implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	private int age;
	private java.lang.String desc;
	private java.lang.String name;

	public Person() {
	}
	public Person(String name,int age) {
		this.age = age;
		this.name = name;
	}
	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public java.lang.String getDesc() {
		return this.desc;
	}

	public void setDesc(java.lang.String desc) {
		this.desc = desc;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public Person(int age, java.lang.String desc, java.lang.String name) {
		this.age = age;
		this.desc = desc;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", desc=" + desc + ", name=" + name + "]";
	}

}