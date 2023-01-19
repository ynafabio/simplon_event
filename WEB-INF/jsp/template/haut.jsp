<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>SIMPLON - Gestion des évènements</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="<c:url value="/plugins/fontawesome-free/css/all.min.css"/>">
  
  <!-- DataTables -->
  <link rel="stylesheet" href="<c:url value="/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css"/>">
  <link rel="stylesheet" href="<c:url value="/plugins/datatables-responsive/css/responsive.bootstrap4.min.css"/>">
  <link rel="stylesheet" href="<c:url value="/plugins/datatables-buttons/css/buttons.bootstrap4.min.css"/>">
  
  <!-- daterange picker -->
  <link rel="stylesheet" href="<c:url value="/plugins/daterangepicker/daterangepicker.css"/>">
  <!-- iCheck for checkboxes and radio inputs -->
  <link rel="stylesheet" href="<c:url value="/plugins/icheck-bootstrap/icheck-bootstrap.min.css"/>">
  <!-- Select2 -->
  <link rel="stylesheet" href="<c:url value="/plugins/select2/css/select2.min.css"/>">
  <link rel="stylesheet" href="<c:url value="/plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css"/>">
  
  <!-- Tempusdominus Bootstrap 4 -->
  <link rel="stylesheet" href="<c:url value="/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css"/>">
  <!-- Bootstrap4 Duallistbox -->
  <link rel="stylesheet" href="<c:url value="/plugins/bootstrap4-duallistbox/bootstrap-duallistbox.min.css"/>">
   
  <!-- dropzonejs -->
  <link rel="stylesheet" href="<c:url value="/plugins/dropzone/min/dropzone.min.css"/>">
  <!-- Theme style -->
  <link rel="stylesheet" href="<c:url value="/dist/css/adminlte.css"/>">
  <link rel="stylesheet" href="<c:url value="/dist/css/style.css"/>">
</head>


<c:if test="${!empty sessionPublic}">
<body class="hold-transition login-page" style="min-height: 542px; margin-top:200px;">
</c:if>

<c:if test="${empty sessionPublic}">
<c:if test="${!empty param.p && param.p.equals('inscription') }">
<body class="register-page" style="min-height: 542px; margin-top:200px;">
</c:if>
<c:if test="${empty param.p}">
<body class="hold-transition sidebar-mini">
</c:if>
</c:if>

<div class="wrapper">

<c:if test="${empty sessionPublic}">
<c:if test="${empty param.p}">
<%@include file="menu_gauche.jsp" %>
<%@include file="menu_haut.jsp" %>
<%@include file="menu_droit.jsp" %>
</c:if>
</c:if>