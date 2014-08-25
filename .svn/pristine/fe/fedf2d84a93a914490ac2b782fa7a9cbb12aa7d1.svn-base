package edu.utdallas.videoOnDemand.CategorySvc;

import java.io.Serializable;

import edu.utdallas.videoOnDemand.entities.Category;

@SuppressWarnings("serial")
public class CategoryDTO implements Serializable{
	private Long categoryID;
	private String categoryTitle;
	private int displayOrder;
	
	public CategoryDTO()
	{
		
	}
	public CategoryDTO(Category category)
	{
		this.categoryID = category.getCategoryID();
		this.categoryTitle = category.getTitle();
		this.displayOrder = category.getOrder();
	}

	public Long getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}
	public String getTitle() {
		return categoryTitle;
	}
	public void setTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public int getOrder() {
		return displayOrder;
	}
	public void setOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
}
