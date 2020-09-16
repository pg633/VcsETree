package com.pg.vst.common.parall;

import com.pg.vst.common.tasks.Task;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/9/10 4:45 下午
 */
@AllArgsConstructor
public class SyncPall implements Callable<Object> {
    private final Task task;
    private final CountDownLatch latch;


    @Override
    public Object call() throws Exception {
        try {
            Object obj = task.doTask();
            latch.countDown();
            return obj;
        } catch (Exception e) {
            throw e;
        } finally {
            latch.countDown();
        }
    }
}
