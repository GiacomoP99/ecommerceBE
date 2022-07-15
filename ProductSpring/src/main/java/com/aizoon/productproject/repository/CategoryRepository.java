package com.aizoon.productproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aizoon.productproject.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
