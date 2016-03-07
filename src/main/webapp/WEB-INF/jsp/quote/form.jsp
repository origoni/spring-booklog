<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="/webjars/bootstrap/4.0.0-alpha.2/dist/css/bootstrap.min.css" />
<title>Quote Form</title>
</head>
<body>
	<a href="/" role="button" class="btn btn-info">메인으로</a>
	<section class="container">
		<header>
			<h2>기억에 남는 구절</h2>
		</header>
		<hr />
		<form method="post"
			action="${requestScope['javax.servlet.forward.servlet_path']}">
			<fieldset class="form-group">
				<label for="formGroupTitle">Book Title</label> <select
					class="form-control form-control-lg" name="isbn">
					<c:forEach var="book" items="${bookList}">
						<option
							<c:if test="${quote.isbn == book.isbn}">selected="selected"</c:if>
							value="${book.isbn}">${book.title}</option>
					</c:forEach>
				</select>
			</fieldset>

			<fieldset class="form-group">
				<label for="formGroupPage">Page Number</label> <input
					value="${quote.pageNumber}" id="formGroupPage" type="number"
					class="form-control" name="pageNumber"
					placeholder="페이지 번호를 입력해 주세요" />
			</fieldset>

			<fieldset class="form-group">
				<label for="formGroupWords">Words</label>
				<textarea id="formGroupWords" class="form-control" name="words"
					rows="3">${quote.words}</textarea>
			</fieldset>

			<input class="btn btn-default" type="submit" value="저장" />
		</form>
	</section>
</body>
</html>
