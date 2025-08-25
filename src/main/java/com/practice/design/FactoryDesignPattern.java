package com.practice.design;

public class FactoryDesignPattern {

	public static int getFactoryPattern(String str) {
		if (str.trim().equalsIgnoreCase("java developer")) {
			JavaDevloperFactory factory = new JavaDevloperFactory();
			return factory.getSalary();
		} else if (str.trim().equalsIgnoreCase("Android developer")) {
			AndroidDevloperFactory factory = new AndroidDevloperFactory();
			return factory.getSalary();
		}
		return 0;
	}
}
