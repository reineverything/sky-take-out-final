package com.skyserver.mapper;

import com.skypojo.dto.UserReportDTO;
import com.skypojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName UserMapper
 * @Author shuai
 * @create 2023/8/3 9:33
 * @Instruction
 */

@Mapper
public interface UserMapper {
    User selectByOpenid(String openid);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insert(User user);

    List<UserReportDTO> countAddByCreateTime(LocalDateTime beginTime, LocalDateTime endTime);

    Integer countTotalByCreateTime(LocalDateTime beginTime);

    Integer selectUserCount(LocalDateTime begin, LocalDateTime end);
}
