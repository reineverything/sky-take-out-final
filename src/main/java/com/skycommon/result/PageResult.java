package com.skycommon.result;


import java.io.Serializable;
import java.util.List;

/**
 * @ClassName EmployeePageDTO
 * @Author shuai
 * @create 2023/7/30 16:21
 * @Instruction 查询分页结果的结果
 */
public class PageResult implements Serializable {

    private long total;//记录数

    private List records;//总的记录

    public PageResult(){}

    public PageResult(long total, List records) {
        this.total = total;
        this.records = records;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRecords() {
        return records;
    }

    public void setRecords(List records) {
        this.records = records;
    }
}
