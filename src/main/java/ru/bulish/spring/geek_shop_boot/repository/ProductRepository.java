package ru.bulish.spring.geek_shop_boot.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.bulish.spring.geek_shop_boot.entity.Product;


public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {

//    public Page<Product> findProductByCategories(Specification<Product> spec, PageRequest pageRequest, Category category);


}
