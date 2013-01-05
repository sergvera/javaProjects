package com.template.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * A custom service for retrieving users from a custom datasource, such as a
 * database.
 * <p>
 */

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	/**
	 * Retrieves a user record containing the user's credentials and access.
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		// Declare a null Spring User
		UserDetails user = null;

		try {

			// Search database for a user that matches the specified username
			// You can provide a custom DAO to access your persistence layer
			// Or use JDBC to access your database
			

			// Populate the Spring User object with details from the dbUser
			// Here we just pass the username, password, and access level
			// getAuthorities() will translate the access level to the correct
			// role type

			List<GrantedAuthority> dummyAUthorities = new ArrayList<GrantedAuthority>();
			dummyAUthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			
			
			if(username.trim().equals("serg"))
			{
				dummyAUthorities.add(new SimpleGrantedAuthority("ROLE_SERG"));
			}

			user = new User(username, "dummyPass", true, true, true, true, dummyAUthorities);

		} catch (Exception e) {

			throw new UsernameNotFoundException("Error in retrieving user");
		}

		// Return user to Spring for processing.
		// Take note we're not the one evaluating whether this user is
		// authenticated or valid
		// We just merely retrieve a user that matches the specified username
		return user;
	}
}