<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<h3><fmt:formatDate value = "${today}"  pattern = "yyyy-MM-dd HH:mm"/></h3>
	<hr>
	<ul>
		<li>
			<a href = "form1">form1-get</a>
		</li>
		<li>
			<a href = "form2">form2-post-dto</a>
		</li>
		<li>
			<a href = "form3">form3-post-map</a>
		</li>
		<li>
			<a href = "form4">form4-post-map</a>
		</li>
	</ul>
</body>
</html>
