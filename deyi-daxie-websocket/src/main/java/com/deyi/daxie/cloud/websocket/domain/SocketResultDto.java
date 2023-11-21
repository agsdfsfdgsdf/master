package com.deyi.daxie.cloud.websocket.domain;

import lombok.Data;

/**
 * Description:
 *
 * @author Chen Xu
 * @date 2023/6/6
 */
@Data
public class SocketResultDto<T> {
    private String method;
    private T data;
}
