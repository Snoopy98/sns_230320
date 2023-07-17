package com.sns.user;

import java.util.HashMap;
import java.util.Map;

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
}
