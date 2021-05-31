package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class AjaxServlet6
 */
@WebServlet("/jQueryTest6.do")
public class AjaxServlet6 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxServlet6() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(new User(1, "박신우", "한국"));
		userList.add(new User(2, "타일러 라쉬", "미국"));
		userList.add(new User(3, "쯔위", "중국"));
		userList.add(new User(4, "모모", "일본"));
		userList.add(new User(5, "리사", "태국"));
		userList.add(new User(6, "알베르토 몬디", "이탈리아"));
		userList.add(new User(7, "샘 오취리", "가나"));
		
		int userNo = Integer.parseInt(request.getParameter("userNo"));

		User user = null;
		for(int i = 0; i < userList.size(); i++) {
			if(userList.get(i).getUserNo() == userNo) {
				user = userList.get(i);
			}
		}
		
		// User 객체를 JSON Object에 담고 JSON Object를 JSONArray에 담자
		JSONArray jArray = new JSONArray();
		JSONObject jObject = null;
		
		
		if(user != null) {
			jObject = new JSONObject();
			
			jObject.put("userNo", user.getUserNo());
			jObject.put("userName", user.getUserName());
			jObject.put("userNation", user.getUserNation());
			
			jArray.add(jObject);
		} else {
			for(User userInfo : userList) {
				jObject = new JSONObject();
				
				jObject.put("userNo", userInfo.getUserNo());
				jObject.put("userName", userInfo.getUserName());
				jObject.put("userNation", userInfo.getUserNation());
				
				jArray.add(jObject);
			}
		} 
		
		// JSONArray : 배열 형태 반환, list형식
		// JSONObject : map형식, key와 value값으로 저장, 스스로 데이터 넣음, 뽑았을 때 순서가 유지되지 않음
		
		response.setContentType("application/json; charset=UTF-8"); 
		
		PrintWriter out = response.getWriter();
		out.println(jArray);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
