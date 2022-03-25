<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>
	<title>List Customers</title>	
	
	<link type = "text/css"
		rel = "stylesheet"
		href = "${pageContext.request.contextPath}/resources/css/style.css" />
		
	<script src="<c:url value="/resources/js/alert.js" />"></script>
	
</head>

<body>

	<!-- Header -->
	<div id = "wrapper">
		<div id = "header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	
	<!-- Content -->
	<div id = "container">
		<div id = "content">
		
			<!-- Add customer button -->
			<input type = "button" value = "Add Customer" 
				onClick = "window.location.href='showFormForAdd'; return false;"
				class = "add-button"
			/>
			
			<!-- Customer table -->
		
			<table>
			
			<tr>
				<th>ID</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Options</th>
			</tr>
			
			<c:forEach var = "tempCustomer" items = "${customers}">
			
				<!-- Create variable for update -->
				<c:url var = "updateLink" value = "/customer/showFormForUpdate">
					<c:param name = "customerId" value = "${tempCustomer.id}" />
				</c:url>
				
				<!-- Create variable for delete -->
				<c:url var = "deleteLink" value = "/customer/showFormForDelete">
					<c:param name = "customerId" value = "${tempCustomer.id}" />
				</c:url>
									
				<tr>
					<td>${tempCustomer.id}</td>
					<td>${tempCustomer.firstName}</td>
					<td>${tempCustomer.lastName}</td>
					<td>${tempCustomer.email}</td>
					<td>
						<a href = "${updateLink}">update</a>
						<a href = "${deleteLink}"
							onclick = "delete_alert()">delete</a>
					</td>
				</tr>
				
			</c:forEach>
			
			</table>
				
		</div>
	</div>

</body>

</html>