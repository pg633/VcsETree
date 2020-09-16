package com.pg.vst.common.parall;

import com.google.common.collect.Maps;
import com.pg.vst.common.tasks.IndicatorExeTask;
import com.pg.vst.common.tasks.Task;
import com.pg.vst.common.vo.TaskFlow;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/9/10 4:54 下午
 */
@Slf4j
@Service
public class PallExec {
    @Autowired
    private ApplicationContext applicationContext;
    /**
     * 并行执行
     * @param tasks
     * @param timeout
     * @return
     */
    public Map<String, Object> pallExec(Map<String, Task> tasks,
                                        int timeout) {
        return tasks.entrySet().parallelStream().map(k -> {
            String key = k.getKey();
            Task value = k.getValue();
            Object obj = null;
            try {
                obj = CompletableFuture.supplyAsync(() -> k.getValue().doTask())
                        .get(timeout, TimeUnit.MILLISECONDS);
            } catch (TimeoutException e) {
                log.error("## 异步指标 {} 超时 ", key);
            } catch (Exception e) {
                log.error("## 指标异常 ");
            }
            return new ImmutablePair<String, Object>(key, obj);
        }).filter(Objects::nonNull)
                .collect(Collectors.toMap(
                        ImmutablePair::getLeft,
                        ImmutablePair::getRight,
                        (k1, k2) -> k1
                ));
    }

    /**
     * 并发层级指标分组查询
     * @param tasks
     * @param contextMap
     * @param timeout
     * @return
     */
    public Map<String, Object> tupleExec(Map<Integer, List<TaskFlow>> tasks,
                                         Map<String, Object> contextMap,
                                         int timeout) {
        tasks.entrySet().stream()
                .forEach(s -> {
                    Integer level = s.getKey();
                    List<TaskFlow> taskFlows = s.getValue();
                    Map<String, Task> callMap = Maps.newHashMap();
                    taskFlows.parallelStream().forEach(flow -> {
                        callMap.put(flow.getName(), new IndicatorExeTask(flow, contextMap, timeout,applicationContext));
                    });
                    Map<String, Object> tmpExec = pallExec(callMap, timeout);
                    contextMap.putAll(tmpExec);
                });
        return contextMap;
    }

}
