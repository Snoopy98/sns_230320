<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="container">
<h2>회원가입</h2>
<form method="post" action="/user/sign_up" id="signUpForm">   
	<div>ID</div>
	<div class="d-flex">
	<input type="text" name="loginId" id="loginId" class="form-control col-3">
	<button type="button" class="btn btn-primary" id="idCheckBtn">중복확인</button>
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
		//alert("클릭")
		let loginId = $('#loginId').val().trim();
		
		$('#idCheckLength').addClass("d-none");
		$('#idCheckDuplicated').addClass("d-none");
		$('#idCheckOk').addClass("d-none");
		
		if(loginId.length < 4){
			$('#idCheckLength').removeClass("d-none");
			return;
		}
		$.ajax({
			//request
			url:"/user/is_duplicatedId"
			,data:{"loginId":loginId}
			,success:function(data){
				if(data.isDuplicatedId){
					//참 = 중복
					$('#idCheckDuplicated').removeClass("d-none");
				}else{
					// 중복아님 사용 가능
					$('#idCheckOk').removeClass("d-none");
				}
			}
			,error:function(request,status,error){
				alert("아이디 중복확인에 실패했습니다.");
			}
		});
	}); //중복확인
	
	// 회원가입
	$('#signUpForm').on("submit",function(e){
		e.preventDefault();
		let loginId = $('#loginId').val().trim();
		let password = $('#password').val();
		let confirmPassword = $('#confirmPassword').val();
		let name = $('#name').val().trim();
		let email = $('#email').val().trim();
		//validation
		if(!loginId){
			alert("아이디를 입력해주세요.");
			return false;
		}
		if(!password || !confirmPassword){
			alert("비밀번호를 입력해주세요.");
			return false;
		}
		if(password != confirmPassword){
			alert("비밀번호를 확인해주세요.");
			return false;
		}
		if(!name){
			alert("이름을 입력해주세요.")
			return false;
		}
		if(!email){
			alert("이메일을 입력해주세요.")
			return false;
		}
		// 아이디 중복확인 을 하였는 지 확인
		if($('#idCheckOk').hasClass("d-none")){ // 참 이면 
			alert("아이디 중복확인을 다시해주세요.");
		return false;
		}
		
		// 서버로 데이터 보내기 ajax 사용 
		let url = $(this).attr("action"); // attr 로 action 값 뽑아내기 
		//console.log(url);
		let params = $(this).serialize(); // JSON 으로 만들어주는 함수 
		//console.log(params);
		
		$.post(url,params)// request
		.done(function(data){
			//response
			if(data.code == 1){
				alert("가입을 환영합니다! 로그인 해주세요.");
				location.href = "/user/sign_in_view";
			}else{
				alert(data.errorMessage);
			}
		});
	}); // 회원가입
	
	
});// ready 
</script>