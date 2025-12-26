package com.example.webchat.storage;

import com.example.webchat.model.dao.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserDao, UUID> {
    /* @Modifying
    @Transactional
    @Query("UPDATE UserDao u SET u.name = :name, u.dateOfBirth = :dateOfBirth WHERE u.id = :id")
    int updateUser(@Param("id") UUID id, @Param("name") String name, @Param("dateOfBirth") Date dateOfBirth); */

    UserDao findFirstByName(String name);
}