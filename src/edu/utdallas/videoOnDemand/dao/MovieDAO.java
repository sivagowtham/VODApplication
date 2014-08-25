package edu.utdallas.videoOnDemand.dao;

import java.util.List;

import edu.utdallas.videoOnDemand.dao.impl.DAOException;
import edu.utdallas.videoOnDemand.entities.Movie;
import edu.utdallas.videoOnDemand.entities.History;

public interface MovieDAO
{
	Movie retrieveMovie(Long movieID) throws DAOException;

	void insertMovie(Movie movie) throws DAOException;

	void updateMovie(Movie movie) throws DAOException;

	boolean removeMovie(Long movieID) throws DAOException;

	List<Movie> retrieveAllMovies() throws DAOException;
	
	List<Movie> retrieveMoviesByCategory(Long categoryID) throws DAOException;
	
	List<String> retriveAllMovieTitles() throws DAOException;
	
	List<Movie> retrieveWatchHistory(Long userID) throws DAOException;
	
	void insertHistory(History history) throws DAOException;

	List<Movie> retrieveFavoriteMovies(Long userId) throws DAOException;

	boolean addMovieToFavorite(Long movieID, Long userID) throws DAOException;

	boolean removeMovieFromFavorite(Long movieID, Long userID)
			throws DAOException;

	boolean checkMovieAsFavorite(Long movieID, Long userId) throws DAOException;

	List<Movie> searchMovies(String keyword, String type) throws DAOException;

	void updateMovieRating(Movie movie) throws DAOException;

	boolean hideMovie(Long movieId);

	boolean showMovie(Long movieId);

	List<Movie> retrievePurchaseMovies(Long userId) throws DAOException;

	List<Movie> retrieveRentedMovies(Long userId) throws DAOException;

	List<Movie> retrieveAllVisibleMovies() throws DAOException;
	
} 
