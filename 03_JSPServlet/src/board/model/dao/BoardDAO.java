package board.model.dao;

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

import board.model.vo.Attachment;
import board.model.vo.Board;
import board.model.vo.PageInfo;
import board.model.vo.Reply;
import notice.model.vo.Notice;

public class BoardDAO {
	
	private Properties prop = new Properties();
	
	public BoardDAO() {
		String filePath = BoardDAO.class.getResource("/sql/board/board-query.properties").getPath();
		
		try {
			prop.load(new FileReader(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getListCount(Connection conn) { // check
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("getListCount");
		// getListCount=SELECT COUNT(*) FROM BOARD WHERE STATUS='Y' AND BOARD_TYPE=1
		// 보드타입 : 1이면 글게시판 2이면 사진게시판
		
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset); // import static 해줘야 빨간 줄 안 뜸
			close(stmt);
		}
		
		return result;
	}

	
	/*
	 * SQL DEVELOPER
	 
	 * BEGIN
    		FOR I IN 1..97
    	LOOP
        	INSERT INTO BOARD VALUES(SEQ_BID.NEXTVAL, 1, 10, I, I+1000, 'admin', DEFAULT, SYSDATE, SYSDATE, DEFAULT);
     	END LOOP;
        -- boardId, boardType, category, boardTitle, boardContent, boardWriter, nickName,  boardCount, createDate, modifyDate, status
   
		END;
		/

		COMMIT;
	 * */
	
	
// -----------------------------------------------------------------------------------------------------------------------	
	
	
	public ArrayList<Board> selectList(Connection conn, PageInfo pi) { // check
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
		
		String query = prop.getProperty("selectList");
		// selectList=SELECT * FROM BLIST WHERE RNUM BETWEEN ? AND ? AND BOARD_TYPE=1
		
		try {
			// page 1 ==> 1 2 3 4 5 6 7 8 9 10
			// page 2 ==> 11 12 13 14 15 16 17 18 19 20
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1; // 10n+1
			int endRow = startRow + pi.getBoardLimit() - 1;
			

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Board>();
			
			while(rset.next()) {
				Board b = new Board(rset.getInt("BOARD_ID"),
									rset.getInt("BOARD_TYPE"),
									rset.getString("CATE_NAME"),
									rset.getString("BOARD_TITLE"),
									rset.getString("BOARD_CONTENT"),
									rset.getString("BOARD_WRITER"),
									rset.getString("NICKNAME"),
									rset.getInt("BOARD_COUNT"),
									rset.getDate("CREATE_DATE"),
									rset.getDate("MODIFY_DATE"),
									rset.getString("STATUS"));
				list.add(b);
				
			}
			// 컬럼명 : 아래 쿼리를 통해 나온 결과값들의 컬럼명을 보고 가져오는 거임
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	
	/*
	 * SQL DEVELOPER
	 
	 *  SELECT ROWNUM, DESCBOARD.*
		FROM(SELECT BOARD_ID, BOARD_TYPE, CATE_NAME, BOARD_TITLE, BOARD_CONTENT, BOARD_WRITER, NICKNAME, 
             		BOARD_COUNT, CREATE_DATE, B.MODIFY_DATE, B.STATUS
      	FROM BOARD B
           	 JOIN MEMBER ON (BOARD_WRITER = USER_ID)
             JOIN CATEGORY USING (CATE_ID)
      	WHERE B.STATUS = 'Y'
      	ORDER BY BOARD_ID DESC) DESCBOARD;
      	
      	DESCBOARD.* => ROWNUM과 *를 같이 사용할 수 없기 때문에 DESCBOARD로 묶어서 DESCBOARD.*으로 하면 ROWNUM을 가져올 수 있다!!!!!
	   
	   인라인 뷰 : FROM절에 서브쿼리 사용한 것 -> 원하는 판을 짜주기 위해. 위 쿼리가 원하는 판임
	   
	   
	   -- 1) INLINE VIEW 중복
	   
		SELECT *
		FROM (SELECT ROWNUM RNUM, DESCBOARD.*
		      FROM(SELECT BOARD_ID, BOARD_TYPE, CATE_NAME, BOARD_TITLE, BOARD_CONTENT, BOARD_WRITER, NICKNAME, 
		                  BOARD_COUNT, CREATE_DATE, B.MODIFY_DATE, B.STATUS
		           FROM BOARD B
		                JOIN MEMBER ON (BOARD_WRITER = USER_ID)
		                JOIN CATEGORY USING (CATE_ID)
		           WHERE B.STATUS = 'Y'
		           ORDER BY BOARD_ID DESC) DESCBOARD)
		WHERE RNUM >= 5 AND RNUM <= 10;
		
		
		
		-- 2) VIEW
		
		GRANT CREATE VIEW TO JSP_Servlet;
		-- VIEW를 만들 수 있는 권한을 부여해야 함 - system으로 바꿔서 설정하고 다시 JSP_Servlet으로 돌아옴
		
		CREATE OR REPLACE VIEW BLIST
		AS
		SELECT ROWNUM RNUM, DESCBOARD.*
		FROM(SELECT BOARD_ID, BOARD_TYPE, CATE_NAME, BOARD_TITLE, BOARD_CONTENT, BOARD_WRITER, NICKNAME, 
		            BOARD_COUNT, CREATE_DATE, B.MODIFY_DATE, B.STATUS
		     FROM BOARD B
		          JOIN MEMBER ON (BOARD_WRITER = USER_ID)
		          JOIN CATEGORY USING (CATE_ID)
		     WHERE B.STATUS = 'Y'
		     ORDER BY BOARD_ID DESC) DESCBOARD;
		
		SELECT * FROM BLIST
		WHERE RNUM >= 5 AND RNUM <= 10 AND BOARD_TYPE=1;
	   
	 */
	
	
	
	
	public int insertBoard(Connection conn, Board b) { // check
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertBoard");
		// insertBoard=INSERT INTO BOARD VALUES(SEQ_BID.NEXTVAL, ?, ?, ?, ?, ?, DEFAULT, SYSDATE, SYSDATE, DEFAULT)
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, b.getBoardType());
			pstmt.setInt(2, Integer.parseInt(b.getCategory()));
			pstmt.setString(3, b.getBoardTitle());
			pstmt.setString(4, b.getBoardContent());
			pstmt.setString(5, b.getBoardWriter());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList selectBList(Connection conn) { // boardList // check
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
		
		String query = prop.getProperty("selectBList");
		// selectBList=SELECT * FROM BLIST WHERE BOARD_TYPE=2
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Board>();
			
			while(rset.next()) {
				list.add(new Board(rset.getInt("BOARD_ID"),
								   rset.getInt("BOARD_TYPE"),
								   rset.getString("CATE_NAME"),
								   rset.getString("BOARD_TITLE"),
								   rset.getString("BOARD_CONTENT"),
								   rset.getString("BOARD_WRITER"),
								   rset.getString("NICKNAME"),
								   rset.getInt("BOARD_COUNT"),
								   rset.getDate("CREATE_DATE"),
								   rset.getDate("MODIFY_DATE"),
								   rset.getString("STATUS")));
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	public ArrayList selectFList(Connection conn) { // check
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Attachment> list = null;
		
		String query = prop.getProperty("selectFList");
		// selectFList=SELECT * FROM ATTACHMENT WHERE STATUS='Y' AND FILE_LEVEL=0
		// 파일리스트 조회(사진게시판 목록 불러오기)
		// FILE_LEVEL=0인 이유 : 썸네일, 1 : 내용 안에 들어있는 사진
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Attachment>();
			// 전체 썸네일 이미지들을 가져오기 때문에 ArrayList로 받는다
			while(rset.next()) {
				list.add(new Attachment(rset.getInt("BOARD_ID"), // 어느 게시판에 있는 건지
										rset.getString("CHANGE_NAME")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	public int insertAttachment(Connection conn, ArrayList<Attachment> fileList) { // check
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertAttachment");
		// insertAttachment=INSERT INTO ATTACHMENT VALUES(SEQ_FID.NEXTVAL, SEQ_BID.CURRVAL, ?, ?, ?, SYSDATE, ?, DEFAULT, DEFAULT)
		// 									FILE_ID BOARD_ID ORIGIN_NAME CHANGE_NAME FILE_PATH UPLOAD_DATE FILE_LEVEL DOWNLOAD_COUNT STATUS
		
			
			try { 
				for(int i = 0; i < fileList.size(); i++) {
					Attachment at = fileList.get(i);
					
					pstmt = conn.prepareStatement(query);
					
					pstmt.setString(1, at.getOriginName());
					pstmt.setString(2, at.getChangeName());
					pstmt.setString(3, at.getFilePath());
					pstmt.setInt(4, at.getFileLevel());
					
					result += pstmt.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
		
		return result;
	}

	public int updateCount(Connection conn, int bId) { // check
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateCount");
		// updateCount=UPDATE BOARD SET BOARD_COUNT = BOARD_COUNT + 1 WHERE BOARD_ID=?
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public Board selectBoard(Connection conn, int bId) { // check
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board b = null;
		
		String query = prop.getProperty("selectBoard");
		// selectBoard=SELECT * FROM BDETAIL WHERE BOARD_ID=?
		/*
		 *  SQL DEVELOPER
		 
		 	CREATE OR REPLACE VIEW BDETAIL
			AS
			SELECT BOARD_ID, BOARD_TYPE, CATE_NAME, BOARD_TITLE, BOARD_CONTENT, BOARD_WRITER, 
			        NICKNAME, BOARD_COUNT, CREATE_DATE, BOARD.MODIFY_DATE, BOARD.STATUS
			FROM BOARD 
			    JOIN MEMBER ON (USER_ID = BOARD_WRITER) 
			    JOIN CATEGORY USING(CATE_ID)
			WHERE BOARD.STATUS='Y'; 
		 */
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b = new Board(rset.getInt("BOARD_ID"),
								rset.getInt("BOARD_TYPE"),
								rset.getString("CATE_NAME"),
								rset.getString("BOARD_TITLE"),
								rset.getString("BOARD_CONTENT"),
								rset.getString("BOARD_WRITER"),
								rset.getString("NICKNAME"),
								rset.getInt("BOARD_COUNT"),
								rset.getDate("CREATE_DATE"),
								rset.getDate("MODIFY_DATE"),
								rset.getString("STATUS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
				
		return b;
	}

	public ArrayList<Attachment> selectThumbnail(Connection conn, int bId) { // check
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Attachment> list = null;
		
		String query = prop.getProperty("selectThumbnail");
		// selectThumbnail=SELECT * FROM ATTACHMENT WHERE BOARD_ID=? AND STATUS='Y' ORDER BY FILE_ID
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Attachment>();
			while(rset.next()) {
				Attachment at = new Attachment();
				at.setFileId(rset.getInt("FILE_ID"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
				at.setFilePath(rset.getString("FILE_PATH"));
				
				list.add(at);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int updateBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateBoard");
		// updateBoard=UPDATE BOARD SET CATE_ID=?, BOARD_TITLE=?, BOARD_CONTENT=? WHERE BOARD_ID=?
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, Integer.parseInt(board.getCategory()));
			pstmt.setString(2, board.getBoardTitle());
			pstmt.setString(3, board.getBoardContent());
			pstmt.setInt(4, board.getBoardId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteBoard(Connection conn, int bId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteBoard");
		// deleteBoard=UPDATE BOARD SET STATUS='N' WHERE BOARD_ID=?
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Reply> selectReplyList(Connection conn, int bId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Reply> rList = null;
		
		String query = prop.getProperty("selectReplyList");
		// SELECT * FROM RLIST WHERE REF_BID = ?;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			
			rset = pstmt.executeQuery();
			rList = new ArrayList<Reply>();
			while(rset.next()) {
				rList.add(new Reply(rset.getInt("REPLY_ID"),
									rset.getString("REPLY_CONTENT"),
									rset.getInt("REF_BID"),
									rset.getString("REPLY_WRITER"),
									rset.getString("NICKNAME"),
									rset.getDate("CREATE_DATE"),
									rset.getDate("MODIFY_DATE"),
									rset.getString("STATUS")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return rList;
	}
	/*
	 *  SQL DEVELOPER
	 
	 	CREATE OR REPLACE VIEW RLIST
		AS
		SELECT REPLY_ID, REPLY_CONTENT, REF_BID, REPLY_WRITER, NICKNAME, CREATE_DATE, REPLY.MODIFY_DATE, REPLY.STATUS
		FROM REPLY 
		     JOIN MEMBER ON (USER_ID = REPLY_WRITER)
		WHERE REPLY.STATUS = 'Y'
		ORDER BY REPLY_ID DESC; 

	 */

	public int insertReply(Connection conn, Reply r) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertReply");
		// insertReply=INSERT INTO REPLY VALUES(SEQ_RID.NEXTVAL, ?, ?, ?, SYSDATE, SYSDATE, DEFAULT)
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, r.getReplyContent());
			pstmt.setInt(2, r.getRefBId());
			pstmt.setString(3, r.getReplyWriter());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
}
