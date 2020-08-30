package com.skillsup.patterns;

import java.util.List;
import java.util.Map;

import com.skillsup.patterns.dto.Credentials;
import com.skillsup.patterns.dto.User;
import com.skillsup.patterns.exeption.UnauthorizedAccessException;

public interface UserService {

	/**
	 * no role is required for this operation
	 * this method should create user and save his credentials to db
	 * @param credentials user password and user unique login
	 * @return created user
	 */
	User createUser(Credentials credentials);
	User createAdmin(Credentials credentials);
	User createBanned(Credentials credentials);

	/**
	 * admin role is required for this operation
	 * this method should create user and save his credentials to db
	 * @param id unique user identifier
	 * @return boolean result to show if the user was deleted
	 */
	boolean deleteUser(long id);

	/**
	 * admin role is required for this operation
	 * this method should create user and save his credentials to db
	 * @param credentials user password and user unique login
	 * @return boolean result to show if the user was deleted
	 */
	List<User> findAllUsers(Credentials credentials,Map<UserRole, Boolean> access) throws UnauthorizedAccessException;

	/**
	 * common/admin role is required for this operation
	 * this method should create user and save his credentials to db
	 * @param credentials user password and user unique login
	 * @return boolean result to show if the user was deleted
	 */
	User findUser(Credentials credentials);

	Long findNextIdFromNewUser();

	void addNewUser(Credentials credentials, Map<UserRole, Boolean> access) throws UnauthorizedAccessException;
	void removeUser(Credentials credentials, Map<UserRole, Boolean> access) throws UnauthorizedAccessException;

}
