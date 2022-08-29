package com.wangc.springcloud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author
 * @Description:
 * @date 2022/4/20 15:29
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Result {

    private int code;
    private String message;
    private Object data;
}
