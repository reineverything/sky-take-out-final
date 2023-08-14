package com.skyserver.mapper;

import com.skycommon.enumeration.OperationType;
import com.skypojo.dto.SetmealPageQueryDTO;
import com.skypojo.entity.Setmeal;
import com.skypojo.vo.SetmealVO;
import com.skyserver.annotation.AutoFill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * @ClassName SetmealMapper
 * @Author shuai
 * @create 2023/8/1 12:27
 * @Instruction
 */
@Mapper
public interface SetmealMapper {
    Long countByCategoryId(long categoryId);

    List<SetmealVO> list(SetmealPageQueryDTO setmealPageQueryDTO);

    @AutoFill(OperationType.INSERT)
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void save(Setmeal setmeal);

    Setmeal getById(Long id);

    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);

    int countOnSale(List<Long> ids);

    void delete(List<Long> ids);

    List<Setmeal> getByCategoryId(Long categoryId);

    Integer countByStatus(int status);
}
