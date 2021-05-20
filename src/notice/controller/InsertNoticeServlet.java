package notice.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class InsertNoticeServlet
 */
@WebServlet("/insert.no")
public class InsertNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertNoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post방식으로 보내니까 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// view에서 가져올 것 : 제목, 작성일, 내용, 유저아이디
		String title = request.getParameter("title");
		String date = request.getParameter("date");
		String content = request.getParameter("content");
		String userId = ((Member)request.getSession().getAttribute("loginUser")).getUserId(); 
		
		Date dat = null;
		if(date.equals("")) {
			dat = new Date(new GregorianCalendar().getTimeInMillis());
		} else {
			String[] dateArr = date.split("-");
			
			int year = Integer.parseInt(dateArr[0]);
			int month = Integer.parseInt(dateArr[1])-1;
			int day = Integer.parseInt(dateArr[2]);
			
			dat = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
		}
		
		Notice n = new Notice(title, content, userId, dat);
		
		int result = new NoticeService().insertNotice(n);
		
		if(result > 0) {
			// 공지사항 목록(list.no)로 가기
			response.sendRedirect("list.no");
		} else {
			request.setAttribute("msg", "공지사항 등록에 실패했습니다.");
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
