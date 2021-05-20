package com.kh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet1 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		System.out.println("servlet으로 넘어왔음"); // 페이지 뜨면 servlet 1 누르고 정보 입력하고 확인 누르면 아래에 servlet으로 넘어왔음 문구가 뜸!
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String city = request.getParameter("city");
		String height = request.getParameter("height"); // getParameter의 반환값이 string이기 때문에 string으로 넣어줘야 하는 것임!
		String[] foodArr = request.getParameterValues("food");
		
		System.out.println(name);
		System.out.println(gender);
		System.out.println(age);
		System.out.println(city);
		System.out.println(height);
		for(int i = 0; i < foodArr.length; i++) {
			System.out.println(foodArr[i]);
		}
		// 이제 이걸 썼으니까 값을 입력해야지만 오류가 안남~
		
		// servers - 빨간네모눌러서 멈춤 - 다시 스타트 시킴 - 브라우저 뒤로가기하고 새로고침해서 실행시키면
		// 서버 재실행과 같은 의미가 된다 (프로젝트 이름 누르고 ctrl f11)
		
		
		response.setContentType("text/html; charset=UTF-8"); // 페이지에 대한 타입을 써줌
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>개인 정보 출력 화면</title>");
		out.println("<style>");
		out.println("h2{color: red;}");
		out.println("span.name{color: orange; font-weight: bold;}");
		out.println("span.gender{color: yellow; font-weight: bold; background: black;}");
		out.println("span.age{color: green; font-weight: bold;}");
		out.println("span.city{color: blue; font-weight: bold;}");
		out.println("span.height{color: navy; font-weight: bold;}");
		out.println("span.food{color: purple; font-weight: bold;}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>개인 취향 테스트 결과(GET)</h2>");
		out.printf("<span class='name'>%s</span>님은 ", name);
		out.printf("<span class='age'>%s</span>이시며, ", age);
		out.printf("<span class='city'>%s</span>에 사는 ", city);
		out.printf("키 <span class='height'>%s</span>cm인 ", height);
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
