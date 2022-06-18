package my.myMap.controller;


import my.myMap.model.Bus;
import my.myMap.model.busID;
import my.myMap.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("bus")
public class BusSearchController {

    @Autowired
    private JsonService jsonService;

    @GetMapping("/BusSearch")
    @ResponseBody
    public List<Map<String, Object>> busNumJsonParser(Bus busNum) throws IOException {
        busID busID = jsonService.JsonTransNum(busNum);
        List<Map<String, Object>> busIDS = jsonService.NumTransID(busID.getBusRouteId());

        return  busIDS;

    }
}