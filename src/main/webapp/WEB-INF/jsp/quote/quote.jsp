<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="/webjars/bootstrap/4.0.0-alpha.2/dist/css/bootstrap.min.css" />
<title>${quote.words}</title>
</head>
<body>
	<a href="/" role="button" class="btn btn-info">메인으로</a>
	<section class="container">
		<article>
			<header>
				<div>${quote.recordAt}</div>
			</header>

			<blockquote class="blockquote">
				<p>${quote.words}</p>
				<footer class="blockquote-footer">
					<cite title="Source Title">${quote.book.title}</cite>
					p.${quote.pageNumber}
				</footer>
			</blockquote>

		</article>
		<hr />
		<footer>
			<a href="/quote/list" role="button" class="btn btn-info">List</a> <a
				href="/quote/${quote.id}/edit" role="button" class="btn btn-warning">Edit</a>
			<a href="/quote/${quote.id}/delete" role="button"
				class="btn btn-danger">Delete</a>
		</footer>
	</section>
</body>
</html>
