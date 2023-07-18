package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.common.EncryptUtils;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@RestController
@RequestMapping("/user")
public class UserRestController {
	@Autowired
	private UserBO userBO;
	
	/**
	 * 중복확인 APi
	 * @param loginId
	 * @return
	 */
	@RequestMapping("/is_duplicatedId")
	public Map<String,Object>isDuplicatedId(
			@RequestParam("loginId")String loginId){
		//select 
		UserEntity userEntity = userBO.getUserEntityByLoginId(loginId);
		
		Map<String,Object> result = new HashMap<>();
		if(userEntity != null) {
			// 유저가 비어있지않을떄 즉슨 디비에서 찾아옴 = 중복 
			result.put("code", 1);
			result.put("isDuplicatedId", true);
		}else {
			result.put("code", 1);
			result.put("isDuplicatedId", false);
		}
		return result;
	}
	/**
	 * 회원가입 API
	 * @param loginId
	 * @param password
	 * @param name
	 * @param email
	 * @return
	 */
	@PostMapping("/sign_up")
	public Map<String,Object> signUp(
			@RequestParam("loginId")String loginId,
			@RequestParam("password")String password,
			@RequestParam("name")String name,
			@RequestParam("email")String email){
		// 비밀번호 해싱
		String hashedPassword = EncryptUtils.md5(password);
		// db insert
		Integer userId = userBO.addUserEntity(loginId, hashedPassword, name, email);
		Map<String,Object> result = new HashMap<>();
		if(userId != null) {
			// 인서트 성공했을때 유저 아이디에는 수가 있다. 
			result.put("code", 1);
			result.put("result", "성공");
		}else {
			result.put("code", 500);
			result.put("errorMessage", "회원 가입에 실패했습니다.");
		}
		return result;
	}
	
	
	/**
	 * 로그인 API
	 * @param loginId
	 * @param password
	 * @param session
	 * @return
	 */
	@PostMapping("/sign_in")
	public Map<String, Object> signIn(@RequestParam("loginId") String loginId,
			@RequestParam("password") String password, HttpSession session) {

		// password hashing
		String hashedPassword = EncryptUtils.md5(password);

		// loginId, hashedPassword로 UserEntity => null or 채워져있음
		UserEntity userEntity = userBO.getUserEntityByLoginIdPassword(loginId, hashedPassword);

		Map<String, Object> result = new HashMap<>();
		if (userEntity != null) {
			// 로그인 처리
			session.setAttribute("userId", userEntity.getId());
			session.setAttribute("userLoginId", userEntity.getLoginId());
			session.setAttribute("userName", userEntity.getName());

			result.put("code", 1);
			result.put("result", "성공");
		} else {
			// 로그인 불가
			result.put("code", 500);
			result.put("errorMessage", "존재하지 않는 사용자입니다.");
		}

		return result;
	}
}
