package com.codementoring.ebookapi.repository;

import com.codementoring.ebookapi.model.Category;


public interface ICategoryRepository extends IGenericRepository<Category, Integer> {

    boolean existsByNameOrDescription(String name, String description);
    boolean existsByidCategory(Integer idCategory);
}
