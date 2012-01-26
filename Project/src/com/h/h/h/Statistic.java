package com.h.h.h;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Statistic extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statistic);
		Button b = (Button) findViewById(R.id.b);
		
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Downloader.Download("new.txt");
			}
		});
	}
}
