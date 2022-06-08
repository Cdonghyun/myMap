package my.myMap.controller;

import org.json.XML;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RestController
@RequestMapping(value = "bus",method = RequestMethod.GET)
public class BusSearchController {

    @GetMapping("/information")
    public String getRouteId() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=PFJo24LCvJzZzwvBaJscmSUs%2BRCK7VhdXDqP754kX2LM4c9%2B%2BPIulcBYh2m1A1POKCHhdzUouN4Nn26pFML47A%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("strSrch", "UTF-8") + "=" + URLEncoder.encode("2012", "UTF-8")); /*노선ID*/
        URL url = new URL(urlBuilder.toString());



        String jsonPrintString = null;
        try {

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));
            String returnLine;

            while((returnLine = bufferedReader.readLine()) != null) {
                urlBuilder.append(returnLine);

            }
            urlConnection.disconnect();
            JSONObject jsonObject = XML.toJSONObject(urlBuilder.toString());
            jsonPrintString = jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonPrintString;
    }
    }

