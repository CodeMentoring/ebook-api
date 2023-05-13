package com.codementoring.ebookapi.service.impl;

import com.codementoring.ebookapi.exception.DataAlreadyExistsException;
import com.codementoring.ebookapi.exception.ModelNotFoundException;
import com.codementoring.ebookapi.model.Category;
import com.codementoring.ebookapi.repository.ICategoryRepository;
import com.codementoring.ebookapi.repository.IGenericRepository;
import com.codementoring.ebookapi.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends CRUDImpl<Category, Integer> implements ICategoryService {

    private final ICategoryRepository repo;


    @Override
    protected IGenericRepository<Category, Integer> getRepo() {
        return repo;
    }

    @Override
    public Category save(Category category) throws Exception {
        String name = category.getName();
        String description = category.getDescription();

        if (isCategoryDuplicate(name, description)) {
            throw new DataAlreadyExistsException("La categoría ya existe con el nombre y/o descripcion que has ingresado.");
        }

        return super.save(category);
    }

    @Override
    public Category update(Category category, Integer id) throws Exception {

        String name = category.getName();
        String description = category.getDescription();

        getRepo().findById(id).orElseThrow( () -> new ModelNotFoundException("La categoría con id " + id + " no existe."));

        if (isCategoryDuplicateUpdate(name, description)) {
            throw new DataAlreadyExistsException("La categoría ya existe con el nombre y/o descripción que has ingresado.");
        }

        return super.update(category,id);
    }


    public boolean isCategoryDuplicate(String name, String description) {
        return repo.existsByNameOrDescription(name, description);
    }

    public boolean isCategoryDuplicateUpdate(String name, String description) {
        return repo.existsByNameAndDescription(name, description);
    }


}
