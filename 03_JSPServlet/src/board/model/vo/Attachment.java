package board.model.vo;

public class Attachment {
	private int fileId;
	private int boardId;
	private String originName;
	private String changeName;
	private String filePath;
	private int fileLevel;
	private int downloadCount;
	private String status;
	
	public Attachment() {}
	
	public Attachment(int boardId, String changeName) {
		super();
		this.boardId = boardId;
		this.changeName = changeName; 
		// 왜 changeName이라고 하는지? 0408 6교시
		// 카카오 사용 시 카카오OOOO으로 설정 -> 사용자가 너무 많아서 뒤에 숫자가 붙게 됨 이렇게 사용자가 많으면 같은 이름을 방지하기 위해 중복되지 않는 다른 이름들로 설정됨 => changeName
	}

	public Attachment(int fileId, int boardId, String originName, String changeName, String filePath, int fileLevel,
			int downloadCount, String status) {
		super();
		this.fileId = fileId;
		this.boardId = boardId;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.fileLevel = fileLevel;
		this.downloadCount = downloadCount;
		this.status = status;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changName) {
		this.changeName = changName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getFileLevel() {
		return fileLevel;
	}

	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}

	public int getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Attachment [fileId=" + fileId + ", boardId=" + boardId + ", originName=" + originName + ", changName="
				+ changeName + ", filePath=" + filePath + ", fileLevel=" + fileLevel + ", downloadCount=" + downloadCount
				+ ", status=" + status + "]";
	}
	
	
	
}
