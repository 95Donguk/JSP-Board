<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true"
         import="java.util.Date" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h3><%= exception.getClass().getName()%>발생!</h3>
    <% Date currentTime = new Date(); %>
    예외 발생 시간 : <%= currentTime.toString() %>
    <hr>
    <% StackTraceElement[] elements = exception.getStackTrace(); %>
    예외 위치: <%= elements[0].toString() %>
</center>
</body>
</html>
