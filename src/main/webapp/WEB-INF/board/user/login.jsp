<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container mt-3">
	<form>
	  <div class="mb-3 mt-3">
	    <label for="username" class="form-label">userName:</label>
	    <input type="text" class="form-control" id="username" placeholder="Enter username" name="username">
	  </div>
	  <div class="mb-3">
	    <label for="password" class="form-label">Password:</label>
	    <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
	  </div>
	  <button id="btn-login" type="submit" class="btn btn-primary">로그인</button>
	</form>
</div>

<script src="/js/login.js"></script>
<%@ include file="../layout/footer.jsp" %>