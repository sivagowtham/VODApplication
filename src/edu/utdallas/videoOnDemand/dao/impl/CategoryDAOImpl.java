/**
 * 
 */
package edu.utdallas.videoOnDemand.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import edu.utdallas.videoOnDemand.dao.CategoryDAO;
import edu.utdallas.videoOnDemand.entities.Category;

/**
 * @author Amber Gullatte;
 * @date 07/22/2014;
 * @version 1;
 * @job CategoryDAOImpl;
 */
public class CategoryDAOImpl extends BaseDAO implements CategoryDAO {

	private static final Logger logger = Logger.getLogger(CategoryDAOImpl.class);
	
		
	private final String retrieveAllQuery = "SELECT * FROM TBL_CATEGORIES ORDER BY DISPLAY_ORDER ASC";
	
	@Override
	public List<Category> retrieveAllCategories()
			throws DAOException {
		
		logger.debug("retrieveAllCategoriesTitle");

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		try {
			List<Category> result = jdbcTemplate.query(retrieveAllQuery, new CategoryMapper());
			return result;
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	@Override
	public List<String> retriveAllCategoryTitles() throws DAOException
	{
		logger.debug("Starting retrieveAllMovieTitles");
		List<String> categoryTitles = new ArrayList<String>(); 
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Category> categories = jdbcTemplate.query(retrieveAllQuery,
				new CategoryMapper());
		for (Category category : categories)
		{
			categoryTitles.add(category.getTitle());
		}
		return categoryTitles;
	}
	
private final String retrieveQuery = "SELECT * FROM TBL_CATEGORIES WHERE CATEGORY_ID = ?";
	
	@Override
	public Category retrieveCategory(Long categoryID)
			throws DAOException {
		
		logger.debug("retrieveCategory");
		if (categoryID == null) {
			throw new DAOException(
					"Required key category id");
		}
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		try {
			Object args[] = { categoryID };
			Category result = jdbcTemplate.queryForObject(retrieveQuery, args,new CategoryMapper());
			return result;
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
private final String InsertQuery = "INSERT INTO TBL_CATEGORIES (CATEGORY_TITLE, DISPLAY_ORDER) VALUES (?,?)";
	
	@Override
	public void addCategory(Category category)
			throws DAOException {
		
		logger.debug("addCategory");
		
		if (category.getTitle() == null || category.getOrder() == 0) {
			throw new DAOException(
					"Category Title and Display Order cannot be null");
		}

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object values[] = { category.getTitle(), category.getOrder() };
		jdbcTemplate.update(InsertQuery, values);
	}
	
private final String UpdateQuery = "UPDATE TBL_CATEGORIES SET CATEGORY_TITLE=?, DISPLAY_ORDER=? WHERE CATEGORY_ID=?";
	
	@Override
	public void updateCategory(Category category)
			throws DAOException {
		logger.debug("updateCategory");
		if (category.getTitle() == null || category.getOrder() == 0) {
			throw new DAOException(
					"Category Title and Display Order cannot be null");
		}

		try {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = dataSource.getConnection();
				ps = conn.prepareStatement(UpdateQuery);
				ps.setString(1, category.getTitle()); // TITLE
				ps.setInt(2, category.getOrder()); // DISPLAY_ORDER
				ps.setLong(3, category.getCategoryID()); // CATEGORY_ID
		
				ps.executeUpdate();
			} finally {
				DbUtils.close(ps);
				DbUtils.close(conn);
			}
		} catch (Exception ex) {
			throw new DAOException(ex.getMessage(), ex);
		}
	}
	
private final String DeleteQuery = "DELETE FROM TBL_CATEGORIES WHERE CATEGORY_ID=?";
	
	@Override
	public void deleteCategory(Long categoryID)
			throws DAOException {
		
		logger.debug("deleteCategory");
		
		if (categoryID == null) {
			throw new DAOException(
					"Category ID cannot be null");
		}

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object values[] = { categoryID };
		jdbcTemplate.update(DeleteQuery, values);
	}
	class CategoryMapper implements RowMapper<Category>
	{
		@Override
		public Category mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			Category result = new Category();
			result.setCategoryID(rs.getLong("CATEGORY_ID"));
			result.setTitle(rs.getString("CATEGORY_TITLE"));
			result.setOrder(rs.getInt("DISPLAY_ORDER"));

			return result;
		}
	}

	
	
}
