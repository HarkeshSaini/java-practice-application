package com.practice.map;

import java.util.HashMap;

public class HashMapDetail {

	public static HashMap<String, Integer> hashMapInteger() {
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("A", 10);
		hashMap.put("C", 30);
		hashMap.put("B", 20);
		hashMap.put("E", 50);
		hashMap.put("D", 40);
		return hashMap;
	}

	public static HashMap<String, String> hashMapString() {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("A", "Math");
		hashMap.put("C", "English");
		hashMap.put("B", "Hindi");
		hashMap.put("E", "Data");
		hashMap.put("D", "Manage");
		return hashMap;
	}
}
