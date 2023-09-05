<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="../errors/boardError.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>게시판 프로그램</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/WEB-INF/css/main.css">
</head>
<body>
<hr>
<a href="${pageContext.request.contextPath}/main.jsp">Home</a>&nbsp;&nbsp;
<c:if test="${sessionScope.user == null}">
<a href="${pageContext.request.contextPath}/user/new-form.do">회원가입</a>&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/login/form.do">로그인</a>&nbsp;&nbsp;
</c:if>
<c:if test="${sessionScope.user != null}">
<a href="${pageContext.request.contextPath}/board/new-form.do">새글등록</a>&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/logout.do">로그아웃</a>&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/boards.do">글목록</a>
</c:if>
<hr>
<br>