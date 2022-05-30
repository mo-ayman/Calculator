package com.example.calc;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CalcController {
    Calculations calculations = new Calculations();

    CalcController() {}
    @GetMapping(value = "/")
    public String h() {
        System.out.print("root request");
        return "Server is running on port 8080 ....";
    }
    @CrossOrigin(origins = "*")
    @PostMapping(value = "calc")
    public Map<String, Object> calc(@RequestBody Calculations data ) {
        System.out.println("data.operand : " + data.operand + "  \n" + "data.operator : "  + data.operator);
        System.out.println("calc received");

        String response = this.calculations.calc(data);
        Map<String, Object> JSON = new HashMap<String, Object>();
        JSON.put("message", response);
        return JSON;
    }



}
