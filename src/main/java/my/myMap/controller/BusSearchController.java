package my.myMap.controller;

import my.myMap.model.Bus;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RestController
@RequestMapping("/bus")
public class BusSearchController {

    @GetMapping("/BusSearch")
    public String getRouteId(@RequestParam Bus busNum) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=PFJo24LCvJzZzwvBaJscmSUs%2BRCK7VhdXDqP754kX2LM4c9%2B%2BPIulcBYh2m1A1POKCHhdzUouN4Nn26pFML47A%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("strSrch", "UTF-8") + "=" + URLEncoder.encode(busNum.getBusNum(), "UTF-8")); /*버스번호*/

        System.out.println(busNum);
        URL url = new URL(urlBuilder.toString());
        System.out.println(url);

        String jsonPrintString = null;
        try {
            //http 통신 연결
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            //읽어오기
            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));
            String returnLine;
            //읽은거 보여주기
            while ((returnLine = bufferedReader.readLine()) != null) {
                urlBuilder.append(returnLine);

            }
            //연결 끊기
            urlConnection.disconnect();

            //읽은거 JSON으로 나타내기
            JSONObject jsonObject = XML.toJSONObject(urlBuilder.toString());
            jsonPrintString = jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonPrintString;
    }
}

