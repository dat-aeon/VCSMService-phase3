package mm.aeon.com.api.outletinfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import mm.aeon.com.dto.outletinfo.OutletInfoResDto;
import mm.aeon.com.zconfig.MessageCode;
import mm.aeon.com.zconfig.exception.BusinessLogicException;

@RestController
@RequestMapping("/outlet-info")
public class OutletInfoController {

	@Value("${properties.outletInfoListApiLink}")
	private String outletInfoListApiLink;

	@Value("${properties.outletImageApiLink}")
	private String outletImageApiLink;

	@Value("${properties.outletLimitMetre}")
	private Double outletLimitMetre;

	@RequestMapping(value = "/outlet-info-list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public OutletInfoResDto getOutletInfoList() throws Exception {
		OutletInfoResDto outletInfoResDto = null;
		try {
			URL url = new URL(outletInfoListApiLink);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new BusinessLogicException(MessageCode.SERVICE_UNAVAILABLE, conn.getResponseMessage());
			}

			JsonParser jp = new JsonParser(); // from gson
			JsonElement root = jp.parse(new InputStreamReader((InputStream) conn.getContent()));
			JsonObject rootobj = root.getAsJsonObject();
			String status = rootobj.get("status").getAsString();

			if (status.equals("SUCCESS")) {
				outletInfoResDto = new Gson().fromJson(rootobj.get("data").getAsJsonObject(), OutletInfoResDto.class);
			}
			conn.disconnect();
		} catch (ConnectException e) {
			throw new BusinessLogicException(MessageCode.SERVICE_UNAVAILABLE, e.getMessage());
		}
		outletInfoResDto.setOutletLimitMetre(outletLimitMetre);
		return outletInfoResDto;

	}

	@RequestMapping(value = "/outlet-image-file/{id:.+}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getOutletImage(@PathVariable("id") String id) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			URL url = new URL(outletImageApiLink + id);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new BusinessLogicException(MessageCode.SERVICE_UNAVAILABLE, conn.getResponseMessage());
			}

			InputStream inputStream = conn.getInputStream();
			byte[] byteChunk = new byte[4096]; // Or whatever size you want to
												// read
												// in at a time.
			int n;
			while ((n = inputStream.read(byteChunk)) > 0) {
				baos.write(byteChunk, 0, n);
			}
		} catch (ConnectException e) {
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(baos.toByteArray());
		}

		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(baos.toByteArray());

	}

	public static void main(String[] args) throws ClassNotFoundException {

		try {

			URL url = new URL("http://10.1.9.70:83/assm2/outlet-image-files/" + "20190625013550841.png");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			InputStream inputStream = conn.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] byteChunk = new byte[4096]; // Or whatever size you want to
												// read in at a time.
			int n;

			while ((n = inputStream.read(byteChunk)) > 0) {
				baos.write(byteChunk, 0, n);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	public void processJSON(Object obj) {
		JSONObject jsonObj = null;
		JSONArray jsonArr = null;
		jsonObj = objectToJSONObject(obj);
		jsonArr = objectToJSONArray(obj);
		if (jsonObj != null) {
			// process JSONObject
		} else if (jsonArr != null) {
			// process JSONArray
		}
	}

	public static JSONObject objectToJSONObject(Object object) {
		Object json = null;
		JSONObject jsonObject = null;
		try {
			json = new JSONTokener(object.toString()).nextValue();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if (json instanceof JSONObject) {
			jsonObject = (JSONObject) json;
		}
		return jsonObject;
	}

	public static JSONArray objectToJSONArray(Object object) {
		Object json = null;
		JSONArray jsonArray = null;
		try {
			json = new JSONTokener(object.toString()).nextValue();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if (json instanceof JSONArray) {
			jsonArray = (JSONArray) json;
		}
		return jsonArray;
	}

}
