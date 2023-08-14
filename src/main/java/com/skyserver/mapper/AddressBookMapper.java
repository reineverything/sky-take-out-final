package com.skyserver.mapper;

import com.skypojo.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName AddressBookMapper
 * @Author shuai
 * @create 2023/8/6 10:55
 * @Instruction
 */

@Mapper
public interface AddressBookMapper {
    void save(AddressBook addressBook);

    List<AddressBook> list(Long currentId);

    AddressBook getDefault(Long currentId);

    void setAllNotDefault(Long currentId);

    void update(AddressBook addressBook);

    AddressBook getById(Long id);

    void deleteById(Long id);
}
