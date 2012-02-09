package com.h.h.h;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Display;
import android.view.Window;
import android.widget.TextView;

public class About extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		GradientDrawable gd = (GradientDrawable) getApplicationContext().getResources().getDrawable(R.drawable.grad);
        Display display = getWindowManager().getDefaultDisplay(); 
        int width = display.getWidth();
        int height = display.getHeight();
    	gd.setGradientRadius((float) (Math.max(width,height)*0.5 + 20));
		
		TextView mTextSample = (TextView) findViewById(R.id.textView1);
		mTextSample.setMovementMethod(LinkMovementMethod.getInstance());
		String text = "Дана програма виконана в рамках конкурсу \"Золотий Байт\".<p></p><a href='http://vk.com/cupidon4uk'>Миляник Іван</a><p></p><a href='http://vk.com/haawa'>Харчишин Андрій</a>";
		mTextSample.setText(Html.fromHtml(text));
	}
	
	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		Window window = getWindow();
		// Eliminates color banding
		window.setFormat(PixelFormat.RGBA_8888);
	}
}
