<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>

<html>

<head>
	<title>Delete Customer</title>	
	
	<link type = "text/css"
		rel = "stylesheet"
		href = "${pageContext.request.contextPath}/resources/css/style.css" />
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
		
			<h3>Delete Customer</h3>
		
			<!-- Form to add a customer -->
			
			<form:form action = "deleteCustomer"
				modelAttribute = "customer"
				method = "POST">
				
				<!-- Adding a hidden form field to assign the customer ID -->
				<form:hidden path="id" />
			
				<table>
				
					<tbody>
						
						<tr>
							<td><label>First name:</label></td>
							<td><label>${customer.firstName}</label></td>
						</tr>
						
						<tr>
							<td><label>Last name:</label></td>
							<td><label>${customer.lastName}</label></td>
						</tr>
						
						<tr>
							<td><label>Email:</label></td>
							<td><label>${customer.email}</label></td>
						</tr>	
						
						<tr>
							<td/>
							<td>
								<br>
								<input type = "submit" value = "Delete Customer" 									
									class = "add-button" />
							</td>
						</tr>
											
					</tbody>
				
				</table>
			
			</form:form>
			
			<div style = "clear; both;"></div>
			
			<p>
				<a href = "${pageContext.request.contextPath}/customer/list">Back to List</a>
			</p>
			
		</div>
	</div>

</body>

</html>