package com.h.h.h;

public class Singleton {
	private static Singleton instance;
	MyTest test;
	int ID;
	private Singleton() {}
	public static Singleton getInstance() {
		if( instance == null )
			instance = new Singleton();
		return instance;
	}
	
}
