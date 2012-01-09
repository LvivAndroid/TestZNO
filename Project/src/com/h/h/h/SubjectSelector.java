package com.h.h.h;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SubjectSelector extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subject_selector);
	}
	
	public void RunMaths(View view) {
		Intent myIntent = new Intent(SubjectSelector.this, ListOfTests.class);
		/* add info about subject */
		startActivity(myIntent);
	}
	public void RunPhysics(View view) {
		Intent myIntent = new Intent(SubjectSelector.this, ListOfTests.class);
		/* add info about subject */
		startActivity(myIntent);
	}
}
