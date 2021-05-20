package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class UpdatePwdServlet
 */
@WebServlet(urlPatterns="/updatePwd.me", name="UpdatePwdServlet")
public class UpdatePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 누구의 비밀번호를 수정할 것인지 아이디 얻기
		String userId = ((Member)(request.getSession().getAttribute("loginUser"))).getUserId(); // import하면 빨간 줄 사라짐
		String userPwd = request.getParameter("userPwd");
		String newPwd = request.getParameter("newPwd");
		
		int result = new MemberService().updatePwd(userId, userPwd, newPwd);
		// 비밀번호수정4) MemberService에 updatePwd 메소드 만들기
		
		if(result > 0) {
			response.sendRedirect("mypage.me");
		} else {
			request.setAttribute("msg", "회원정보 변경에 실패했습니다.");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
