package com.testterra.main;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class Settings extends Activity {

	String time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);

		GradientDrawable gd = (GradientDrawable) getApplicationContext()
				.getResources().getDrawable(R.drawable.grad);
		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		gd.setGradientRadius((float) (Math.max(width, height) * 0.5 + 20));

		final RadioButton TestAmount1 = (RadioButton) findViewById(R.id.TestAmount1);
		final RadioButton TestAmount2 = (RadioButton) findViewById(R.id.TestAmount2);

		AlternativeDB alt1 = new AlternativeDB(this);

		alt1.open();
		Log.d("set", alt1.getKEY_Numb(1));
		if (Integer.parseInt(alt1.getKEY_Numb(1)) == 12)
			TestAmount1.setChecked(true);
		else
			TestAmount2.setChecked(true);
		alt1.close();

		Button SettingsConf = (Button) findViewById(R.id.SettingsConf);

		final AlternativeDB alt;
		alt = new AlternativeDB(this);

		SettingsConf.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				alt.open();

				if (TestAmount1.isChecked())
					alt.DBexec(12);
				else
					alt.DBexec(20);

				Log.d("Settings", alt.getKEY_Numb(1) + " ->  to DB");

				alt.close();
				finish();
			}
		});
	}

}
