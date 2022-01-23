package ru.bulish.spring.geek_shop_boot.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.bulish.spring.geek_shop_boot.entity.UserEntity;

public interface UserService {

    UserEntity findByUsername(String username);

    UserEntity save(UserEntity user);

    Page<UserEntity> findAllByPage(Pageable pageRequest);

    void setEnable(Long userId, Boolean enable);

}
