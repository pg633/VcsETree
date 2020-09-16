package com.pg.vst.common.tasks;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/9/10 4:44 下午
 */
public interface Task {
    Object doTask() throws ClassNotFoundException;
}
