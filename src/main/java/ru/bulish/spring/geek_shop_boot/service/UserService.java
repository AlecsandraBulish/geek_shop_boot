package ru.bulish.spring.geek_shop_boot.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.bulish.spring.geek_shop_boot.entity.UserEntity;

/**
 * Interface Service layer for User to implement business logic
 * @author Sorokina
 * @see ru.bulish.spring.geek_shop_boot.service.impl.UserServiceImpl
 */
public interface UserService {

    UserEntity findByUsername(String username);

    UserEntity save(UserEntity user);

    Page<UserEntity> findAllByPage(Pageable pageRequest);

    void setEnable(Long userId, Boolean enable);

}
