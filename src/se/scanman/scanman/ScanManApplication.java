package se.scanman.scanman;

import android.app.Application;

public class ScanManApplication extends Application {
	private static final String API_KEY = "tyn2h7fv9jv59szvk95j3qjr";
	private String sessionId = "666e67fc-84d1-4165-8c85-e4d9f9e708d4";

	private String upc = "076840100477";// 021131501013//050200559105

	public ScanManApplication() {
		super();
	}

	String getUpc() {
		return upc;
	}

	void setUpc(String upc) {
		this.upc = upc;
	}

	String getSessionId() {
		return sessionId;
	}

	void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	static String getApiKey() {
		return API_KEY;
	}
}
