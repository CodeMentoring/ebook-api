package com.codementoring.ebookapi.config;


import com.codementoring.ebookapi.dto.BookDTO;
import com.codementoring.ebookapi.dto.CategoryDTO;
import com.codementoring.ebookapi.model.Book;
import com.codementoring.ebookapi.model.Category;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MapperConfig {

    @Primary
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



    @Bean("bookMapper")
    public ModelMapper bookMapper(){
        ModelMapper mapper = new ModelMapper();
        TypeMap<BookDTO, Book> typeMap1 = mapper.createTypeMap(BookDTO.class, Book.class);
        TypeMap<Book, BookDTO> typeMap2 = mapper.createTypeMap(Book.class, BookDTO.class);

        typeMap1.addMapping(BookDTO::getIdCategory, (dest, v) -> dest.getCategory().setIdCategory((Integer) v));
        typeMap2.addMapping(b -> b.getCategory().getIdCategory(), (dest, v) -> dest.setIdCategory((Integer) v));

        return mapper;
    }


}
