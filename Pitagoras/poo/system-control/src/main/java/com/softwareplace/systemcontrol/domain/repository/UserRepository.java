package com.softwareplace.systemcontrol.domain.repository;

import com.softwareplace.systemcontrol.domain.models.User;

public class UserRepository extends BaseRepository<User> {

	public UserRepository() {
		super("users", User.class);
	}
}
