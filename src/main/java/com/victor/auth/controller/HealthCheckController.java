package com.victor.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * HealthCheckController
 *
 * @Author nicklbx
 * @Date 2022/8/27 14:04
 */
@Api(tags = "健康检查")
@RestController
public class HealthCheckController {

    @ApiOperation(value = "health-check")
    @GetMapping("/health-check")
    public Map<String,Object> ok(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", "ok");
        map.put("time", new Date());
        return map;
    }

    @ApiImplicitParam(name = "name",value = "姓名",required = true)
    @ApiOperation(value = "向客人问好")
    @GetMapping("/sayHi")
    public ResponseEntity<String> sayHi(@RequestParam(value = "name")String name){
        return ResponseEntity.ok("Hi:"+name);
    }
}
