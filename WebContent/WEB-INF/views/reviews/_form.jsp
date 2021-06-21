<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="review_date">日付</label><br />
<input type="date" name="review_date" value="<fmt:formatDate value='${review.reviewDate}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="name">名前</label><br />
<input type="text" name="name" value="${review.name}" />
<br /><br />

<label for="title">タイトル</label><br />
<input type="text" name="title" value="${review.title}" />
<br /><br />

<label for="impression">感想</label><br />
<textarea name="impression" rows="10" cols="50">${review.impression}</textarea>
<br /><br />

<label for="evaluation">評価 </label><br />
<select name="evaluation">
    <option value="0"<c:if test="${review.evaluation == 0}"> selected</c:if>>⭐️⭐️⭐️</option>
    <option value="1"<c:if test="${review.evaluation == 1}"> selected</c:if>>⭐⭐️️</option>
    <option value="2"<c:if test="${review.evaluation == 2}"> selected</c:if>>⭐</option>
</select>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>