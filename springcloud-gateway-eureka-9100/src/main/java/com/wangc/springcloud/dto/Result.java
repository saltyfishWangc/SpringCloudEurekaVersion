package com.wangc.springcloud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author
 * @Description:
 * @date 2022/3/23 20:33
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Result {
    private int code;
    private String message;
    private Object result;
}
