package se.scanman.scanman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	private static final String TAG = "MainActivity";

	private Button scanBtn;
	private TextView formatTxt, contentTxt;
	private String upc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		scanBtn = (Button) findViewById(R.id.scan_button);
		formatTxt = (TextView) findViewById(R.id.scan_format);
		contentTxt = (TextView) findViewById(R.id.scan_content);

		scanBtn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.scan_button) {
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			// scanIntegrator.initiateScan();
			// run this while testing
			connectToFoodDatabase();
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if (scanResult != null) {
			contentTxt.setText(scanResult.getContents());
			upc = scanResult.getContents();
			ScanManApplication scanMan = (ScanManApplication) getApplicationContext();
			scanMan.setUpc(upc);
		}
		// else continue with any other code you need in the method
		connectToFoodDatabase();
	}

	private void connectToFoodDatabase() {
		// Create session id
		Intent createSessionIntent = new Intent(this, CreateSessionIntent.class);
		// createSessionIntent.putExtra("apiKey", API_KEY);
		this.startService(createSessionIntent);// calls onHandleIntent
		// this.stopService(createSessionIntent);
		Intent searchForProductIntent = new Intent(this, SearchForProductIntent.class);
		// searchForProductIntent.putExtra("apiKey", API_KEY);
		// searchForProductIntent.putExtra("upc", upc);
		this.startService(searchForProductIntent);// calls onHandleIntent

	}
}
