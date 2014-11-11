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

public class SearchForProductIntent extends IntentService {

	private static final String TAG = "SearchForProductIntent";

	private final XmlParser xmlParser = new XmlParser();

	public SearchForProductIntent(String name) {
		super(name);
	}

	public SearchForProductIntent() {
		super("");
	}

	@Override
	protected void onHandleIntent(Intent workIntent) {
		// Gets data from the incoming Intent
		// String apiKey = workIntent.getStringExtra("apiKey");
		// String upc = workIntent.getStringExtra("upc");
		ScanManApplication scanMan = (ScanManApplication) getApplicationContext();
		String response = searchForProduct(scanMan.getUpc(), ScanManApplication.getApiKey());
		String fakeResponse = "<?xml version='1.0' encoding='UTF-8'?><o><product class='object'><additives class='array'/><allergens class='array'/><brand type='string'>FRITO-LAY</brand><manufacturer type='string'>FRITO-LAY, INC.</manufacturer><nutrients class='array'><e class='object'><nutrient_fe_level type='string'>6</nutrient_fe_level><nutrient_name type='string'>Calories</nutrient_name><nutrient_uom type='string'>Cal</nutrient_uom><nutrient_value type='string'>130.0</nutrient_value></e><e class='object'><nutrient_fe_level type='string'>7</nutrient_fe_level><nutrient_name type='string'>Dietary Fiber</nutrient_name><nutrient_uom type='string'>g</nutrient_uom><nutrient_value type='string'>1.0</nutrient_value></e><e class='object'><nutrient_fe_level type='string'>5</nutrient_fe_level><nutrient_name type='string'>Protein</nutrient_name><nutrient_uom type='string'>g</nutrient_uom><nutrient_value type='string'>2.0</nutrient_value></e><e class='object'><nutrient_fe_level type='string'>5</nutrient_fe_level><nutrient_name type='string'>Saturated Fat</nutrient_name><nutrient_uom type='string'>g</nutrient_uom><nutrient_value type='string'>1.0</nutrient_value></e><e class='object'><nutrient_fe_level type='string'>7</nutrient_fe_level><nutrient_name type='string'>Sodium</nutrient_name><nutrient_uom type='string'>mg</nutrient_uom><nutrient_value type='string'>230.0</nutrient_value></e><e class='object'><nutrient_fe_level type='string'>5</nutrient_fe_level><nutrient_name type='string'>Sugars</nutrient_name><nutrient_uom type='string'>g</nutrient_uom><nutrient_value type='string'>1.0</nutrient_value></e><e class='object'><nutrient_fe_level type='string'>7</nutrient_fe_level><nutrient_name type='string'>Total Carbohydrate</nutrient_name><nutrient_uom type='string'>g</nutrient_uom><nutrient_value type='string'>20.0</nutrient_value></e><e class='object'><nutrient_fe_level type='string'>6</nutrient_fe_level><nutrient_name type='string'>Total Fat</nutrient_name><nutrient_uom type='string'>g</nutrient_uom><nutrient_value type='string'>5.0</nutrient_value></e></nutrients><procingredients class='array'/><product_name type='string'>CHEETOS, BAKED, CRUNCHY CHEESE</product_name><productscore type='number'>75</productscore><upc type='string'>028400071932</upc></product></o>";
		// String response =
		// "<?xml version='1.0' encoding='UTF-8'?><o><app_id type='string'>ert</app_id><device_id type='string'>ert</device_id><retailer_id type='number'>1</retailer_id><session_id type='string'>1f2648b1-518f-485c-998f-bba728521ed1</session_id><user_id type='string'>ert</user_id><validUser type='string'>true</validUser><version type='string'>2.00</version></o>";
		// the above method returns
		// xml result <?xml version="1.0" encoding="UTF-8"?><o><product class="object"><additives class="array"/><allergens class="array"/><brand type="string">FRITO-LAY</brand><manufacturer type="string">FRITO-LAY, INC.</manufacturer><nutrients
		// class="array"><e class="object"><nutrient_fe_level type="string">6</nutrient_fe_level><nutrient_name type="string">Calories</nutrient_name><nutrient_uom type="string">Cal</nutrient_uom><nutrient_value type="string">130.0</nutrient_value></e><e
		// class="object"><nutrient_fe_level type="string">7</nutrient_fe_level><nutrient_name type="string">Dietary Fiber</nutrient_name><nutrient_uom type="string">g</nutrient_uom><nutrient_value type="string">1.0</nutrient_value></e><e
		// class="object"><nutrient_fe_level type="string">5</nutrient_fe_level><nutrient_name type="string">Protein</nutrient_name><nutrient_uom type="string">g</nutrient_uom><nutrient_value type="string">2.0</nutrient_value></e><e
		// class="object"><nutrient_fe_level type="string">5</nutrient_fe_level><nutrient_name type="string">Saturated Fat</nutrient_name><nutrient_uom type="string">g</nutrient_uom><nutrient_value type="string">1.0</nutrient_value></e><e
		// class="object"><nutrient_fe_level type="string">7</nutrient_fe_level><nutrient_name type="string">Sodium</nutrient_name><nutrient_uom type="string">mg</nutrient_uom><nutrient_value type="string">230.0</nutrient_value></e><e
		// class="object"><nutrient_fe_level type="string">5</nutrient_fe_level><nutrient_name type="string">Sugars</nutrient_name><nutrient_uom type="string">g</nutrient_uom><nutrient_value type="string">1.0</nutrient_value></e><e
		// class="object"><nutrient_fe_level type="string">7</nutrient_fe_level><nutrient_name type="string">Total Carbohydrate</nutrient_name><nutrient_uom type="string">g</nutrient_uom><nutrient_value type="string">20.0</nutrient_value></e><e
		// class="object"><nutrient_fe_level type="string">6</nutrient_fe_level><nutrient_name type="string">Total Fat</nutrient_name><nutrient_uom type="string">g</nutrient_uom><nutrient_value
		// type="string">5.0</nutrient_value></e></nutrients><procingredients class="array"/><product_name type="string">CHEETOS, BAKED, CRUNCHY CHEESE</product_name><productscore type="number">75</productscore><upc
		// type="string">028400071932</upc></product></o>
		Log.v(TAG, "response=" + response);
		// ...
		// Do work here, based on the contents of dataString
		// ...
		xmlParser.getProductScore(fakeResponse);
	}

	public String searchForProduct(final String upc, final String apiKey) {
		ScanManApplication scanMan = (ScanManApplication) getApplicationContext();
		HttpClient httpclient = new DefaultHttpClient();
		// HttpGet httpget = new HttpGet("http://api.foodessentials.com/searchprods?q=" + upc + "&sid=" + sessionId + "&n=5&s=1&f=xml&v=2.00&api_key=" + apiKey);
		// http://api.foodessentials.com/labelarray?u=076840100477&sid=9fe4492b-492e-4336-86b8-7d278e02aa51&n=2&s=0&f=json&api_key=YOUR_API_KEY_HERE
//		String uri = "http://api.foodessentials.com/labelarray?u=" + upc + "&sid=" + scanMan.getSessionId() + "&n=2&s=0&f=xml&api_key=" + apiKey;
		 String uri = "http://api.foodessentials.com/label?u=" + upc + "&sid=" + scanMan.getSessionId() + "&appid=demoApp_01&f=xml&long=38.6300&lat=90.2000&api_key=" + apiKey;
		HttpGet httpget = new HttpGet(uri);
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
