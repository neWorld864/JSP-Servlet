package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;


/**
 * Servlet implementation class LoginServlet
 */
/* annotation을 통한 mapping */
@WebServlet(urlPatterns="/login.me", name="LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request.setCharacterEncoding("UTF-8"); // 여긴 get방식이지만 post방식으로 들어온 것을 받아주기 때문에 인코딩이 필요 ??
		// 로그인을 하려면 아이디와 비밀번호가 필요
		String id = request.getParameter("userId");
		String pwd = request.getParameter("userPwd");
		System.out.println(pwd);
		
		Member member = new Member(id, pwd);
		Member loginUser = new MemberService().loginMember(member); 
		// 굳이 db에서 가져오는 이유
		// 네이버 등 : 탭 누를 때마다 로그인을 하라고 하지 않음. 정보를 유지한 상태에서 개인정보, 카페 쓴 글, 블로그 쓴 글 등이 나타남. 간단하게 count로만 하면 알 수 있는 정보들이 없음 
		// request말고 login을 유지시키기 위해서 session을 사용
		
		System.out.println(loginUser);
		
		if(loginUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			session.setMaxInactiveInterval(600); // 세션이 유지되는 시간 설정 (10분이 지나서 자동 로그아웃이 되었습니다), 설정 안 했으면 기본적으로 30분 유지, 600 : 10분 뒤
			// 정보를 가지고 있는 상태 -> requestDispatcher 사용하겠지만 여기선 sendRedirect 사용 -> 왜?
			
//			response.sendRedirect("index.jsp"); // request와 response를 새로 만듦 
			response.sendRedirect(request.getContextPath()); // index.jsp로 하면 경로가 유출되기때문에 바로 그 경로로 갈 수 있도록 설정
			
		} else {
			request.setAttribute("msg", "로그인 실패");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp");
			view.forward(request, response);
		}
		
		
		
		
		// 데이터를 session에 담음 
		// sendRedirect의 경우는 새 request객체를 만들어줌
		// ==> session에 데이터를 담았기 때문에 새 request를 만들어도 상관없음
		// if 데이터를 request에 담을 경우 sendRedirect를 하면 안 됨! ==> 새 request 영역을 만들어서 데이터를 다 날려버리므로
	}
	/* get방식으로 하든 post방식으로 하든 doGet안에다가 구현을 함 => post방식으로 해도 어차피 doPost에서 doGet을 호출하기때문에 doGet으로 넘어옴*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response); /* post방식으로 해도 doGet으로 넘어가도록 설정 */
	}
	/*  */

}
