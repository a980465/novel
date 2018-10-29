<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/content.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.js" ></script>
<title>12138</title>
</head>
<body>
	<div class="body">
		<div class="header">
			<p class="title">${content[1]}</p>
		</div>
		<div class="content">
			<c:forEach var="i" items="${contentList}">
				<br>&nbsp;&nbsp;&nbsp;&nbsp;${i}<br>
			</c:forEach>
			<div class="next" id="${content[0]}" novelId="${content[3]}">下一章->></div>
		</div>
		<div class="bottom"></div>
	</div>
</body>
<script type="text/javascript">
	$(".next").click(function(){
		var id = $(this).attr("id");
		var novelId = $(this).attr("novelId");
		window.location.href="/novel/novel/nextChapter.html?currentId="+id+"&novelId="+novelId;
	});
</script>
</html>