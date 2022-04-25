package kr.or.ddit.util;
import java.net.URLEncoder;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

public class PagingUtil {
	
	// 출력될 게시글 리스트를 전달받아 페이징이 구현된 html 코드를 생성 후 해당 페이지에 추가.
	
	// 현재페이지(최초 페이징 시 1.. 이후 브라우저에 출력되는 페이지 번호 클릭시의 번호)
	private int currentPage;		
	private int totalCount;			// 전체 게시물 수
	private int totalPage;			// 전체 페이지 수
	private int blockCount;			// 한 페이지당 출력될 게시물 수
	private int blockPage;			// 한 화면에 보여줄 페이지 번호 갯수 
	private int startCount;			// 시작 게시물 번호
	private int endCount;			// 종료 게시물 번호
	private int startPage;			// 시작 페이지 번호
	private int endPage;			// 종료 페이지 번호
	private String param;           // 개별 페이지 번호 마다 코딩될 링크주소에 포함될 파라메터 값 구성
	private String pageVariableName; // 페이지 번호 마다 코딩될 링크주소에 포함될 페이지 번호  , 기본은 "currentPage"
	
	
	// 페이지 링크 HTML 데이터를 담는 버퍼
	private StringBuffer pageHtml = new StringBuffer();

	public PagingUtil(int currentPage, int totalCount, 
			int blockCount, int blockPage, HttpServletRequest request){
		this.totalCount = totalCount;
		this.blockCount = blockCount;
		this.blockPage = blockPage;
		
		// 1. 총페이지 수 구하기
		totalPage = (int)Math.ceil(totalCount / (double)blockCount);
		
		if(totalPage == 0){
			totalPage = 1;
		}
		
		// 2. 시작/종료 게시물 번호
		// 화면에 출력되는 페이지 번호 중 클릭되어 currentPage 변수에 담긴 수로부터 -1 * 한페이지에 출력될
		// 게시물수를 곱하기 연산하고 +1 연산해 이전 페이지의 가장 마지막 게시물 수 +1을 startCount로 할당함.
		startCount = totalCount - (currentPage - 1) * blockCount ;
		endCount = startCount - blockCount + 1;
		
		if(endCount < 0){
			endCount = 1;
		}
		
		// 3. 시작/종료 페이지 번호
		// 화면에 출력되는 페이지 번호 중 클릭되어 currentPage 변수에 담긴 수로부터 -1하여 이전 페이지
		startPage = ((currentPage - 1) / blockPage * blockPage) + 1;
		endPage = startPage + blockPage - 1;
		
		if(endPage > totalPage){
			endPage = totalPage;
		}
		
		//memberList.jsp?search_keycode=ALL&search_keyword=힝
		// 요청 URI 가져오기
		String requestURI = request.getRequestURI();
		
		if(pageVariableName==null || pageVariableName.isEmpty()) pageVariableName = "currentPage" ;
		
		// 요청 패러미터 셋팅
		String params = "";
		Enumeration<String> paramEnum = request.getParameterNames();		
		while(paramEnum.hasMoreElements()){
			
			String name = paramEnum.nextElement();
			if(!pageVariableName.equals(name)){
				String[] values = request.getParameterValues(name);
				for(int i = 0; i < values.length; i++){
					try{
						params += name + "=" + URLEncoder.encode(values[i], "utf-8") + "&";
					}catch(Exception e){}
				}
			}
		}
		this.param = params;
		
		// 이전 생성
		if((currentPage - 1) != 0){
			pageHtml.append("&nbsp;<a href='" + requestURI + "?" + params + pageVariableName + "=" + (currentPage -1 ) +
						"'><img src='" + request.getContextPath() + "/image/btn_back01.gif' align='bottom'></a>&nbsp;");
		}else{
			pageHtml.append("&nbsp;<img src='" + request.getContextPath() + "/image/btn_back01.gif' align='bottom'>&nbsp;");
		}
		
		
		pageHtml.append("&nbsp;&nbsp;|&nbsp;&nbsp;");
		// 페이지 네비게이터 생성
		for(int i = startPage; i <= endPage; i++){
			
			if(currentPage == i){
				pageHtml.append("&nbsp;<b><font color='red'>" + i +
						"</font></b>&nbsp;");
			}else{			
				pageHtml.append("&nbsp;<a href='" + requestURI + "?" + params + pageVariableName + "=" + i +
						"'>" + i + "</a>&nbsp;");				
			}
			
			pageHtml.append("&nbsp;&nbsp;|&nbsp;&nbsp;");
		}
		
		// 다음 생성
		if(currentPage < totalPage){
			pageHtml.append("&nbsp;<a href='" + requestURI + "?" + params + pageVariableName + "=" + (currentPage + 1) +
				"'><img src='" + request.getContextPath() + "/image/btn_next01.gif' align='bottom'></a>&nbsp;");
		}else{
			pageHtml.append("&nbsp;<img src='" + request.getContextPath() + "/image/btn_next01.gif' align='bottom'>&nbsp;");
		}
	}	
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBlockCount() {
		return blockCount;
	}

	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}

	public int getBlockPage() {
		return blockPage;
	}

	public void setBlockPage(int blockPage) {
		this.blockPage = blockPage;
	}

	public int getStartCount() {
		return startCount;
	}

	public void setStartCount(int startCount) {
		this.startCount = startCount;
	}

	public int getEndCount() {
		return endCount;
	}

	public void setEndCount(int endCount) {
		this.endCount = endCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public StringBuffer getPageHtml() {
		return pageHtml;
	}

	public void setPageHtml(StringBuffer pageHtml) {
		this.pageHtml = pageHtml;
	}

  public String getParam() {
    return param;
  }

  public void setParam(String param) {
    this.param = param;
  }

  public String getPageVariableName() {
    return pageVariableName;
  }

  public void setPageVariableName(String pageVariableName) {
    this.pageVariableName = pageVariableName;
  }
}
