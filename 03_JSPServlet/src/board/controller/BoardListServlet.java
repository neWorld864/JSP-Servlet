package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.PageInfo;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/list.bo")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 게시판 전체 데이터 가져오기 + 페이징 처리
		BoardService bService = new BoardService();
		
		// 페이징 처리 변수
		int listCount;		// 해당 게시판에 대한 총 게시글 개수 
		int currentPage;	// 현재 페이지
		int pageLimit;		// 한 페이지에 표시될 페이지 수
		int boardLimit;		// 한 페이지에 보일 게시글 최대 개수
		int maxPage;		// 전체 페이지 중 가장 마지막 페이지
		int startPage;		// 페이징 된 페이지 중 시작 페이지
		int endPage;		// 페이징 된 페이지 중 마지막 페이지
				
		listCount = bService.getListCount();
		// System.out.println("BoardListServlet listCount : " + listCount);
		
		currentPage = 1; // currentPage가 넘어온 게 없는 경우 기본값 1
		if(request.getParameter("currentPage") != null) { // currentPage가 넘어온 경우
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			// 넘어온 페이지로 currentPage를 재설정 해주겠다
		} 
		
		pageLimit = 10; // 알아서 설정하기 / 한 페이지에 표시될 페이지 수 ( <- 1 2 3 4 5 6 7 8 9 10 -> )
		
		boardLimit = 10; // 알아서 설정하기  / 한 페이지에 보일 게시글 최대 개수 (ex. 10개)
		
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		// 마지막페이지 = 총게시글수/한페이지당 게시글 수 => 나눴을 때 나머지가 있는 경우 +1을 해줘야함. 
		// (총 게시글 수 131 / 한 페이지 당 글 수 10 => 13.1 ==> 총 14페이지)
		// 그러기 위해서는 둘 다 int이면 안 되고 하나는 double로 바꿔주어야 한다.
		// +1을 위해 Math.ceil => 올림, 올림한 후 최종적인 값은 int가 되어야 하므로 형변환(14.0페이지라는건 없으니까)
		
		startPage = ((currentPage - 1) / pageLimit) * pageLimit + 1;
		//            -----------------------------
		//                        n을 의미
		
		// 시작페이지: 1, 11, 21, 31 .. => 10n+1 (n>=0)
		// 페이지가 1~10인 경우 항상 시작페이지는 1 => n=0 
		// 어떤 페이지에(1~10 사이) 있던지 간에 "항상"시작페이지는 1이어야 함 -> currentPage와 관련(내가 "지금 어떤" 페이지(1~10)에 있던지 간에 시작페이지가 1이어야 함)
		// (currentPage(1~10)-1) / pageLimit(10) => 몫이 무조건 0 (currentPage가 10일때 몫이 1이 되므로 currentPage - 1 로 계산한다)
		
		// 페이지가 11~20인 경우 항상 시작페이지는 11 => n=1
		// 어떤 페이지에(11~20 사이) 있던지 간에 "항상"시작페이지는 11이어야 함 -> currentPage와 관련(내가 "지금 어떤" 페이지(11~20)에 있던지 간에 시작페이지가 11이어야 함)
		// (currentPage(11~20)-1) / pageLimit(10) => 몫이 무조건 1 (currentPage가 20일때 몫이 2이 되므로 currentPage - 1 로 계산한다)
		
		// 페이지가 21~30인 경우 항상 시작페이지는 21 => n=2
		// 어떤 페이지에(21~30 사이) 있던지 간에 "항상"시작페이지는 21이어야 함 -> currentPage와 관련(내가 "지금 어떤" 페이지(21~30)에 있던지 간에 시작페이지가 21이어야 함)
		// (currentPage(21~30)-1) / pageLimit(10) => 몫이 무조건 1 (currentPage가 30일때 몫이 3이 되므로 currentPage - 1 로 계산한다)
		
		// ... 이렇게 계속 이어짐
		
		endPage = startPage + pageLimit - 1;
		// 문제가 있음 -> if maxPage = 13인 경우에도 
		// endPage = 20이 됨 -> 조건을 걸자
		if(endPage > maxPage) { 
			endPage = maxPage; // endPage가 20이 아닌 13이 되도록
		}
		
		PageInfo pi = new PageInfo(currentPage, listCount, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		ArrayList<Board> list = bService.selectList(pi);
		
		String page = null;
		if(list != null) {
			page = "WEB-INF/views/board/boardList.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
		} else {
			page = "WEB-ING/views/common/errorPage.jsp";
			request.setAttribute("msg", "게시판 조회에 실패하였습니다.");
		}
		
		request.getRequestDispatcher(page).forward(request, response);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
