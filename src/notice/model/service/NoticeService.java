package notice.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import notice.model.dao.NoticeDAO;
import notice.model.vo.Notice;

public class NoticeService {
	// 공지사항2
	public ArrayList<Notice> selectList() { // check
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDAO().selectList(conn);
		
		close(conn); // 원래 해줘야하는데 멤버에서 안해줌...
		
		return list;
		
	}

	public int insertNotice(Notice n) { // check
		Connection conn = getConnection();
		
		int result = new NoticeDAO().insertNotice(conn, n);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public Notice selectNotice(int no) { // check
		Connection conn = getConnection();
		
		NoticeDAO nDAO = new NoticeDAO();
		
		int result = nDAO.updateCount(conn, no);
		
		Notice notice = null;
		
		
		if(result > 0) {
			notice = new NoticeDAO().selectNotice(conn, no);
			if(notice == null) {
				rollback(conn);
			} else {
				commit(conn);
			}
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return notice;
	}

	public int updateNotice(Notice notice) { // check
		Connection conn = getConnection();
		
		int result = new NoticeDAO().updateNotice(conn, notice);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int deleteNotice(int no) { // check
		Connection conn = getConnection();
		
		int result = new NoticeDAO().deleteNotice(conn, no);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

}
