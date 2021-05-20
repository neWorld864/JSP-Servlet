package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class insertMemberServlet
 */
@WebServlet(urlPatterns="/insert.me", name="InsertMemberServlet")
public class InsertMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertMemberServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("joinUserId");
		String userPwd = request.getParameter("joinUserPwd");
		String userName = request.getParameter("userName");
		String nickName = request.getParameter("nickName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String[] iArr = request.getParameterValues("interest");
		String interest = "";
		if(iArr != null) {
			for(int i = 0; i < iArr.length; i++) {
				if(i == iArr.length - 1) {// 끝에있는 요소
					interest += iArr[i];
				} else {
					interest += iArr[i] + ",";
				}
			}
		}
	
		Member member = new Member(userId, userPwd, userName, nickName, phone, email, address,interest, null, null, null);
		
		int result = new MemberService().insertMember(member);
		
		if(result > 0) {
			 response.sendRedirect(request.getContextPath());
		} else {
			request.setAttribute("msg", "회원가입에 실패하였습니다.");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp");
			view.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
