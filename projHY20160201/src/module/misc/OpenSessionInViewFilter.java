package module.misc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import module.util.HibernateUtil;

public class OpenSessionInViewFilter implements Filter {
	private FilterConfig filterConfig;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
//		System.out.println("filter init");
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
//		System.out.println("do filter");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
//			System.out.println("begin tr");
			chain.doFilter(request, response);
//			System.out.println("chain");
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
//			System.out.println("after tr");
		} catch (HibernateException e) {
			e.printStackTrace();
//			System.out.println("rollback");
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
			chain.doFilter(request, response);
		}
	}
	@Override
	public void destroy() {

	}
}
