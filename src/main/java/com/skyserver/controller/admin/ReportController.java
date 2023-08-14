package com.skyserver.controller.admin;

import com.skycommon.result.Result;
import com.skypojo.vo.OrdersReportVO;
import com.skypojo.vo.SalesTop10ReportVO;
import com.skypojo.vo.TurnoverReportVO;
import com.skypojo.vo.UserReportVO;
import com.skyserver.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;

/**
 * @ClassName ReportController
 * @Author shuai
 * @create 2023/8/13 10:25
 * @Instruction 数据统计报表
 */

@Slf4j
@RestController
@RequestMapping("/admin/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 统计营业额
     * @return
     */
    @GetMapping("/turnoverStatistics")
    public Result<TurnoverReportVO> turnoverStatistics(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {//需要用到时分秒用LocalDateTime没有就用LocalDate
        log.info("营业额统计{}-{}",begin,end);
        TurnoverReportVO turnoverReportVO=reportService.turnoverStatistics(begin,end);
        return Result.success(turnoverReportVO);
    }

    /**
     * 用户统计
     */
    @GetMapping("/userStatistics")
    public Result<UserReportVO> userStatistics(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                               @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("用户统计{}{}",begin,end);
        UserReportVO userReportVO=reportService.userStatistics(begin,end);
        return Result.success(userReportVO);
    }

    /**
     * 订单完成率
     */
    @GetMapping("/ordersStatistics")
    public Result<OrdersReportVO> ordersStatistics(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                                   @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("订单统计{}-{}",begin,end);
        OrdersReportVO ordersReportVO=reportService.ordersStatistics(begin,end);
        return Result.success(ordersReportVO);
    }

    /**
     * 销量排行top10
     */
    @GetMapping("/top10")
    public Result<SalesTop10ReportVO> top10(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("查询销量前十菜品：{}-{}",begin,end);
        SalesTop10ReportVO salesTop10ReportVO=reportService.top10(begin,end);
        return Result.success(salesTop10ReportVO);
    }

    /**
     * 导出excel
     */
    @GetMapping("/export")
    public void exportData() throws IOException {
        reportService.exportData();
    }
}
