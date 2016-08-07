package com.ponleu.app.form;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ProductForm {
	
	private Long id;
	private String productName;
	private String productDescription;
	private MultipartFile image;
	private List<MultipartFile> otherFiles;
	private String categoryId;
	private List<AttributeForm> attributes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public List<MultipartFile> getOtherFiles() {
		return otherFiles;
	}
	public void setOtherFiles(List<MultipartFile> otherFiles) {
		this.otherFiles = otherFiles;
	}

	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public List<AttributeForm> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<AttributeForm> attributes) {
		this.attributes = attributes;
	}

	
}
