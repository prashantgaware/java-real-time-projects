package com.nt.service.implementation;

import com.nt.dto.UserDto;
import com.nt.entity.UserEntity;
import com.nt.repo.IUserRegistrationRepo;
import com.nt.service.IUserRegistrationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRegistrationService implements IUserRegistrationService {

    private final IUserRegistrationRepo userRegistrationRepo;

    public UserRegistrationService(IUserRegistrationRepo userRegistrationRepo) {
        this.userRegistrationRepo = userRegistrationRepo;
    }

    /*public UserRegistrationService(IUserRegistrationRepo userRegistrationRepo) {
        this.userRegistrationRepo = userRegistrationRepo;
    }*/

    @Override
    public List<UserDto> getAll() {
        return this.userRegistrationRepo.findAll()
                .stream()
                .map(this:: mapToDTO)
                .toList();
    }

    @Override
    public void create(UserDto userDto) {
        this.userRegistrationRepo.save(
                UserEntity.builder()
                        .username(userDto.getUsername())
                        .password(userDto.getPassword())
                        .email(userDto.getEmail())
                        .build()
        );
    }

    private UserDto mapToDTO(UserEntity userEntity) {
        return UserDto.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .build();
    }
}
