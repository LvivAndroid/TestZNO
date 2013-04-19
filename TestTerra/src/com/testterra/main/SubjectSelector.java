package com.testterra.main;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

public class SubjectSelector extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subject_selector);

		GradientDrawable gd = (GradientDrawable) getApplicationContext()
				.getResources().getDrawable(R.drawable.grad);
		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		gd.setGradientRadius((float) (Math.max(width, height) * 0.5 + 20));

	}

	public void RunUkrm(View view) {
		if (checkfile(getString(R.string.ukrm_database_name),
				getString(R.string.all_databases_path))) {
			Intent myIntent = new Intent(SubjectSelector.this,
					ListOfTests.class);
			/* add info about subject */
			Singleton.getInstance().setTABLE("Ukrm1");
			startActivity(myIntent);
		}
	}
	
	public void RunUkrl(View view) {
		if (checkfile(getString(R.string.ukrm_database_name),
				getString(R.string.all_databases_path))) {
			Intent myIntent = new Intent(SubjectSelector.this,
					ListOfTests.class);
			/* add info about subject */
			Singleton.getInstance().setTABLE("Ukrl1");
			startActivity(myIntent);
		}
	}

	public void RunUkrh(View view) {
		if (checkfile(getString(R.string.ukrm_database_name),
				getString(R.string.all_databases_path))) {
			Intent myIntent = new Intent(SubjectSelector.this,
					ListOfTests.class);
			/* add info about subject */
			Singleton.getInstance().setTABLE("Ukrh1");
			startActivity(myIntent);
		}
	}
	
	public boolean checkfile(String filename, String dir) {
		File file = new File(dir + filename);
		if (!file.exists()) {

			AlertDialog.Builder builder = new AlertDialog.Builder(
					SubjectSelector.this);
			builder.setMessage(getString(R.string.db_not_found_title))
					.setCancelable(false)
					.setPositiveButton(getString(R.string.db_not_found_ok),
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {

									Intent intent = new Intent(
											SubjectSelector.this,
											Download.class);
									startActivity(intent);
								}
							})
					.setNegativeButton(getString(R.string.db_not_found_cancel),
							new DialogInterface.OnClickListener() {
								@Override
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
