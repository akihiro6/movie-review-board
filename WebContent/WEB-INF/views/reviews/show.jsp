<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${review != null}">
                <h2>レビュー　詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>名前</th>
                            <td><c:out value="${review.name}" /></td>
                        </tr>
                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${review.reviewDate}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <th>タイトル</th>
                            <td><c:out value="${review.title}" /></td>
                        </tr>
                        <tr>
                            <th>感想</th>
                            <td>
                                <pre><c:out value="${review.impression}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>評価</th>
                            <td>
                                <c:choose>
                                    <c:when test="${review.evaluation == 0}">⭐️⭐⭐️️</c:when>
                                    <c:when test="${review.evaluation == 1}">⭐️⭐</c:when>
                                    <c:otherwise>⭐️</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${review.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${review.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <p><a href="<c:url value='/reviews/edit?id=${review.id}' />">このレビューを編集する</a></p>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/reviews/index" />">一覧に戻る</a></p>
    </c:param>
</c:import>