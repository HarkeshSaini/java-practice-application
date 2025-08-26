package com.practice.design;

public class BuilderDesignPattern {

	private int id;
	private String name;
	private String emailId;
	private String phone;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getPhone() {
		return phone;
	}

	public BuilderDesignPattern(BuilderPattern builderPattern) {
		this.id = builderPattern.id;
		this.emailId = builderPattern.emailId;
		this.name = builderPattern.name;
		this.phone = builderPattern.phone;
	}

	public static class BuilderPattern {

		private int id;
		private String name;
		private String emailId;
		private String phone;

		public BuilderPattern setId(int id) {
			this.id = id;
			return this;
		}

		public BuilderPattern setName(String name) {
			this.name = name;
			return this;
		}

		public BuilderPattern setEmailId(String emailId) {
			this.emailId = emailId;
			return this;
		}

		public BuilderPattern setPhone(String phone) {
			this.phone = phone;
			return this;
		}
		
		public BuilderDesignPattern build() {
			return new BuilderDesignPattern(this);
		}
	}

}
