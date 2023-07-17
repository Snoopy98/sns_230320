<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="container">
<h2>회원가입</h2>
<form method="post" action="/user/sign_up" id="signUpForm">   
	<div>ID</div>
	<div class="d-flex">
	<input type="text" name="loginId" id="loginId" class="form-control col-3">
	<button type="submit" class="btn btn-primary" id="idCheckBtn">중복확인</button>
	</div>
	<div id="idCheckLength" class="small text-danger d-none">ID를 4자 이상 입력해주세요.</div>
	<div id="idCheckDuplicated" class="small text-danger d-none">이미 사용중인 ID입니다.</div>
    <div id="idCheckOk" class="small text-success d-none">사용 가능한 ID 입니다.</div>
	
	<div>password</div>
	<input type="password" class="form-control col-3" id="password" name="password">
	
	<div>confirm password</div>
	<input type="password" class="form-control col-3" id="confirmPassword" name="confirmPassword">
	
	<div>이름</div>
	<input type="text" class="form-control col-3" id="name" name="name">
	
	<div>이메일</div>
	<input type="text" class="form-control col-3" id="email" name="email">
	
	<button type="submit" class="btn btn-primary text-end" id="signUpBtn">가입하기</button>
	
</form>
</div>
<script>
$(document).ready(function(){
	// 중복확인 버튼
	$('#idCheckBtn').on("click",function(){
		alert("클릭")
		
		let loginId = $('#loginId').val().trim();
		
		$('#idCheckLength').addClass("d-none");
		$('#idCheckDuplicated').addClass("d-none");
		$('#idCheckOk').addClass("d-none");
		
		if(loginId.length < 4){
			$('#idCheckLength').removeClass("d-none");
			return;
		}
	}); //중복확인
	
	
});// ready 
</script>