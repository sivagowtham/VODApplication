package edu.utdallas.videoOnDemand.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.utdallas.videoOnDemand.CategorySvc.CategoryDTO;
import edu.utdallas.videoOnDemand.dao.impl.CategoryDAOImpl;
import edu.utdallas.videoOnDemand.dao.impl.MovieDAOImpl;
import edu.utdallas.videoOnDemand.entities.Movie;
import edu.utdallas.videoOnDemand.entities.Category;
import edu.utdallas.videoOnDemand.movieManagementSvc.MovieDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"datasourceContext.xml"})
public class CategoryDAOTestCase
{
	@Autowired
    DataSource datasource;

	@Test
	public void testWiring()
	{
		assertNotNull(datasource);
	}
	
	@Test
	public void testretrieveMoviesByCategory()throws Exception{

		MovieDAOImpl testDAO = new MovieDAOImpl(); 	
		testDAO.setDataSource(datasource);
		List<Movie> movies = testDAO.retrieveMoviesByCategory(new Long(1));
			assertNotNull(movies);
			assertTrue(movies.size() > 0);
	}
	
		@Test
	public void testretrieveCategory()throws Exception{

			CategoryDAOImpl testDAO = new CategoryDAOImpl();
			testDAO.setDataSource(datasource);
			Category category = testDAO.retrieveCategory(new Long(1));
			assertNotNull(category);
		
	}
	/*
	 * retrieve all Categories
	 */
		@Test
	public void retrieveAllCategories() throws Exception {

			CategoryDAOImpl testDAO = new CategoryDAOImpl();
			testDAO.setDataSource(datasource);
			List<Category> category = testDAO.retrieveAllCategories();
			assertNotNull(category);
			assertTrue(category.size() > 0);
	}
}
