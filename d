[1mdiff --git a/TestTerra/src/com/testterra/main/Download.java b/TestTerra/src/com/testterra/main/Download.java[m
[1mindex f103952..dd23d04 100644[m
[1m--- a/TestTerra/src/com/testterra/main/Download.java[m
[1m+++ b/TestTerra/src/com/testterra/main/Download.java[m
[36m@@ -81,13 +81,25 @@[m [mpublic class Download extends Activity {[m
 [m
 		URL url = new URL(ukrm_url_link);[m
 		URLConnection urlConnection = url.openConnection();[m
[32m+[m[41m		[m
[32m+[m		[32murlConnection.setUseCaches(false);[m
[32m+[m		[32mlong tm1 = urlConnection.getLastModified();[m
 		urlConnection.connect();[m
 		long new_file_size = urlConnection.getContentLength();[m
[32m+[m[41m		[m
[32m+[m[41m		[m
[32m+[m		[32mURL static_url = new URL("http://pitest.org.ua/android/ukrm1.sqlite");[m
[32m+[m		[32mURLConnection static_urlConnection = static_url.openConnection();[m
[32m+[m		[32mlong tm2 = static_urlConnection.getLastModified();[m
[32m+[m[41m		[m
 		new_length = new_file_size;[m
 		File file = new File(ukrm_dir);[m
 		long old_file_size = file.length();[m
 		old_length = old_file_size;[m
[31m-		if (new_file_size > old_file_size)[m
[32m+[m[41m		[m
[32m+[m		[32mtm2 = file.lastModified();[m
[32m+[m[41m		[m
[32m+[m		[32mif (tm2 < tm1)[m
 			return true;[m
 		else {[m
 			CreateToast(getString(R.string.update_noneed_toast));[m
warning: LF will be replaced by CRLF in TestTerra/src/com/testterra/main/Download.java.
The file will have its original line endings in your working directory.
