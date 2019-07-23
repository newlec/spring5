<%@page import="com.newlecture.web.dao.NoticeDao"%>
<%@page import="java.util.Scanner"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="com.newlecture.web.entity.Notice"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" >
    <title>Document</title>
    <!--
        스타일 속성(X) -> 선택자를 통해서 스타일을 부여한다.
    -->
    <link href="../css/style.css" type="text/css" rel="stylesheet" >
    <style>
    	#footer{
    		width:100px;
    		
    		
    	}
    </style>
</head>

<body>
    
    <!-- --- header block -------------------------------------------------------------------------- -->
    <%-- <jsp:include page="../inc/header.jsp" /> --%>
    
    <!-- --- visual block -------------------------------------------------------------------------- -->
    <div id="visual">
        <div class="content-box" style="position: relative;">            
           
        </div>
    </div>
    <!-- --- body block -------------------------------------------------------------------------- -->
    <div id="body">
        <div class="content-box">
            
            <jsp:include page="../../inc/aside.jsp" />

            <!-- main 부분 -->
            <!-- <? style="clear:left;">막내양</?> -->
        </div>
        
    </div>
    <!-- --- footer block -------------------------------------------------------------------------- -->    
    <jsp:include page="../../inc/footer.jsp" />
</body>
</html>