<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="css/novel.css" />
<script type="text/javascript" src="js/jquery-1.7.1.js" ></script>
<title>12138</title>
</head>
<body>
	<div class="body">
		<div class="header">
			<p class="title"></p>
		</div>
		<div class="content">
			<ul>
				<c:forEach var="novel" items="${novelList}">
					<li id="${novel[0]}" class="tochapter">
						<p>[仙侠]</p>
						<p>${novel[1]}</p>
						<p>完结</p>
						<p>${novel[2]}</p>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="bottom"></div>
	</div>
</body>
<script type="text/javascript">
	$(".tochapter").click(function(){
		var id = $(this).attr("id");
		window.location.href="novel/chapterList.html?novelId="+id;
	});
</script>
</html>