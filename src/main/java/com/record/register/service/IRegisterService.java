package com.record.register.service;

import com.record.register.dto.LoginRequestDto;
import com.record.register.dto.LoginResponse;
import com.record.register.dto.UserDto;

public interface IRegisterService {

    void register(UserDto userDto);

    LoginResponse login(LoginRequestDto request);
}
