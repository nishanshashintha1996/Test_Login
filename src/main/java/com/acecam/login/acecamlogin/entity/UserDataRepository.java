package com.acecam.login.acecamlogin.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserDataRepository extends JpaRepository<UserData, Integer> {
    UserData findByUserName(String userName);
}
