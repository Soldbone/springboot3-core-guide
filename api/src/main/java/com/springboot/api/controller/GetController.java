package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")

public class GetController {

    @GetMapping("/hello")
    public String getHello() {
        return "Hello World";
    }

    @GetMapping(value = "/name")
    public String getName() {
        return "Flature";
    }

    @GetMapping(value = "/variable1/{variable}")
    public String getVariable(@PathVariable String variable) {
        return variable;
    }

    @GetMapping(value = "/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var) {
        return var;
    }


    @Operation(summary = "GET 메서드 예제", description = "@RequestParam을 활용한 GET 메서드")
    @GetMapping(value = "/request1")
    public String getRequestParam1(
            @Parameter(name = "name", description = "이름", required = true) @RequestParam String name,
            @Parameter(name = "email", description = "이메일", required = true) @RequestParam String email,
            @Parameter(name = "organization", description = "회사", required = true) @RequestParam String organization) {
        return name + " " + email + " " + organization;
    }

    @GetMapping(value = "/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();

       param.forEach((key, value) -> {key += " " + value+ "\n";});

        return sb.toString();
    }

   @GetMapping(value = "/request3")
    public String getRequestParam3(MemberDto memberDTO) {
        return memberDTO.toString();
   }
}
