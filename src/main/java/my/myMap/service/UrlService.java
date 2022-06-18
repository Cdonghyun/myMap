package my.myMap.service;

import lombok.SneakyThrows;
import my.myMap.model.Bus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URL;

@Service
public class UrlService {

    private String RouteList = "http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList?";
    private String RouteLocation = "http://ws.bus.go.kr/api/rest/buspos/getBusPosByRtid?";
    private String serviceKey = "serviceKey=PFJo24LCvJzZzwvBaJscmSUs%2BRCK7VhdXDqP754kX2LM4c9%2B%2BPIulcBYh2m1A1POKCHhdzUouN4Nn26pFML47A%3D%3D";


    @SneakyThrows
    public URL RouteNum(@RequestParam Bus busNum) {

        String url = RouteList + serviceKey + "&strSrch=" + busNum.getBusNum();
        URL u = new URL(url);
        System.out.println(u);
        return u;
    }

    @SneakyThrows
    public URL RouteID(int Id){

        String url = RouteLocation + serviceKey + "&busRouteId=" + Id;
        URL u = new URL(url);
        System.out.println(u);
        return u;
    }
}