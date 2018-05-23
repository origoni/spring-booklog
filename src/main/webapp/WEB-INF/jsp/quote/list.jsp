<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="/webjars/bootstrap/4.1.0/css/bootstrap.min.css" />
<title>기억에 남는 구절 목록</title>
</head>
<body>
	<a href="/" role="button" class="btn btn-info">메인으로</a>
	<section class="container">
		<article>
			<c:forEach var="quote" items="${quoteList}">
				<header>

					<div>${quote.book.title}</div>
				</header>
				<blockquote class="blockquote">
					<p>${quote.words}</p>

				</blockquote>
				<footer>
					<a href="/quote/${quote.id}" role="button" class="btn btn-info">Read</a>
				</footer>
				<hr />
			</c:forEach>
		</article>

		<a href="/quote/write" role="button" class="btn btn-info">Write</a>
	</section>

</body>
</html>
