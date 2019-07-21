<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<header id="header">
        <div class="content-box">
            
            <h1 id="logo">뉴렉처 온라인</h1>
            <section>
                <h1 class="d-none">머릿말</h1>            
                
                <nav id="main-menu">
                    <h1 class="d-none">메인메뉴</h1>
                    <ul>
                        <li><a href="">학습가이드</a></li>
                        <li><a href="">강좌선택</a></li>
                        <li><a href="">AnswerIs</a></li>
                    </ul>
                </nav>
                
                

                <section id="lecture-search-form">
                    <h1 class="d-none">강좌선택 폼</h1>    
                    <form>
                        <label>과정검색</label>
                        <input type="text" >
                        <input type="submit" value="검색" >
                    </form>
                </section>
                
                <nav id="member-menu">
                    <h1 class="d-none">회원메뉴</h1>
                    <ul>
                        <li>HOME</li>
                        <li>
                        <c:if test="${not empty id}">
                        	<a href="/member/logout">로그아웃</a>
                        </c:if>
                        <c:if test="${empty id}">
                        	<a href="/member/login">로그인</a>
                        </c:if>
                        </li>
                        <li>회원가입</li>
                    </ul>
                </nav>
                
                <nav id="direct-menu">
                    <h1 class="d-none">자주사용하는 메뉴</h1>
                    <ul>
                        <li>마이페이지</li>
                        <li>고객센터</li>
                    </ul>   
                </nav>
            </section>
        </div>
    </header>