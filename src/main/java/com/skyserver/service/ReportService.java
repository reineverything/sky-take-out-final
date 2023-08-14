package com.skyserver.service;

import com.skypojo.vo.OrdersReportVO;
import com.skypojo.vo.SalesTop10ReportVO;
import com.skypojo.vo.TurnoverReportVO;
import com.skypojo.vo.UserReportVO;

import java.io.IOException;
import java.time.LocalDate;

/**
 * @ClassName ReportService
 * @Author shuai
 * @create 2023/8/13 10:33
 * @Instruction 数据统计业务接口
 */
public interface ReportService {
    TurnoverReportVO turnoverStatistics(LocalDate begin, LocalDate end);

    UserReportVO userStatistics(LocalDate begin, LocalDate end);

    void exportData() throws IOException;

    OrdersReportVO ordersStatistics(LocalDate begin, LocalDate end);

    SalesTop10ReportVO top10(LocalDate begin, LocalDate end);
}
