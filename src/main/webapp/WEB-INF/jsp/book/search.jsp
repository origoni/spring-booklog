<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" href="/webjars/bootstrap/4.1.0/css/bootstrap.min.css"/>
    <title>책 검색 및 추가</title>
</head>
<body>
<a href="/" role="button" class="btn btn-info">메인으로</a>
<section class="container">

    <a href="/quote/write" role="button" class="btn btn-info">Write</a>

    <form method="get" action="/api/v1/books/new">
        <select class="form-control input-lg" name="p">
            <c:forEach var="provider" items="${providers}">
                <option value="${provider}">${provider}</option>
            </c:forEach>
        </select>

        <fieldset class="form-group">
            <label for="formGroupTitle">검색어</label>
            <input id="formGroupTitle" type="text" class="form-control" name="q" placeholder="Title input"/>
        </fieldset>

        <input class="btn btn-warning" type="submit" value="검색"/>
    </form>

    <div id="target" class="row"></div>
</section>


<script src="/webjars/jquery/3.3.1-1/jquery.min.js"></script>
<script src="/webjars/mustachejs/2.2.1/mustache.min.js"></script>

<script id="template" type="x-tmpl-mustache">
{{#.}}
<div class="col-lg-4 col-md-6" style="height: 400px; overflow: hidden;">
	<div class="thumbnail"
		style="padding: 15px; border-radius: 2px; border: 0;
				-webkit-box-shadow: 0 8px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
				box-shadow: 0 8px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);">
		<img class="media-object" src="{{image_url}}" alt="{{title}}"
				style="max-height: 170px; overflow: hidden; padding-bottom: 10px;">
		<div class="caption">
			<h5>{{title}} ({{isbn}})</h5>
			<p>{{published_date}}</p>
			<p>{{authors}} / {{publisher}}</p>
			<p>
				<a href="#" class="btn btn-primary" role="button" onclick="addBook({{isbn}})">추가하기</a>
			</p>
		</div>
	</div>
</div>
{{/.}}





</script>

<script type="text/javascript">
    function addBook(isbn) {

        var provider = $("select[name='p']").val();

        $.ajax({
            type: "POST",
            url: "/api/v1/books",
            data: "isbn=" + isbn + "&p=" + provider,
            dataType: 'json',
            success: function (data, status) {
                alert("책 목록에 추가 완료됨. (메인의 책 목록에서 확인하세요.)");
            },
            error: function (data, status) {
                alert(data.responseJSON.message);
            }
        });
    }

    var template = $('#template').html();
    Mustache.parse(template);
    $('form').submit(function (event) {
        var form = $(this);
        $.ajax({
            type: form.attr('method'),
            url: form.attr('action'),
            data: form.serialize(),
            dataType: 'json',
            cache: false,
            success: function (data, status) {
                $('#target').html(Mustache.render(template, data));

            },
            error: function (data, status) {
                alert(data.responseJSON.message);
            }
        }).always(function () {
            //alert("ok");
        });
        event.preventDefault();
    });
</script>

</body>
</html>
