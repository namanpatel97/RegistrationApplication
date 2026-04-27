package com.record.register.controller;


import com.record.register.constants.RegisterConstants;
import com.record.register.dto.ResponseDto;
import com.record.register.dto.UserDto;
import com.record.register.service.IRegisterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/users", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class RegisterController {

    public final IRegisterService registerService;

    public RegisterController(IRegisterService registerService){
        this.registerService = registerService;
    }


    @PostMapping("/register")
    public ResponseEntity<ResponseDto>register(@Valid @RequestBody UserDto userDto){
        registerService.register(userDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(RegisterConstants.STATUS_201, RegisterConstants.MESSAGE_201));
    }
}
