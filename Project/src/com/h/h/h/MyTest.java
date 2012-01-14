package com.h.h.h;

import java.io.IOException;
import java.util.Scanner;

import android.widget.Toast;

public class MyTest {
	 public String statement;
	 public String answers[];
	 public int correct_answer;
	public MyTest() {
		statement = "";
		answers = new String[5];
		correct_answer = 0;
	}
	public void read(Scanner s) throws IOException {
		correct_answer = s.nextInt();
		s.nextLine();
		statement = s.nextLine();
		for(int i=0;i<5;i++)
			answers[i] = s.nextLine();
	}
}

/*
 2
 What do you mean?
 one
 two
 three
 four
 five
*/
