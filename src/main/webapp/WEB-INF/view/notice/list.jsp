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
    <script src="../../js/notice/list.js"></script>
</head>

<body>
    
    <!-- --- header block -------------------------------------------------------------------------- -->
    <jsp:include page="../inc/header.jsp" />
    
    <!-- --- visual block -------------------------------------------------------------------------- -->
    <div id="visual">
        <div class="content-box" style="position: relative;">            
           
        </div>
    </div>
    <!-- --- body block -------------------------------------------------------------------------- -->
    <div id="body">
        <div class="content-box">
            
            <jsp:include page="../inc/aside.jsp" />

            <main>
                
                <section>
                    <h1>공지사항</h1>

                    <section id="breadcrumb">
                        <h1 class="d-none">경로</h1>
                        <ol>
                            <li>home</li>
                            <li>고객센터</li>
                            <li>공지사항</li>
                        </ol>
                    </section>
                    
                    <section>
                    	<h1>공지사항 검색</h1>
                    	<form>
                    		<select>
                    			<option>제목</option>
                    			<option>작성자</option>
                    			<option>내용</option>
                    		</select>	                        
	                        <input type="text" >
	                        <input type="submit" value="검색" >
	                    </form>
                    </section>
                    
                    <section id="notice">
                        <h1 class="d-none">공지사항 목록</h1>
                        <style>
                           	.even{
                           		background: beige;
                           	}
                           </style>
                        <template class="notice-template">
                        	<tr>
                                 <td class="num"></td>
                                 <td class="title"> 
                                 	<a href="detail?id="></a>
                                 	<span></span>
                                 	<span><a href="list?eid=">수정</a><a href="">삭제</a></span>
                                 </td>
                                 <td class="writer"></td>
                                 <td class="date"></td>
                                 <td class="hit"></td>
                        	</tr>
                        </template>
                        <table>
                            <thead>
                                <tr>
                                    <td class="num">번호</td>
                                    <td class="title">제목</td>
                                    <td class="writer">작성자</td>
                                    <td class="date">작성일</td>
                                    <td class="hit">조회수</td>
                                </tr>
                            </thead>
                            <tbody>                                   
                            <c:forEach var="n" items="${list}" varStatus="s">                            
                            
                            	<c:if test="${s.index%2==1}">
                            	<tr class="even">
                            	</c:if>
                            	<c:if test="${s.index%2==0}">
                                <tr>
                                </c:if>
                                    <td class="num">${n.id}</td>
                                    <td class="title"> 
                                    	<a href="detail?id=${n.id}">${n.title}</a>
                                    	<span>[23]</span>
                                    	<span><a href="list?eid=${n.id}">수정</a><a href="">삭제</a></span>
                                    </td>
                                    <td class="writer">newlec</td>
                                    <td class="date">2019-02-18</td>
                                    <td class="hit">255</td>		
                                </tr>
                            </c:forEach>
                            <!-- 주석 -->
                            <%-- 주석
                            <tr>
                            	<td colspan="5">--------------------------------</td>
                            </tr>
                            
                            <c:forEach var="n" items="${list}" begin="4" end="7" varStatus="s">                            
                            
                                <tr>
                                    <td class="num">${n.id}</td>
                                    <td class="title"> 
                                    	<a href="detail?id=${n.id}">${s.index}/${s.current.title} : ${n.title}</a>
                                    	<span>[23]</span>
                                    	<span><a href="list?eid=${n.id}">수정</a><a href="">삭제</a></span>
                                    </td>
                                    <td class="writer">newlec</td>
                                    <td class="date">2019-02-18</td>
                                    <td class="hit">255</td>		
                                </tr>
                            
                            </c:forEach>
                             --%>
                            </tbody>
                        </table>
                        
                        <div>
                        	<a href="reg">글쓰기</a>
                        </div>
                    </section>
                    
                    <section id="page-index">
                        <h1 class="d-none">페이지 정보</h1>
                        <div>
                            <span class="color-highlight font-bold">1</span> / 1 pages
                        </div>
                    </section>
                    
                    <div id="test-remove">
                    	<label>삭제할 게시글 ID</label><input type="text" value="">
                    	<input type="button" value="삭제">
                    </div>
                    
                    <div id="test-pager">
                    	<input type="text" value="1">
                    	<input type="button" value="요청">
                    </div>
                    
					
					<!-- 1,6,11,16,21,....-> page + total 레코드 수 -> 마지막 번호 
					
					page : 1  	start ==> 1
					page : 13  	start ==> 11
					page : 44  	start ==> 41
					page : 39  	start ==> 36 
					
					page : 1~5 	:=> 1
					page : 6~10 	:=> 6
					
					start =page - (page%5-1)
					-->
					
					<c:set var="page" value="${(empty param.p) ? 1 : param.p}" />
					
					<c:set var="start" value="${page-(page-1)%5}" />
					<c:set var="last" value="" />
										
                    <section id="pager">
                        <h1 class="d-none">페이저</h1>
                        <div>
                            <div><a href="list?p=${(start==1)?1:start-1}">이전</a></div>                            
                            <ul>
                            <c:forEach var="n" begin="${start}" end="${start+4}" varStatus="s">
                                <li><a href="list?p=${n}">${n}</a></li>
                            </c:forEach>
                            </ul>
                            <div><a href="list?p=${start+5}">다음</a></div>
                        </div>
                    </section>
                </section>
            </main>
            <!-- <? style="clear:left;">막내양</?> -->
        </div>
        
    </div>
    <!-- --- footer block -------------------------------------------------------------------------- -->    
    <jsp:include page="../inc/footer.jsp" />
</body>
</html>