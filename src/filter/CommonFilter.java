package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class CommonFilter
 */
@WebFilter(filterName = "encoding", urlPatterns = { "/*" })
// encoding: 실제 이름, /* : 모든 곳에 적용하겠다
public class CommonFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CommonFilter() {
        System.out.println("CommonFilter 작동");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		// 문자셋 변환 필터 : 인코딩
		
		// 뷰에서 전달받은 값에 한글이 있는 경우
		request.setCharacterEncoding("UTF-8");
		
		// 뷰로 전달할 값이 한글이 있는 경우
		response.setContentType("text/html; charset=UTF-8");
		// 프로젝트 -> 너무 긴 css를 따로 외부로 빼놓았는데 먹지 않는다 -> 얘를 지워주어야 함
		
		// 비밀번호 암호화 -> wrapper 사용. 
		// SHA-512, Bcypt
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
