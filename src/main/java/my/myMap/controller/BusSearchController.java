package my.myMap.controller;


import my.myMap.model.Bus;
import my.myMap.model.busID;
import my.myMap.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;


@Controller
@RequestMapping(value = "bus", method = RequestMethod.GET)
public class BusSearchController {

    @Autowired
    private JsonService jsonService;

    @GetMapping("/BusSearch")
    @ResponseBody
    public String busNumJsonParser(Bus busNum) throws IOException {
        busID busID = jsonService.JsonTransNum(busNum);
        String busIDS = jsonService.NumTransID(busID.getBusRouteId());
        return busIDS;
    }
}