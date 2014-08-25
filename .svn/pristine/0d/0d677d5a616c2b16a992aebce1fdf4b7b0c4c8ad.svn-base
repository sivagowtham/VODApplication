package edu.utdallas.videoOnDemand.CategorySvc;

import java.util.List;

import org.apache.log4j.Logger;

import edu.utdallas.videoOnDemand.CategorySvc.CategoryDTO;
import edu.utdallas.videoOnDemand.CategorySvc.CategoryDTOValidator;
import edu.utdallas.videoOnDemand.dao.CategoryDAO;
import edu.utdallas.videoOnDemand.dao.MovieDAO;
import edu.utdallas.videoOnDemand.dao.impl.DAOException;
import edu.utdallas.videoOnDemand.entities.Category;
import edu.utdallas.videoOnDemand.entities.Movie;
import edu.utdallas.videoOnDemand.movieManagementSvc.MovieDTO;
import edu.utdallas.videoOnDemand.movieManagementSvc.MovieDTOValidator;
import edu.utdallas.videoOnDemand.services.CategoryService;
import edu.utdallas.videoOnDemand.services.ServiceException;

/**
 * @author Amber Gullatte;
 * @date 07/22/2014;
 * @version 1;
 * @job CategoryServiceImp;
 */

public class CategoryServiceImpl implements CategoryService{
	private static final Logger logger = Logger
			.getLogger(CategoryServiceImpl.class);
	
	private CategoryDAO categoryDAO;
	private MovieDAO movieDAO;

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}
	
	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	@Override
	public List<MovieDTO> retrieveMoviesByCategory(Long categoryID)throws ServiceException{
		try {
			List<Movie> movies = movieDAO.retrieveMoviesByCategory(categoryID);
			List<MovieDTO> moviesDTO = MovieDTOValidator.covertToDTO(movies);
			return moviesDTO;
		} catch (Exception ex) {
			throw new ServiceException(ex.getMessage(), ex);
		}
	}
	
	@Override
	public CategoryDTO retrieveCategory(Long categoryID)throws ServiceException{
		try {
			Category category = categoryDAO.retrieveCategory(categoryID);
			CategoryDTO categoryDTO = new CategoryDTO(category);
			return categoryDTO;
		} catch (Exception ex) {
			throw new ServiceException(ex.getMessage(), ex);
		}
		
	}
	/*
	 * retrieve all Categories
	 */
	@Override
	public List<CategoryDTO> retrieveAllCategories() throws ServiceException {
		try {
			List<Category> category = categoryDAO.retrieveAllCategories();
			List<CategoryDTO> categoryDTO = CategoryDTOValidator.covertToDTO(category);
			
			return categoryDTO;
		} catch (Exception ex) {
			throw new ServiceException(ex.getMessage(), ex);
		}
	}

	/*
	 * add a Category
	 */
	@Override
	public void addCategory(CategoryDTO categoryDTO)
			throws ServiceException{
		Category category = CategoryDTOValidator.convert(categoryDTO);
		try {
			List<String> categoryTitles = categoryDAO.retriveAllCategoryTitles();
			if(!categoryTitles.contains(category.getTitle()))
			{
				categoryDAO.addCategory(category);
				logger.info("New Category Added " + category.getTitle());
			}
			else
			{
				logger.info("Category with same title already available in the Database");
			}

		} catch (DAOException ex) {
			throw new ServiceException(ex.getMessage(), ex);
		}
	}
	/*
	 * update a Category
	 */
	@Override
	public void updateCategory(CategoryDTO categoryDTO)
			throws ServiceException{
		try {
			Category category = CategoryDTOValidator.convert(categoryDTO);
			categoryDAO.updateCategory(category);
	
		} catch (DAOException ex) {
			throw new ServiceException(ex.getMessage(), ex);
		}
		
	}
	/*
	 * delete a Category
	 */
	@Override
	public void deleteCategory(Long categoryID)
			throws ServiceException{
		try {
			categoryDAO.deleteCategory(categoryID);
			logger.info("Category Removed " + categoryID);
		} catch (Exception ex) {
			throw new ServiceException(ex.getMessage(), ex);
		}
	}
}
