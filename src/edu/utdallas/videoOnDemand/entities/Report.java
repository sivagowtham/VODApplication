package edu.utdallas.videoOnDemand.entities;
/**
 * @author : Mahalakshmi Balasubramanian;
 * @date 7/23/2014;
 * @version 1;
 * @job Report;
 */

public class Report {
	private Long userID;
	private String username;
	private String firstName;
	private String lastName;
	private String isActive;
	private String isAdmin;
	private Integer moviesPurchased; //from purchase info table
	private Integer moviesRented; // from rental info table
	private Integer maxActivity; // from query result for report 
	private Long movieId;
	private String title;
	private String category;
	private String imdbId;
	private Integer numOfPurchases; //from purchase info table
	private Integer numOfRents; // from rental info table

	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Integer getMoviesPurchased() {
		return moviesPurchased;
	}
	public void setMoviesPurchased(Integer moviesPurchased) {
		this.moviesPurchased = moviesPurchased;
	}
	public Integer getMoviesRented() {
		return moviesRented;
	}
	public void setMoviesRented(Integer moviesRented) {
		this.moviesRented = moviesRented;
	}
	public Integer getMaxActivity() {
		return maxActivity;
	}
	public void setMaxActivity(Integer maxActivity) {
		this.maxActivity = maxActivity;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImdbId() {
		return imdbId;
	}
	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}
	public Integer getNumOfPurchases() {
		return numOfPurchases;
	}
	public void setNumOfPurchases(Integer numOfPurchases) {
		this.numOfPurchases = numOfPurchases;
	}
	public Integer getNumOfRents() {
		return numOfRents;
	}
	public void setNumOfRents(Integer numOfRents) {
		this.numOfRents = numOfRents;
	}
}
