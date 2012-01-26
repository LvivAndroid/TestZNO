package com.h.h.h;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

public class Statistic extends Activity {
	
	String dates[];
    int values[];
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statistic);
		
		GetAllScore();
		
	}
	
	public boolean isExternalStorageAvailable() {
        boolean state = false;
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            state = true;
        }
        return state;
    }
    
    // Helper Method to Test if external Storage is read only
    public boolean isExternalStorageReadOnly() {
        boolean state = false;
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            state = true;
        }
        return state;
    }
	
	private void GetAllScore() {
		if(!isExternalStorageAvailable() || isExternalStorageReadOnly())
    		return;
        String path = Environment.getExternalStorageDirectory() + File.separator;
        String MY_FILE = path + R.string.scoreFile;
        
        try {
            Scanner s = new Scanner( new FileReader(MY_FILE));
           
            int n = s.nextInt();
            dates = new String[n];
            values = new int[n];
            int idx = 0;
            for(int i=0;i<n;++i) {
            	values[i] = s.nextInt();
            	dates[i] = s.nextLine();
            	if(values[i]>values[idx])
            		idx = i;
            }
            s.close();
            
            int all_tests = 12;
            TextView tmp_tv = (TextView) findViewById(R.id.Title);
            tmp_tv.setVisibility(1);
            tmp_tv = (TextView) findViewById(R.id.fullTable);
            tmp_tv.setVisibility(1);
            tmp_tv = (TextView) findViewById(R.id.maxScore);
            tmp_tv.setText(values[idx] + " бали(-ів) з " + all_tests + ", " + dates[idx]);
            String show = "";
            
            for(int i=0;i<n;i++) {
            	show += values[i] + " бали(-ів) з " + all_tests + ", " + dates[i] + "\n";
            }
            tmp_tv = (TextView) findViewById(R.id.allResults);
            tmp_tv.setText(show);
        }
        catch (FileNotFoundException e) {
        	TextView maxScore = (TextView) findViewById(R.id.maxScore);
        	maxScore.setText("Досі Ви не проходили тестування");
        	e.printStackTrace();
    	}
	}
	
}
