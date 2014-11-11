package se.scanman.scanman;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Entity;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class XmlParser {

	private static final String TAG = "XmlParser";

	public static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		if (child instanceof Element) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}

		if (child instanceof Entity) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}

	public String getSessionId(String xmlRecords) {
		String sessionId = "";
		try {
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();

			is.setCharacterStream(new StringReader(xmlRecords));
			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("o");

			for (int i = 0; i < nodes.getLength(); i++) {
				Element element = (Element) nodes.item(i);

				NodeList name = element.getElementsByTagName("session_id");
				Element line = (Element) name.item(0);
				sessionId = getCharacterDataFromElement(line);
				Log.v(TAG, "session_id=" + sessionId);
			}
		} catch (Exception e) {
			Log.v(TAG, "e=" + e);
		}
		return sessionId;

	}

	public String getProductScore(String xmlRecords) {
		String nutrients = "";
		try {

			XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
			XmlPullParser myParser = xmlFactoryObject.newPullParser();

			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlRecords));

			myParser.setInput(is.getByteStream(), null);

			int event = myParser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {
				String name = myParser.getName();
				System.out.println(name);
				switch (event) {
				case XmlPullParser.START_TAG:
					break;
				case XmlPullParser.END_TAG:
					// if (name.equals("temperature")) {
					// temperature = myParser.getAttributeValue(null, "value");
					// }
					break;
				}
				event = myParser.next();
			}

			// StringReader reader = new StringReader(xmlRecords);
			// final Sample sample = JAXB.unmarshal(reader, Sample.class);
			// List<Entry> additives = sample.getAdditives();
			// for (Entry additive : additives) {
			// System.out.println(additive.getAdditiveName());
			// Log.v(TAG, "additive=" + additive.getAdditiveName());
			// }

			// JAXBContext jaxbContext = JAXBContext.newInstance(Label.class);
			// Unmarshaller u = jaxbContext.createUnmarshaller();
			//
			// DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			// InputSource is = new InputSource();
			// is.setCharacterStream(new StringReader(xmlRecords));
			// Document doc = db.parse(is);
			//
			// Label label = (Label) u.unmarshal(doc);
			// System.out.println(label.getUpc());
			//
			// NodeList o = doc.getElementsByTagName("o");
			// Element xmlLabel = (Element) o.item(0);// ElementImpl
			//
			// NodeList allergenElements = xmlLabel.getElementsByTagName("allergens");// arraylist
			// Element allergenElement = (Element) allergenElements.item(0);
			// // Allergen[] allergens = (Allergen[])allergenElement.getChildNodes();
			// Log.v(TAG, "allergens element name=" + allergenElement.getNodeName());
			// Log.v(TAG, "allergens element name=" + allergenElement.getTagName());
			//
			// NodeList elements = allergenElement.getElementsByTagName("e");
			// Element allergen_1 = (Element) elements.item(0);
			// NodeList allegenName = allergen_1.getElementsByTagName("allergen_name");
			// Element cereal = (Element) allegenName.item(0);
			// nutrients = getCharacterDataFromElement(cereal);
			// for (int i = 0; i < allergenElements.getLength(); i++) {
			// Element element = (Element) allergenElements.item(i);
			// NodeList allergenElement = element.getElementsByTagName("e");
			// Element line = (Element) allergenElement.item(0);
			// String allergens = getCharacterDataFromElement(line);
			// Log.v(TAG, "allergens=" + allergens);
			// }
			// for (int i = 0; i < nodes.getLength(); i++) {
			// Element element = (Element) nodes.item(i);
			//
			// NodeList name = element.getElementsByTagName("additives");
			// Element line = (Element) name.item(0);
			// nutrients = getCharacterDataFromElement(line);
			Log.v(TAG, "additives=" + nutrients);
			// }
		} catch (Exception e) {
			Log.v(TAG, "e=" + e);
		}
		return nutrients;
	}
}
