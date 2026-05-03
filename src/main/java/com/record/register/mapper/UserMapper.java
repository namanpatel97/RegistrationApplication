package com.record.register.mapper;

import com.record.register.dto.UserDto;
import com.record.register.entity.Users;
import com.record.register.gcs.GcsStorageService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapper {


    public static Users mapToUsers(UserDto userDto, Users users){

        users.setName(userDto.getName());
        users.setEmail(userDto.getEmail());
        users.setMobileNumber(userDto.getMobileNumber());
        return users;
    }
}
