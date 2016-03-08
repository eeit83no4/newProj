package module.misc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.sun.xml.internal.ws.transport.http.HttpAdapter;

public class LoginFilter implements Filter {

	private FilterConfig filterConfig;
	//需要排除的頁面 	 
	private String excludedPages;
	private String[] excludedPageArray;  
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		excludedPages = filterConfig.getInitParameter("excludedPages");  
		if (StringUtils.isNotEmpty(excludedPages)) {
			System.out.println("excludedPages="+excludedPages);
			excludedPageArray = excludedPages.split(",");  
		}  
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)resp;		
		HttpSession session=request.getSession();
		
		boolean isExcludedPage = false;
		//判斷是否在排除範圍
		for(String page:excludedPageArray){		
			if(request.getServletPath().equals(page)){				
				isExcludedPage=true;
				break;
			}
		}
		
		if(isExcludedPage){//在排除範圍			
			chain.doFilter(request, response);
		}else{//不在排除範圍
			if(session.getAttribute("LoginOK")!=null){
				chain.doFilter(request, response);
				System.out.println("已登入");
			}else{
				System.out.println("未登入");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}
		

		
	}

	@Override
	public void destroy() {
		
		
	}

}
