package com.h.h.h;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Test extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		char []Letters = {'А','Б','В','Г','Д'};
		setContentView(R.layout.test);
		final Intent myIntent = getIntent();
		final String a = myIntent.getStringExtra("com.h.h.h.a");
		TextView tv = (TextView) findViewById(R.id.textView1);
		tv.setText("Завдання №" + a);
		//Toast.makeText(getApplicationContext(), ""+Letters[Singleton.getInstance().test.correct_answer], Toast.LENGTH_SHORT).show();
		
		
		//final char corr = 'Г';
		final char corr = Letters[Singleton.getInstance().test.correct_answer];
		
		
		GlobalState gs = (GlobalState) getApplication();

		gs.setcorr(corr + "");
		gs.setnumb(Integer.parseInt(a));

		setTest();
		/* START buttons configuration */
		Button ABut = (Button) findViewById(R.id.ABut);
		ABut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GlobalState gs = (GlobalState) getApplication();
				gs.setansw("А");
				setResult(RESULT_OK);
				finish();
			}
		});

		Button BBut = (Button) findViewById(R.id.BBut);
		BBut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GlobalState gs = (GlobalState) getApplication();
				gs.setansw("Б");
				setResult(RESULT_OK);
				finish();
			}
		});

		Button CBut = (Button) findViewById(R.id.CBut);
		CBut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GlobalState gs = (GlobalState) getApplication();
				gs.setansw("В");
				setResult(RESULT_OK);
				finish();
			}
		});

		Button DBut = (Button) findViewById(R.id.DBut);
		DBut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GlobalState gs = (GlobalState) getApplication();
				gs.setansw("Г");
				setResult(RESULT_OK);
				finish();
			}
		});

		Button EBut = (Button) findViewById(R.id.EBut);
		EBut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GlobalState gs = (GlobalState) getApplication();
				gs.setansw("Д");
				setResult(RESULT_OK);
				finish();
			}
		});
		
		/* END buttons configuration */

	}

	private void setTest() {
		TextView tv = (TextView) findViewById(R.id.textView1);
		tv.setText("Завдання №" + Integer.toString(Singleton.getInstance().ID+1));
		tv = (TextView) findViewById(R.id.Context);
		tv.setText(Singleton.getInstance().test.statement);
		tv = (TextView) findViewById(R.id.ansA);
		tv.setText("A) " + Singleton.getInstance().test.answers[0]);
		tv = (TextView) findViewById(R.id.ansB);
		tv.setText("Б) " + Singleton.getInstance().test.answers[1]);
		tv = (TextView) findViewById(R.id.ansC);
		tv.setText("В) " + Singleton.getInstance().test.answers[2]);
		tv = (TextView) findViewById(R.id.ansD);
		tv.setText("Г) " + Singleton.getInstance().test.answers[3]);
		tv = (TextView) findViewById(R.id.ansE);
		tv.setText("Д) " + Singleton.getInstance().test.answers[4]);
	}
	
	public void onBackPressed() {

		GlobalState gs = (GlobalState) getApplication();
		gs.setback(false);
		setResult(RESULT_OK);
		finish();

	}

}
