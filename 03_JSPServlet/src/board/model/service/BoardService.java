package board.model.service;

import static common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.ArrayList;

import board.model.dao.BoardDAO;
import board.model.vo.Attachment;
import board.model.vo.Board;
import board.model.vo.PageInfo;
import board.model.vo.Reply;
import notice.model.dao.NoticeDAO;
import notice.model.vo.Notice;

public class BoardService {

	public int getListCount() { // check
		Connection conn = getConnection();
		
		int listCount = new BoardDAO().getListCount(conn);
		
		close(conn);
		
		return listCount;
	}

	public ArrayList<Board> selectList(PageInfo pi) { // check
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDAO().selectList(conn, pi);
		
		close(conn);
		
		return list;
	}

	public int insertBoard(Board b) { // check
		Connection conn = getConnection();
		
		int result = new BoardDAO().insertBoard(conn, b);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public ArrayList selectTList(int i) { // check
		Connection conn = getConnection();
		
		ArrayList list = null;
		
		BoardDAO bDAO = new BoardDAO();
		if(i == 1) {
			list = bDAO.selectBList(conn);
		} else {
			list = bDAO.selectFList(conn);
		}
		
		close(conn);
		
		return list;
	}

	public int insertThumbnail(Board b, ArrayList<Attachment> fileList) { // check
		Connection conn = getConnection();
		
		BoardDAO dao = new BoardDAO();
		
		int result1 = dao.insertBoard(conn, b);
		int result2 = dao.insertAttachment(conn, fileList);
		
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1; // result1만 해줘도 되고 result2만 해줘도 됨
	}

	public Board selectBoard(int bId) { // check
		Connection conn = getConnection();
		
		BoardDAO bDAO = new BoardDAO();
		
		int result = bDAO.updateCount(conn, bId);
		
		Board board = null;
		
		
		if(result > 0) {
			board = bDAO.selectBoard(conn, bId);
			if(board != null) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return board;
	}

	public ArrayList<Attachment> selectThumbnail(int bId) { // check
		Connection conn = getConnection();
		
		ArrayList<Attachment> list = new BoardDAO().selectThumbnail(conn, bId);
		
		close(conn);
		
		return list;
	}

	public int updateBoard(Board board) {
		Connection conn = getConnection();
		
		int result = new BoardDAO().updateBoard(conn, board);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int deleteBoard(int bId) {
		Connection conn = getConnection();
		
		int result = new BoardDAO().deleteBoard(conn, bId);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public ArrayList<Reply> selectReplyList(int bId) {
		Connection conn = getConnection();
		
		ArrayList<Reply> list = new BoardDAO().selectReplyList(conn, bId);
		
		close(conn);
		
		return list;
	}

	public ArrayList<Reply> insertReply(Reply r) {
		Connection conn = getConnection();
		BoardDAO bDAO = new BoardDAO();
		
		int result = bDAO.insertReply(conn, r);
		
		ArrayList<Reply> list = null;
		if(result > 0) {
			commit(conn);
			list = bDAO.selectReplyList(conn, r.getRefBId());
		} else {
			rollback(conn);
		}
		
		return list;
	}




}
