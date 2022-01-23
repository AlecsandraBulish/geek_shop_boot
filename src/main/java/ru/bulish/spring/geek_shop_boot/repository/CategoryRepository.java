package ru.bulish.spring.geek_shop_boot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bulish.spring.geek_shop_boot.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {


}
