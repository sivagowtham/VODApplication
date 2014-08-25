/**
 * 
 */
package edu.utdallas.videoOnDemand.entities;

/**
 * @author Lei Cui;
 * @date 6/27/2014;
 * @version 1;
 * @job User;
 */

public class User
{
	private Long userID;
	private Long creditCardID;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String cctype; //credit card type
	private String ccnumber; //credit card number
	private String ccname; //name on credit card 
	private String ccmonth; //credit card expiry month
	private String ccyear; //credit card expiry year
	private String cccvv; //credit card cvv
	private String isActive;
	private String isAdmin;
	
	public Long getUserID()
	{
		return userID;
	}

	public void setUserID(Long userID)
	{
		this.userID = userID;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getIsActive()
	{
		return isActive;
	}

	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}

	public String getIsAdmin()
	{
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin)
	{
		this.isAdmin = isAdmin;
	}

	public String getCctype() {
		return cctype;
	}

	public void setCctype(String cctype) {
		this.cctype = cctype;
	}

	public String getCcnumber() {
		return ccnumber;
	}

	public void setCcnumber(String ccnumber) {
		this.ccnumber = ccnumber;
	}

	public String getCcname() {
		return ccname;
	}

	public void setCcname(String ccname) {
		this.ccname = ccname;
	}

	public String getCcmonth() {
		return ccmonth;
	}

	public void setCcmonth(String ccmonth) {
		this.ccmonth = ccmonth;
	}

	public String getCcyear() {
		return ccyear;
	}

	public void setCcyear(String ccyear) {
		this.ccyear = ccyear;
	}

	public String getCccvv() {
		return cccvv;
	}

	public void setCccvv(String cccvv) {
		this.cccvv = cccvv;
	}

	public Long getCreditCardID() {
		return creditCardID;
	}

	public void setCreditCardID(Long creditCardID) {
		this.creditCardID = creditCardID;
	}

}