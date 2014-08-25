package edu.utdallas.videoOnDemand.dao;

import java.util.List;

import edu.utdallas.videoOnDemand.CategorySvc.CategoryDTO;
import edu.utdallas.videoOnDemand.dao.impl.DAOException;
import edu.utdallas.videoOnDemand.entities.Category;
import edu.utdallas.videoOnDemand.services.ServiceException;

/**
 * @author Amber Gullatte;
 * @date 07/22/2014;
 * @version 1;
 * @job CategoryDAO;
 */

public interface CategoryDAO {

	/*
	 * retrieve all Categories
	 */
	List<Category> retrieveAllCategories()
			throws DAOException;
	/*
	 * retrieve all Category's Titles
	 */
	List<String> retriveAllCategoryTitles()
			throws DAOException;
	
	
	/*
	 * retrieve a single Category
	 */
	Category retrieveCategory(Long categoryID)
			throws DAOException;
	
	/*
	 * Insert a Category
	 */
	void addCategory(Category category)
			throws DAOException;
	
	/*
	 * Update a Category
	 */
	void updateCategory(Category category)
			throws DAOException;
	
	/*
	 * Delete a Category
	 */
	void deleteCategory(Long categoryID)
			throws DAOException;

}
