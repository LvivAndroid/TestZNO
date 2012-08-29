package com.testterra.main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.Toast;

public class Download extends Activity {

	public int width;
	public int height;
	public int orientation;
	public static long new_length;
	public static long old_length;
	public String ukrm_dir = "/data/data/com.testterra.main/databases/ukrm.sqlite";
	public String ukrm_url_link = "http://pitest.org.ua/android/ukrm.sqlite";

	public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
	private ProgressDialog mProgressDialog;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.download);

		GradientDrawable gd = (GradientDrawable) getApplicationContext()
				.getResources().getDrawable(R.drawable.grad);
		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		gd.setGradientRadius((float) (Math.max(width, height) * 0.5 + 20));

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		orientation = getResources().getConfiguration().orientation;

		if (orientation == 1) {
			height = metrics.heightPixels;
			width = metrics.widthPixels;
		}

		if (orientation == 2) {
			width = metrics.heightPixels;
			height = metrics.widthPixels;
		}

		// LinearLayout LL = (LinearLayout) findViewById(R.id.linearLayout1);

		try {
			if (ifAvaliableUpdates()) {
				startDownload(ukrm_url_link);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			CreateToast(getString(R.string.update_fail_toast));
			e.printStackTrace();
			finish();
		}

	}

	private boolean ifAvaliableUpdates() throws IOException {

		URL url = new URL(ukrm_url_link);
		URLConnection urlConnection = url.openConnection();
		urlConnection.connect();
		long new_file_size = urlConnection.getContentLength();
		new_length = new_file_size;
		File file = new File(ukrm_dir);
		long old_file_size = file.length();
		old_length = old_file_size;
		if (new_file_size > old_file_size)
			return true;
		else {
			CreateToast(getString(R.string.update_noneed_toast));
			finish();
			return false;

		}
	}

	public void CreateToast(String message) {
		Context context = getApplicationContext();
		CharSequence text = message;
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
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

				try {
					File file = new File(ukrm_dir);
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}

				InputStream input = new BufferedInputStream(url.openStream());
				OutputStream output = new FileOutputStream(ukrm_dir);

				byte data[] = new byte[1024];

				long total = 0;

				while ((count = input.read(data)) != -1) {
					total += count;
					publishProgress("" + (int) ((total * 100) / lenghtOfFile));
					output.write(data, 0, count);
				}

				output.flush();
				output.close();
				input.close();
			} catch (Exception e) {
			}
			return null;

		}

		@Override
		protected void onProgressUpdate(String... progress) {
			mProgressDialog.setProgress(Integer.parseInt(progress[0]));
		}

		@Override
		protected void onPostExecute(String unused) {
			dismissDialog(DIALOG_DOWNLOAD_PROGRESS);
			CreateToast(getString(R.string.update_success_toast));
			finish();
		}
	}

}