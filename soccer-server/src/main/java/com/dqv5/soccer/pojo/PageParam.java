package com.dqv5.soccer.pojo;

import lombok.Data;

/**
 * @author duq
 * @date 2024/3/3
 */
@Data
public class PageParam {
    private int pageNum = 1;
    private int pageSize = 10;
}
