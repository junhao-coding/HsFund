package com.fund.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/12  21:21
 */
@Data
@NoArgsConstructor
public class Page<T> {
    private List<T> records;
    private long total;
    private int pages;

    public Page(List<T> records, long total, int pages) {
        this.records = records;
        this.total = total;
        this.pages = pages;
    }
}
