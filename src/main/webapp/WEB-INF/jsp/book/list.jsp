<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="/webjars/bootstrap/4.1.0/css/bootstrap.min.css" />
<title>books</title>
</head>
<body>
	<a href="/" role="button" class="btn btn-info">메인으로</a>
	<section class="container">
		<article class="row">
			<c:forEach var="book" items="${bookList}">
				<div class="col-lg-4 col-md-6"
					style="height: 400px; overflow: hidden;">
					<div class="thumbnail"
						style="padding: 15px; border-radius: 2px; border: 0; -webkit-box-shadow: 0 8px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19); box-shadow: 0 8px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);">
						<img class="media-object" src="${book.imageUrl}"
							alt="${book.title}"
							style="max-height: 170px; overflow: hidden; padding-bottom: 10px;">
						<div class="caption">
							<h5>${book.title} (${book.isbn})</h5>
							<p>${book.publishedDate}</p>
							<p>${book.authors} / ${book.publisher}</p>
							<%--<p>--%>
								<%--<a href="#" class="btn btn-primary" role="button"--%>
									<%--onclick="addBook('${book.isbn}');">찜하기</a>--%>
							<%--</p>--%>
						</div>
					</div>
				</div>
			</c:forEach>
		</article>
	</section>

</body>
</html>
