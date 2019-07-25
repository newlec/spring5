<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<main>
	<form method="post">
		<div>
			<label>아이디:</label>
			<input type="text" name="id" >
		</div>
		<div>
			<label>비밀번호:</label>
			<input type="password" name="pwd" >
		</div>
		<div>
			<label>이름:</label>
			<input type="text" name="name" >
		</div>
		<div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="submit" value="등록">
 		</div>
	</form>
</main>