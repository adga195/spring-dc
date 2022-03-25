<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>

<html>

<head>
	<title>Save Customer</title>	
	
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
		
			<h3>Save Customer</h3>
		
			<!-- Form to add a customer -->
			
			<form:form action = "saveCustomer"
				modelAttribute = "customer"
				method = "POST">
			
				<table>
				
					<tbody>
						<tr>
							<td><label>First name:</label></td>
							<td><form:input path = "firstName" /></td>
						</tr>
						
						<tr>
							<td><label>Last name:</label></td>
							<td><form:input path = "lastName" /></td>
						</tr>
						
						<tr>
							<td><label>Email:</label></td>
							<td><form:input path = "email" /></td>
						</tr>	
						
						<tr>
							<td/>
							<td>
								<br>
								<input type = "submit" value = "Save Customer" 									
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
			
			<br>
		
			<!-- Add customer button -->
			<input type = "button" value = "Save Customer" 
				onClick = ""
				class = "add-button"
			/>
			
		</div>
	</div>

</body>

</html>