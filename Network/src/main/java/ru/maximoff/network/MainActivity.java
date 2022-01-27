package ru.maximoff.network;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		try {
			openSettings(Build.VERSION.SDK_INT >= 30 ? "com.android.phone.settings.RadioInfo" : "com.android.settings.RadioInfo");
		} catch (Exception e) {
			Toast.makeText(this, getString(R.string.error, e.toString()), Toast.LENGTH_LONG).show();
		}
		finish();
    }

	private void openSettings(String component) throws Exception {
		String[] splitComponent = component.split("\\.");
		StringBuilder className = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			if (i > 0) {
				className.append(".");
			}
			className.append(splitComponent[i]);
		}
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setClassName(className.toString(), component);
		startActivity(intent);
	}
}
