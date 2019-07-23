<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <form method="post">
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
                            
                            <c:forEach var="n" items="${list}">
                            <c:if test="${param.eid == n.id}">                            
                                <tr>
                                    <td class="num">${n.id}</td>
                                    <td class="title">
                                    
                                    	<input type="text" name="title" value="${n.title}">
                                    	<span>[23]</span>
                                    	<input type="hidden" name="id" value="${n.id }" >
                                    	<span><input type="submit" value="저장" ></span>
                                    
                                    </td>
                                    <td class="writer">newlec</td>
                                    <td class="date">2019-02-18</td>
                                    <td class="hit">255</td>		
                                </tr>                           
                            </c:if>
                            <c:if test="${param.eid != n.id}">
                                <tr>
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
                             </c:if>
                            </c:forEach>
                               
                            </tbody>
                        </table>
                        </form>
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

                    <section id="pager">
                        <h1 class="d-none">페이저</h1>
                        <div>
                            <div>이전</div>
                            <ul>
                                <li><a href="list?p=1">1</a></li>
                                <li class="current"><a href="list?p=2">2</a></li>
                                <li><a href="list?p=3">3</a></li>
                                <li><a href="list?p=4">4</a></li>
                                <li><a href="list?p=5">5</a></li>
                            </ul>
                            <div>다음</div>
                        </div>
                    </section>
                </section>
            </main>