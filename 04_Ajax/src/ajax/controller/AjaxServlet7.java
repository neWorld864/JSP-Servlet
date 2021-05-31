package ajax.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class AjaxServlet7
 */
@WebServlet("/jQueryTest7.do")
public class AjaxServlet7 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxServlet7() {
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
		
		String userNo = request.getParameter("userNo"); // 바로 Integer.parseInt로 받아오면 오류남!
		String[] ids = userNo.split(",");
		
		JSONArray jArray = new JSONArray();
		
		for(String id : ids) {
			for(int i = 0; i < userList.size(); i++) {
				if(Integer.parseInt(id) == userList.get(i).getUserNo()) {
					User user = userList.get(i);
					
					JSONObject jObject = new JSONObject();
					jObject.put("userNo", user.getUserNo());
					jObject.put("userName", user.getUserName());
					jObject.put("userNation", user.getUserNation());
					
					jArray.add(jObject);
				}
			}
		}
		
		JSONObject result = new JSONObject();
		result.put("list", jArray);
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(result);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
