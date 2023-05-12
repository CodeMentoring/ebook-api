package com.codementoring.ebookapi.controller;


import com.codementoring.ebookapi.dto.CategoryDTO;
import com.codementoring.ebookapi.model.Category;
import com.codementoring.ebookapi.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {


    private final ICategoryService service;

    @Qualifier("categoryMapper")
    private final ModelMapper mapper;


    @PostMapping
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO dto) throws Exception {
        Category obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }



    /////////////////////convert mapper//////////////////

    private CategoryDTO convertToDto(Category obj){
        return mapper.map(obj, CategoryDTO.class);
    }

    private Category convertToEntity(CategoryDTO dto){
        return mapper.map(dto, Category.class);
    }

}
