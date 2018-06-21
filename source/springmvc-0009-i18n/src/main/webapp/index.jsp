<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring MVC 0009 i18n</title>

<!-- import master js -->
<script type="text/javascript" src="lib/mattdamon.js"></script>

<script type="text/javascript">
	
</script>

</head>
<body>
	<h1>Spring MVC 0009 i18n Index Page</h1>
	<hr>
	<a href="i18n/client.action">基于浏览器locate</a>
	<br>
	<a href="i18n/session.action?langType=zh">基于session zh</a> <>
	<a href="i18n/session.action?langType=en">基于session en</a>
	<br>
	<a href="i18n/cookie.action?langType=zh">基于cookie zh</a> <>
	<a href="i18n/cookie.action?langType=en">基于cookie en</a>
</body>
</html>