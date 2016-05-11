<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>404 Not Found</title>
<meta name="keywords"
	content="404 iphone web template, Andriod web template, Smartphone web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<link href="<c:url value='/resources/css/error.css'></c:url>"
	rel="stylesheet" type="text/css" media="all" />
</head>
<body>
	<!--start-wrap--->
	<div class="wrap">
		<!---start-header---->
		<div class="header"></div>
		<!---End-header---->
		<!--start-content------>
		<div class="content">
			<img src="<c:url value='/resources/img/error-img.png'></c:url>"
				title="error" />
			<p>
				<span><label>O</label>hh.....</span>You Requested the page that is
				no longer There.
			</p>
			<a href="<%=request.getContextPath()%>/">Back To Home</a>
			<div class="copy-right"></div>
		</div>
		<!--End-Cotent------>
	</div>
	<!--End-wrap--->
</body>
</html>

