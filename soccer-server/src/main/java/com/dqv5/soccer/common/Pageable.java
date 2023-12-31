package com.dqv5.soccer.common;

import lombok.Data;

/**
 * @author duqian
 * @date 2023/12/6
 */
@Data
public class Pageable {
    private int pageNumber;
    private int pageSize;

    public static Pageable of(int pageNumber, int pageSize) {
        Pageable pageable = new Pageable();
        pageable.setPageNumber(pageNumber);
        pageable.setPageSize(pageSize);
        return pageable;
    }
}
