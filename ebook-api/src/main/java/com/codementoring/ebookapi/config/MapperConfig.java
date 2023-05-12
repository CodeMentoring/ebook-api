package com.codementoring.ebookapi.config;


import com.codementoring.ebookapi.dto.CategoryDTO;
import com.codementoring.ebookapi.model.Category;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("categoryMapper")
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();
        TypeMap<CategoryDTO, Category> typeMap1 = mapper.createTypeMap(CategoryDTO.class, Category.class);
        TypeMap<Category, CategoryDTO> typeMap2 = mapper.createTypeMap(Category.class, CategoryDTO.class);

        typeMap1.addMapping(CategoryDTO::getName, (dest, v) -> dest.setName((String) v));
        typeMap2.addMapping(Category::getName, (dest, v) -> dest.setName((String) v));

        typeMap1.addMapping(CategoryDTO::getDescription, (dest, v) -> dest.setDescription((String) v));
        typeMap2.addMapping(Category::getDescription, (dest, v) -> dest.setDescription((String) v));



        return mapper;
    }


}
