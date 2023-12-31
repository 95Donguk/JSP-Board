<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../layout/header.jsp" %>

<center>
    <h1>게시글 등록</h1>
    <hr>
    <form action="${pageContext.request.contextPath}/board/register.do" method="post">
        <table border="1" cellspacing="0" cellpadding="0">
            <tr>
                <td bgcolor="orange" width="70">제목</td>
                <td align="left"><input type="text" name="title"></td>
            </tr>
            <tr>
                <td bgcolor="orange">작성자</td>
                <td align="left">${user.name}</td>
            </tr>
            <tr>
                <td bgcolor="orange">내용</td>
                <td align="left"><textarea name="content" cols="40" rows="10"></textarea></td>
            </tr>
            <tr>
                <td bgcolor="orange">파일</td>
                <td align="left"><input type="file" name="file"/></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="새글등록">
                </td>
            </tr>
        </table>
    </form>
</center>

<%@ include file="../layout/footer.jsp" %>