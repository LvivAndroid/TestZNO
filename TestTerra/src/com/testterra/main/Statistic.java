package com.testterra.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.view.Display;
import android.widget.TextView;

public class Statistic extends Activity {

	String dates[];
	int values[];
	int outof[];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statistic);

		GradientDrawable gd = (GradientDrawable) getApplicationContext()
				.getResources().getDrawable(R.drawable.grad);
		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		gd.setGradientRadius((float) (Math.max(width, height) * 0.5 + 20));

		GetAllScore();

	}

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

	private void GetAllScore() {
		if (!isExternalStorageAvailable() || isExternalStorageReadOnly())
			return;
		String path = Environment.getExternalStorageDirectory()
				+ File.separator;
		String MY_FILE = path + R.string.scoreFile;

		try {
			Scanner s = new Scanner(new FileReader(MY_FILE));

			int n = s.nextInt();
			dates = new String[n];
			values = new int[n];
			outof = new int[n];
			int idx = 0;
			for (int i = 0; i < n; ++i) {
				values[i] = s.nextInt();
				outof[i] = s.nextInt();
				dates[i] = s.nextLine();
				if (values[i] > values[idx])
					idx = i;
			}
			s.close();

			TextView tmp_tv = (TextView) findViewById(R.id.Title);
			tmp_tv.setVisibility(1);
			tmp_tv = (TextView) findViewById(R.id.fullTable);
			tmp_tv.setVisibility(1);
			tmp_tv = (TextView) findViewById(R.id.maxScore);
			tmp_tv.setText(values[idx] + " бали(-ів) з " + outof[idx] + ", "
					+ dates[idx]);
			String show = "";

			for (int i = 0; i < n; i++) {
				show += "<b>" + values[i] + "</b> бали(-ів) з <b>" + outof[i] + "</b>, "
						+ dates[i] + "<br>";
			}
			tmp_tv = (TextView) findViewById(R.id.allResults);
			tmp_tv.setTextSize(16);
			tmp_tv.setText(Html.fromHtml(show));
			//tmp_tv.setText(show);
		} catch (FileNotFoundException e) {
			TextView maxScore = (TextView) findViewById(R.id.maxScore);
			maxScore.setText("Досі Ви не проходили тестування");
			
			BufferedWriter out2;
			try {
				out2 = new BufferedWriter(new FileWriter(MY_FILE));
				out2.write("0\n");out2.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}
	}

}
