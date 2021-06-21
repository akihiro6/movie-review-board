<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>レビュー　一覧</h2>
        <table id="review_list">
            <tbody>
                <tr>
                    <th class="review_name">名前</th>
                    <th class="review_date">日付</th>
                    <th class="review_title">タイトル</th>
                    <th class="review_review">レビュー</th>
                </tr>
                <c:forEach var="review" items="${reviews}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="review_name"><c:out value="${review.name}" /></td>
                        <td class="review_date"><fmt:formatDate value='${review.reviewDate}' pattern='yyyy-MM-dd' /></td>
                        <td class="review_title">${review.title}</td>
                        <td class="review_review"><a href="<c:url value='/reviews/show?id=${review.id}' />">詳細</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${reviews_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((reviews_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/reviews/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/reviews/new' />">新規レビューの登録</a></p>

    </c:param>
</c:import>