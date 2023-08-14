package com.skyserver.service.serviceImpl;

import com.skycommon.utils.ThreadLocalUtil;
import com.skypojo.entity.AddressBook;
import com.skyserver.annotation.AutoFill;
import com.skyserver.mapper.AddressBookMapper;
import com.skyserver.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName AddressBookServiceImpl
 * @Author shuai
 * @create 2023/8/6 10:56
 * @Instruction
 */
@Service
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    private AddressBookMapper addressBookMapper;

    @Override
    public void save(AddressBook addressBook) {
        addressBook.setUserId((long)(int) ThreadLocalUtil.get());
        addressBook.setIsDefault(0);
        addressBookMapper.save(addressBook);
    }

    @Override
    public List<AddressBook> list() {
        Long currentId=(long)(int)ThreadLocalUtil.get();
        return addressBookMapper.list(currentId);
    }

    @Override
    public AddressBook getDefault() {
        Long currentId=(long)(int)ThreadLocalUtil.get();
        return addressBookMapper.getDefault(currentId);
    }

    @Override
    public void setDefault(AddressBook addressBook) {

        //将其他所有地址的is_default改成0
        Long currentId=(long)(int)ThreadLocalUtil.get();
        addressBookMapper.setAllNotDefault(currentId);

        //将要设置的地址改成1（动态sql）
        addressBook.setIsDefault(1);
        addressBookMapper.update(addressBook);

    }

    @Override
    public AddressBook getById(Long id) {
        return addressBookMapper.getById(id);
    }

    @Override
    public void update(AddressBook addressBook) {
        addressBookMapper.update(addressBook);
    }

    @Override
    public void deleteById(Long id) {
        addressBookMapper.deleteById(id);
    }
}
