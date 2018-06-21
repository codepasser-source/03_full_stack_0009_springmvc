<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring MVC 0002 data</title>

<script type="text/javascript" src="content/jquery/jquery-1.9.1.min.js"></script>
</head>
<body>
	<h1>Spring MVC 0002 data</h1>
	<hr>
	<div>
		<button id="jsonBT">json post data</button>
		<br>
		<button id="jsonGetBT">json get data</button>
	</div>
</body>

<script type="text/javascript">
	$(document).ready(function() {
		$("#jsonBT").on("click", jsonBTHandler);
	});

	function jsonBTHandler() {

		var userData = {
			'name' : 'tom',
			'password' : 'cat'
		}
		console.log(userData);
		console.log(JSON.stringify(userData));

		$.ajax({
			type : "POST",
			url : "server/json.do",
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

	$(document).ready(function() {
		$("#jsonGetBT").on("click", jsonGetBTHandler);
	});

	function jsonGetBTHandler() {

		var userData = {
			'name' : 'tom',
			'password' : 'cat'
		}
		console.log(userData);
		console.log(JSON.stringify(userData));

		$.ajax({
			type : "GET",
			url : "server/jsonGet.do",
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