package member.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmailServlet
 */
@WebServlet("/sendEmail.do")
public class EmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8"); // 사실 안 해도 상관 없음 왜냐면 filter로 했기 때문임
		
		String receiver = request.getParameter("receiver");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		contents = "<h1 style='color:blue;'>" + contents + "</h1>";
//		System.out.println(receiver);
//		System.out.println(title);
//		System.out.println(contents);
		
		// 사용하는 메일
		String host = "smtp.naver.com";
		
		// 보내는 사람 메일
		String sender = "irene4563@naver.com";
		
		// 보내는 사람 비밀번호
		String password = "";
		
		Properties prop = new Properties();
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.auth", true);
		
		// javax.mail-1.6.2.jar lib 폴더에 넣기
		Session session = Session.getDefaultInstance(prop, new Authenticator() { // 익명 클래스 
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		}); 
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			
			message.setSubject("[JSP/Servlet Mail Test] " + title);
			message.setText(contents, "UTF-8", "html");
			
			Transport.send(message);
			System.out.println("전송 완료");
			
		} catch (AddressException e) {
			e.printStackTrace();
			System.out.println("전송 실패");
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("전송 실패");
		}
		
		response.sendRedirect(request.getContextPath()); // 메인으로 돌아가기
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
