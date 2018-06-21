<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>cookie</title>
</head>
<body>
	下面展示的是后台获取的国际化信息：(基于cookie)
	<br /> ${money}
	<br /> ${date}
	<br /> 下面展示的是视图中直接绑定的国际化信息：
	<br />
	<spring:message code="money" />
	<br />
	<spring:eval expression="contentModel.money"></spring:eval>
	<br />
	<spring:message code="date" />
	<br />
	<spring:eval expression="contentModel.date"></spring:eval>
	<br />
</body>
</html>