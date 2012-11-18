package com.testterra.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Menu extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

		GradientDrawable gd = (GradientDrawable) getApplicationContext()
				.getResources().getDrawable(R.drawable.grad);
		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		gd.setGradientRadius((float) (Math.max(width, height) * 0.5 + 20));

		folderCreate();
		File alt = new File(getString(R.string.all_databases_path)
				+ getString(R.string.alt_database_name));
		if (!alt.exists()) {
			CopyAssets();
		}

		// Перевірка чи є інтернет
		if (isNetworkAvailable()) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(R.string.update_dialog_message)
					.setCancelable(false)
					.setPositiveButton(R.string.update_dialog_yes,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									Intent myIntent = new Intent(Menu.this,
											Download.class);
									startActivity(myIntent);
								}
							})
					.setNegativeButton(R.string.update_dialog_no,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {

								}
							});
			AlertDialog alert = builder.create();
			alert.show();

		}

		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		Log.d(tm.getSimCountryIso() + " -sim location", this.getResources()
				.getConfiguration().locale.getDisplayCountry() + " - location");

		Button ChooseTest = (Button) findViewById(R.id.ChooseTest);
		ChooseTest.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(Menu.this, SubjectSelector.class);
				startActivity(myIntent);
			}
		});

		Button ShowStatistic = (Button) findViewById(R.id.ShowStatistic);
		ShowStatistic.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(Menu.this, Statistic.class);
				startActivity(myIntent);
			}
		});
	}

	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null;
	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.optionmenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.SettingItem: {
			Intent myIntent = new Intent(Menu.this, Settings.class);
			startActivity(myIntent);
			return true;
		}
		case R.id.AboutItem: {
			Intent myIntent = new Intent(Menu.this, About.class);
			startActivity(myIntent);
			return true;
		}
		case R.id.DownloadsItem: {
			Intent myIntent = new Intent(Menu.this, Download.class);
			startActivity(myIntent);
			return true;
		}
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onBackPressed() {
		AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this);
		builder.setMessage(getString(R.string.onback_press_dialog_message))
				.setCancelable(false)
				.setPositiveButton(getString(R.string.onback_press_dialog_yes),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								Singleton.getInstance().call_on_create = true;
								Menu.this.finish();
							}
						})
				.setNegativeButton(getString(R.string.onback_press_dialog_no),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {

							}
						});
		AlertDialog alert = builder.create();
		alert.show();

	}

	private void CopyAssets() {
		AssetManager assetManager = getAssets();
		String[] files = null;
		try {
			files = assetManager.list("");
		} catch (IOException e) {
			Log.e("tag", e.getMessage());
		}
		for (String filename : files) {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = assetManager.open(filename);
				out = new FileOutputStream(
						getString(R.string.all_databases_path) + filename);
				copyFile(in, out);
				in.close();
				in = null;
				out.flush();
				out.close();
				out = null;
			} catch (Exception e) {
				Log.e("tag", e.getMessage());
			}
		}
	}

	private void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
	}

	private void folderCreate() {// Simple folder creating
		File folder = new File("/data/data/com.testterra.main/databases");
		if (!folder.exists()) {
			folder.mkdir();
		}

	}

}