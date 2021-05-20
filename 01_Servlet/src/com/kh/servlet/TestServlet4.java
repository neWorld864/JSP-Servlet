package com.kh.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet4 extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String city = request.getParameter("city");
		String height = request.getParameter("height"); 
		String[] foodArr = request.getParameterValues("food");
		
		// 다 request 안에 담아서 옴
		
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
		
		// jsp에게 html코드를 작성할 수 있도록 위임 준비
		// request객체 속성에 변수 지정해 데이터 전달
		request.setAttribute("name", name);
		request.setAttribute("age", age);
		request.setAttribute("gender", gender);
		request.setAttribute("city", city);
		request.setAttribute("height", height);
		String foods = String.join(", ", foodArr);
		request.setAttribute("foods", foods);
		request.setAttribute("recommendation", recommendation);
		// setAttribute로 값을 지정해주고
		// testServlet4End.jsp로 가서 getAttribute로 값 받고  형변환해줌~
		
		
		// JSP
		// Controller -> View : 데이터 가져감 ===> RequestDispatcher
		RequestDispatcher view = request.getRequestDispatcher("servlet/testServlet4End.jsp"); // request는 요청. 또 요청을 하고 있다?
		// 여태 했던 것들(1~3)은 요청을 getParameter를 통해 받아와서 내 거에서 바로 화면을 출력해주기 때문에 응답(response)이 맞는데 
		// 이 경우에는 testServlet4End.jsp에 출력 요청을 해주기 때문에 request를 써야 한다.
		// request를 이용해 뷰로 전환하는 것
		view.forward(request, response); // response를 이용해 값을 받아옴
	}
}
