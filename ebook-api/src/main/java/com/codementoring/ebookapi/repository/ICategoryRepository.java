package com.codementoring.ebookapi.repository;

import com.codementoring.ebookapi.model.Category;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ICategoryRepository extends IGenericRepository<Category, Integer> {

    boolean existsByNameOrDescription(String name, String description);
    boolean existsByidCategory(Integer idCategory);

    @Query("FROM Category c WHERE c.name = ?1 AND c.description LIKE %?2%")
    List<Category> getByNameAndDescription(String name, String description);
}
