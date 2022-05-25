package my.myMap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/map")
public class myMapController {

        @GetMapping("/map")
        public String main(){
            return "map/map";
        }
}
