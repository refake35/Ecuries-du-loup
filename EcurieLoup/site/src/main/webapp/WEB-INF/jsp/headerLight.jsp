<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<fmt:setBundle basename="i18n.general"/>

<%
 request.setAttribute("ctx", request.getContextPath());
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		
		
		<title><fmt:message key="general.meta.title"/></title>
		
		<link rel="icon" type="image/png" href="${ctx}/images/Logo.png" />
		
		<!-- lien css -->
		<link rel="stylesheet" media="screen" type="text/css" title="Ecuries du loup" href="${ctx}/css/general.css" />
		
		<script language="javascript" src="${ctx}/js/gestionVariable.js"> </script>
		<script language="javascript">
			set_ctx("${ctx}");
		</script>
		<!-- le les fiche chevaux -->
		<script language="javascript" src="${ctx}/js/ficheChevaux.js"> </script>
		<script language="javascript" src="${ctx}/js/edlCode.js"> </script>
		
	</head>
	
	
	<body>