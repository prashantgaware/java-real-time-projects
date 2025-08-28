package com.nt.repo;

import com.nt.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRegistrationRepo extends JpaRepository<UserEntity, Long> {
}
