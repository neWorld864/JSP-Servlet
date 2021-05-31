package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class AjaxServlet5
 */
@WebServlet("/jQueryTest5.do")
public class AjaxServlet5 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxServlet5() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		// getParameter로 넘겨주면 string으로 넘어옴 -> 숫자로 받고 싶으면 Integer.parseInt로 
		// 하지만 객체를 받아오는 경우 json으로 받아서 printwriter로 넘겨주어야 함
		
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(new User(1, "박신우", "한국"));
		userList.add(new User(2, "타일러 라쉬", "미국"));
		userList.add(new User(3, "쯔위", "중국"));
		userList.add(new User(4, "모모", "일본"));
		userList.add(new User(5, "리사", "태국"));
		userList.add(new User(6, "알베르토 몬디", "이탈리아"));
		userList.add(new User(7, "샘 오취리", "가나"));
		
		User user = null;
		for(int i = 0; i < userList.size(); i++) {
			if(userList.get(i).getUserNo() == userNo) {
				user = userList.get(i);
			}
		}
		
//		response.setCharacterEncoding("UTF-8");
//		response.getWriter().println(user);
		
		// json: map방식 -> 키와 value로 들어가 있음
		JSONObject json = null;
		if(user != null) {
			json = new JSONObject();
			json.put("userNo", user.getUserNo());
			json.put("userName", user.getUserName());
			json.put("userNation", user.getUserNation());
		}
		// 객체
		
		response.setContentType("application/json; charset=UTF-8"); 
		// json으로 보낼 때 항상 해줘야함!!
		
		PrintWriter out = response.getWriter();
		out.println(json);
		// PrintWriter은 string으로 보내는데 왜 굳이 PrintWriter를 쓸까
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
