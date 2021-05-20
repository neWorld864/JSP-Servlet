package com.kh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet2 extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST 방식 : 인코딩이 필요
		
		request.setCharacterEncoding("UTF-8");
		
		
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String city = request.getParameter("city");
		String height = request.getParameter("height"); 
		String[] foodArr = request.getParameterValues("food");
		
		System.out.println(name);
		System.out.println(gender);
		System.out.println(age);
		System.out.println(city);
		System.out.println(height);
		for(String f : foodArr) {
			System.out.println(f);
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>개인정보 출력화면</title>");
		out.println("<style>");
		out.println("h2{color: red;}");
		out.println("span.name{color: orange; font-weight: bold;}");
		out.println("span.gender{color: yellow; font-weight: bold; background: black;}");
		out.println("span.age{color: green; font-weight: bold;}");
		out.println("span.ciry{color: blue; font-weight: bold;}");
		out.println("span.height{color: navy; font-weight: bold;}");
		out.println("span.food{color: purple; font-weight: bold;}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>개인취향 테스트 결과(POST)</h2>");
		out.printf("<span class='name'>%s</span>님은 ", name);
		out.printf("<span class='age'>%s</span>이시며, ", age);
		out.printf("<span class='city'>%s</span>에 사는 ", city);
		out.printf("<span class='height'>%s</span>cm인 ", height);
		out.printf("<span class='gender'>%s</span>입니다. ", gender);
		out.printf("좋아하는 음식은 <span class='food'>");
		for(int i = 0; i < foodArr.length; i++) {
			if(i == 0) {
				out.printf("%s", foodArr[i]);
			} else {
				out.printf(", %s", foodArr[i]);
			}
		}
		out.println("</span>입니다.");
		out.println("</body>");
		out.println("</html>");
		
		
		
		
	}
}
