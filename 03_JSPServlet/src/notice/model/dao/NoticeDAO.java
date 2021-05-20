package notice.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import notice.model.vo.Notice;

public class NoticeDAO {
	
	// 기본생성자 만드는 이유 : sql이 담겨져있는 properties 파일을 불러오기 위해서
	private Properties prop = new Properties();

	public NoticeDAO() {
		String fileName = NoticeDAO.class.getResource("/sql/notice/notice-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName)); // multi catch
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Notice> selectList(Connection conn) { // check
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Notice> list = new ArrayList<Notice>();
		// SELECT * FROM NOTICE WHERE STATUS='Y' => 위치홀더 넣을만한 곳 없으니 PreparedStatement말고 그냥 Statement쓰면 됨
		
		String query = prop.getProperty("selectList");
		/*SELECT NOTICE_NO, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_WRITER, NICKNAME,
        		 NOTICE_COUNT, NOTICE_DATE, NOTICE.STATUS 
		  FROM NOTICE 
    			JOIN MEMBER ON (NOTICE_WRITER = USER_ID)
		  WHERE NOTICE.STATUS = 'Y'; 
		  
		  * 엔터 들어가면 안 됨
		  */
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Notice no = new Notice(rset.getInt("NOTICE_NO"),
											rset.getString("NOTICE_TITLE"),
											rset.getString("NOTICE_CONTENT"),
											rset.getString("NOTICE_WRITER"),
											rset.getString("NICKNAME"),
											rset.getInt("NOTICE_COUNT"),
											rset.getDate("NOTICE_DATE"));
				
				list.add(no);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}

		return list;
	}

	public int insertNotice(Connection conn, Notice n) { // check
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertNotice");
		// insertNotice=INSERT INTO NOTICE VALUES(SEQ_NNO.NEXTVAL, ?, ?, ?, DEFALUT, ?, DEFALUT)
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setString(3, n.getNoticeWriter());
			pstmt.setDate(4, n.getNoticeDate());
			
			result = pstmt.executeUpdate(); // pstmt의 executeUpdate를 통해 쿼리를 보내줌

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public Notice selectNotice(Connection conn, int no) { // check
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice notice = null;
		
		String query = prop.getProperty("selectNotice");
		// selectNotice=SELECT NOTICE_NO, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_WRITER, NICKNAME, NOTICE_COUNT, NOTICE_DATE FROM NOTICE JOIN MEMBER ON (USER_ID = NOTICE_WRITER) WHERE NOTICE_NO=? AND NOTICE.STATUS='Y'
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				notice = new Notice(rset.getInt("NOTICE_NO"),
						rset.getString("NOTICE_TITLE"),
						rset.getString("NOTICE_CONTENT"),
						rset.getString("NOTICE_WRITER"),
						rset.getString("NICKNAME"),
						rset.getInt("NOTICE_COUNT"),
						rset.getDate("NOTICE_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return notice;
	}

	public int updateCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateCount");
		// updateCount=UPDATE NOTICE SET NOTICE_COUNT = NOTICE_COUNT + 1 WHERE NOTICE_NO = ?
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int updateNotice(Connection conn, Notice notice) { // check
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateNotice");
		// updateNotice=UPDATE NOTICE SET NOTICE_TITLE=?, NOTICE_CONTENT=?, NOTICE_DATE=? WHERE NOTICE_NO=?
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getNoticeTitle());
			pstmt.setString(2, notice.getNoticeContent());
			pstmt.setDate(3, notice.getNoticeDate());
			pstmt.setInt(4, notice.getNoticeNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteNotice(Connection conn, int no) { // check
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteNotice");
		// deleteNotice=UPDATE NOTICE SET STATUS='N' WHERE NOTICE_NO=?
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


}
