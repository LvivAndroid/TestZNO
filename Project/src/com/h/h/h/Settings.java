package com.h.h.h;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;

public class Settings extends Activity {

	String time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		
		GradientDrawable gd = (GradientDrawable) getApplicationContext().getResources().getDrawable(R.drawable.grad);
        Display display = getWindowManager().getDefaultDisplay(); 
        int width = display.getWidth();
        int height = display.getHeight();
    	gd.setGradientRadius((float) (Math.max(width,height)*0.5 + 20));

		final RadioButton RB1 = (RadioButton) findViewById(R.id.TimeNotv);
		final RadioButton RB2 = (RadioButton) findViewById(R.id.TimeYtv);

		RB1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (RB1.isChecked()) {
					RB1.setChecked(true);
					RB2.setChecked(false);
				}
			}
		});

		RB2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (RB2.isChecked()) {
					RB1.setChecked(false);
					RB2.setChecked(true);
				}
			}
		});

		Button SettingsConf = (Button) findViewById(R.id.SettingsConf);
		
		Helper H = new Helper(this);
		H.open();
		String data = H.getKEY_TEST(13);
		
		SettingsConf.setText(Html.fromHtml(data));
		H.close();
		
		
		
		
		
		
		
		
		SettingsConf.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (RB1.isChecked())
					time = "true";
				else
					time = "false";

				Intent myIntent = new Intent(Settings.this, Menu.class);
				myIntent.putExtra("time", time);
				finish();
			}
		});
	}
	
	
	
}
