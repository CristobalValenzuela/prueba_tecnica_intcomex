package com.incomex.api.mappers;

import org.mapstruct.Mapper;

import com.incomex.api.data.out.UserOut;
import com.incomex.api.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserOut userToUserOut(User user);
}
