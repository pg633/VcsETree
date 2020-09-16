package com.pg.vst.common.parall;

import com.pg.vst.common.tasks.Task;
import lombok.AllArgsConstructor;

import java.util.concurrent.Callable;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/9/10 4:52 下午
 */
@AllArgsConstructor
public class ASyncPall implements Callable<Object> {
    private final Task task;

    @Override
    public Object call() throws Exception {
        try {
            return task.doTask();
        } catch (Exception e) {
            throw e;
        }
    }
}
