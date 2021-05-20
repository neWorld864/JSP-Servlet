package board.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.vo.Board;

/**
 * Servlet implementation class UpdateBoardFormServlet
 */
@WebServlet("/boardUpdateForm.bo")
public class UpdateBoardFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBoardFormServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int bId = Integer.parseInt(request.getParameter("bId"));
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String nickName = request.getParameter("nickName");
		int count = Integer.parseInt(request.getParameter("count"));
		String date = request.getParameter("date");
		String content = request.getParameter("content");
		
		String[] dateArr = date.split("-");
		int year = Integer.parseInt(dateArr[0]);
		int month = Integer.parseInt(dateArr[1])-1;
		int day = Integer.parseInt(dateArr[2]);
		
		Date dat = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
		
		Board b = new Board(bId, category, title, content, nickName, count, dat);
		
		
		request.setAttribute("b", b);
		request.getRequestDispatcher("WEB-INF/views/board/boardUpdateForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
