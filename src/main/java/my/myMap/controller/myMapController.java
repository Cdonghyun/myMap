package my.myMap.controller;

import my.myMap.model.Bus;
import my.myMap.model.busID;
import my.myMap.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/map")
public class myMapController {

        @GetMapping("/map")
        public String main(){
            return "map/map";
        }


}
