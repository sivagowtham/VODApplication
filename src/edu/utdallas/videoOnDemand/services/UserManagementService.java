package edu.utdallas.videoOnDemand.services;


import java.util.List;

import edu.utdallas.videoOnDemand.dao.impl.DAOException;
import edu.utdallas.videoOnDemand.userManagementSvc.CreditCardDTO;
import edu.utdallas.videoOnDemand.userManagementSvc.UserDTO;

/**
 * @author Lei Cui;
 * @date 6/27/2014;
 * @version 1;
 * @job UserManagementService;
 */

public interface UserManagementService
{
	/*
	 * insert user into table
	 * */
	boolean addUser(UserDTO userDTO) throws ServiceException;
	/*
	 * validate user
	 * @parameter: userDTO
	 * @return: boolean, if true, pass validation; otherwise, false represents no pass;
	 * */
	boolean validateUser(UserDTO userDTO) throws ServiceException;
	/*
	 * retrieve a user through a userID
	 * @parameter: userID
	 * @return a userDTO
	 * */
	UserDTO retrieveUserInfo() throws ServiceException;
	/*
	 * retrieve a user through a password or a credit card number
	 * @parameter: parameter
	 * @return a userDTO
	 * */
	UserDTO retrieveUserInfo(Long parameter) throws ServiceException;
	/*
	 * retrieve all users
	 * @parameter: 
	 * @return a List<userDTO>
	 * */
	
	void updateUser(UserDTO userDTO) throws ServiceException;
	/*
	 * update a user
	 * @parameter: userDTO
	 * */
	List<UserDTO> retrieveAllUsers() throws ServiceException;
	/*
	 * login
	 * @parameter: username, passord
	 * @return: if true, login successfully, otherwise, failure
	 * */
	boolean login(String username, String password) throws ServiceException;
	
	/*
	 * authenticate
	 * @parameter: 
	 * @return: if true, authenticate successfully, otherwise, failure
	 * */
	boolean authenticate() throws ServiceException;
	/*
	 * insert Admin into table
	 * */
	boolean addAdmin(UserDTO userDTO) throws ServiceException;
	/*
	 * validate user
	 * @parameter: userDTO
	 * @return: boolean, if true, pass validation; otherwise, false represents no pass;
	 * */
	boolean isAdmin(String username);
	/*
	 * 
	 * check if an user is an admin or user
	 */
	boolean removeAdmin(UserDTO userDTO) throws ServiceException;
	List<UserDTO> retrieveAllAdmins() throws ServiceException;
	boolean activateUser(UserDTO userDTO) throws ServiceException;
	boolean deActivateUser(UserDTO userDTO) throws ServiceException;
	boolean makeNoAdmin(UserDTO userDTO) throws ServiceException;
	boolean makeAdmin(UserDTO userDTO) throws ServiceException;
	boolean addUser(UserDTO userDTO, CreditCardDTO creditCardDTO)
			throws ServiceException;
	boolean validateCreditCard(CreditCardDTO creditCardDTO)
			throws ServiceException;
	
	/**
	 * The method is used for user to modify the user profile
	 * @param: UserDTO
	 * @return: boolean flag
	 * */
	public boolean updateUserProfile(UserDTO userDTO) throws ServiceException;

	/**
	 * The method is used for user to modify the user profile
	 * @param: CreditCardDTO
	 * @return: boolean flag
	 * */
	public boolean updateCreditCardInfo(CreditCardDTO ccardDTO) throws ServiceException;
	public String retrieveCreditCardInfo() throws ServiceException;
	
	
}
