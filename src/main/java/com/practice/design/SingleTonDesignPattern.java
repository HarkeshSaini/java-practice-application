package com.practice.design;

public class SingleTonDesignPattern {

	public static SingleTonDesignPattern pattern;

	private SingleTonDesignPattern() {
		// TODO default
	}

	public static SingleTonDesignPattern singleTonDesignPattern() {
		if (pattern == null) {
			synchronized (SingleTonDesignPattern.class) {
				pattern = new SingleTonDesignPattern();
			}
		}
		return pattern;
	}
}
