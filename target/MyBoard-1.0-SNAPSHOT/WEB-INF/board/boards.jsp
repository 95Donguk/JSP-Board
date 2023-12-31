<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../layout/header.jsp" %>

<fmt:setLocale value="${param.lang}" scope="session"/>
<fmt:bundle basename="message.messageSource">
    <center>
            <%--    검색 시작--%>
        <form action="${pageContext.request.contextPath}/boards.do" method="post">
            <table border="1" cellpadding="0" cellspacing="0" width="700"></table>
            <tr>
                <td align="right">
                    <select name="searchCondition">
                        <option value="TITLE" <c:if test="${condition == 'TITLE'}"> selected</c:if>>
                            <fmt:message key="board.list.condition.title"/>
                        </option>
                        <option value="CONTENT" <c:if test="${condition == 'CONTENT'}"> selected</c:if>>
                            <fmt:message key="board.list.condition.content"/>
                        </option>
                    </select>
                    <input name="searchKeyword" type="text" value="${keyword}">
                    <input type="submit" value="<fmt:message key="board.list.condition.btn"/>">
                </td>
            </tr>
            </table>
        </form>
            <%--    검색 종료--%>
        <table border="1" cellspacing="0" cellpadding="0" width="700">
            <tr>
                <th bgcolor="orange" width="100"><fmt:message key="board.list.head.BoardNo"/></th>
                <th bgcolor="orange" width="200"><fmt:message key="board.list.head.title"/></th>
                <th bgcolor="orange" width="150"><fmt:message key="board.list.head.writer"/></th>
                <th bgcolor="orange" width="150"><fmt:message key="board.list.head.regDate"/></th>
                <th bgcolor="orange" width="100"><fmt:message key="board.list.head.cnt"/></th>
            </tr>
            <c:forEach var="board" items="${boardPage.boards}">
                <tr>
                    <td>${board.seq - boardPage.totalBoardCount + 1}</td>
                    <td align="left"><a
                            href="${pageContext.request.contextPath}/board.do?seq=${board.seq}">${board.title}</a></td>
                    <td>${board.writer}</td>
                    <td>${board.regDate}
                    </td>
                    <td>${board.cnt}
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <a href='${pageContext.request.contextPath}/board/new-form.do'>새글 등록</a>
        <div class="page-div">
            <div class="page-prev">
                <c:if test="${boardPage.startPageNum != 1}">
                    <a class="range"
                       href="${pageContext.request.contextPath}/boards.do?page=${boardPage.startPageNum - 1}">이전</a>
                </c:if>
            </div>
            <div class="page-box">
                <c:forEach var="status" begin="${boardPage.startPageNum}" end="${boardPage.endPageNum}">
                    <a class="range" href="${pageContext.request.contextPath}/boards.do?page=${status}">${status}</a>
                </c:forEach>
            </div>
            <div class="page-next">
                <c:if test="${boardPage.endPageNum < boardPage.totalPageCount}">
                    <a class="range"
                       href="${pageContext.request.contextPath}/boards.do?page=${boardPage.endPageNum + 1}">다음</a>
                </c:if>
            </div>
        </div>
    </center>
</fmt:bundle>

<%@ include file="../layout/footer.jsp" %>
