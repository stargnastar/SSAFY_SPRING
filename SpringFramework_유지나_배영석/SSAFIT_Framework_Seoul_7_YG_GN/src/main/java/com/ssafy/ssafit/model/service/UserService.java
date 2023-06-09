package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Follow;
import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.Subscribe;
import com.ssafy.ssafit.model.dto.User;

public interface UserService {
	
	
	
	
	//Create
	int signup(User user);
	
	//Update
	int modifyUserInfo(User user);
	
	//Delete
	int withdraw(int no); //회원 고유 번호호 회원 삭제
	
	//Read
	List<User> searchUser(SearchCondition con); //조건 별로 검색
	List<User> getUserList();
	User getUserById(String id);
	User login(String id, String pw);
	
	//JIM SUBSCRIBE
	int doFollow(Follow follow);
	int doSubscribe(Subscribe subscribe);
	int unFollow(Follow follow);
	int unSubscribe(Subscribe subscribe);
	
	//팔로우 조회
	List<Follow> getFollowInfo(SearchCondition condition);
	//구독 조회
	List<Subscribe> getSubscribeInfo(SearchCondition condition);
}
