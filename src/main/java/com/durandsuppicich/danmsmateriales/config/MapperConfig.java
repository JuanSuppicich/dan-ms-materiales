package com.durandsuppicich.danmsmateriales.config;

import com.durandsuppicich.danmsmateriales.mapper.IProductMapper;
import com.durandsuppicich.danmsmateriales.mapper.ProductMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public IProductMapper orderMapper() {
        return new ProductMapper();
    }
}
