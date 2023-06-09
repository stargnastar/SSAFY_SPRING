package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.ssafit.model.dao.UserDao;
import com.ssafy.ssafit.model.dto.Follow;
import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.Subscribe;
import com.ssafy.ssafit.model.dto.User;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public int signup(User user) {
//		//조건 별로 확인
//		SearchCondition con=new SearchCondition();
//		//1.아이디 중복 확인, 이메일 중복 확인
//		con.setKey("id");
//		con.setWord(user.getId());
//		User identify=userDao.selectOne(con);

		return userDao.insertUser(user);
	}

	@Override
	public int modifyUserInfo(User user) {
		
	return	userDao.updateUser(user);
		
		
	}

	@Override
	public int withdraw(int no) {
		return userDao.deleteUser(no);
	}

	@Override
	public List<User> searchUser(SearchCondition con) {
		return userDao.selectByCondition(con);
	}

	@Override
	public List<User> getUserList() {
		return userDao.selectAll();
	}

	@Override
	public User login(String id, String pw) {
		
		User u=userDao.selectById(id);
		if(u==null || !u.getPw().equals(pw)) return null;
		return u;
	}

	@Override
	public User getUserById(String id) {
		return userDao.selectById(id);
	}

	@Override
	public int doFollow(Follow follow) {
		return userDao.insertFollow(follow);
	}

	@Override
	public int doSubscribe(Subscribe subscribe) {
		return userDao.insertJim(subscribe);
	}

	@Override
	public int unFollow(Follow follow) {
		return userDao.deleteFollow(follow);
	}

	@Override
	public int unSubscribe(Subscribe subscribe) {
		return userDao.deleteJim(subscribe);
	}

	@Override
	public List<Follow> getFollowInfo(SearchCondition condition) {
		return userDao.selectFollow(condition);
	}

	@Override
	public List<Subscribe> getSubscribeInfo(SearchCondition condition) {
		return userDao.selectSubscribe(condition);
	}
	
}
