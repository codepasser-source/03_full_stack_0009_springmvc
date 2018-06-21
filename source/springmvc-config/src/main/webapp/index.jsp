<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring MVC 0006</title>

<!-- import master js -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/view-support/requirejs/2.1.11/require.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/view-support/neusoft.js"></script>
</head>
<body>
	<h1>Spring MVC config</h1>
	<hr>
	<a href="cache/list.do">cache list</a>
	<br>
	<a href="cache/modify.do">cache modify</a>

	<form action="file/save.do" id="fileForm" name="fileForm" method="post"
		enctype="multipart/form-data">
		<input name="name" type="text" /><br> <input name="password"
			type="text" /><br> <input type="file" name="effectFile" /><br>
		<input type="submit" value="提交" />
	</form>
	<button id="submitBT">提交</button>

</body>
<script type="text/javascript">
	//支持IE7、8
	requirejs.config({
		baseUrl : "${pageContext.request.contextPath}/view-support/requirejs/"
				+ version.requirejs,
		paths : {
			"neusoft" : "../../neusoft/" + version.neusoft,
			"jquery" : "../../jquery/" + version.jquery,
			"easyui" : "../../easyui/" + version.easyui
		},
		config : {
			i18n : {
				locale : "zh"
			}
		}
	});
	require([ "jquery/jquery.min" ], function() {
		neusoft.setPath("${pageContext.request.contextPath}");
		require([ "easyui/jquery.easyui.min" ], function() {
			require([ "easyui/jquery.easyui.min", "neusoft/plugins/json2" ],
					function() {
						var bt = $("button[id='submitBT']");
						$(bt).on("click", function() {
							submitBtnHandle();
						});
					});
		});
	});
	var submitBtnHandle = function() {

		neusoft.formSubmit(
		// form
		$("form[id='fileForm']"),
		// url
		neusoft.path + "/file/save.do",
		// validation
		function() {
			return true;
		},
		// success callback
		function() {
			console.log("callback")
		});

	}
</script>
</html>