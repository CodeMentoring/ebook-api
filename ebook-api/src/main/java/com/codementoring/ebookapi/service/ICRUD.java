package com.codementoring.ebookapi.service;

public interface ICRUD<T, ID> {

    T save(T t) throws Exception;

}
