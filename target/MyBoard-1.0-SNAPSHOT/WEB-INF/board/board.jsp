<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../layout/header.jsp" %>

<center>
    <form action="${pageContext.request.contextPath}/board/edit.do" method="post">
        <input name="seq" type="hidden" value="${board.seq}"/>
        <table border="1" cellpadding="0" cellspacing="0">
            <tr>
                <td bgcolor="orange" width="70">제목</td>
                <td align="left"><input name="title" type="text" value="${board.title}"/></td>
            </tr>
            <tr>
                <td bgcolor="orange">작성자</td>
                <td align="left">${board.writer}
                    <input name="writer-id" type="hidden" value="${board.userId}">
                </td>
            </tr>
            <tr>
                <td bgcolor="orange">내용</td>
                <td align="left"><textarea name="content" cols="40" rows="10">${board.content}</textarea></td>
            </tr>
            <tr>
                <td bgcolor="orange">조회수</td>
                <td align="left">${board.cnt}
                </td>
            </tr>
            <tr>
                <c:if test="${user.id == board.userId}">
                    <td colspan="2" align="center">
                        <input type="submit" value="글 수정"/>
                    </td>
                </c:if>
            </tr>
        </table>
    </form>
    <br>
    <c:if test="${user.role == 'ADMIN' || user.id == board.userId}">
        <a href="${pageContext.request.contextPath}/board/remove.do?seq=${board.seq}">글삭제</a>
    </c:if>
</center>

<%@ include file="../layout/footer.jsp" %>
