package member.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import member.model.vo.Member;

public class MemberDAO {
	
	private Properties prop = new Properties();
	
	public MemberDAO() {
		String fileName = MemberDAO.class.getResource("/sql/member/member-query.properties").getPath();
		
			try {
				prop.load(new FileReader(fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	
	public Member loginMember(Connection conn, Member member) { // check
		
		PreparedStatement pstmt = null;
		ResultSet rset = null; // 받아온 아이디, 비밀번호가 있는지 없는지 확인 -> select ==> resultset
		Member loginUser = null;
		
		String query = prop.getProperty("loginMember");
		// loginMember=SELECT * FROM MEMBER WHERE USER_ID = ? AND USER_PWD = ? AND STATUS = 'Y'
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserPwd());
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				loginUser = new Member(rset.getString("USER_ID"),
										rset.getString("USER_PWD"),
										rset.getString("USER_NAME"),
										rset.getString("NICKNAME"),
										rset.getString("PHONE"),
										rset.getString("EMAIL"),
										rset.getString("ADDRESS"),
										rset.getString("INTEREST"),
										rset.getString("ENROLL_DATE"),
										rset.getString("MODIFY_DATE"),
										rset.getString("STATUS"));
				// SELECT해서 가져온 컬럼값들만 가져오는 것임. 순서는 MEMBER의 순서를 따라야 한다!
				// 여기서는 *로 가져왔기 때문에 모든 컬럼 값을 다 가져온 것
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// import static common.JDBCTemplate.close; 를 해줘야 빨간 줄이 안 뜸
			close(rset); 
			close(pstmt);
		}
		
		return loginUser;
	}

	public int insertMember(Connection conn, Member member) { // check
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertMember");
		// insertMember=INSERT INTO MEMBER VALUES(?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, SYSDATE, 'Y')
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserPwd());
			pstmt.setString(3, member.getUserName());
			pstmt.setString(4, member.getNickName());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getAddress());
			pstmt.setString(8, member.getInterest());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int checkId(Connection conn, String inputId) { // check
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("idCheck");
		// idCheck=SELECT COUNT(*) FROM MEMBER WHERE USER_ID=?
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, inputId);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return result;
	}

	public Member selectMember(Connection conn, String userId) { // check
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		
		String query = prop.getProperty("selectMember");
		// selectMember=SELECT * FROM MEMBER WHERE USER_ID = ?
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				member = new Member(rset.getString("USER_ID"),
									rset.getString("USER_PWD"),
									rset.getString("USER_NAME"),
									rset.getString("NICKNAME"),
									rset.getString("PHONE"),
									rset.getString("EMAIL"),
									rset.getString("ADDRESS"),
									rset.getString("INTEREST"),
									rset.getString("ENROLL_DATE"),
									rset.getString("MODIFY_DATE"),
									rset.getString("STATUS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return member;
	}

	public int checkNick(Connection conn, String inputNick) { // check
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("nickCheck");
		// nickCheck=SELECT COUNT(*) FROM MEMBER WHERE NICKNAME=?
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, inputNick);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return result;
	}

	public int updateMember(Connection conn, Member myInfo) { // check
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateInfo");
		// updateInfo=UPDATE MEMBER SET USER_NAME=?, NICKNAME=?, PHONE=?, EMAIL=?, ADDRESS=?, INTEREST=?, MODIFY_DATE=SYSDATE WHERE USER_ID = ?
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, myInfo.getUserName());
			pstmt.setString(2, myInfo.getNickName());
			pstmt.setString(3, myInfo.getPhone());
			pstmt.setString(4, myInfo.getEmail());
			pstmt.setString(5, myInfo.getAddress());
			pstmt.setString(6, myInfo.getInterest());
			pstmt.setString(7, myInfo.getUserId());
			
			result = pstmt.executeUpdate(); // 채워넣은 거 보내주기
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}



	public int updatePwd(Connection conn, String userId, String userPwd, String newPwd) { // check
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updatePwd");
		// 비밀번호수정6) member-query.properties에 쿼리문 작성하기
		// updatePwd=UPDATE MEMBER SET USER_PWD=? WHERE USER_ID=? AND USER_PWD=?
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newPwd);
			pstmt.setString(2, userId);
			pstmt.setString(3, userPwd);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteMember(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteMember");
		// deleteMember=UPDATE MEMBER SET STATUS='N' WHERE USER_ID=?
		// 탈퇴하기 -> delete아니고 update로 (status를 'n'으로 변경) 
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
