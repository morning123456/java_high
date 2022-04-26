package kr.or.ddit.basic.stream;

import java.awt.Panel;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DialogTest {

	public static void main(String[] args) {
		// SWING의 파일 열기창, '저장창', 연습
		
		JFileChooser chooser = new JFileChooser();
		
		//선택할 파일의 확장자 설정
		
		FileNameExtensionFilter doc = new FileNameExtensionFilter("MS Word", "docx","doc");
		
		FileNameExtensionFilter img = new FileNameExtensionFilter("Images", new String[] {"png","jpg","gif"});
		
		FileNameExtensionFilter txt = new FileNameExtensionFilter("Test파일", "txt");
		
		// 구성한 확장자들을 추가한다.
		chooser.addChoosableFileFilter(doc);
		chooser.addChoosableFileFilter(img);
		chooser.addChoosableFileFilter(txt);
		
		// 확장자 목록 중에 처음부터 선택될 확장자 지정
		chooser.setFileFilter(img); 

		//Dialog 창이 나타낼 기본 경로 설정
		// 예1) 'D:/d_other' 폴더로 설정하기
//		chooser.setCurrentDirectory(new File("d:/d_other"));
		
		// 예2) '바탕화면' 으로 설정하기
		chooser.setCurrentDirectory(new File(System.getProperty("user.home")+"/desktop"));
		
		
		//확장자 목록 중에 '모든 파일'목록의 표시 여부 설정하기
		//(true : 모든 파일목록 보이기, false : 해제)
//		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setAcceptAllFileFilterUsed(true);
		
		
		//열기용 창 보여주기
		int result = chooser.showOpenDialog(new Panel());
		
		//저장용 창 보여주기
//		int result = chooser.showSaveDialog(new Panel());
		
		// 창에서'저장' 또는 '열기' 버튼을 클릭했을 떄 처리
		if(result == JFileChooser.APPROVE_OPTION) {
			//현재 선택한 파일 정보를 가져올 수 있다.
			File selectedFile = chooser.getSelectedFile();
			System.out.println("선택한 파일 : " + selectedFile.getAbsolutePath());
			
			//이후의 내용은 선택한 파일을 실제 읽어오거나 저장하는 코드를 작성하면 된다.
			
		}

	}
	
	

}
