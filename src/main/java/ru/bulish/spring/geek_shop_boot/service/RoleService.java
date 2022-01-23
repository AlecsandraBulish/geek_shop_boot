package ru.bulish.spring.geek_shop_boot.service;



import ru.bulish.spring.geek_shop_boot.entity.RoleEntity;

import java.util.List;

public interface RoleService {

    RoleEntity findByName(String name);

    List<RoleEntity> findAll();
}
