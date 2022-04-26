package kr.or.ddit.board.controller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

/*쿼리문에
조건 있으면 PreparedStatement
조건 없으면 Statement*/

public class BoardController {

	
	private Scanner scan = new Scanner(System.in);
	private IBoardService service;
	
	//생성자
	public BoardController() {
		//service = new MemberServiceImpl();
		service = BoardServiceImpl.getInstance(); //싱글톤
	}
	
	
	//시작메서드
			public void startBoard() {
				while(true) {
					int choice = dispalyMenu();
					switch(choice) {
					case 1 :  //새 글 작성
						insertBoard();        
						break;
					case 2 :  //게시글 보기
						readBoard();
						break;
					case 3 :  //검색
					//	deleteBoard();
						break;
					case 0 :  //작업 끝
						System.out.println("작업을 마칩니다.");
						return;
					default : System.out.println("번호를 잘못 입력했습니다. 다시입력하세요.");
					}
				}
			}

	
			private void readBoard() {
				

				System.out.println("보기를 원하는 게시물 번호 입력 >>");
				int boardNO = scan.nextInt();
				
				List<BoardVO> boardList = service.readBoard(boardNO);

				if(boardList==null ||boardList.size()==0) {
					System.out.println("출력할 자료가 없습니다.");
						
				}else {
					for(BoardVO boardVo : boardList) {
						int NO = boardVo.getNo();
						String title = boardVo.getTitle();
						String WRITER = boardVo.getUser();
						Date date = boardVo.getDate();
						int num = boardVo.getNum();
						String CONTENT = boardVo.getCont();
						
						System.out.println(boardNO +"번글 내용");
						System.out.println("-------------------------------------------------------------");
						System.out.println("- 제  목 :"+title);		  
						System.out.println("- 작성자 :"+WRITER);		  
						System.out.println("- 내  용 : "+CONTENT);		  
						System.out.println("- 작성일 : "+date);		  
						System.out.println("- 조회수 : "+num);		  
						System.out.println("-------------------------------------------------------------");

						System.out.println("출력 끝...");
					}
					
				}
				
				
				System.out.println("메뉴 : 1. 수정    2. 삭제    3. 리스트로 가기");
				int input = scan.nextInt();
				switch(input) {
				case 1 : updateBoard(boardNO); break;
				case 2 : deleteBoard(boardNO); break;
				case 3 : startBoard();
					
				}
					
		
	}


			private void deleteBoard(int boardNO) {
				
				  // 입력한 데이터를 VO객체에 저장한다.
			      BoardVO boardVo = new BoardVO();
			      boardVo.setNo(boardNO); 
				  
				int cnt = service.deleteBoard(boardNO);
				if(cnt>0) {
					System.out.println(boardNO +"번 삭제 성공!!");
				}else {
					System.out.println(boardNO + " 없는 번호이거나 삭제에 실패했습니다.");
				}
			
		}


		private void updateBoard(int boardNO) {
			
			System.out.println("수정 작업하기");
			System.out.println("-----------------------------------");
			System.out.print("- 제  목 :");
			String title = scan.next();
			System.out.print("- 내  용 : ");
			scan.nextLine();  //입력버퍼지우기
			String cont = scan.nextLine();  //띄어쓰기 필요하므로 nextLine
			System.out.println(boardNO+"번 글이 수정되었습니다.");
			
			 BoardVO boardVo = new BoardVO();
			 
			 boardVo.setTitle(title);
			 boardVo.setCont(cont);
			  
			  int cnt = service.updateBoard(boardVo, boardNO);
			  
			  if(cnt>0) {
				  System.out.println("정보 수정 성공!!");
			  }else {
				  System.out.println("정보 수정 실패~");
			  }
		}


			private void insertBoard() {
				System.out.println();
				System.out.println("새 글 작성하기");
				
				
				int count = 0;  
				
				  System.out.print("제목 >> ");
				  String boardTitle = scan.next();
				  
				  System.out.print("작성자 : ");
				  String boardWriter = scan.next();
				  
				  scan.nextLine();  //입력버퍼지우기  //nextLine -> 띄어쓰기 위해 
				  System.out.print("내용 :");
				  String boardContent = scan.nextLine();
				  
				  // 입력한 데이터를 VO객체에 저장한다.
				  BoardVO boardVo = new BoardVO();
				  boardVo.setTitle(boardTitle);
				  boardVo.setUser(boardWriter);
				  boardVo.setCont(boardContent);
				  
				  int cnt = service.insertBoard(boardVo);
				  
				  if(cnt>0) {
					  System.out.println("새 글 작성 성공!!");
				  }else {
					  System.out.println("새 글 작성 실패~");
				  }
				
			}


			
			private void getAllboard() {
				
				List<BoardVO> boardList = service.getAllboard();
				System.out.println();
				System.out.println("-------------------------------------------------");
				System.out.println("No	제 목    작성자 	조회수   ");
				System.out.println("-------------------------------------------------");
				
				
				if(boardList==null ||boardList.size()==0) {
					System.out.println("출력할 자료가 없습니다.");
						
				}else {
					for(BoardVO boardVo : boardList) {
						int no = boardVo.getNo();
						String title = boardVo.getTitle();
						String writer = boardVo.getUser();
						int num = boardVo.getNum();
						
						System.out.println(no + "\t"+title+"\t"+writer+"\t"+num);

					}
				}
					System.out.println("-------------------------------------------------");
				
			}
			
			
			
			
			//메뉴를 출력하고 선택한 작업번호를 반환하는 메서드
			private int dispalyMenu() {
				System.out.println();
			
				getAllboard();				
				
				System.out.println(" == 작 업 선 택 == ");
				System.out.println(" 1.새 글 작성 2.게시글 보기 3.검색 0.작업 끝");
			
				System.out.println("----------------------");
				System.out.print("원하는 작업번호 입력 >> ");		
				return scan.nextInt();
			}
	
	public static void main(String[] args) {
		new BoardController().startBoard();

	}

}
