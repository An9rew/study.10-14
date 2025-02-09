<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container mt-3">
	<form method="post" action="/auth/insertuser">
	  <div class="mb-3 mt-3">
	    <label for="username" class="form-label">userName:</label>
	    <input type="text" class="form-control" id="username" placeholder="Enter username" name="username">
	  </div>
	  <div class="mb-3">
	    <label for="password" class="form-label">Password:</label>
	    <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
	  </div>
	  <div class="mb-3">
	    <label for="email" class="form-label">Email:</label>
	    <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
	  </div>
	  <button id="btn-save" type="submit" class="btn btn-primary">회원가입</button>
	</form>
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>