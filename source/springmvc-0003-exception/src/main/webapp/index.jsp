<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring MVC 0003</title>

<script type="text/javascript" src="content/jquery/jquery-1.9.1.min.js"></script>
</head>
<body>
	<h1>Spring MVC 0003 exception</h1>
	<hr>

	<a href="server/systemError.do">system error test</a>
	<br>
	<a href="server/customError.do">custom error test</a>
	<br>
	<button id="custBT">自定义异常测试</button>
	<br>
	<a href="server/notfindError.do">notfind error test</a>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#custBT").on("click", custBTHandler);
	});

	function custBTHandler() {

		var userData = {
			'name' : 'tom',
			'password' : 'cat'
		}
		console.log(userData);
		console.log(JSON.stringify(userData));

		$.ajax({
			type : "GET",
			url : "server/customError.do",
			cache : false,
			data : JSON.stringify(userData),
			contentType : "application/json",
			dataType : "json",
			success : function(data) {
				console.log(data)
			},
			error : function() {
				console.log("error")
			}
		});
	}
</script>


</html>