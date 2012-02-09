package com.h.h.h;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Test extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		GradientDrawable gd = (GradientDrawable) getApplicationContext().getResources().getDrawable(R.drawable.grad);
        Display display = getWindowManager().getDefaultDisplay(); 
        int width = display.getWidth();
        int height = display.getHeight();
    	gd.setGradientRadius((float) (Math.max(width,height)*0.5 + 20));
		
		String[] Letters = { "�", "�", "�", "�", "�" };
		setContentView(R.layout.test);
		final Intent myIntent = getIntent();
		final String a = myIntent.getStringExtra("com.h.h.h.a");
		TextView tv = (TextView) findViewById(R.id.textView1);
		tv.setText("�������� �" + a);
		// Toast.makeText(getApplicationContext(),
		// ""+Letters[Singleton.getInstance().test.correct_answer],
		// Toast.LENGTH_SHORT).show();

		// final char corr = '�';
		final String corr = Letters[Singleton.getInstance().test.correct_answer];

		GlobalState gs = (GlobalState) getApplication();

		gs.setcorr(corr);
		gs.setnumb(Integer.parseInt(a));

		setTest();
		/* START buttons configuration */
		Button ABut = (Button) findViewById(R.id.ABut);
		if (!gs.getsubm())
			ABut.setEnabled(false);
		ABut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GlobalState gs = (GlobalState) getApplication();
				gs.setansw("�");
				setResult(RESULT_OK);
				finish();
			}
		});

		Button BBut = (Button) findViewById(R.id.BBut);
		if (!gs.getsubm())
			BBut.setEnabled(false);
		BBut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GlobalState gs = (GlobalState) getApplication();
				gs.setansw("�");
				setResult(RESULT_OK);
				finish();
			}
		});

		Button CBut = (Button) findViewById(R.id.CBut);
		if (!gs.getsubm())
			CBut.setEnabled(false);
		CBut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GlobalState gs = (GlobalState) getApplication();
				gs.setansw("�");
				setResult(RESULT_OK);
				finish();
			}
		});

		Button DBut = (Button) findViewById(R.id.DBut);
		if (!gs.getsubm())
			DBut.setEnabled(false);
		DBut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GlobalState gs = (GlobalState) getApplication();
				gs.setansw("�");
				setResult(RESULT_OK);
				finish();
			}
		});

		Button EBut = (Button) findViewById(R.id.EBut);
		if (!gs.getsubm())
			EBut.setEnabled(false);
		EBut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GlobalState gs = (GlobalState) getApplication();
				gs.setansw("�");
				setResult(RESULT_OK);
				finish();
			}
		});

		/* END buttons configuration */

	}

	private void setTest() {
		TextView tv = (TextView) findViewById(R.id.textView1);
		tv.setText("�������� �"
				+ Integer.toString(Singleton.getInstance().ID + 1));
		tv = (TextView) findViewById(R.id.Context);
		tv.setText(Singleton.getInstance().test.statement);
		tv = (TextView) findViewById(R.id.ansA);
		tv.setText("A) " + Singleton.getInstance().test.answers[0]);
		tv = (TextView) findViewById(R.id.ansB);
		tv.setText("�) " + Singleton.getInstance().test.answers[1]);
		tv = (TextView) findViewById(R.id.ansC);
		tv.setText("�) " + Singleton.getInstance().test.answers[2]);
		tv = (TextView) findViewById(R.id.ansD);
		tv.setText("�) " + Singleton.getInstance().test.answers[3]);
		tv = (TextView) findViewById(R.id.ansE);
		tv.setText("�) " + Singleton.getInstance().test.answers[4]);
	}

	public void onBackPressed() {
		GlobalState gs = (GlobalState) getApplication();
		gs.setback(false);
		setResult(RESULT_OK);
		finish();
	}

	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		Window window = getWindow();
		// Eliminates color banding
		window.setFormat(PixelFormat.RGBA_8888);
	}

}
