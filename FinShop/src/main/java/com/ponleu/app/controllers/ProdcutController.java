package com.ponleu.app.controllers;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ponleu.app.form.ProductForm;
import com.ponleu.app.services.AttributeService;
import com.ponleu.app.services.CategoryService;

@Controller
@RequestMapping(value = "/products")
public class ProdcutController {

	@Inject
	private AttributeService attributeService;

	@Inject
	private CategoryService categoryService;

	@RequestMapping(value = "/popupDetail", method = RequestMethod.GET)
	public String productDetail() {
		return "product.popup.productDetail";
	}
	
	@RequestMapping(value = "/addNew", method = RequestMethod.GET)
	public String addNew(ModelMap model) {

		model.addAttribute("product", new ProductForm());

		model.addAttribute("categories", categoryService.getAll());
		model.addAttribute("attributes", attributeService.getAll());

		return "product.addNew";

	}

	@RequestMapping(value = "category/popupAddNew", method = RequestMethod.GET)
	public String categoryPopupAddNew() {
		return "category.popup.addNew";
	}

	@RequestMapping(value = "attribute/popupAddNew", method = RequestMethod.GET)
	public String attributePopupAddNew() {
		return "attribute.popup.addNew";
	}

}
