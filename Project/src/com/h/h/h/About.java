package com.h.h.h;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class About extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		
		Button AboutBack = (Button) findViewById(R.id.AboutBack);
		AboutBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent myIntent = new Intent(About.this, Menu.class);
				About.this.startActivity(myIntent);
				
				
			}
		});
		
		
		
		
	}

	
	
	
	
}
