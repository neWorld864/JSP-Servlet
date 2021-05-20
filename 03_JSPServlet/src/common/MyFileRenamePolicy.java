package common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy { // 이렇게 리네임을 하겠다라는 정책 implements FileRenamePolicy라는 상위 클래스가 있어야 함

	/*
	 * 리네임하는 이유: 파일 이름을 바꿔주지 않으면 덮어씌워질수있고 다른 이름으로 저장될 수 있고 한글, 특수문자인 경우 가져오지 못 하는 경우가 생길 수 있음
	 * => 정확하게 내가 원하는 파일을 가져오기 위해
	 * 인코딩이 multipartformdate?로 되어있을때 가져올 수 있는 라이브러리가 존재 -> cos.jar
	 * cos: com.oreilly.servlet 약어
	 * */
	
	
	@Override
	public File rename(File originFile) {
		// 파일 이름 겹치지 않게 만드는 가장 만만한 요소 : 시간
		// 만일 극한의 확률로 시간이 겹칠 수 있으니 랜덤한 숫자 하나도(범위 크게해서) 추가함
		
		long currentTime = System.currentTimeMillis();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		// SimpleDateFormat : Date의 날짜, 시간 정보를 원하는 format으로 출력하는 기능
		
		int ranNum = (int)Math.random()*1000000;
		
		// 파일을 첨부할 거면 확장자를 유지시켜줘야함 -> 어디까지가 확장자인지 확인
		// 확장자 가져오기
		String name = originFile.getName();
		String ext = null;
		
		int dot = name.lastIndexOf(".");
		// 확장자가 없는 경우 -1이 반환
		
		if(dot != -1) {
			ext = name.substring(dot);
		} else {
			ext = "";
		}
		
		String fileName = sdf.format(new Date(currentTime)) + ranNum + ext;
		// Date import는 util에 있는 걸로
		File newFile = new File(originFile.getParent(), fileName); // getName(): Returns the pathname 경로를 가져옴
		// rename하면서 저장했기때문에 thumbnail.uploadFiles에 사진이 저장된다 -> 게시글 등록 안 했으면 삭제해도됨
		
		return newFile;
	} 
	
}
