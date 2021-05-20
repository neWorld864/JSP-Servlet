package wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptWrapper extends HttpServletRequestWrapper{
	// 암호화는 요청할 때? 응답을 받을 때? 언제 해야할까
	// -> 요청을 할 때 암호화가 들어가야 한다! : 응답을 받는 건 이미 받고 보내서 리턴을 받아오는 것이기 때문에
	// select, insert... 등등 할 때부터 암호화가 들어가 있어야 함 => HttpServletRequestWrapper 상속 받아야 함
	
	
	// Implicit super constructor HttpServletRequestWrapper() is undefined for default constructor. 
	// Must define an explicit constructor
	public EncryptWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub // add constructor 해서 생성
		
	}
	
	@Override
	public String getParameter(String name) {
		// 매개변수 name으로 parameter 이름이 들어옴
		// ex. joinUserPwd, joinUserId, joinUserName 등등
		// 하지만 나는 여기서 비밀번호에 대한 parameter만 필요함 => 비밀번호에 관련된 것들을 해볼건데 ...
		
		String value = null;
		
		// 비밀번호 들어가 있는 것 : 회원가입(joinUserPwd), 로그인(userPwd in menubar), 비밀번호 업데이트(newPwd)
		if(name != null && (name.equals("userPwd") || name.equals("joinUserPwd") || name.equals("newPwd"))) {
			// 암호화 시작
			
			try {
				// SHA-512 방식의 암호화 객체
				MessageDigest md = MessageDigest.getInstance("SHA-512");
				
				// userPwd, joinUserPwd, newPwd 파라미터 안에 담긴 데이터를 가져오는 코드
				String pwd = super.getParameter(name);
				byte[] bytes = pwd.getBytes(Charset.forName("UTF-8"));
				
				md.update(bytes); // md객체에 bytes배열을 전달하여 갱신
				
//				value = Base64.getEncoder().encodeToString(md.digest());
				Encoder encoder = Base64.getEncoder(); // import java.util.Base64.Encoder;
				value = encoder.encodeToString(md.digest());
				
			} catch (NoSuchAlgorithmException e) { // 없는 알고리즘일때 생기는 오류
				e.printStackTrace();
			} 
			
		} else {
			// 원래 있었던 getParameter 따르면 됨
			value = super.getParameter(name);
		}
		
		return value;
	}
}
