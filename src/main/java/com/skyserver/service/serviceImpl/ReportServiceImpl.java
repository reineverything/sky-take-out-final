package com.skyserver.service.serviceImpl;

import com.skypojo.dto.GoodSalesDTO;
import com.skypojo.dto.OrdersReportDTO;
import com.skypojo.dto.TurnoverReportDTO;
import com.skypojo.dto.UserReportDTO;
import com.skypojo.entity.Orders;
import com.skypojo.vo.*;
import com.skyserver.mapper.OrdersMapper;
import com.skyserver.mapper.UserMapper;
import com.skyserver.service.ReportService;
import com.skyserver.service.WorkSpaceService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName ReportServiceImpl
 * @Author shuai
 * @create 2023/8/13 10:33
 * @Instruction
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WorkSpaceService workSpaceService;

    @Autowired
    private HttpServletResponse httpServletResponse;//响应对象

    @Override
    public TurnoverReportVO turnoverStatistics(LocalDate begin, LocalDate end) {//06-01,06-30
        //1.获取日期列表
        List<String> dateList = getDateList(begin, end);

        //2.统计指定日期的营业额(很重要的sql语句编写！！！）
        LocalDateTime beginTime = LocalDateTime.of(begin, LocalTime.MIN);//06-01-0000
        LocalDateTime endTime = LocalDateTime.of(end, LocalTime.MAX);//06-30-5959
        List<TurnoverReportDTO> turnoverReportDTOList = ordersMapper.selectTurnoverList(beginTime, endTime, Orders.COMPLETED);//传入状态，更加灵活

        //2.2 将原始结果封装成map
        Map<String, BigDecimal> dateMap = turnoverReportDTOList.stream().collect(Collectors.toMap(TurnoverReportDTO::getOrderDate, TurnoverReportDTO::getOrderMoney));

        //2.3 遍历dateList判断，如果对应map没有对应的键，就新增键值对并将值设置为0
        List<BigDecimal> turnoverList = dateList.stream().map(date -> dateMap.containsKey(date) ? dateMap.get(date) : new BigDecimal(0)).collect(Collectors.toList());
        //3封装数据并返回

        //SpringUtils的拼接使用！！！
        return new TurnoverReportVO(StringUtils.join(dateList, ","), StringUtils.join(turnoverList, ","));
    }

    @Override
    public UserReportVO userStatistics(LocalDate begin, LocalDate end) {
        //1获取日期列表
        List<String> dateList = getDateList(begin, end);

        //2获取指定时间内，用户新增列表数据-sql
        LocalDateTime beginTime = LocalDateTime.of(begin, LocalTime.MIN);//06-01-0000
        LocalDateTime endTime = LocalDateTime.of(end, LocalTime.MAX);//06-30-5959
        List<UserReportDTO> userReportDTOList = userMapper.countAddByCreateTime(beginTime, endTime);

        Map<String, Integer> dateMap = userReportDTOList.stream().collect(Collectors.toMap(UserReportDTO::getCreateDate, UserReportDTO::getUserCount));
        List<Integer> newUserList = dateList.stream().map(date -> dateMap.containsKey(date) ? dateMap.get(date) : 0).collect(Collectors.toList());

        //3.获取用户总量列表
        List<Integer> totalUserList = new ArrayList<>();
        Integer baseCount = userMapper.countTotalByCreateTime(beginTime);
        for (Integer add : newUserList
        ) {
            baseCount += add;
            totalUserList.add(baseCount);
        }
        return new UserReportVO(StringUtils.join(dateList, ","), StringUtils.join(totalUserList, ","), StringUtils.join(newUserList, ","));

    }

    @Override
    public void exportData() throws IOException {
        /**
         * 导出一个月的运营数据报表
         */
        //1.获取近一个月时间-开始结束
        LocalDate begin = LocalDate.now().minusDays(30);
        LocalDate end = LocalDate.now().minusDays(1);
        //2.查询数据库-概览
        BusinessDataVO businessData = workSpaceService.getBusinessData(LocalDateTime.of(begin, LocalTime.MIN), LocalDateTime.of(end, LocalTime.MAX));
        String rangeDate = "时间范围: " + begin.toString() + "到" + end.toString();
        //3.查询明细

        //4。加载模板文件，插入数据后会自动转换格式
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("templates/运营数据报表模板.xlsx");//获取类路径下指定文件！！！！
        Workbook workbook = new XSSFWorkbook(inputStream);

        //5.定位单元格填充数据
        Sheet sheet = workbook.getSheetAt(0);
        sheet.getRow(1).getCell(1).setCellValue(rangeDate);//时间
        sheet.getRow(3).getCell(2).setCellValue(businessData.getTurnover());//营业额
        sheet.getRow(3).getCell(4).setCellValue(businessData.getOrderCompletionRate());//完成率
        sheet.getRow(3).getCell(6).setCellValue(businessData.getNewUsers());//新增用户
        sheet.getRow(4).getCell(2).setCellValue(businessData.getValidOrderCount());//有效订单
        sheet.getRow(4).getCell(4).setCellValue(businessData.getUnitPrice());//平均客单价

        //6.下载文件
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        workbook.write(outputStream);//将excel作为流响应给浏览器，浏览器会自动下载!!!
        //7.释放资源
        outputStream.close();
        workbook.close();
    }

    @Override
    public OrdersReportVO ordersStatistics(LocalDate begin, LocalDate end) {
        //1获取日期列表
        List<String> dateList = getDateList(begin, end);
        //2获取指定时间内，订单新增列表数据-sql
        LocalDateTime beginTime = LocalDateTime.of(begin, LocalTime.MIN);//06-01-0000
        LocalDateTime endTime = LocalDateTime.of(end, LocalTime.MAX);//06-30-5959

        List<OrdersReportDTO> validOrdersReportDTOList = ordersMapper.selectOrderList(beginTime, endTime, Orders.COMPLETED);//有效订单列表
        List<OrdersReportDTO> totalOrdersReportDTOList = ordersMapper.selectOrderList(beginTime, endTime, null);//全部订单列表

        //3,将订单列表转成map集合
        Map<String, Integer> validOrdersReportDTOMap = validOrdersReportDTOList.stream().collect(Collectors.toMap(OrdersReportDTO::getOrderTime, OrdersReportDTO::getOrderCount));
        Map<String, Integer> totalOrdersReportDTOMap = totalOrdersReportDTOList.stream().collect(Collectors.toMap(OrdersReportDTO::getOrderTime, OrdersReportDTO::getOrderCount));

        //4.遍历dateList，将map中没有的设置为0
        List<Integer> validOrderCountList = dateList.stream().map(date -> validOrdersReportDTOMap.containsKey(date) ? validOrdersReportDTOMap.get(date) : 0).collect(Collectors.toList());
        List<Integer> totalOrderCountList = dateList.stream().map(date -> totalOrdersReportDTOMap.containsKey(date) ? validOrdersReportDTOMap.get(date) : 0).collect(Collectors.toList());

        int validOrderCount = 0;
        int totalOrderCount = 0;
        for (OrdersReportDTO ordersReportDTO : validOrdersReportDTOList
        ) {
            validOrderCount += ordersReportDTO.getOrderCount();
        }
        for (OrdersReportDTO ordersReportDTO : totalOrdersReportDTOList
        ) {
            totalOrderCount += ordersReportDTO.getOrderCount();
        }
        return new OrdersReportVO(StringUtils.join(dateList, ","), (double) validOrderCount / totalOrderCount,
                StringUtils.join(totalOrderCountList, ","), totalOrderCount, validOrderCount, StringUtils.join(validOrderCountList, ","));
    }

    @Override
    public SalesTop10ReportVO top10(LocalDate begin, LocalDate end) {
        /**
         * 在已完成订单中查询销量前十的套餐信息，并倒序排序展示
         */
        List<String> dateList = getDateList(begin, end);
        LocalDateTime beginTime = LocalDateTime.of(begin, LocalTime.MIN);//06-01-0000
        LocalDateTime endTime = LocalDateTime.of(end, LocalTime.MAX);//06-30-5959

        List<GoodSalesDTO> goodSalesDTOList = ordersMapper.selectTop10(beginTime, endTime);//卖的好的菜品。注意sql的编写
        List<String> names = goodSalesDTOList.stream().map(GoodSalesDTO::getName).collect(Collectors.toList());
        List<Integer> numbers = goodSalesDTOList.stream().map(GoodSalesDTO::getNumber).collect(Collectors.toList());

        return new SalesTop10ReportVO(StringUtils.join(names, ","), StringUtils.join(numbers, ","));
    }

    /**
     * 获取两个LocalDate之间的所有日期，以字符串列表的方式返回
     *
     * @param begin
     * @param end
     * @return
     */
    private List<String> getDateList(LocalDate begin, LocalDate end) {
        List<LocalDate> localDateList = new ArrayList<>();

        LocalDate currentDate = begin;
        while (currentDate.isBefore(end) || currentDate.isEqual(end)) {
            localDateList.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }
        return localDateList.stream().map(LocalDate -> {//组成新的集合用map，里面可以加返回值
            return LocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }).collect(Collectors.toList());
    }
}
