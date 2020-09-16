package com.pg.vst.web.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableBiMap;
import com.pg.vst.common.vo.TaskFlow;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.Flow;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/9/16 7:59 下午
 */
@Component
@Slf4j
public class FuncRouteMap {
    // 缓存所有数据
    private static Cache<String, TaskFlow> flowRoute = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .build();

    /**
     * 问就是lock 了
     * @param mp
     * @return
     */
    public synchronized Boolean inputCache(ImmutablePair<String, Object> mp) {
        try {
            TaskFlow flow = convertObject(mp.getRight());
            flowRoute.put(mp.getLeft(), flow);
            return true;
        } catch (Exception e) {
            log.error("## in error ", e);
            return false;
        }
    }

    /**
     *
     */
    private TaskFlow convertObject(Object in) {
        //实际转换
        return TaskFlow.builder().name("123").build();
    }
}
