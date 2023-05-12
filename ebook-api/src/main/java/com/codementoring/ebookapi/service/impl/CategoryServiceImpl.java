package com.codementoring.ebookapi.service.impl;

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

}
