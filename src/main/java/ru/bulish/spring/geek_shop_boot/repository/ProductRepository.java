package ru.bulish.spring.geek_shop_boot.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.bulish.spring.geek_shop_boot.entity.Product;


/**
 * Interface Repository layer for Product Entity
 * @author Spring DATA JPA
 * @see JpaRepository
 */
public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {

}
