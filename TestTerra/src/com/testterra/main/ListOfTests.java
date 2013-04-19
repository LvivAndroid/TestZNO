package com.testterra.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ListOfTests extends Activity {

	int NumberOfTests ;
	private int DbSize;
	/* HAAWA's CONSTS */

	public int score = 0;
	public int count = 0;

	public MyTest[] tests;
	public TextView[] TestTV;
	public TextView[] LettersTV;
	public TextView[] Corr;
	public boolean check = true;
	public LinearLayout LL;
	public ScrollView SV;
	public TableLayout TL;
	public int width;
	public int height;
	public int RowWidth;
	public int orientation;
	public Button Submit;
	private String DB_TABLE;
	/* end HAAWA's CONSTS */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_of_tests);
		DB_TABLE = Singleton.getInstance().getTABLE();
		Helper H = new Helper(this, DB_TABLE);
		H.open();
		DbSize = (int) H.getSize();
		H.close();
		
		NumberOfTests = getNumbOfTest();
		
		TestTV = new TextView[NumberOfTests];
		LettersTV = new TextView[NumberOfTests];
		Corr = new TextView[NumberOfTests];

		// SV = new ScrollView(this);
		TL = new TableLayout(this);
		TableLayout.LayoutParams TR_layout_params = new TableLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 1f);
		TR_layout_params.setMargins(4, 4, 4, 4);
		TL.setLayoutParams(TR_layout_params);
		//TL.setBackgroundColor(Color.WHITE);
		TL.setGravity(Gravity.CENTER);
		LL = new LinearLayout(this);
		SV = new ScrollView(this);
		//SV.setBackgroundColor(Color.WHITE);

		GradientDrawable gd = (GradientDrawable) getApplicationContext()
				.getResources().getDrawable(R.drawable.grad);
		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		gd.setGradientRadius((float) (Math.max(width, height) * 0.5 + 20));
		
		Singleton.getInstance().ALL_TESTS = NumberOfTests;
		if (Singleton.getInstance().call_on_create)
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

		if (orientation == 2) {
			width = metrics.heightPixels;
			height = metrics.widthPixels;
		}

		// КІНЕЦЬ: Перевірка орієнтації екрану,-> пошук ширини і висоти екрану

		int itemH = Math.min(height, width) / 6;
		//itemH = 60 * height / 800;
		
		/*
		 * тут потрібно витягти з Bundle імя + розширення файлу, з якого
		 * зчитуватимемо тести
		 */
		tests = new MyTest[NumberOfTests];
		GenereteListOfTests();
		/* HAAWA's CODE */
		GlobalState gs = (GlobalState) getApplication();
		gs.setsubm(true);

		for (int i = 0; i < NumberOfTests; i++) {

			TableRow TR = new TableRow(this);
			TR.setGravity(Gravity.CENTER);
			//TR.setBackgroundDrawable(gd);

			// Creating name TextViews
			TestTV[i] = new TextView(this);
			TestTV[i].setGravity(Gravity.CENTER);
			TestTV[i].setText(" Завдання " + (i + 1) + " ");
			TestTV[i].setTextColor(Color.WHITE);
			TestTV[i].setTypeface(null, Typeface.BOLD);
			TestTV[i].setBackgroundResource(R.drawable.bg_head);
			final int temp = i;
			TestTV[i].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					OpenTest(temp);
				}
			});

			// Creating Letters TextViews
			LettersTV[i] = new TextView(this);
			LettersTV[i].setText("");
			LettersTV[i].setTextColor(Color.WHITE);
			LettersTV[i].setTypeface(null, Typeface.BOLD);
			LettersTV[i].setGravity(Gravity.CENTER);
			LettersTV[i].setBackgroundResource(R.drawable.bg_mid);
			//

			// Creating TextViews with correct answers
			Corr[i] = new TextView(this);
			Corr[i].setText("");
			Corr[i].setTextColor(Color.WHITE);
			Corr[i].setTypeface(null, Typeface.BOLD);
			Corr[i].setGravity(Gravity.CENTER);
			Corr[i].setBackgroundResource(R.drawable.bg_tail);
			//

			// Ієрархія View
			
			TR.addView(TestTV[i], 260 * width / 480, itemH);
			TR.addView(LettersTV[i], 60 * width / 480, itemH);
			TR.addView(Corr[i], 60 * width / 480, itemH);
			TL.addView(TR);
		}

		Submit = new Button(this);
		Submit.setText("Звірити результати");
		Submit.setTypeface(null, Typeface.BOLD);
		Submit.setBackgroundResource(R.drawable.submit_state);
		//Submit.setBackgroundColor(R.drawable.submit_state);
		Submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				GlobalState gs = (GlobalState) getApplication();
				if (gs.getsubm()) {
					
					Submit.setText("Вихід");
					String[] Letters = { "А", "Б", "В", "Г", "Д" };
					for (int i = 0; i < NumberOfTests; i++) {
						Corr[i].setText(Letters[tests[i].correct_answer]);
						if(Corr[i].getText().toString().equals(LettersTV[i].getText().toString())) {
							score++;
							TestTV[i].setBackgroundResource(R.drawable.bg_head_green);
							LettersTV[i].setBackgroundResource(R.drawable.bg_mid_green);
							Corr[i].setBackgroundResource(R.drawable.bg_tail_green);
						}
						else {
							TestTV[i].setBackgroundResource(R.drawable.bg_head_red);
							LettersTV[i].setBackgroundResource(R.drawable.bg_mid_red);
							Corr[i].setBackgroundResource(R.drawable.bg_tail_red);
						}
					}
					AddNewScore(score, NumberOfTests);
					
					for (int i = 0; i < NumberOfTests; i++) {
						Corr[i].setVisibility(0);
						//LettersTV[i].setBackgroundResource(R.drawable.bg_mid);
					}

					AlertDialog.Builder builder = new AlertDialog.Builder(
							ListOfTests.this);
					builder.setMessage(
							"Ви набрали " + +score + " балів з усіх "
									+ NumberOfTests)
							.setCancelable(false)
							.setPositiveButton("OK",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog, int id) {

										}
									});

					AlertDialog alert = builder.create();
					alert.show();

					gs.setsubm(false);

				} else {
					Singleton.getInstance().call_on_create = true;
					finish();
				}

			}
		});
		
		TL.addView(Submit, 380 * width / 480, itemH);
		SV.setBackgroundDrawable(gd);
		LL.addView(TL);
		LL.setOrientation(LinearLayout.VERTICAL);
		SV.addView(LL);

		super.setContentView(SV);

		/* end WAAWA's CODE */
	}

	int generRandom(int n) {
		return Math.abs((new Random()).nextInt(n));
	}

	void GenereteListOfTests() {

		int amount_of_tests = DbSize;
		Boolean[] used = new Boolean[amount_of_tests];
		for (int i = 0; i < amount_of_tests; i++)
			used[i] = false;

		int[] WhatTests = new int[NumberOfTests]; // номери тестів, які увійшли
													// до набору
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
		Helper H = new Helper(this, DB_TABLE);
		H.open();
		for (int i = 0; i < NumberOfTests; i++)
			tests[i] = new MyTest();
		//WhatTests[0] = 923;
		for (int i = 0; i < NumberOfTests; i++) {
			int index = WhatTests[i] + 1;
			tests[i].statement = H.getKEY_TEST(index);
			tests[i].A_answer = H.getKEY_A_answer(index);
			tests[i].B_answer = H.getKEY_B_answer(index);
			tests[i].C_answer = H.getKEY_C_answer(index);
			tests[i].D_answer = H.getKEY_D_answer(index);
			tests[i].E_answer = H.getKEY_E_answer(index);
			tests[i].correct_answer = H.getKEY_CORRECT(index);
			tests[i].Eans = H.getKEY_IFEEXISTS(index);
		}
		
		H.close();
	}

	/* start HAAWA's CODE */
	void OpenTest(int num) {
		Singleton.getInstance().test = tests[num];
		Singleton.getInstance().ID = num;
		GlobalState gs = (GlobalState) getApplication();
		gs.setback(true);
		Intent myIntent = new Intent(ListOfTests.this, Test.class);
		myIntent.putExtra("com.testterra.main.a", num + 1 + "");
		startActivityForResult(myIntent, 1);
	}

	@Override
	protected void onPause() {
		super.onPause();

	}

	@Override
	protected void onResume() {

		super.onResume();
		count++;
		if (count > 1)
		{
			GlobalState gs = (GlobalState) getApplication();

			if (gs.getback()) {
				LettersTV[gs.getnumb() - 1].setText(gs.getansw() + "");
				gs.setansw("");
				gs.setcorr("");
				gs.setback(false);
			}
		}
	}

	@Override
	public void onBackPressed() {
		AlertDialog.Builder builder = new AlertDialog.Builder(ListOfTests.this);
		builder.setMessage(
				"Ви справді хочете вийти? (Дані про тестування будуть втрачені)")
				.setCancelable(false)
				.setPositiveButton("Вихід",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								Singleton.getInstance().call_on_create = true;
								ListOfTests.this.finish();
							}
						})
				.setNegativeButton("Скасувати",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {

							}
						});
		AlertDialog alert = builder.create();
		alert.show();

	}

	/* end HAAWA's CODE */

	public boolean isExternalStorageAvailable() {
		boolean state = false;
		String extStorageState = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
			state = true;
		}
		return state;
	}

	// Helper Method to Test if external Storage is read only
	public boolean isExternalStorageReadOnly() {
		boolean state = false;
		String extStorageState = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
			state = true;
		}
		return state;
	}

	private String GetDate() {
		String today = "";
		Date date = new Date();
		today += Integer.toString(date.getYear() + 1900) + '/';
		today += Integer.toString(date.getMonth() + 1) + '/';
		today += Integer.toString(date.getDate());
		return today;
	}

	private void AddNewScore(int Score, int N_Tests) {
		if (!isExternalStorageAvailable() || isExternalStorageReadOnly())
			return;
		String dates[] = null;
		int values[] = null;
		int outof[] = null;
		String path = Environment.getExternalStorageDirectory()
				+ File.separator;
		String MY_FILE = path + R.string.scoreFile;
		int n;
		String add;
		/*
		//clean up old type of file
		BufferedWriter out2;
		try {
			out2 = new BufferedWriter(new FileWriter(MY_FILE));
			out2.write("0\n");out2.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
		

		/* read old data */
		try {
			Scanner s = new Scanner(new FileReader(MY_FILE));
			n = s.nextInt();
			dates = new String[n];
			values = new int[n];
			outof = new int[n];
			for (int i = 0; i < n; ++i) {
				values[i] = s.nextInt();
				outof[i] = s.nextInt();
				dates[i] = s.nextLine();
			}
			s.close();
			n++;
		} catch (FileNotFoundException e) {
			n = 1;
			e.printStackTrace();
		}
		add = Integer.toString(Score) + " " + Integer.toString(N_Tests) + " " + GetDate();

		/* write new data */
		BufferedWriter out;
		
		
		
		try {
			out = new BufferedWriter(new FileWriter(MY_FILE));
			out.write(Integer.toString(n) + "\n");
			for (int i = 0; i < n - 1; i++)
				out.write(Integer.toString(values[i]) + " " + Integer.toString(outof[i]) + dates[i] + "\n");
			out.write(add);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	int getNumbOfTest() {
		final AlternativeDB alt;
		alt = new AlternativeDB(this);
		alt.open();
		int res = 5;
		if (Integer.parseInt(alt.getKEY_Numb(1)) == 10)
			res = 10;
		else
			res = 15;
		alt.close();
		return res;
	}

}
