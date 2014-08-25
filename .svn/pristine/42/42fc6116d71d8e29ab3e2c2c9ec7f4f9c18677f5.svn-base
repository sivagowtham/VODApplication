package edu.utdallas.videoOnDemand.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.utdallas.videoOnDemand.CategorySvc.CategoryDTO;
import edu.utdallas.videoOnDemand.CategorySvc.CategoryDTOValidator;
import edu.utdallas.videoOnDemand.CategorySvc.CategoryServiceImpl;
import edu.utdallas.videoOnDemand.dao.CategoryDAO;
import edu.utdallas.videoOnDemand.dao.MovieDAO;
import edu.utdallas.videoOnDemand.dao.impl.DAOException;
import edu.utdallas.videoOnDemand.entities.Category;
import edu.utdallas.videoOnDemand.entities.Movie;
import edu.utdallas.videoOnDemand.movieManagementSvc.MovieDTO;
import edu.utdallas.videoOnDemand.movieManagementSvc.MovieDTOValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"serviceContext.xml"})
public class CategoryServiceTestCase
{
	@Autowired
	private CategoryService categorySvc;

	@Test
	public void testWiring()
	{
		assertNotNull(categorySvc);
	}

	@Test
	public void testretrieveMoviesByCategory()throws Exception{

			List<MovieDTO> movies = categorySvc.retrieveMoviesByCategory(new Long(1));
			assertNotNull(movies);
			assertTrue(movies.size() > 0);
	}
	
		@Test
	public void testretrieveCategory()throws Exception{

			CategoryDTO category = categorySvc.retrieveCategory(new Long(1));
			assertNotNull(category);
		
	}
	/*
	 * retrieve all Categories
	 */
		@Test
	public void retrieveAllCategories() throws Exception {

			List<CategoryDTO> category = categorySvc.retrieveAllCategories();
			assertNotNull(category);
			assertTrue(category.size() > 0);
	}

	
}
