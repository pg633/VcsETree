package com.pg.vst.common.tasks;

import com.google.common.collect.Maps;
import com.pg.vst.common.Indi.FlowBeanCommon;
import com.pg.vst.common.vo.TaskFlow;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编译角度
 * @author lianzheng04
 * @version 1.0
 * @date 2020/9/10 7:43 下午
 */
@Data
@AllArgsConstructor
public class IndicatorExeTask implements Task {
    private TaskFlow flow;
    private Map<String, Object> contextMap;
    private int timeout;
    private ApplicationContext applicaionContext;
    @Override
    public Object doTask() throws ClassNotFoundException {
        HashMap<Object, Object> hashMap = Maps.newHashMap();
        FlowBeanCommon bean = (FlowBeanCommon) applicaionContext.getBean(flow.getName());
        Object caculte = bean.caculte(contextMap);
        hashMap.put(flow.getOutputs(), caculte);
        return hashMap;
    }
}
