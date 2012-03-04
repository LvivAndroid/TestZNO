package com.h.h.h;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
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

	
	private int NumberOfTests = 12;

	/* HAAWA's CONSTS */

	public int score = 0;
	public int count = 0;
	
	public MyTest[]   tests;
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
		
		GradientDrawable gd = (GradientDrawable) getApplicationContext().getResources().getDrawable(R.drawable.grad);
        Display display = getWindowManager().getDefaultDisplay(); 
        int width = display.getWidth();
        int height = display.getHeight();
    	gd.setGradientRadius((float) (Math.max(width,height)*0.5 + 20));
		
		Singleton.getInstance().ALL_TESTS = NumberOfTests;
		if (Singleton.getInstance().call_on_create)
			Singleton.getInstance().call_on_create = false;
		else
			return;

		// �������: �������� �������� ������,-> ����� ������ � ������ ������

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

		// ʲ����: �������� �������� ������,-> ����� ������ � ������ ������

		String filename = "ukrm.txt";
		/*
		 * ��� ������� ������� � Bundle ��� + ���������� �����, � �����
		 * ������������� �����
		 */

		tests = new MyTest[NumberOfTests];
		GenereteListOfTests();

		/* HAAWA's CODE */
		GlobalState gs = (GlobalState) getApplication();
		gs.setback(true);
		gs.setsubm(true);

		// �������: ��������� scrollLook & TableLayout
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

		// ʲ����: ��������� scrollLook & TableLayout

		for (int i = 0; i < NumberOfTests; i++) {

			Button[] Line1 = new Button[NumberOfTests];
			Button[] Line2 = new Button[NumberOfTests];
			for (int k = 0; k <= 4; k++) {
				// ������� ��������� ������������ �����������
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

				// ����� ��������� �����������
			}

			// ��������� ������� � ���� TableRow

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
			TestTV[i].setText(" �������� �" + (i + 1) + "  ");
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

			// ��������� ��������� ������� � ���� TableRow

			// ��������� ���� �������� ����� ����, ���� ������ ������� � ������
			// � ������� �����
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
		Submit.setText("������ ����������");

		Submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GlobalState gs = (GlobalState) getApplication();
				if (gs.getsubm()) {
					AddNewScore(score);
					Submit.setText("�����");
					String[] Letters = { "�", "�", "�", "�", "�" };
					for (int i = 0; i < NumberOfTests; i++) {
						Corr[i].setText(Letters[tests[i].correct_answer]);
					}

					for (int i = 0; i < NumberOfTests; i++) {
						if (TestCB[i].isChecked())
							TestCB[i].setVisibility(0);
						Corr[i].setVisibility(0);
					}

					Context context = getApplicationContext();
					CharSequence text = "�� ������� " + score
							+ " ���������� �������� � ��� " + NumberOfTests
							+ " ";
					int duration = Toast.LENGTH_LONG;

					AlertDialog.Builder builder = new AlertDialog.Builder(
							ListOfTests.this);
					builder.setMessage(
							"�� ������� " + +score + " ���� � ��� "
									+ NumberOfTests)
							.setCancelable(false)
							.setPositiveButton("OK",
									new DialogInterface.OnClickListener() {
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

		// ��������� ���� �������� ����� ����, ���� ������ ������� � ������ �
		// ������� �����

		sv.addView(TL);

		super.setContentView(sv);
		/* end WAAWA's CODE */
	}

	int generRandom(int n) {
		return Math.abs((new Random()).nextInt(n));
	}

	void GenereteListOfTests() {
		
		
		int amount_of_tests = 24;
		Boolean[] used = new Boolean[amount_of_tests];
		for (int i = 0; i < amount_of_tests; i++)
			used[i] = false;
		int[] WhatTests = new int[NumberOfTests]; // ������ �����, �� ������ �� ������
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
		
		Helper H = new Helper(this);
		H.open();
		for(int i=0;i<NumberOfTests;i++)
			tests[i] = new MyTest();
		for(int i=0;i<NumberOfTests;i++) {
			int index = WhatTests[i]+1;
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
				"�� ������ ������ �����? (���� ��� ���������� ������ ��������)")
				.setCancelable(false)
				.setPositiveButton("�����",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Singleton.getInstance().call_on_create = true;
								ListOfTests.this.finish();
							}
						})
				.setNegativeButton("���������",
						new DialogInterface.OnClickListener() {
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
        today += Integer.toString(date.getDate() );
        return today;
    }

	private void AddNewScore(int Score) {
		if( !isExternalStorageAvailable() || isExternalStorageReadOnly() )
			return;
		String dates[] = null;
		int values[] = null;
		String path = Environment.getExternalStorageDirectory() + File.separator;
        String MY_FILE = path + R.string.scoreFile;
        int n;
        String add;
        
        /* read old data */
		try {
			Scanner s = new Scanner(new FileReader(MY_FILE));
			n = s.nextInt();
			dates = new String[n];
			values = new int[n];
			for (int i = 0; i < n; ++i) {
				values[i] = s.nextInt();
				dates[i] = s.nextLine();
			}
			s.close();
			n++;
		} catch (FileNotFoundException e) {
			n = 1;
			e.printStackTrace();
		}
		add = Integer.toString(Score) + " " + GetDate();
		
		/* write new data */
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(MY_FILE));
			out.write(Integer.toString(n) + "\n");
			for(int i=0;i<n-1;i++)
				out.write(Integer.toString(values[i]) + dates[i] + "\n");
			out.write(add);
			out.close();
		} catch (IOException e) {
			//TODO something went wrong
			e.printStackTrace();
		}
        
	}

}
