package com.aizoon.productproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aizoon.productproject.model.Category;
import com.aizoon.productproject.service.CategoryService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class CategoryController extends BaseController {

	@Autowired
	private CategoryService catServ;

	@GetMapping("/public/categories")
	public List<Category> getAllCategories() {
		return catServ.getCategories();
	}
}
