package com.skyserver.service;

import com.skypojo.entity.AddressBook;

import java.util.List;

/**
 * @ClassName AddressBookService
 * @Author shuai
 * @create 2023/8/6 10:55
 * @Instruction
 */
public interface AddressBookService {
    void save(AddressBook addressBook);

    List<AddressBook> list();

    AddressBook getDefault();

    void setDefault(AddressBook addressBook);

    AddressBook getById(Long id);

    void update(AddressBook addressBook);

    void deleteById(Long id);
}
