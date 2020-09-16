package com.pg.vst.web;

import com.pg.vst.web.cache.FuncRouteMap;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/9/10 4:21 下午
 */
@Component
public class BaCacheInit implements CommandLineRunner {
    //    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        System.out.println("run app");
//    }
    @Autowired
    private FuncRouteMap funcRouteMap;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("asd");
        funcRouteMap.inputCache(new ImmutablePair<String, Object>("1", "1"));
    }
}
