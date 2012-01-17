package com.h.h.h;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Test extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.test);
		final Intent myIntent = getIntent();
		final String a = myIntent.getStringExtra("com.h.h.h.a");
		TextView tv = (TextView) findViewById(R.id.textView1);
		tv.setText("Завдання №" + a);

		final char corr = 'Г';

		GlobalState gs = (GlobalState) getApplication();

		gs.setcorr(corr + "");
		gs.setnumb(Integer.parseInt(a));

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

	}

	public void onBackPressed() {

		GlobalState gs = (GlobalState) getApplication();
		gs.setback(false);
		setResult(RESULT_OK);
		finish();

	}

}
