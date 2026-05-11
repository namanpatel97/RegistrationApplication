package com.record.register.service.impl;

import com.record.register.dto.LoginRequestDto;
import com.record.register.dto.LoginResponse;
import com.record.register.dto.UserDto;
import com.record.register.entity.Users;
import com.record.register.exception.UserAlreadyExistsException;
import com.record.register.gcs.GcsStorageService;
import com.record.register.mapper.UserMapper;
import com.record.register.repository.UserRepository;
import com.record.register.security.JwtUtil;
import com.record.register.service.IRegisterService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RegisterServiceImpl implements IRegisterService {

    private final UserRepository userRepository;
    private final GcsStorageService gcsStorageService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public RegisterServiceImpl(UserRepository userRepository, GcsStorageService gcsStorageService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil){
        this.gcsStorageService = gcsStorageService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    @Transactional
    public void register(UserDto userDto) {

        if(userDto.getPhotoUrl() == null || userDto.getPhotoUrl().isEmpty()){
            throw new RuntimeException("Photo is required");
        }

        String contentType = userDto.getPhotoUrl().getContentType();

        if(contentType == null ||
                !(contentType.equals("image/jpeg") ||
                        contentType.equals("image/png") ||
                        contentType.equals("image/jpg"))){
            throw new RuntimeException("Only JPG, JPEG, PNG images are allowed");
        }

        long maxSize = 2 * 1024 * 1024;

        if (userDto.getPhotoUrl().getSize() > maxSize) {
            throw new RuntimeException("Image size must not exceed 2 MB");
        }

        Optional<Users> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            throw new UserAlreadyExistsException("User already registered with the given email");
        }

        Optional<Users> optionalUserMobile = userRepository.findByMobileNumber(userDto.getMobileNumber());
        if(optionalUserMobile.isPresent()){
            throw new UserAlreadyExistsException("User already registered with the given mobile number");
        }

        String encode = passwordEncoder.encode(userDto.getPassword());

        String uploadedFile = gcsStorageService.uploadFile(userDto.getPhotoUrl());

        Users user = UserMapper.mapToUsers(userDto, new Users());
        user.setRole("USER");
        user.setStatus("PENDING");
        user.setPassword(encode);
        user.setPhotoUrl(uploadedFile);

        userRepository.save(user);
    }

    @Override
    @Transactional
    public LoginResponse login(LoginRequestDto request) {

        Users user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new RuntimeException("Email doesn't exists")
        );

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Password does not match");
        }

        user.setLastLoginAt(LocalDateTime.now());
        userRepository.save(user);

        String token = jwtUtil.generateToken(
                user.getUserId(),
                user.getEmail(),
                user.getRole()
        );

        return new LoginResponse(token, "Bearer ");
    }


}
