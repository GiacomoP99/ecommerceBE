package com.aizoon.productproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aizoon.productproject.exceptions.ResourceNotFoundException;
import com.aizoon.productproject.model.Category;
import com.aizoon.productproject.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository repo;

	public List<Category> getCategories() {
		return repo.findAll();
	}

	public void deleteCategory(Long id) {
		repo.deleteById(id);

	}

	public Category getCategoryById(Long id) throws ResourceNotFoundException {
		Category cat = null;
		cat = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + id));
		return cat;
	}
}
