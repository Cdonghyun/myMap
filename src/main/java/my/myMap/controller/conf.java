package my.myMap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fragments")
public class conf {

    @GetMapping("/home")
    public String home(){
        return "fragments/home";
    }

    @GetMapping("/bustest")
    public String bustest(){
        return "fragments/bustest";
    }


}
