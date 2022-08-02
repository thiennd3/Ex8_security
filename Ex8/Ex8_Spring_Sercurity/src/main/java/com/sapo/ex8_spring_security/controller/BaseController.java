package com.sapo.ex8_spring_security.controller;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BaseController<T, S> {

    public S getAll(String name,  Integer page, Integer size);

    public S add(T body);

    public S update(Integer Id, T body);

    public S delete(Integer Id);


}
