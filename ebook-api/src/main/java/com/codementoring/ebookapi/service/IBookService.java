package com.codementoring.ebookapi.service;

import com.codementoring.ebookapi.model.Book;

import java.util.List;

public interface IBookService extends ICRUD<Book, Integer>{

    boolean isBookDuplicate(String isbn);
    boolean existsCategoryById(Integer idCategory);

    List<Book> findBooksByIsbn(String isbn);

}