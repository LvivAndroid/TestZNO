package com.h.h.h;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ListOfTests extends Activity {

	private MyTest[] tests;
	private int NumberOfTests = 3;

	/* HAAWA's CONSTS */
	public int score = 0;
	public int count = 0;
	public CheckBox[] TestCB = new CheckBox[30];
	public TextView[] TestTV = new TextView[30];
	public TextView[] LettersTV = new TextView[30];
	public boolean check = true;

	/* end HAAWA's CONSTS */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_of_tests);
		String filename = "example.txt";
		/*
		 * тут потрібно витягти з Bundle імя + розширення файлу, з якого
		 * зчитуватимемо тести
		 */
		tests = new MyTest[NumberOfTests];
		try {
			GenereteListOfTests(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}

		/* HAAWA's CODE */
		GlobalState gs = (GlobalState) getApplication();
		gs.setback(true);

		ScrollView sv = new ScrollView(this);
		sv.setBackgroundColor(R.color.white);

		TableLayout TL = new TableLayout(this);
		TL.setLayoutParams(new TableLayout.LayoutParams(4, 5));
		TL.setBackgroundColor(0xff808080);

		for (int i = 0; i <= 29; i++) {

			TableRow TR = new TableRow(this);

			TR.setGravity(Gravity.CENTER);

			TestCB[i] = new CheckBox(this);
			TestCB[i].setBackgroundResource(R.drawable.chboxbg);
			TestCB[i].setText("");
			TestCB[i].setWidth(20);
			TestCB[i].setHeight(53);
			TestCB[i].setGravity(Gravity.CENTER);
			TestCB[i].setVisibility(0);
			TestCB[i].setChecked(false);
			TestCB[i].setClickable(false);
			TestCB[i].setEnabled(false);

			TestTV[i] = new TextView(this);
			TestTV[i].setBackgroundResource(R.drawable.testnumbbg);
			TestTV[i].setText(" Завдання №" + (i + 1) + "  ");
			TestTV[i].setGravity(Gravity.CENTER);
			TestTV[i].setClickable(true);
			TestTV[i].setEnabled(true);
			TestTV[i].setWidth(270);
			TestTV[i].setHeight(53);
			TestTV[i].setTextColor(Color.BLACK);
			final int temp = i;
			TestTV[i].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					OpenTest(temp);

				}
			});

			LettersTV[i] = new TextView(this);
			LettersTV[i].setBackgroundResource(R.drawable.lettersbg);
			LettersTV[i].setText("    F");
			LettersTV[i].setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			LettersTV[i].setTextColor(Color.BLACK);

			LettersTV[i].setWidth(20);
			LettersTV[i].setHeight(53);

			TR.addView(TestCB[i]);
			TR.addView(TestTV[i]);
			TR.addView(LettersTV[i]);

			TL.addView(TR);

		}

		// TableRow TR = new TableRow(this);
		// TextView space = new TextView(this);
		// space.setText("");
		// space.setHeight(25);
		// TR.addView(space);
		// TL.addView(TR);
		// TableRow TR1 = new TableRow(this);
		// Button Submit = new Button(this);
		// Submit.setHeight(70);
		// Submit.setText("Звірити результати");
		// TR1.setGravity(Gravity.CENTER);
		// TR1.addView(Submit);
		// TL.addView(TR1);

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
			e.printStackTrace();
		}
	}

	/* for testing only */
	/*
	 * private void ShowData() { TextView tv1 = (TextView)
	 * findViewById(R.id.TextRow1); TextView tv2 = (TextView)
	 * findViewById(R.id.TextRow2); TextView tv3 = (TextView)
	 * findViewById(R.id.TextRow3); String STemp = tests[0].statement + "#" +
	 * Integer.toString(tests[0].correct_answer); for(int i=0;i<5;i++) STemp =
	 * STemp + "#" + tests[0].answers[i]; tv1.setText(STemp);
	 * 
	 * STemp = tests[1].statement + "#" +
	 * Integer.toString(tests[1].correct_answer); for(int i=0;i<5;i++) STemp =
	 * STemp + "#" + tests[1].answers[i]; tv2.setText(STemp);
	 * 
	 * STemp = tests[2].statement + "#" +
	 * Integer.toString(tests[2].correct_answer); for(int i=0;i<5;i++) STemp =
	 * STemp + "#" + tests[2].answers[i]; tv3.setText(STemp);
	 * Toast.makeText(getApplicationContext(), "Table updated.",
	 * Toast.LENGTH_SHORT).show(); }
	 */

	/* start HAAWA's CODE */
	void OpenTest(int num) {
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
				LettersTV[gs.getnumb() - 1].setText("    " + gs.getansw()
						+ "          " + gs.getcorr());
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
