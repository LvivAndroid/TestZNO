package com.testterra.main;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class About extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);

		// GradientDrawable gd = (GradientDrawable)
		// getApplicationContext().getResources().getDrawable(R.drawable.grad);
		// Display display = getWindowManager().getDefaultDisplay();
		// int width = display.getWidth();
		// int height = display.getHeight();
		// gd.setGradientRadius((float) (Math.max(width,height)*0.5 + 20));

		TextView About_text_1 = (TextView) findViewById(R.id.about_text1);
		About_text_1.setMovementMethod(LinkMovementMethod.getInstance());
		About_text_1.setText(getString(R.string.about_text));

		TextView About_text_2 = (TextView) findViewById(R.id.about_text2);
		About_text_2.setMovementMethod(LinkMovementMethod.getInstance());
		String text = getResources().getString(R.string.about_text_2);
		
		About_text_2.setText(Html.fromHtml(text));
	}

}
