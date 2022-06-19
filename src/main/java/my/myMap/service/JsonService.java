package my.myMap.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import my.myMap.model.Bus;
import my.myMap.model.busID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.*;

@Service
public class JsonService {

    @Autowired
    private UrlService urlService;
    @Autowired
    private XMLService xmlService;

    public busID JsonTransNum(Bus busNum) throws IOException {

        HashMap<String, Object> itemList = xmlService.getItem(urlService.RouteNum(busNum));
        System.out.println("busRouteId = " + itemList.get(("busRouteId")));
        //map -> vo  (model)
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        busID busID = mapper.convertValue(itemList, new TypeReference<busID>() {});
        System.out.println("busID = " + busID);
        return busID;
    }

    public String NumTransID(int busRouteId) throws IOException {

        List itemList = xmlService.getItemList(urlService.RouteID(busRouteId));
        System.out.println("itemList = " + itemList);

        //List<map> -> List<vo> ->json String
        ObjectMapper mapper = new ObjectMapper();
        String jsonList = "";
        jsonList = mapper.writeValueAsString(itemList);
        List<Map<String, Object>> itemMap = mapper.readValue(jsonList, new TypeReference<List<Map<String, Object>>>() {});
        String jsonStr =mapper.writeValueAsString(itemMap);
        System.out.println("itemMap = " + jsonStr);
//            for (Map<String, Object> busID1 : itemMap){
//                System.out.println("busID1.getItemList() = " + busID1.get("gpsX"));
//                System.out.println("busID1.getItemList() = " + busID1.get("gpsY"));
//            }
        return jsonStr;
        }
    }