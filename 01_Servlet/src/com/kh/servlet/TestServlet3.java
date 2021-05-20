package com.kh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet3 extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		String recommendation = "";
		switch(age) {
		case "10대 미만" : recommendation = "곰인형"; break;
		case "10대" : recommendation = "텀블러"; break;
		case "20대" : recommendation = "향수"; break;
		case "30대" : recommendation = "캔들"; break;
		case "40대" : recommendation = "건조기"; break;
		case "50대" : recommendation = "마사지기"; break;
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>개인 정보 출력 화면</title>");
		out.println("<style>");
		out.println("h2{color: red;}");
		out.println("span.name{color: orange; font-weight: bold;}");
		out.println("span.gender{color: yellow; background: black; font-weight: bold;}");
		out.println("span.age{color: green; font-weight: bold;}");
		out.println("span.city{color: blue; font-weight: bold;}");
		out.println("span.height{color: navy; font-weight: bold;}");
		out.println("span.food{color: purple; font-weight: bold;}");
		// 색이 안 바뀌면 {}가 잘 있는지 의심해라
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>개인 취향 테스트 결과(POST)</h2>");
		out.printf("<span class='name'>%s</span>님은 ", name);
		out.printf("<span class='age'>%s</span>이시며 ", age);
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
		out.println("<hr>");
		out.println("<h3>" + age + "에 맞는 선물 추천</h3>");
		out.printf("'%s' 선물은 어떠신가요?", recommendation);
		out.println("</body>");
		out.println("</html>");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}
}
