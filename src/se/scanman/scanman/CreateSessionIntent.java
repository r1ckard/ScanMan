package se.scanman.scanman;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class CreateSessionIntent extends IntentService {

	private static final String TAG = "CreateSessionIntent";

	private final XmlParser xmlParser = new XmlParser();
	private String sessionId;

	public CreateSessionIntent(String name) {
		super(name);
	}

	public CreateSessionIntent() {
		super("");
	}

	@Override
	protected void onHandleIntent(Intent workIntent) {
		// Gets data from the incoming Intent
		// String apiKey = workIntent.getStringExtra("apiKey");

		String response = createSession(ScanManApplication.getApiKey());
		// String response =
		// "<?xml version='1.0' encoding='UTF-8'?><o><app_id type='string'>ert</app_id><device_id type='string'>ert</device_id><retailer_id type='number'>1</retailer_id><session_id type='string'>1f2648b1-518f-485c-998f-bba728521ed1</session_id><user_id type='string'>ert</user_id><validUser type='string'>true</validUser><version type='string'>2.00</version></o>";
		// the above method returns
		// json result {"session_id":"bdcd6201-6af7-4ee1-816c-a6c62c6cb6da","user_id":"ert","app_id":"ert","device_id":"ert","validUser":"true","version":"2.00","retailer_id":1}
		// xml result <?xml version="1.0" encoding="UTF-8"?><o><app_id type="string">ert</app_id><device_id type="string">ert</device_id><retailer_id type="number">1</retailer_id><session_id
		// type="string">1f2648b1-518f-485c-998f-bba728521ed1</session_id><user_id type="string">ert</user_id><validUser type="string">true</validUser><version type="string">2.00</version></o>
		Log.v(TAG, "response=" + response);
		// ...
		// Do work here, based on the contents of dataString
		// ...
		sessionId = xmlParser.getSessionId(response);
		ScanManApplication scanMan = (ScanManApplication) getApplicationContext();
		scanMan.setSessionId(sessionId);

	}

	public String createSession(String apiKey) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("http://api.foodessentials.com/createsession?uid=ert&devid=" + android.provider.Settings.System.ANDROID_ID + "&appid=" + apiKey + "&f=xml&v=2.00&api_key=" + apiKey);
		String line = "";
		try {
			HttpResponse response = httpclient.execute(httpget);
			if (response != null) {
				InputStream inputstream = response.getEntity().getContent();
				line = convertStreamToString(inputstream);
			}
		} catch (Exception e) {
		}
		return line;
	}

	private String convertStreamToString(InputStream is) {
		String line = "";
		StringBuilder total = new StringBuilder();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		try {
			while ((line = rd.readLine()) != null) {
				total.append(line);
			}
		} catch (Exception e) {
			// Toast.makeText(this, "Stream Exception",
			// Toast.LENGTH_SHORT).show();
		}
		return total.toString();
	}
}
