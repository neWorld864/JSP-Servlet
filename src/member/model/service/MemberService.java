package member.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

import member.model.dao.MemberDAO;
import member.model.vo.Member;

public class MemberService {

	public Member loginMember(Member member) {
		// connection 객체를 불러와야 함 -> Template 만들어서 getConnection() 연결 => common/JDBCTemplate 생성
		// db 생성하고
		// src/sql/driver.properties 생성
		
		Connection conn = getConnection(); // JDBCTemplate.getConnection()이라고 원래는 써야 하는데 너무 기니까 임포트해주는 대신 줄여 씀
		
		Member loginUser = new MemberDAO().loginMember(conn, member);
		
		close(conn);
		
		return loginUser;
	}

	public int insertMember(Member member) { // check
		Connection conn = getConnection();
		
		int result = new MemberDAO().insertMember(conn, member);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}

	public int checkId(String inputId){ // check
		Connection conn = getConnection();
		
		int result = new MemberDAO().checkId(conn, inputId);
		
		close(conn);
		
		return result;
	}

	public Member selectMember(String userId){ // check
		Connection conn = getConnection();
		
		Member member = new MemberDAO().selectMember(conn, userId);
		
		close(conn);
		
		return member;
	}

	public int checkNick(String inputNick){ // check
		Connection conn = getConnection();
		
		int result = new MemberDAO().checkNick(conn, inputNick);
		
		close(conn);
		
		return result;
	}

	public int updateMember(Member myInfo){ // check
		Connection conn = getConnection();
		
		int result = new MemberDAO().updateMember(conn, myInfo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}


	public int updatePwd(String userId, String userPwd, String newPwd){ // check
		Connection conn = getConnection();
		
		int result = new MemberDAO().updatePwd(conn, userId, userPwd, newPwd);
		// 비밀번호수정5) MemberDAO에 updatePwd메소드 만들기
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int deleteMember(String userId){
		Connection conn = getConnection();
		
		int result = new MemberDAO().deleteMember(conn, userId);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
		
}
