package my.myMap.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

@Service
public class XMLService {

    @Autowired
    UrlService urlService;

    public static String HttpJson(URL url) {

        String jsonPrintString = null;
        try {

            //http 통신 연결
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            //읽어오기
            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));
            String returnLine;
            String a="";
            //읽기
            while ((returnLine = bufferedReader.readLine()) != null) {
                a += returnLine;
            }
            //연결 끊기
            bufferedReader.close();
            urlConnection.disconnect();

            //읽은거 JSON 으로 파싱
            JSONObject jsonObject = XML.toJSONObject(a);
            jsonPrintString =  jsonObject.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonPrintString;
    }

    public static HashMap<String, Object> getItem(URL url) throws IOException {


        String jsonPrintString = HttpJson(url);

        //json -> map
        HashMap<String, Object> jsonMap = new ObjectMapper().readValue(jsonPrintString, HashMap.class);
        HashMap<String, Object> serviceResult = (HashMap<String, Object>) jsonMap.get("ServiceResult");
        HashMap<String, Object> msgBody = (HashMap<String, Object>) serviceResult.get("msgBody");
        HashMap<String, Object> itemList = (HashMap<String, Object>) msgBody.get("itemList");

        return itemList;
    }

    public static List getItemList(URL url) throws IOException {

         String jsonPrintString = HttpJson(url);
        System.out.println("url = " + jsonPrintString);
        //json -> map
        HashMap<String, Object> jsonMap = new ObjectMapper().readValue(jsonPrintString, HashMap.class);
        HashMap<String, Object> serviceResult = (HashMap<String, Object>) jsonMap.get("ServiceResult");
        HashMap<String, Object> msgBody = (HashMap<String, Object>) serviceResult.get("msgBody");
        List itemList = (List) msgBody.get("itemList");

        return itemList;

    }


}
