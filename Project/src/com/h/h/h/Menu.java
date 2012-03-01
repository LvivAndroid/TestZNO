package com.h.h.h;

import java.io.File;

import com.h.h.h.Menu;
import com.h.h.h.Settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Menu extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		GradientDrawable gd = (GradientDrawable) getApplicationContext().getResources().getDrawable(R.drawable.grad);
        Display display = getWindowManager().getDefaultDisplay(); 
        int width = display.getWidth();
        int height = display.getHeight();
    	gd.setGradientRadius((float) (Math.max(width,height)*0.5 + 20));

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
			Intent myIntent =  new Intent("com.h.h.h.download");
			startActivity(myIntent);
			return true;
		}
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void onBackPressed() {
		AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this);
		builder.setMessage("Ви справді хочете вийти?")
				.setCancelable(false)
				.setPositiveButton("Вихід",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Singleton.getInstance().call_on_create = true;
								Menu.this.finish();
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
}