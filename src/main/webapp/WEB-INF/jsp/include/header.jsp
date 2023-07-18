<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<div class="d-flex align-items-center justify-content-between">
	<div>
		<h1>Marondalgram</h1>
	</div>
	<div>
		<c:if test="${not empty userName}">
		<span>${userName} 님 안녕하세요!</span>
		<a href="/user/sign_out">로그아웃</a>
		</c:if>
	</div>
</div>