package com.codegym.dao;

import com.codegym.model.Category;


import java.util.List;

public interface ICategoryDAO {
    List<Category> selectAllCategory();
    boolean existByProductId(int id);
}

