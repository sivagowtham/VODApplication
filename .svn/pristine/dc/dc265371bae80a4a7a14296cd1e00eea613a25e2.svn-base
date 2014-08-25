package edu.utdallas.videoOnDemand.movieManagementSvc;

import java.io.Serializable;
import java.util.Date;

import edu.utdallas.videoOnDemand.entities.Movie;

@SuppressWarnings("serial")
public class MovieDTO implements Serializable {

	private Long movieId;
	private String title;
	private String description;
	private String director;
	private String actors;
	private String category;
	private Long categoryID;
	private double rating;
	private String videoURL;
	private String imdbId;
	private Date addedOn;
	private double rentAmount;
	private double purchaseAmount;
	private String posterURL;
	private String isVisible;

	public MovieDTO() {
	}

	public MovieDTO(Movie movie) {
		setMovieId(movie.getMovieId());
		setTitle(movie.getTitle());
		setDescription(movie.getDescription());
		setDirector(movie.getDirector());
		setActors(movie.getActors());
		setCategory(movie.getCategory());
		setCategoryID(movie.getCategoryID());
		setRating(movie.getRating());
		setVideoURL(movie.getVideoURL());
		setImdbId(movie.getImdbId());
		setAddedOn(movie.getAddedOn());
		setRentAmount(movie.getRentAmount());
		setPurchaseAmount(movie.getPurchaseAmount());
		setPosterURL(movie.getPosterURL());
		setIsVisible(movie.getIsVisible());
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public Long getCategoryID()
	{
		return categoryID;
	}

	public void setCategoryID(Long categoryID)
	{
		this.categoryID = categoryID;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public double getRentAmount() {
		return rentAmount;
	}

	public void setRentAmount(double rentAmount) {
		this.rentAmount = rentAmount;
	}

	public double getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public String getPosterURL() {
		return posterURL;
	}

	public void setPosterURL(String posterURL) {
		this.posterURL = posterURL;
	}
	public String getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(String isVisible) {
		this.isVisible = isVisible;
	}

}
