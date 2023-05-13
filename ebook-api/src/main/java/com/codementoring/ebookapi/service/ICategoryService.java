package com.codementoring.ebookapi.service;

import com.codementoring.ebookapi.model.Category;

public interface ICategoryService extends ICRUD<Category, Integer>{
    public boolean isCategoryDuplicate(String name, String description);
}
