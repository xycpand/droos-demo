package com.defang.scorecard_drl.entity;

/**
 * This class was automatically generated by the data modeler tool.
 */

@javax.persistence.Entity
public class Applicant implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO, generator = "APPLICANT_ID_GENERATOR")
	@javax.persistence.Id
	@javax.persistence.SequenceGenerator(sequenceName = "APPLICANT_ID_SEQ", name = "APPLICANT_ID_GENERATOR")
	private java.lang.Long id;

	@org.kie.api.definition.type.Label("年龄")
	private java.lang.Integer age;

	@org.kie.api.definition.type.Label("性别")
	private String sex;

	@org.kie.api.definition.type.Label("婚姻状况")
	private java.lang.String maritalStatus;

	@org.kie.api.definition.type.Label("最高学历")
	private java.lang.String degree;

	@org.kie.api.definition.type.Label("评分")
	private float score;

	@org.kie.api.definition.type.Label("信用评级")
	private java.lang.String creditRating;

	public Applicant() {
	}

	public Applicant(int age, java.lang.String sex,
			java.lang.String maritalStatus, java.lang.String degree, float score) {
		this.age = age;
		this.sex = sex;
		this.maritalStatus = maritalStatus;
		this.degree = degree;
		this.score = score;
	}

	public java.lang.Long getId() {
		return this.id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.Integer getAge() {
		return this.age;
	}

	public void setAge(java.lang.Integer age) {
		this.age = age;
	}

	public java.lang.String getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setMaritalStatus(java.lang.String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public java.lang.String getDegree() {
		return this.degree;
	}

	public void setDegree(java.lang.String degree) {
		this.degree = degree;
	}

	public float getScore() {
		return this.score;
	}

	public void setScore(float score) {
		this.score = this.score + score;
	}

	public java.lang.String getCreditRating() {
		return this.creditRating;
	}

	public void setCreditRating(java.lang.String creditRating) {
		this.creditRating = creditRating;
	}

	public java.lang.String getSex() {
		return this.sex;
	}

	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}

	public Applicant(java.lang.Long id, java.lang.Integer age,
			java.lang.String sex, java.lang.String maritalStatus,
			java.lang.String degree, float score, java.lang.String creditRating) {
		this.id = id;
		this.age = age;
		this.sex = sex;
		this.maritalStatus = maritalStatus;
		this.degree = degree;
		this.score = score;
		this.creditRating = creditRating;
	}

	@Override
	public String toString() {
		return "Applicant [id=" + id + ", age=" + age + ", sex=" + sex + ", maritalStatus=" + maritalStatus
				+ ", degree=" + degree + ", score=" + score + ", creditRating=" + creditRating + "]";
	}

}