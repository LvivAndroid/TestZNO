package com.h.h.h;

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
		
		TextView mTextSample = (TextView) findViewById(R.id.textView1);
		mTextSample.setMovementMethod(LinkMovementMethod.getInstance());
		String text = "Дана програма виконана в рамках конкурсу \"Золотий Байт\".<p></p><a href='http://vk.com/cupidon4uk'>Миляник Іван</a><p></p><a href='http://vk.com/haawa'>Харчишин Андрій</a>";
		mTextSample.setText(Html.fromHtml(text));
	}
}
