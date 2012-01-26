package com.h.h.h;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ListOfTests extends Activity {

	private MyTest[] tests;
	private int NumberOfTests = 12;

	/* HAAWA's CONSTS */
	
	public int score = 0;
	public int count = 0;
	public CheckBox[] TestCB = new CheckBox[NumberOfTests];
	public TextView[] TestTV = new TextView[NumberOfTests];
	public TextView[] LettersTV = new TextView[NumberOfTests];
	public TextView[] Corr = new TextView[NumberOfTests];
	public boolean check = true;
	public int width;
	public int height;
	public int RowWidth;
	public int orientation;

	/* end HAAWA's CONSTS */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_of_tests);

		if( Singleton.getInstance().call_on_create )
			Singleton.getInstance().call_on_create = false;
		else
			return;
		
		// ПОЧАТОК: Перевірка орієнтації екрану,-> пошук ширини і висоти екрану

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		orientation = getResources().getConfiguration().orientation;

		if (orientation == 1) {
			height = metrics.heightPixels;
			width = metrics.widthPixels;
		}

		if (orientation == 2)
		{
			width = metrics.heightPixels;
			height = metrics.widthPixels;
		}

		// КІНЕЦЬ: Перевірка орієнтації екрану,-> пошук ширини і висоти екрану

		String filename = "ukrm.txt";
		/*
		 * тут потрібно витягти з Bundle імя + розширення файлу, з якого
		 * зчитуватимемо тести
		 */
		
		tests = new MyTest[NumberOfTests];
		try {
			GenereteListOfTests(filename);
		} catch (IOException e) {
			Toast.makeText(getApplicationContext(), "File IO error.", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}

		/* HAAWA's CODE */
		GlobalState gs = (GlobalState) getApplication();
		gs.setback(true);
		gs.setsubm(true);

		// ПОЧАТОК: Створення scrollLook & TableLayout
		ScrollView sv = new ScrollView(this);
		sv.setBackgroundColor(R.color.white);

		TableLayout TL = new TableLayout(this);
		TL.setLayoutParams(new TableLayout.LayoutParams(4, 5));
		TL.setGravity(Gravity.CENTER);
		TL.setBackgroundColor(Color.DKGRAY);
		
		TextView firstspace = new TextView(this);
		firstspace.setHeight((int) 15 * height / 800);
		TableRow firstspacerow = new TableRow(this);
		firstspacerow.addView(firstspace);
		TL.addView(firstspacerow);

		// КІНЕЦЬ: Створення scrollLook & TableLayout

		for (int i = 0; i < NumberOfTests; i++) {

			Button[] Line1 = new Button[NumberOfTests];
			Button[] Line2 = new Button[NumberOfTests];
			for (int k = 0; k <= 4; k++) {
				// ПОЧАТОК Створення Вертикальних розділювачів
				Line1[k] = new Button(this);
				Line1[k].setHeight(56 * height / 800);
				Line1[k].setWidth(2);
				Line1[k].setText("");
				Line1[k].setClickable(false);
				Line1[k].setBackgroundColor(Color.BLACK);

				Line2[k] = new Button(this);
				Line2[k].setHeight(2);
				Line2[k].setWidth(2);
				Line2[k].setText("");
				Line2[k].setClickable(false);
				Line2[k].setBackgroundColor(Color.BLACK);

				// кінець створення розділювачів
			}

			// Створення кожного з ітих TableRow

			TableRow TR = new TableRow(this);
			TR.setBackgroundColor(Color.GRAY);
			TR.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			TR.setGravity(Gravity.CENTER);

			TestCB[i] = new CheckBox(this);
			TestCB[i].setText("");
			TestCB[i].setWidth((int) 60 * width / 480);
			TestCB[i].setHeight((int) 56 * height / 800);
			TestCB[i].setGravity(Gravity.CENTER);
			TestCB[i].setVisibility(4);
			TestCB[i].setChecked(false);
			TestCB[i].setClickable(false);
			TestCB[i].setEnabled(false);
			TestCB[i].setBackgroundColor(Color.GRAY);

			TestTV[i] = new TextView(this);
			TestTV[i].setText(" Завдання №" + (i + 1) + "  ");
			TestTV[i].setGravity(Gravity.CENTER);
			TestTV[i].setClickable(true);
			TestTV[i].setEnabled(true);
			TestTV[i].setWidth((int) 260 * width / 480);
			TestTV[i].setHeight((int) 56 * height / 800);
			TestTV[i].setTextColor(Color.BLACK);
			TestTV[i].setBackgroundColor(Color.GRAY);
			final int temp = i;
			TestTV[i].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					OpenTest(temp);

				}
			});

			LettersTV[i] = new TextView(this);
			LettersTV[i].setText("");
			LettersTV[i].setGravity(Gravity.CENTER);
			LettersTV[i].setTextColor(Color.BLACK);
			LettersTV[i].setBackgroundColor(Color.GRAY);
			LettersTV[i].setWidth((int) 60 * width / 480);
			LettersTV[i].setHeight((int) 56 * height / 800);

			Corr[i] = new TextView(this);
			Corr[i].setText("");
			Corr[i].setGravity(Gravity.CENTER);
			Corr[i].setTextColor(Color.BLACK);
			Corr[i].setBackgroundColor(Color.GRAY);
			Corr[i].setWidth((int) 60 * width / 480);
			Corr[i].setHeight((int) 56 * height / 800);
			Corr[i].setVisibility(4);

			TR.addView(Line1[0]);
			TR.addView(TestCB[i]);
			TR.addView(Line1[1]);
			TR.addView(TestTV[i]);
			TR.addView(Line1[2]);
			TR.addView(LettersTV[i]);
			TR.addView(Line1[3]);
			TR.addView(Corr[i]);
			TR.addView(Line1[4]);

			// закінчення створення кожного з ітих TableRow

			// створення двох останніх тейбл ролів, один просто пустота а другий
			// з кнопкою сабміт
			TableRow TRd = new TableRow(this);
			TRd.setBackgroundColor(R.color.silver);
			TRd.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			TRd.setGravity(Gravity.CENTER);
			TRd.addView(Line2[0]);

			TextView spac0 = new TextView(this);
			TextView spac1 = new TextView(this);
			TextView spac2 = new TextView(this);
			TextView spac3 = new TextView(this);

			spac0.setWidth((int) 60 * width / 480 + 2);

			spac0.setHeight(2);
			spac0.setBackgroundColor(Color.BLACK);
			spac0.setText("");
			TRd.addView(spac0);
			TRd.addView(Line2[1]);

			spac1.setWidth((int) 260 * width / 480 + 2);

			spac1.setHeight(2);
			spac1.setBackgroundColor(Color.BLACK);
			spac1.setText("");
			TRd.addView(spac1);
			TRd.addView(Line2[2]);

			spac2.setWidth((int) 60 * width / 480 + 2);

			spac2.setHeight(2);
			spac2.setBackgroundColor(Color.BLACK);
			spac2.setText("");
			TRd.addView(spac2);
			TRd.addView(Line2[3]);

			spac3.setWidth((int) 60 * width / 480 + 2);

			spac3.setHeight(2);
			spac3.setBackgroundColor(Color.BLACK);
			spac3.setText("");
			TRd.addView(spac3);
			TRd.addView(Line2[4]);

			TL.addView(TR);
			TL.addView(TRd);
		}

		TableRow TR = new TableRow(this);
		TextView space = new TextView(this);
		TR.setBackgroundColor(Color.DKGRAY);
		space.setText("");
		space.setHeight((int) 15 * height / 800);
		TR.addView(space);
		TL.addView(TR);
		TableRow TR1 = new TableRow(this);
		final Button Submit = new Button(this);
		Submit.setHeight((int) 70 * height / 800);
		Submit.setWidth((int) 230 * height / 800);
		Submit.setText("Звірити результати");
		
		
		Submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GlobalState gs = (GlobalState) getApplication();
				if(gs.getsubm())
				{
					Submit.setText("Вихід");
					String []Letters = {"А","Б","В","Г","Д"};
					for(int i=0;i<NumberOfTests;i++) {
						Corr[i].setText(Letters[tests[i].correct_answer]);
					}
					
					for(int i=0;i<NumberOfTests;i++)
					{
						if (TestCB[i].isChecked())
						
							TestCB[i].setVisibility(0);
						Corr[i].setVisibility(0);
					}
							
							Context context = getApplicationContext();
							CharSequence text = "Ви набрали "+ score+" правильних відповідей з усіх "+ NumberOfTests+" ";
							int duration = Toast.LENGTH_LONG;

							

							AlertDialog.Builder builder = new AlertDialog.Builder(ListOfTests.this);
							builder.setMessage(
									"Ви набрали "+ + score+" балів з усіх "+NumberOfTests)
									.setCancelable(false)
									.setPositiveButton("OK",
											new DialogInterface.OnClickListener() {
												public void onClick(DialogInterface dialog, int id) {
													
												}
											});
									
							AlertDialog alert = builder.create();
							alert.show();
							
							
							gs.setsubm(false);
						
					
					
				}
				else {
					Singleton.getInstance().call_on_create = true;
					finish();
				}
				
			}
		});
		
		
		TR1.setGravity(Gravity.CENTER);

		TextView t0 = new TextView(this);
		t0.setWidth(2);
		t0.setBackgroundColor(Color.DKGRAY);

		TextView t1 = new TextView(this);
		t1.setWidth((int) 60 * width / 480);
		t1.setBackgroundColor(Color.DKGRAY);

		TextView t2 = new TextView(this);
		t2.setWidth(2);
		t2.setBackgroundColor(Color.DKGRAY);

		TextView t3 = new TextView(this);
		t3.setWidth(2);
		t3.setBackgroundColor(Color.DKGRAY);

		TextView t4 = new TextView(this);
		t4.setWidth((int) 60 * width / 480);
		t4.setBackgroundColor(Color.DKGRAY);

		TextView t5 = new TextView(this);
		t5.setWidth(2);
		t5.setBackgroundColor(Color.DKGRAY);

		TextView t6 = new TextView(this);
		t6.setWidth((int) 60 * width / 480);
		t6.setBackgroundColor(Color.DKGRAY);

		TextView t7 = new TextView(this);
		t7.setWidth(2);
		t7.setBackgroundColor(Color.DKGRAY);

		TR1.addView(t0);
		TR1.addView(t1);
		TR1.addView(t2);
		TR1.addView(Submit);
		TR1.addView(t3);
		TR1.addView(t4);
		TR1.addView(t5);
		TR1.addView(t6);
		TR1.addView(t7);

		TL.addView(TR1);

		// створення двох останніх тейбл ролів, один просто пустота а другий з
		// кнопкою сабміт

		sv.addView(TL);

		super.setContentView(sv);
		/* end WAAWA's CODE */
	}

	int generRandom(int n) {
		return Math.abs((new Random()).nextInt(n));
	}

	void GenereteListOfTests(String filename) throws IOException {
		String path = Environment.getExternalStorageDirectory()
				+ File.separator;
		String MY_FILE = path + filename;
		try {
			Scanner scanner = new Scanner(new FileReader(MY_FILE));
			int amount_of_tests = scanner.nextInt();
			scanner.nextLine();
			Boolean[] used = new Boolean[amount_of_tests];
			for (int i = 0; i < amount_of_tests; i++)
				used[i] = false;
			int[] WhatTests = new int[NumberOfTests]; // номери тестів, які
														// увійшли до набору
			int added = 0;
			while (added < NumberOfTests) {
				int tmp = generRandom(amount_of_tests - added + 1);
				int it = 0;
				while (it < amount_of_tests && used[it])
					it++;
				if (tmp == 0)
					it++;
				while (tmp != 0) {
					if (!used[it])
						tmp--;
					it++;
					if (it == amount_of_tests)
						it = 0;
				}
				it = (it - 1 + amount_of_tests) % amount_of_tests;
				used[it] = true;
				WhatTests[added] = it;
				added++;
			}

			MyTest test_tmp;
			for (int i = 0; i < amount_of_tests; i++) {
				test_tmp = new MyTest();
				scanner.nextLine();
				test_tmp.read(scanner);
				for (int j = 0; j < NumberOfTests; j++)
					if (WhatTests[j] == i)
						tests[j] = test_tmp;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			Toast.makeText(getApplicationContext(), "File not founded.", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
	}

	/* start HAAWA's CODE */
	void OpenTest(int num) {
		Singleton.getInstance().test = tests[num];
		Singleton.getInstance().ID = num;
		
		Intent myIntent = new Intent(ListOfTests.this, Test.class);
		myIntent.putExtra("com.h.h.h.a", num + 1 + "");
		startActivityForResult(myIntent, 1);

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub

		super.onResume();
		count++;
		if (count > 1)

		{

			GlobalState gs = (GlobalState) getApplication();

			if (gs.getback()) {

				if (gs.getansw() == gs.getcorr()) {
					
					if (!TestCB[gs.getnumb() - 1].isChecked()) {
						TestCB[gs.getnumb() - 1].setChecked(true);
						score++;
						
					}

				} else {
					if (TestCB[gs.getnumb() - 1].isChecked()) {
						TestCB[gs.getnumb() - 1].setChecked(false);
						score--;

					}

				}
				
				LettersTV[gs.getnumb() - 1].setText(gs.getansw() + "");
				Corr[gs.getnumb() - 1].setText(gs.getcorr() + "");
				gs.setansw("");
				gs.setcorr("");
			}

			else {
				gs.setback(true);

			}

		}
	}

	public void onBackPressed() {
		AlertDialog.Builder builder = new AlertDialog.Builder(ListOfTests.this);
		builder.setMessage(
				"Ви справді хочете вийти? (Дані про тестування будуть втрачені)")
				.setCancelable(false)
				.setPositiveButton("Вихід",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Singleton.getInstance().call_on_create = true;
								ListOfTests.this.finish();
							}
						})
				.setNegativeButton("Скасувати",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

							}
						});
		AlertDialog alert = builder.create();
		alert.show();

	}
	/* end HAAWA's CODE */

}
