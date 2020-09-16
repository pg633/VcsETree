package com.pg.vst.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/9/16 7:34 下午
 */
@Controller
public class HiController {
    @RequestMapping("/")
    @ResponseBody
    public String getControlelr(){
        return "this a cool controller";
    }
}
