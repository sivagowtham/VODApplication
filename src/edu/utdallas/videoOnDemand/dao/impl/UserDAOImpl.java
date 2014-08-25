/**
 * 
 */
package edu.utdallas.videoOnDemand.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import edu.utdallas.videoOnDemand.dao.UserDAO;
import edu.utdallas.videoOnDemand.entities.CreditCard;
import edu.utdallas.videoOnDemand.entities.User;

/**
 * @author Lei Cui;
 * @date 6/27/2014;
 * @version 1;
 * @job UserDAOImpl;
 */
public class UserDAOImpl extends BaseDAO implements UserDAO {
	private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

	private static final String insertUserQuery = "insert into tbl_users (username,password,first_name,last_name,email,credit_card_id,isactive,isadmin) values (?,?,?,?,?,?,?,?)";
	private static final String insertCCInfo = "INSERT INTO TBL_CREDIT_CARD_INFO (NAME,CREDIT_CARD_NUMBER,CREDIT_CARD_TYPE, SECURITY_CODE,EXPIRY_MONTH,EXPIRY_YEAR) VALUES (?,?,?,?,?,?)";

	@Override
	public Long addUser(User user) throws DAOException {
		logger.debug("addUser " + user);
		Long userID = 0L;

		if (user.getUserID() != null) {
			throw new DAOException("Inserted User must have a 0 ID");
		}

		try {
			Connection conn = null;
			PreparedStatement psCC = null;
			PreparedStatement ps = null;
			int CCID = 0;
			try {
				conn = dataSource.getConnection();

				psCC = conn.prepareStatement(insertCCInfo,
						Statement.RETURN_GENERATED_KEYS);
				psCC.setString(1, user.getCcname());
				psCC.setString(2, user.getCcnumber());
				psCC.setString(3, user.getCctype());
				psCC.setString(4, user.getCccvv());
				psCC.setString(5, user.getCcmonth());
				psCC.setString(6, user.getCcyear());

				psCC.executeUpdate();

				ResultSet rsCC = psCC.getGeneratedKeys();
				while (rsCC.next()) {
					CCID = rsCC.getInt(1);
				}

				if (CCID != 0) {
					ps = conn.prepareStatement(insertUserQuery,
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, user.getUsername());
					ps.setString(2, user.getPassword());
					ps.setString(3, user.getFirstName());
					ps.setString(4, user.getLastName());
					ps.setString(5, user.getEmail());
					ps.setLong(6, CCID);
					ps.setString(
							7,
							user.getIsActive() == null ? "1" : user
									.getIsActive());
					ps.setString(8,
							user.getIsAdmin() == null ? "0" : user.getIsAdmin());

					ps.executeUpdate();
					ResultSet rs = ps.getGeneratedKeys();
					while (rs.next()) {
						userID = rs.getLong(1);
						user.setUserID(userID);
					}
				}
			} finally {
				DbUtils.close(ps);
				DbUtils.close(conn);
			}
		} catch (Exception ex) {
			throw new DAOException(ex.getMessage(), ex);
		}

		return userID;
	}

	private final String removeAdmin = "DELETE FROM TBL_USERS WHERE USER_ID = ?";

	@Override
	public boolean removeAdmin(User user) {
		boolean flag = false;
		JdbcTemplate template = new JdbcTemplate(dataSource);
		Object[] params = { user.getUserID() };
		int[] types = { Types.INTEGER };
		System.out.println("Params in DAO ===========" + params);
		template.update(removeAdmin, params, types);
		flag = true;
		return flag;
	}

	@Override
	public Long addAdmin(User user) throws DAOException {
		logger.debug("addAdmin " + user.getFirstName());
		Long userID = 0L;

		if (user.getUserID() != null) {
			throw new DAOException("Inserted Admin must have a 0 ID");
		}

		try {
			Connection conn = null;
			PreparedStatement ps = null;
			conn = dataSource.getConnection();
			try {

				ps = conn.prepareStatement(insertUserQuery,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getFirstName());
				ps.setString(4, user.getLastName());
				ps.setString(5, user.getEmail());
				ps.setLong(6, 2);
				ps.setString(7, "1");
				ps.setString(8, "1");
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next()) {
					userID = rs.getLong(1);
					user.setUserID(userID);
				}

			} finally {
				DbUtils.close(ps);
				DbUtils.close(conn);
			}
		} catch (Exception ex) {
			throw new DAOException(ex.getMessage(), ex);
		}

		return userID;
	}

	@Override
	public boolean validateUser(User user) throws DAOException {
		
		return false;
	}

	private final String retrieveUserQueryByID = "SELECT u.USER_ID,u.USERNAME,u.PASSWORD,u.FIRST_NAME,u.LAST_NAME,u.EMAIL,u.ISACTIVE,u.ISADMIN,c.NAME,c.CREDIT_CARD_NUMBER,c.CREDIT_CARD_TYPE,c.SECURITY_CODE,c.EXPIRY_MONTH,c.EXPIRY_YEAR FROM TBL_USERS AS u JOIN TBL_CREDIT_CARD_INFO AS c ON u.CREDIT_CARD_ID = c.CREDIT_CARD_ID WHERE u.USER_ID = ?";

	@Override
	public User retrieveUserInfo(Long userID) throws DAOException {
		logger.debug("Starting retrieveUserInfo by ID");
		if (userID == 0) {
			throw new DAOException(
					"Entities with keys can not be (re)inserted Long the database");
		}

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object values[] = { userID };
		User searchUser = null;
		List<User> users = jdbcTemplate.query(retrieveUserQueryByID, values,
				new UserMapper());
		if (users.size() > 0) {
			searchUser = users.get(0);
		}
		return searchUser;
	}

	private final String retrieveAllUsersQuery = "SELECT u.USER_ID,u.USERNAME,u.PASSWORD,u.FIRST_NAME,u.LAST_NAME,u.EMAIL,u.ISACTIVE,u.ISADMIN,c.NAME,c.CREDIT_CARD_NUMBER,c.CREDIT_CARD_TYPE,c.SECURITY_CODE,c.EXPIRY_MONTH,c.EXPIRY_YEAR FROM TBL_USERS AS u JOIN TBL_CREDIT_CARD_INFO AS c ON u.CREDIT_CARD_ID = c.CREDIT_CARD_ID";

	@Override
	public List<User> retrieveAllUsers() throws DAOException {
		logger.debug("Starting retrieveAllUsers");

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<User> users = jdbcTemplate.query(retrieveAllUsersQuery,
				new UserMapper());
		return users;
	}

	private final String login = "SELECT u.USER_ID,u.USERNAME,u.PASSWORD,u.FIRST_NAME,u.LAST_NAME,u.EMAIL,u.ISACTIVE,u.ISADMIN,c.NAME,c.CREDIT_CARD_NUMBER,c.CREDIT_CARD_TYPE,c.SECURITY_CODE,c.EXPIRY_MONTH,c.EXPIRY_YEAR FROM TBL_USERS AS u JOIN TBL_CREDIT_CARD_INFO AS c ON u.CREDIT_CARD_ID = c.CREDIT_CARD_ID WHERE u.ISACTIVE ='1' AND u.USERNAME = ? AND u.PASSWORD = ?";

	@Override
	public User login(String username, String password) {
		User user = null;
		JdbcTemplate template = new JdbcTemplate(dataSource);
		Object[] params = { username, password };
		List<User> users = template.query(login, params, new UserMapper());
		if (users.size() == 1) {
			user = users.get(0);
		}
		return user;
	}

	private final String isAdminQuery = "SELECT u.USER_ID,u.USERNAME,u.PASSWORD,u.FIRST_NAME,u.LAST_NAME,u.EMAIL,u.ISACTIVE,u.ISADMIN,c.NAME,c.CREDIT_CARD_NUMBER,c.CREDIT_CARD_TYPE,c.SECURITY_CODE,c.EXPIRY_MONTH,c.EXPIRY_YEAR FROM TBL_USERS AS u JOIN TBL_CREDIT_CARD_INFO AS c ON u.CREDIT_CARD_ID = c.CREDIT_CARD_ID WHERE u.USERNAME = ?";

	@Override
	public boolean isAdmin(String username) {
		User user = null;
		boolean isAdmin = false;
		int isAdminInt = 0;
		JdbcTemplate template = new JdbcTemplate(dataSource);
		Object[] params = { username };
		List<User> users = template.query(isAdminQuery, params,
				new UserMapper());
		if (users.size() == 1) {
			user = users.get(0);
			isAdminInt = Integer.parseInt(user.getIsAdmin());
		}
		if (isAdminInt == 1) {
			isAdmin = true;
		}
		return isAdmin;
	}

	public class UserMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User result = new User();
			result.setUserID(rs.getLong("USER_ID"));
			result.setUsername(rs.getString("USERNAME"));
			result.setPassword(rs.getString("PASSWORD"));
			result.setFirstName(rs.getString("FIRST_NAME"));
			result.setLastName(rs.getString("LAST_NAME"));
			result.setEmail(rs.getString("EMAIL"));
			result.setCctype(rs.getString("CREDIT_CARD_TYPE"));
			result.setCcnumber(rs.getString("CREDIT_CARD_NUMBER"));
			result.setCcname(rs.getString("NAME"));
			result.setCcmonth(rs.getString("EXPIRY_MONTH"));
			result.setCcyear(rs.getString("EXPIRY_YEAR"));
			result.setCccvv(rs.getString("SECURITY_CODE"));
			result.setIsActive(rs.getString("ISACTIVE"));
			result.setIsAdmin(rs.getString("ISADMIN"));

			return result;
		}
	}

	private final String retrieveAllAdminQuery = "SELECT u.USER_ID,u.USERNAME,u.PASSWORD,u.FIRST_NAME,u.LAST_NAME,u.EMAIL,u.ISACTIVE,u.ISADMIN,c.NAME,c.CREDIT_CARD_NUMBER,c.CREDIT_CARD_TYPE,c.SECURITY_CODE,c.EXPIRY_MONTH,c.EXPIRY_YEAR FROM TBL_USERS AS u JOIN TBL_CREDIT_CARD_INFO AS c ON u.CREDIT_CARD_ID = c.CREDIT_CARD_ID WHERE u.ISADMIN = '1'";

	@Override
	public List<User> retrieveAllAdmins() {
		logger.debug("Starting retrieveAllUsers");

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<User> users = jdbcTemplate.query(retrieveAllAdminQuery,
				new UserMapper());
		return users;
	}
	
	private final String userAvailableStatus = "SELECT count(*) from TBL_Users where USER_ID = ?";

	@SuppressWarnings("deprecation")
	@Override
	public boolean userAvailableStatus(User user) {
		JdbcTemplate template = new JdbcTemplate(dataSource);
		Object[] params = { user.getUserID() };
		int[] types = { Types.INTEGER };
		System.out.println("Params in DAO ===========" + params);
		Long status = template.queryForLong(userAvailableStatus, params, types);
		boolean flag = false;
		if(status >0)
		{
			flag =true;
		}
		return flag;
	}
	
	private final String activateUser = "UPDATE TBL_Users SET ISACTIVE = '1' where USER_ID = ?";

	@Override
	public boolean activateUser(User user) {
		boolean flag = false;
		boolean status = userAvailableStatus(user);
		if (status == true)
		{
			flag = true;
			JdbcTemplate template = new JdbcTemplate(dataSource);
			Object[] params = { user.getUserID() };
			int[] types = { Types.INTEGER };
			System.out.println("Params in DAO ===========" + params);
			template.update(activateUser, params, types);
		}
		return flag;
	}

	private final String deActivateUser = "UPDATE TBL_Users SET ISACTIVE = '0' where USER_ID = ?";

	@Override
	public boolean deActivateUser(User user) {
		boolean flag = false;
		boolean status = userAvailableStatus(user);
		if (status == true)
		{
			flag = true;
			JdbcTemplate template = new JdbcTemplate(dataSource);
			Object[] params = { user.getUserID() };
			int[] types = { Types.INTEGER };
			System.out.println("Params in DAO ===========" + params);
			template.update(deActivateUser, params, types);
		}
		return flag;
	}

	private final String makeAdminQuery = "UPDATE TBL_Users SET ISADMIN = '1' where USER_ID = ?";

	@Override
	public boolean makeAdmin(User user) {
		boolean flag = false;
		boolean status = userAvailableStatus(user);
		if (status == true)
		{
			flag = true;
			JdbcTemplate template = new JdbcTemplate(dataSource);
			Object[] params = { user.getUserID() };
			int[] types = { Types.INTEGER };
			System.out.println("Params in DAO ===========" + params);
			template.update(makeAdminQuery, params, types);
		}
		return flag;
	}

	private final String makeNoAdmin = "UPDATE TBL_Users SET ISADMIN = '0' where USER_ID = ?";

	@Override
	public boolean makeNoAdmin(User user) {
		boolean flag = false;
		boolean status = userAvailableStatus(user);
		if (status == true)
		{
			flag = true;
			JdbcTemplate template = new JdbcTemplate(dataSource);
			Object[] params = { user.getUserID() };
			int[] types = { Types.INTEGER };
			System.out.println("Params in DAO ===========" + params);
			template.update(makeNoAdmin, params, types);
		}
		return flag;
	}

	@Override
	public Long addUser(User user, CreditCard creditCard) throws DAOException {
		logger.debug("addUser " + user);
		Long userID = 0L;

		if (user.getUserID() != null) {
			throw new DAOException("Inserted User must have a 0 ID");
		}

		try {
			Connection conn = null;
			PreparedStatement psCC = null;
			PreparedStatement ps = null;
			int CCID = 0;
			try {
				conn = dataSource.getConnection();

				psCC = conn.prepareStatement(insertCCInfo,
						Statement.RETURN_GENERATED_KEYS);
				psCC.setString(1, creditCard.getName());
				psCC.setString(2, creditCard.getCreditcardNumber());
				psCC.setString(3, creditCard.getCreditcardType());
				psCC.setString(4, creditCard.getSecurityCode());
				psCC.setString(5, creditCard.getExpiryMonth());
				psCC.setString(6, creditCard.getExpiryYear());

				psCC.executeUpdate();

				ResultSet rsCC = psCC.getGeneratedKeys();
				while (rsCC.next()) {
					CCID = rsCC.getInt(1);
				}

				if (CCID != 0) {
					ps = conn.prepareStatement(insertUserQuery,
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, user.getUsername());
					ps.setString(2, user.getPassword());
					ps.setString(3, user.getFirstName());
					ps.setString(4, user.getLastName());
					ps.setString(5, user.getEmail());
					ps.setLong(6, CCID);
					ps.setString(
							7,
							user.getIsActive() == null ? "1" : user
									.getIsActive());
					ps.setString(8,
							user.getIsAdmin() == null ? "0" : user.getIsAdmin());

					ps.executeUpdate();
					ResultSet rs = ps.getGeneratedKeys();
					while (rs.next()) {
						userID = rs.getLong(1);
						user.setUserID(userID);
					}
				}
			} finally {
				DbUtils.close(ps);
				DbUtils.close(conn);
			}
		} catch (Exception ex) {
			throw new DAOException(ex.getMessage(), ex);
		}

		return userID;
	}

	private static final String validateCreditCard = "SELECT * from TBL_CREDIT_CARD_INFO where lower(name)=lower(?) and credit_card_number = ? and security_code=? ";
	@Override
	public boolean validateCreditCard(CreditCard creditCard)
			throws DAOException {
		logger.debug("validate credit card information ");

		boolean flag = false;

		if (creditCard.getCreditcardId() != null) {
			try {
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					conn = dataSource.getConnection();
					ps = conn.prepareStatement(validateCreditCard,
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, creditCard.getName());
					ps.setString(2,creditCard.getCreditcardNumber());
					ps.setString(3, creditCard.getSecurityCode());

					rs = ps.executeQuery();
					if (rs.next()) {
						flag = true;
					}
				} finally {
					DbUtils.close(ps);
					DbUtils.close(conn);
				}
			} catch (Exception ex) {
				throw new DAOException(ex.getMessage(), ex);
			}
		}
		return flag;
	}

	private static final String updateUserProfile = "update tbl_users set username=?, password=?, first_name=?, last_name=?, email=? where user_id = ?";
	@Override
	public boolean updateUserProfile(User user) throws DAOException {
		logger.debug("update user profile ");

		boolean flag = false;

		if (user.getUserID() != null) {
			try {
				Connection conn = null;
				PreparedStatement ps = null;
				try {
					conn = dataSource.getConnection();
					ps = conn.prepareStatement(updateUserProfile,
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, user.getUsername());
					ps.setString(2, user.getPassword());
					ps.setString(3, user.getFirstName());
					ps.setString(4, user.getLastName());
					ps.setString(5, user.getEmail());
					ps.setLong(6, user.getUserID());

					int i = ps.executeUpdate();
					if (i != 0) {
						flag = true;
					}
				} finally {
					DbUtils.close(ps);
					DbUtils.close(conn);
				}
			} catch (Exception ex) {
				throw new DAOException(ex.getMessage(), ex);
			}
		}
		return flag;
	}

	private static final String updateCrediteCardInfo = "update TBL_CREDIT_CARD_INFO set name=?,credit_card_number=?, credit_card_type=?, security_code=?, expiry_month=?, expiry_year=? where credit_card_id=?";
	@Override
	public boolean updateCreditCardInfo(CreditCard creditCard)
			throws DAOException {
		logger.debug("update credit card information ");

		boolean flag = false;

		if (creditCard.getCreditcardId() != null) {
			try {
				Connection conn = null;
				PreparedStatement ps = null;
				try {
					conn = dataSource.getConnection();
					ps = conn.prepareStatement(updateCrediteCardInfo,
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, creditCard.getName());
					ps.setString(2,creditCard.getCreditcardNumber());
					ps.setString(3, creditCard.getCreditcardType());
					ps.setString(4, creditCard.getSecurityCode());
					ps.setString(5, creditCard.getExpiryMonth());
					ps.setString(6, creditCard.getExpiryMonth());
					ps.setLong(7, creditCard.getCreditcardId());

					int i = ps.executeUpdate();
					if (i != 0) {
						flag = true;
					}
				} finally {
					DbUtils.close(ps);
					DbUtils.close(conn);
				}
			} catch (Exception ex) {
				throw new DAOException(ex.getMessage(), ex);
			}
		}
		return flag;
	}

	private static final String selectCreditCardID="select credit_card_id FROM tbl_users where user_id=?";
	@Override
	public Long retrieveCreditCardIDByUserID(Long userID) throws DAOException {
		logger.debug("retrieve credit card ID ");

		Long creditCardID = null;

		if (userID != null) {
			try {
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					conn = dataSource.getConnection();
					ps = conn.prepareStatement(selectCreditCardID,
							Statement.RETURN_GENERATED_KEYS);
					ps.setLong(1, userID);

					rs = ps.executeQuery();
					while(rs.next()){
						creditCardID = rs.getLong(1);
					}
				} finally {
					DbUtils.close(ps);
					DbUtils.close(conn);
				}
			} catch (Exception ex) {
				throw new DAOException(ex.getMessage(), ex);
			}
		}
		return creditCardID;
	}
	
}