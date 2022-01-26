package ru.bulish.spring.geek_shop_boot.service;



import ru.bulish.spring.geek_shop_boot.entity.RoleEntity;

import java.util.List;

/**
 * Interface Service layer for Role to implement business logic
 * @author Sorokina
 * @see ru.bulish.spring.geek_shop_boot.service.impl.RoleServiceImpl
 */
public interface RoleService {

    RoleEntity findByName(String name);

    List<RoleEntity> findAll();
}
