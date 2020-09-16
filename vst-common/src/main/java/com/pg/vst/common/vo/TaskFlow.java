package com.pg.vst.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/9/10 7:24 下午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskFlow {
    int timeout;
    String name;

    String alias;

    Map<String, Param> inputs;
    Map<String, Param> outputs;
    String methodName;
    String claName;
    List<String>   retClaName;
    @Data
    class Param {
        String name;
        String type;
        Object value;
    }
}
