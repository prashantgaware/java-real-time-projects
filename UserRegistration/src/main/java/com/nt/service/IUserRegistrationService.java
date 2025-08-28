package com.nt.service;

import com.nt.dto.UserDto;

import java.util.List;

public interface IUserRegistrationService {
    List<UserDto> getAll();
    void create(UserDto userDto);
}
