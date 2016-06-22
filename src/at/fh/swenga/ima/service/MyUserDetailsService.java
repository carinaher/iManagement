package at.fh.swenga.ima.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.ima.dao.SimpleUserRepository;
import at.fh.swenga.ima.model.User;
import at.fh.swenga.ima.model.UserRole;


@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	SimpleUserRepository userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;
		List<User> userList = userDao.findFirstByUserName(username);
		if (userList != null && userList.size() > 0) {
			user = userList.get(0);
			List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
			return buildUserForAuthenticator(user, authorities);
		} else {
			throw new UsernameNotFoundException("Bad credentials");
		}
	}
	
	private org.springframework.security.core.userdetails.User buildUserForAuthenticator (
			User user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(
				user.getUserName(),
				user.getPassword(),
				user.isEnabled(),
				true, // account not expired
				true, // credentials not expired
				true, // account not locked
				authorities
				);
	}
	
	
	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		
		userRoles.stream().forEach(userRole -> setAuths.add(new SimpleGrantedAuthority(userRole.getRole())));
		
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
		return Result;
	}
	
}
