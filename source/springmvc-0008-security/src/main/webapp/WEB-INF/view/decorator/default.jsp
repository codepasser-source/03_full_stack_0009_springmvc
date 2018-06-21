<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="/WEB-INF/view/decorator/taglibs.jsp"%>

<!-- HTTP 1.1 -->
<meta http-equiv="Cache-Control" content="no-store" />
<!-- HTTP 1.0 -->
<meta http-equiv="Pragma" content="no-cache" />
<!-- Prevents caching at the Proxy Server -->
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<link rel="icon" href="favicon.ico" />
<link rel="shortcut icon" href="${base}/favicon.ico" />
<link rel="apple-touch-icon" href="${base}/favicon.ico" />
</head>
<body>
	<div name="server-data" style="display: none;">
		<div name="serverDateTime" field="serverDateTime">${serverDateTime}</div>
	</div>
	<div name="header-container" class="header-container">header</div>
	<div name="page-container" class="page-container">
		<hr></hr>
		<sitemesh:write property='body' />
		<hr></hr>
	</div>
	<div name="footer-container" class="footer-container">footer</div>
</body>
</html>