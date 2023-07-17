package com.sns.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sns.user.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String>{

	public UserEntity findByLoginId(String loginId);

	
}
