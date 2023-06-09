package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.Follow;
import com.ssafy.ssafit.model.dto.Subscribe;
import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.User;

public interface UserDao {
	
	//Create
	int insertUser(User user);
	
	//Update
	int updateUser(User user);
	
	//Delete
	int deleteUser(int no); //회원 고유 번호호 회원 삭제
	
	//Read
	List<User> selectByCondition(SearchCondition con); 
	User selectById(String id);
	List<User> selectAll();
	
	//팔로우 하기,취소하기
	int insertFollow(Follow follow);
	int deleteFollow(Follow follow);
	
	//찜 하기, 취소하기
	int insertJim(Subscribe jim);
	int deleteJim(Subscribe jim);
	
	//팔로우 조회
	List<Follow> selectFollow(SearchCondition condition);
	//구독 조회
	List<Subscribe> selectSubscribe(SearchCondition condition);
	
	
	

}
