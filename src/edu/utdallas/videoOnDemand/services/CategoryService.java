package edu.utdallas.videoOnDemand.services;

import java.util.List;

import edu.utdallas.videoOnDemand.CategorySvc.CategoryDTO;
import edu.utdallas.videoOnDemand.movieManagementSvc.MovieDTO;

/**
 * @author Amber Gullatte;
 * @date 07/22/2014;
 * @version 1;
 * @job CategoryService;
 */

public interface CategoryService {
	/*
	 * retrieve Movies in a Category
	 */
	List<MovieDTO> retrieveMoviesByCategory(Long categoryID)
			throws ServiceException;

	/*
	 * retrieve all Categories
	 */
	List<CategoryDTO> retrieveAllCategories()
			throws ServiceException;
	/*
	 * retrieve a Category
	 */
	CategoryDTO retrieveCategory(Long categoryID)
			throws ServiceException;
	/*
	 * add a Category
	 */
	void addCategory(CategoryDTO category)
			throws ServiceException;
	/*
	 * update a Category
	 */
	void updateCategory(CategoryDTO category)
			throws ServiceException;
	/*
	 * delete a Category
	 */
	void deleteCategory(Long categoryID)
			throws ServiceException;

	
}
