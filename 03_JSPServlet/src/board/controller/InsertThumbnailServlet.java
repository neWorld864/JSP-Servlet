package board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import board.model.service.BoardService;
import board.model.vo.Attachment;
import board.model.vo.Board;
import common.MyFileRenamePolicy;
import member.model.vo.Member;

/**
 * Servlet implementation class InsertThumbnailServlet
 */
@WebServlet("/insert.th")
public class InsertThumbnailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertThumbnailServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
//		String title = request.getParameter("title");
//		System.out.println(title);
		
		if(ServletFileUpload.isMultipartContent(request)) { // enctype이 multipart/form-date로 전송되었는지 확인
			
			// 전송파일에 대한 용량 설정
			int maxSize = 1024*1024*10; // 전송파일 용량 : 10Mbyte
			String root = request.getSession().getServletContext().getRealPath("/");
			String savePath = root + "thumbnail_uploadFiles/";
			
			
			//System.out.println(savaPath);
			// 파일 이용할 때는 무조건 JSPServletServer 들어가서 Serve modules without publishing 체크 꼭 해줘야함!!
			
			File f = new File(savePath);
			if(!f.exists()) {
				f.mkdirs();
			}
			// 이거하고 서버 실행해서 등록하긴가?누르면 폴더가 생성됨
			
			/*
			  	<파일 명 변환 및 저장>
			  	
			  	사용자가 올린 파일 명을 그대로 저장하지 않는 것이 일반적
			  	같은 파일 명이 있을 경우 이전 파일을 덮어쓸 수 있는 위험(1)이 있고 DB값과 저장된 파일 명의 이름이 다르면 찾을 수 없음
			  	또한 한글로 되어있거나 특수기호/띄어쓰기 등으로 이루어진 파일 명은 서버에 따라 문제가 생길 수도 있음
			  	
			  	파일 명 변환 클래스 : DefaultFileRenamePolicy 클래스 (cos.jar)
			  		같은 파일 명이 있는지 검사후 있을 경우 파일 명 뒤에 숫자를 붙여줌 (ex. aaa.zip, aaa1.zip aaa2.zip) -> 그래서 우리가 원하는 거랑은 다름
			  		MultipartRequest multipartRequest 
			  			= new MultipartRequest(request, savaPath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
			  			
			  			* cos.jar ?
			  			  view에서 보낸 파일도 받고, 다른 일반 데이터 값들도 받아주는 역할
			  			
			  			
			  	하지만 우리는 DefaultFileRenamePolicy를 사용하지 않고 직접, 우리 방식대로, rename작업을 하기 위한 클래스를 만들 것
			  	--> common.MyFileRenamePolicy
			 
			 */
			
			MultipartRequest multipartRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			ArrayList<String> saveFiles = new ArrayList<String>();		// 바뀐 이름의 파일을 저장할 용도
			ArrayList<String> originFiles = new ArrayList<String>();	// 원본 이름의 파일을 저장할 용도
			
			Enumeration<String> files = multipartRequest.getFileNames(); // getFileNames(): 폼에서 전송된 파일 리스트들의 이름 반환 / getFileNames()의 반환값 : Enumeration (Iterator의 구버전)
			while(files.hasMoreElements()) {
				String name = files.nextElement(); // 사진을 다 넣지 않아도 4321이 다 뜸
				/*
					System.out.println(name);
				 	thumbnailImg4
					thumbnailImg3
					thumbnailImg2
					thumbnailImg1 
					결과가 거꾸로 나옴!
				 */
				
				/*
					System.out.println(multipartRequest.getFilesystemName(name));
				 	* 썸네일만 넣고 출력한 결과
				 	null 파일이 없다
					null
					null
					202104091009239400.png => thumbnailImg1 
					지정한 리네임 방식대로 출력됨
				 */
				
				if(multipartRequest.getFilesystemName(name) != null) { // getFilesystemName(name) : MyFileRenamePolicy.rename()에서 작성한대로 rename된 파일명 반환
					saveFiles.add(multipartRequest.getFilesystemName(name)); // rename한 이름 가져오기
					originFiles.add(multipartRequest.getOriginalFileName(name)); // 실제 업로드된 파일 이름 가져오기
				}
			}
			
			/*
				System.out.println("saveFile : " + saveFiles);
			 	saveFile : [202104091015509160.png]
			 	
				System.out.println("originFile : " + originFiles);
				originFile : [로고-removebg-preview.png] 
			 */
			
			String title = multipartRequest.getParameter("title");
			String content = multipartRequest.getParameter("content");
			String writer = ((Member)request.getSession().getAttribute("loginUser")).getUserId();
			
			Board b = new Board();
			b.setBoardTitle(title);
			b.setBoardContent(content);
			b.setBoardWriter(writer);
			b.setBoardType(2);
			b.setCategory("10");
			
			ArrayList<Attachment> fileList = new ArrayList<Attachment>();
			for(int i = originFiles.size() - 1; i >= 0; i--) {
				Attachment at = new Attachment();
				at.setFilePath(savePath);
				at.setOriginName(originFiles.get(i));
				at.setChangeName(saveFiles.get(i));
				
				if(i == originFiles.size() - 1) {
					at.setFileLevel(0);
				} else {
					at.setFileLevel(1);
				}
				
				fileList.add(at);
			}
			
			// 보내기
			int result = new BoardService().insertThumbnail(b, fileList);
			
			if(result > 0) {
				response.sendRedirect("list.th");
			} else {
				request.setAttribute("msg", "사진 게시판 등록에 실패하였습니다");
				
				for(int i = 0; i < saveFiles.size(); i++) {
					File fail = new File(savePath + saveFiles.get(i));
					fail.delete();
				}
				
				request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
