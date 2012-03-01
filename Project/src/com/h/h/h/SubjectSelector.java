package com.h.h.h;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;

public class SubjectSelector extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subject_selector);

		GradientDrawable gd = (GradientDrawable) getApplicationContext().getResources().getDrawable(R.drawable.grad);
        Display display = getWindowManager().getDefaultDisplay(); 
        int width = display.getWidth();
        int height = display.getHeight();
    	gd.setGradientRadius((float) (Math.max(width,height)*0.5 + 20));
		
	}

	public void RunMaths(View view) {

		if (checkfile("ukrm.txt", "/sdcard/")) {
			Intent myIntent = new Intent(SubjectSelector.this,
					ListOfTests.class);
			/* add info about subject */
			startActivity(myIntent);
		}
	}

	public void RunPhysics(View view) {
		if (checkfile("ukrm.txt", "/sdcard/")) {
			Intent myIntent = new Intent(SubjectSelector.this,
					ListOfTests.class);
			/* add info about subject */
			startActivity(myIntent);
		}
	}

	public boolean checkfile(String filename, String dir) {
		File file = new File(dir + filename);
		if (!file.exists()) {

			AlertDialog.Builder builder = new AlertDialog.Builder(
					SubjectSelector.this);
			builder.setMessage("Файлу не знайдено, завантажте будь-ласка")
					.setCancelable(false)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {

									Intent intent = new Intent(
											SubjectSelector.this,
											download.class);
									startActivity(intent);
								}
							})
					.setNegativeButton("Скасувати",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {

								}
							});

			AlertDialog alert = builder.create();
			alert.show();

		}

		return file.exists();
	}
	
	
	
}
