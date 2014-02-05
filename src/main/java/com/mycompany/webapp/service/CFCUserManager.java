package com.mycompany.webapp.service;

import java.util.List;

import org.appfuse.service.GenericManager;
import org.appfuse.service.UserExistsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mycompany.webapp.dao.CFCUserDao;
import com.mycompany.webapp.model.CFCUser;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * 
 */
public interface CFCUserManager extends GenericManager<CFCUser,Long>{
	    /**
	     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
	     * @param userDao the UserDao implementation to use
	     */
	    void setUserDao(CFCUserDao userDao);

	    /**
	     * Retrieves a user by userId.  An exception is thrown if user not found
	     *
	     * @param userId the identifier for the user
	     * @return User
	     */
	    CFCUser getUser(String userId);

	    /**
	     * Finds a user by their username.
	     * @param username the user's username used to login
	     * @return User a populated user object
	     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException
	     *         exception thrown when user not found
	     */
	    CFCUser getUserByUsername(String username) throws UsernameNotFoundException;

	    /**
	     * Retrieves a list of all users.
	     * @return List
	     */
	    List<CFCUser> getUsers();

	    /**
	     * Saves a user's information.
	     *
	     * @param user the user's information
	     * @throws UserExistsException thrown when user already exists
	     * @return user the updated user object
	     */
	    CFCUser saveUser(CFCUser user) throws UserExistsException;

	    /**
	     * Removes a user from the database
	     *
	     * @param user the user to remove
	     */
	    void removeUser(CFCUser user);

	    /**
	     * Removes a user from the database by their userId
	     *
	     * @param userId the user's id
	     */
	    void removeUser(String userId);

	    /**
	     * Search a user for search terms.
	     * @param searchTerm the search terms.
	     * @return a list of matches, or all if no searchTerm.
	     */
	    List<CFCUser> search(String searchTerm);

	    /**
	     * Builds a recovery password url by replacing placeholders with username and generated recovery token.
	     * 
	     * UrlTemplate should include two placeholders '{username}' for username and '{token}' for the recovery token.
	     * 
	     * @param user
	     * @param urlTemplateurl
	     *            template including two placeholders '{username}' and '{token}'
	     * @return
	     */
	    String buildRecoveryPasswordUrl(CFCUser user, String urlTemplate);

	    /**
	     *
	     * @param user
	     * @return
	     */
	    String generateRecoveryToken(CFCUser user);

	    /**
	     *
	     * @param username
	     * @param token
	     * @return
	     */
	    boolean isRecoveryTokenValid(String username, String token);

	    /**
	     * 
	     * @param user
	     * @param token
	     * @return
	     */
	    boolean isRecoveryTokenValid(CFCUser user, String token);

	    /**
	     * Sends a password recovery email to username.
	     *
	     * @param username
	     * @param urlTemplate
	     *            url template including two placeholders '{username}' and '{token}'
	     */
	    void sendPasswordRecoveryEmail(String username, String urlTemplate);

	    /**
	     * 
	     * @param username
	     * @param currentPassword
	     * @param recoveryToken
	     * @param newPassword
	     * @param applicationUrl
	     * @return
	     * @throws UserExistsException
	     */
	    CFCUser updatePassword(String username, String currentPassword, String recoveryToken, String newPassword, String applicationUrl) throws UserExistsException;
	
        List<CFCUser> searchUser(CFCUser user); 

}
