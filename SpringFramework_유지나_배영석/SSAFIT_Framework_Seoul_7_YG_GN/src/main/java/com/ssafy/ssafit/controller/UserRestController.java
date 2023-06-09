package com.ssafy.ssafit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Follow;
import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.Subscribe;
import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api-user")
@Api(tags="사용자 컨트롤러")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	
	
	
	@GetMapping("/user")
	@ApiOperation(value="사용자 조회", notes="검색 조건을 넣으면 같이 가져옴")
	public ResponseEntity<?> getUserList(SearchCondition searchCondition){
		List<User> list=userService.searchUser(searchCondition);
		
		if(list==null || list.size()==0) return new ResponseEntity<Void>( HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/user/{no}")
	@ApiOperation(value="마이페이지", notes="회원정보를 id로 받아와서 상세 정보 조회")
	public ResponseEntity<?> mypage(@PathVariable int no){
		
		Map<String, Object> map=new HashMap<>();
		
		User u=(User) userService.searchUser(new SearchCondition("no", String.valueOf(no)));
		if(u==null) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
		
		//사용자 정보
		map.put("user", u);
		//팔로우 하는 사람 리스트
		map.put("followingList", userService.getFollowInfo(new SearchCondition("follow",String.valueOf(u.getNo()) )));
		//나를 팔로우 하는 사람 리스트
		map.put("followerList", userService.getFollowInfo(new SearchCondition("followed",String.valueOf(u.getNo()) )));
		//내가 구독중인 영상 리스트
		map.put("subscribeList", userService.getSubscribeInfo(new SearchCondition("subscriber",String.valueOf(u.getNo()))));
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	
	
	
	@PutMapping("/user")
	@ApiOperation(value="회원정보 수정", notes="회원정보를 form data로 받아와서 수정")
	public ResponseEntity<?> mypage(@PathVariable User user){
		
		int u=userService.modifyUserInfo(user);
		if(u==0) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Integer>(u, HttpStatus.OK);
	}
	
	//로그인
	@PostMapping("/login")
	@ApiOperation(value="회원가입", notes="회원정보를 form data형식으로 받아와서 저장")
	public ResponseEntity<?> login(User user, HttpSession session) {
		User tmp = userService.login(user.getId(), user.getPw());
		if(tmp==null) return new ResponseEntity<Void> (HttpStatus.UNAUTHORIZED);
		session.setAttribute("loginUser", tmp);
		
		return new ResponseEntity<String>(tmp.getName(), HttpStatus.OK);
	}
	
	//로그아웃
	@GetMapping("/logout")
	@ApiOperation(value="로그아웃", notes="세션에서 로그인 정보 삭제")
	public ResponseEntity<Void> logout(HttpSession session) {
		session.removeAttribute("loginUser");
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	//회원 탈퇴
	@DeleteMapping("/user")
	@ApiOperation(value="회원탈퇴", notes="회원 아이디를 받아와서 삭제")
	public ResponseEntity<Void> withdraw(HttpSession session) {
		//현재 로그인 된 사람만 회원 탈퇴 가능
		User u=(User) session.getAttribute("loginUser");
		userService.withdraw(u.getNo());
		
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	//구독
	@PostMapping("/subscribe/{id}")
	@ApiOperation(value="구독하기", notes="로그인 유저가 게시글{id}를 받아와 구독")
	public ResponseEntity<?> doJim(@PathVariable int id, HttpSession session) {
		User tmp = (User) session.getAttribute("loginUser");
		if(tmp==null) return new ResponseEntity<Void> (HttpStatus.UNAUTHORIZED);
		
		int result=userService.doSubscribe(new Subscribe(id, tmp.getNo()));
		if(result==0) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	@DeleteMapping("/subscribe/{id}")
	@ApiOperation(value="구독 취소 하기", notes="로그인 유저가 게시글{id}를 받아와 구독 취소")
	public ResponseEntity<?> unJim(@PathVariable int id, HttpSession session) {
		User tmp = (User) session.getAttribute("loginUser");
		if(tmp==null) return new ResponseEntity<Void> (HttpStatus.UNAUTHORIZED);
		
		int result=userService.unSubscribe(new Subscribe(id, tmp.getNo()));
		if(result==0) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	//팔로우
	@PostMapping("/follow/{followed}")
	@ApiOperation(value="팔로우하기", notes="로그인 유저가 사용자 번호{followed}를 받아와 팔로우")
	public ResponseEntity<?> doFollow(@PathVariable int followed, HttpSession session) {
		User tmp = (User) session.getAttribute("loginUser");
		if(tmp==null) return new ResponseEntity<Void> (HttpStatus.UNAUTHORIZED);
		
		int result=userService.doFollow(new Follow(tmp.getNo(), followed));
		if(result==0) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/follow/{followed}")
	@ApiOperation(value="팔로우 취소 하기", notes="로그인 유저가사용자 번호{followed}를 받아와 팔로우 취소")
	public ResponseEntity<?> unFollow(@PathVariable int followed, HttpSession session) {
		User tmp = (User) session.getAttribute("loginUser");
		if(tmp==null) return new ResponseEntity<Void> (HttpStatus.UNAUTHORIZED);
		
		int result=userService.unFollow(new Follow(tmp.getNo(), followed));
		if(result==0) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	


}
