<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ninja Gold Game</title>
<link rel="stylesheet" href="/css/style.css" />
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">

</head>
<body>

	<header>
		<h2>
			Your Gold:
			<c:out value="${gold}"></c:out>
		</h2>
	</header>


	<section class="body">
		<form action="/normal" method="post">
			<input type="hidden" name="farm" /> <label for="farm">Farm</label>
			<p>Earns 10-20 Gold</p>
			<input class="btn btn-primary" type="submit" value="Find Gold" />
		</form>

		<form action="/normal" method="post">
			<input type="hidden" name="cave" /> <label for="cave">Cave</label>
			<p>Earns 5-10 Gold</p>
			<input class="btn btn-primary" type="submit" value="Find Gold" />
		</form>

		<form action="/normal" method="post">
			<input type="hidden" name="house" /> <label for="house">House</label>
			<p>Earns 2-5 Gold</p>
			<input class="btn btn-primary" type="submit" value="Find Gold" />
		</form>

		<form action="/normal" method="post">
			<input type="hidden" name="quest" /> <label for="quest">Quest</label>
			<p>Earns 0-50 Gold or Lose 0-50 Gold</p>
			<input class="btn btn-warning" type="submit" value="Find Gold" />
		</form>

		<form action="/normal" method="post">
			<input type="hidden" name="spa" /> <label for="spa">Spa</label>
			<p>Lose 5-10 Gold</p>
			<input class="btn btn-danger" type="submit" value="Spend Gold" />
		</form>
	</section>


	<footer>

		<h3>Activities:</h3>
			<!-- This is the flash message version 
		<p>
		
			<c:out value="${normal}"></c:out>
		</p>
-->

		<c:forEach var="statements" items="${loopMe}">
			<p>
				<c:out value="${statements}"></c:out>
			</p>
		</c:forEach>
		
	</footer>


</body>
</html>