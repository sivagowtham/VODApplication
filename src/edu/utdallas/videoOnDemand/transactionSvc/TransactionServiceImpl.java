package edu.utdallas.videoOnDemand.transactionSvc;

/**
 * @author Amber Gullatte;
 * @date 6/29/2014;
 * @version 1;
 * @job TransactionServiceImpl;
 */

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import edu.utdallas.videoOnDemand.dao.MovieDAO;
import edu.utdallas.videoOnDemand.dao.TransactionDAO;
import edu.utdallas.videoOnDemand.entities.Movie;
import edu.utdallas.videoOnDemand.entities.Transaction;
import edu.utdallas.videoOnDemand.services.ServiceException;
import edu.utdallas.videoOnDemand.services.TransactionService;
import edu.utdallas.videoOnDemand.movieManagementSvc.*;

public class TransactionServiceImpl implements TransactionService {

	private static final Logger logger = Logger
			.getLogger(TransactionServiceImpl.class);

	private TransactionDAO transactionDAO;
	private MovieDAO movieDAO;
	private MovieManagementServiceImpl movieService;

	@Override
	public void rentMovie(Long movieID) throws ServiceException {
		try {
			logger.debug("user: " + getUserSessionID() + "rents movie " + movieID);
			Transaction tran = new Transaction();
			tran.setMovieID(movieID);
			tran.setUserID(getUserSessionID());
			Movie movie = movieDAO.retrieveMovie(movieID); 
			tran.setAmount(movie.getRentAmount());
			tran.setTransType("R");			
			transactionDAO.addTransaction(tran);
			logger.info("New Rental Added for " + tran.getUserID());
		} catch (Exception ex) {
			throw new ServiceException(ex.getMessage(), ex);
		}
	}

	@Override
	public void purchaseMovie(Long movieID) throws ServiceException {
		try {			
			logger.debug("user: " + getUserSessionID() + "purchased movie "
					+ movieID);
			Transaction tran = new Transaction();
			tran.setMovieID(movieID);
			tran.setUserID(getUserSessionID());
			Movie movie = movieDAO.retrieveMovie(movieID); 
			tran.setAmount(movie.getPurchaseAmount());
			tran.setTransType("P");	
			transactionDAO.addTransaction(tran);
			logger.info("New Purchase Added for " + tran.getUserID());
		} catch (Exception ex) {
			throw new ServiceException(ex.getMessage(), ex);
		}
	}

	@Override
	public List<MovieDTO> retrieveAllHistory(TransactionDTO transDTO)
			throws ServiceException {
		try {
			List<MovieDTO> results = new ArrayList<MovieDTO>();
			
			transDTO.setUserID(getUserSessionID());
			List<Transaction> trans = transactionDAO.retrieveAllHistory(transDTO
					.getUserID());
			
			for(Transaction tran:trans)
			{
				results.add(movieService.retrieveMovie(tran.getMovieID()));
			}

			return results;
		} catch (Exception ex) {
			throw new ServiceException(ex.getMessage(), ex);
		}
	}

	@Override
	public List<MovieDTO> retrieveRentalHistory()
			throws ServiceException {
		try {
			List<MovieDTO> moviesDTO = null;
			Long userId = getUserSessionID();
			if (userId > 0) {
				List<Movie> movies = movieDAO.retrieveRentedMovies(userId);
				moviesDTO = MovieDTOValidator.covertToDTO(movies);
			}
			return moviesDTO;
		} catch (Exception ex) {
			throw new ServiceException(ex.getMessage(), ex);
		}
	}

	@Override
	public List<MovieDTO> retrievePurchaseHistory()
			throws ServiceException {
		try {
			List<MovieDTO> moviesDTO = null;
			Long userId = getUserSessionID();
			if (userId > 0) {
				List<Movie> movies = movieDAO.retrievePurchaseMovies(userId);
				moviesDTO = MovieDTOValidator.covertToDTO(movies);
			}
			return moviesDTO;
		} catch (Exception ex) {
			throw new ServiceException(ex.getMessage(), ex);
		}
	}
	
	@Override
	public String retrieveTransactionDate(Long MovieId)
			throws ServiceException {
		String dateT = null;
		try {				
			Long userId = getUserSessionID();
			if (userId > 0) {
				dateT = transactionDAO.retrieveTransactionDate(MovieId,userId);
				dateT += "_"+MovieId;
			}			
		} catch (Exception ex) {
			throw new ServiceException(ex.getMessage(), ex);
		}
		return dateT;
	}

	public Long getUserSessionID() throws ServiceException
	{
		long userId = 0;
		try {
			WebContext ctx = WebContextFactory.get();
			HttpServletRequest request = ctx.getHttpServletRequest();
			HttpServletResponse response = ctx.getHttpServletResponse();

			HttpSession session = request.getSession();
			if (!session.isNew()) {
				userId = (Long) session.getAttribute("userId");
			}
		}
		catch (Exception ex) {
			throw new ServiceException(ex.getMessage(), ex);
		}
		return userId;
	}
	public TransactionDAO gettransactionDAO() {
		return transactionDAO;
	}

	public void settransactionDAO(TransactionDAO transactionDAO) {
		this.transactionDAO = transactionDAO;
	}

	@Override
	public int getMovieDaysLeft(Long movieID) throws ServiceException {
		int days = 0;
		try {
			logger.debug("getting number of days left to watch on rent for movie id:"+movieID+" and userID"+getUserSessionID());

			Long userId = getUserSessionID();			
			if (userId > 0) {
				days = transactionDAO.getMovieDaysLeft(
						movieID, userId);
			}
			
		} catch (Exception ex) {
			throw new ServiceException(ex.getMessage(), ex);
		}
		return days;
	}

	public void setMovieDAO(MovieDAO movieDAO) {
		this.movieDAO = movieDAO;
	}

	
}
