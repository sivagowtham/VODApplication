package edu.utdallas.videoOnDemand.dao.impl;

/**
 * @author : Mahalakshmi Balasubramanian;
 * @date 7/15/2014;
 * @version 1;
 * @job ReportDAOImpl;
 */
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import edu.utdallas.videoOnDemand.dao.ReportDAO;
import edu.utdallas.videoOnDemand.entities.Report;

public class ReportDAOImpl extends BaseDAO implements ReportDAO {

	private static final Logger logger = Logger.getLogger(ReportDAOImpl.class);
	
	private final String retrieveAllUsersAllInfoQuery = 
			
		"SELECT u.USER_ID,u.USERNAME,u.FIRST_NAME,u.LAST_NAME,u.ISACTIVE, u.ISADMIN, "
		+" COUNT(DISTINCT m.PURCHASED_ID) AS MOVIES_PURCHASED,"
		+" COUNT(DISTINCT r.RENTED_ID) AS MOVIES_RENTED "
		+" (COUNT(DISTINCT m.PURCHASED_ID) + COUNT(DISTINCT r.RENTED_ID)) AS MAX_ACTIVITY "
		+" FROM TBL_USERS AS u "
		+" LEFT JOIN TBL_MOVIE_PURCHASED AS m "
		+" ON u.USER_ID = m.USER_ID "
		+" AND m.PURCHASED_ON BETWEEN '?' AND '?' "
		+" LEFT JOIN TBL_RENTED_MOVIES AS r "
		+" ON u.USER_ID = r.USER_ID "
		+" AND r.RENTED_ON BETWEEN '?' AND '?' "
		+" GROUP BY u.USER_ID,u.USERNAME,u.FIRST_NAME,u.LAST_NAME,u.ISACTIVE,u.ISADMIN "
		+" ORDER BY MAX_ACTIVITY DESC ";
	
	private final String retrieveAllMoviesInfoQuery = 

			"SELECT mov.MOVIE_ID, mov.IMDB_ID, mov.TITLE, mov.CATEGORY, "
			+ "COUNT(DISTINCT m.PURCHASED_ID) AS NUMBER_OF_PURCHASES, "
			+ "COUNT(DISTINCT r.RENTED_ID) AS NUMBER_OF_RENTS "
			+" (COUNT(DISTINCT m.PURCHASED_ID) + COUNT(DISTINCT r.RENTED_ID)) AS MAX_ACTIVITY "
			+" FROM TBL_MOVIES AS mov "
			+" LEFT JOIN TBL_MOVIE_PURCHASED AS m "
			+" ON mov.MOVIE_ID = m.MOVIE_ID "
			+" AND m.PURCHASED_ON BETWEEN '?' AND '?' "
			+" LEFT JOIN TBL_RENTED_MOVIES AS r " 
			+" ON mov.MOVIE_ID = r.MOVIE_ID "
			+" AND r.RENTED_ON BETWEEN '?' AND '?' "
			+" GROUP BY mov.MOVIE_ID, mov.IMDB_ID, mov.TITLE, mov.CATEGORY "
			+" ORDER BY MAX_ACTIVITY DESC ";

	@Override
	public List<Report> retrieveUsersReport(Date startDate, Date endDate) throws DAOException {
		logger.debug("Starting retrieveAllUsersInfo");
		if(startDate==null || endDate==null){
			throw new DAOException("Give a valid Start Date or End Date, date filter cannot be empty");
			}
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] dates = {startDate, endDate, startDate, endDate};
		List<Report> usersReport = jdbcTemplate.query(retrieveAllUsersAllInfoQuery, dates, new ReportMapperForUser());
		// TODO Auto-generated method stub
		return usersReport;
	}

	@Override
	public List<Report> retrieveMoviesReport(Date startDate, Date endDate) throws DAOException {
		logger.debug("Starting retrieveAllMoviesInfo");
		if(startDate==null || endDate==null){
			throw new DAOException("Give a valid Start Date or End Date, date filter cannot be empty");
			}
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] dates = {startDate, endDate, startDate, endDate};
		List<Report> moviesReport = jdbcTemplate.query(retrieveAllMoviesInfoQuery, dates, new ReportMapperForMovies());
		// TODO Auto-generated method stub
		return moviesReport;
	}
	
	public class ReportMapperForUser implements RowMapper<Report> {
		@Override
		public Report mapRow(ResultSet rs, int rowNum) throws SQLException {
			Report userResult = new Report();
			userResult.setUserID(rs.getLong("USER_ID"));
			userResult.setUsername(rs.getString("USERNAME"));
			userResult.setFirstName(rs.getString("FIRST_NAME"));
			userResult.setLastName(rs.getString("LAST_NAME"));
			userResult.setIsActive(rs.getString("ISACTIVE"));
			userResult.setIsAdmin(rs.getString("ISADMIN"));
			userResult.setMoviesPurchased(rs.getInt("MOVIES_PURCHASED"));
			userResult.setMoviesRented(rs.getInt("MOVIES_RENTED"));
			userResult.setMaxActivity(rs.getInt("MAX_ACTIVITY"));
			return userResult;
		}
	}	
		class ReportMapperForMovies implements RowMapper<Report> {
			@Override
			public Report mapRow(ResultSet rs1, int rowNum) throws SQLException {
				Report movResult = new Report();
				movResult.setMovieId(rs1.getLong("MOVIE_ID"));
				movResult.setTitle(rs1.getString("TITLE"));
				movResult.setCategory(rs1.getString("CATEGORY"));
				movResult.setImdbId(rs1.getString("IMDB_ID"));
				movResult.setNumOfPurchases(rs1.getInt("NUMBER_OF_PURCHASES"));
				movResult.setNumOfRents(rs1.getInt("NUMBER_OF_RENTS"));
				movResult.setMaxActivity(rs1.getInt("MAX_ACTIVITY"));
				return movResult;
			}
		}
}
