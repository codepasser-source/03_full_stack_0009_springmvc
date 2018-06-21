<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>view</title>

<!-- import master js -->
<script type="text/javascript" src="lib/mattdamon.js"></script>

<script type="text/javascript">
	mattdamon.lib.jQuery("/jquery.min.js", function() {
		console.log("jquery.js completed");
		mattdamon.lib.requirejs("/require.js", function() {
			requirejs.config({
				baseUrl : "lib/requirejs/" + version.requirejs,
				paths : {
					jquery : "../../jquery/" + version.jQuery,
					mattdamon : "../../mattdamon/" + version.mattdamon
				},
				config : {
					i18n : {
						locale : "ja"
					}
				}
			});
			console.log("require.js completed");
			require([ "i18n!mattdamon/locale/nls/Language" ],
					function(language) {
						console.log(language.Header.labelSet);
					})
		});
	});
</script>


</head>
<body>
</body>
</html>