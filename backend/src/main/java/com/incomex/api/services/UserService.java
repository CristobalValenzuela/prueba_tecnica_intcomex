package com.incomex.api.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.incomex.api.codes.UserCodes;
import com.incomex.api.data.in.UserIn;
import com.incomex.api.data.out.UserOut;
import com.incomex.api.entities.User;
import com.incomex.api.exceptions.UnauthorizedException;
import com.incomex.api.mappers.UserMapper;
import com.incomex.api.repositories.UserRepository;
import com.incomex.api.utils.JWTUtils;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JWTUtils jwtUtils;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserMapper userMapper;
	
	public UserOut validateUser(UserIn userIn) throws UnauthorizedException {
		User user = userRepository.findByName(userIn.getName());
		if(user == null) {
			throw new UnauthorizedException("Not user found", UserCodes.NOT_FOUND_USER.getCode());
		}
		if(!passwordEncoder.matches(userIn.getPassword(), user.getPassword())) {
			throw new UnauthorizedException("Not user found", UserCodes.NOT_FOUND_USER.getCode());			
		}
		UserOut userOut = userMapper.userToUserOut(user);
		userOut.setToken(jwtUtils.getJWTToken(userOut.getName(), new ArrayList<>()));
		return userOut;
	}

}
