<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="/webjars/bootstrap/4.0.0-alpha.2/dist/css/bootstrap.min.css" />
<title>${title}</title>
</head>
<body>
	<section class="container">
		<h1><%=request.getAttribute("title")%></h1>
		<div>${message}</div>
		<div>${date}</div>
		<a href="/quote/write" role="button" class="btn btn-info">Write</a>
		<a href="/quote/list" role="button" class="btn btn-info">기억에 남는 구절 목록</a>
		<a href="/book/search" role="button" class="btn btn-info">책 검색</a>
		<a href="/book/list" role="button" class="btn btn-info">책 목록</a>
	</section>
</body>
</html>
