package com.testterra.main;

public class Singleton {
	private static Singleton instance;
	MyTest test;
	int ID;
	int ALL_TESTS;
	boolean call_on_create;
	private Singleton() {call_on_create=true; test = new MyTest(); ID=0;}
	public static Singleton getInstance() {
		if( instance == null )
			instance = new Singleton();
		return instance;
	}
	
}
