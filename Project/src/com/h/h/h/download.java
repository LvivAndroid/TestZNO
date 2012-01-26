package com.h.h.h;



import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;




public class download extends Activity {
	
	
	
	public int width;
	public int height;
	public int orientation;
   
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    private ProgressDialog mProgressDialog;
   
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download);

        
        DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		orientation = getResources().getConfiguration().orientation;

		if (orientation == 1) {
			height = metrics.heightPixels;
			width = metrics.widthPixels;
		}

		if (orientation == 2)
		{
			width = metrics.heightPixels;
			height = metrics.widthPixels;
		}

        LinearLayout LL = (LinearLayout) findViewById(R.id.linearLayout1);
        Button startBtn1 = (Button) findViewById(R.id.file1);
        startBtn1.setWidth((int)width/4);
        startBtn1.setOnClickListener(new OnClickListener(){
            public void onClick(View v) {
                startDownload("http://pitest.org.ua/android/ukrm1.txt");
            }
        });
        
        
        
        Button startBtn2 = (Button) findViewById(R.id.file2);
        startBtn1.setWidth((int)width/4);
        startBtn1.setOnClickListener(new OnClickListener(){
            public void onClick(View v) {
                startDownload("http://pitest.org.ua/android/ukrm1.txt");
            }
        });
        
        
    }

    private void startDownload(String url) {
        
        new DownloadFileAsync().execute(url);
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
		case DIALOG_DOWNLOAD_PROGRESS:
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setMessage("Downloading file..");
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
			return mProgressDialog;
		default:
			return null;
        }
    }

class DownloadFileAsync extends AsyncTask<String, String, String> {
   
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		showDialog(DIALOG_DOWNLOAD_PROGRESS);
	}

	@Override
	protected String doInBackground(String... aurl) {
		int count;

	try {

	URL url = new URL(aurl[0]);
	URLConnection conexion = url.openConnection();
	conexion.connect();

	int lenghtOfFile = conexion.getContentLength();
	Log.d("ANDRO_ASYNC", "Lenght of file: " + lenghtOfFile);

	
	try {
        File file = new File("/sdcard/ukrm.txt");
        // delete the file if exists
        file.delete();
    } catch (Exception e) {
        // nothing
    }
	
	
	
	
	InputStream input = new BufferedInputStream(url.openStream());
	OutputStream output = new FileOutputStream("/sdcard/ukrm.txt");

	byte data[] = new byte[1024];

	long total = 0;

		while ((count = input.read(data)) != -1) {
			total += count;
			publishProgress(""+(int)((total*100)/lenghtOfFile));
			output.write(data, 0, count);
		}

		output.flush();
		output.close();
		input.close();
	} catch (Exception e) {}
	return null;

	}
	protected void onProgressUpdate(String... progress) {
		 Log.d("ANDRO_ASYNC",progress[0]);
		 mProgressDialog.setProgress(Integer.parseInt(progress[0]));
	}

	@Override
	protected void onPostExecute(String unused) {
		dismissDialog(DIALOG_DOWNLOAD_PROGRESS);
	}
}
}