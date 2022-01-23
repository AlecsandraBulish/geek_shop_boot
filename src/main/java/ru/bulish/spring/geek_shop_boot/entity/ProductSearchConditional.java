package ru.bulish.spring.geek_shop_boot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchConditional {

    private int pageInx = 1;
    private int size = 5;

    private String title;
    private Integer minPrice;
    private Integer maxPrice;



    public int getPageInx() {
        return pageInx;
    }
}
