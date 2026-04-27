package com.record.register.service.impl;

import com.record.register.dto.UserDto;
import com.record.register.entity.Users;
import com.record.register.exception.UserAlreadyExistsException;
import com.record.register.gcs.GcsStorageService;
import com.record.register.mapper.UserMapper;
import com.record.register.repository.UserRepository;
import com.record.register.service.IRegisterService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterServiceImpl implements IRegisterService {

    public final UserRepository userRepository;
    public final GcsStorageService gcsStorageService;
    public final PasswordEncoder passwordEncoder;

    public RegisterServiceImpl(UserRepository userRepository, GcsStorageService gcsStorageService, PasswordEncoder passwordEncoder){
        this.gcsStorageService = gcsStorageService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserDto userDto) {
        Optional<Users> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            throw new UserAlreadyExistsException("User already registered with the given email");
        }

        Optional<Users> optionalUserMobile = userRepository.findByMobileNumber(userDto.getMobileNumber());
        if(optionalUserMobile.isPresent()){
            throw new UserAlreadyExistsException("User already registered with the given mobile number");
        }

        String encode = passwordEncoder.encode(userDto.getPassword());

        Users user = UserMapper.mapToUsers(userDto, new Users());
        user.setRole("USER");
        user.setStatus("PENDING");
        user.setPassword(encode);
    }
}
