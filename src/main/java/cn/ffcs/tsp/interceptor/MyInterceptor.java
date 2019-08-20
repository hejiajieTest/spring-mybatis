package cn.ffcs.tsp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		StringBuffer url = request.getRequestURL();
		if(url.indexOf("index.do") > 0){
			//已经登陆过
			return true ;
		}
		//浏览器会保存session
		HttpSession session = request.getSession() ;
		String strr= (String) session.getAttribute("loginTest");
		if(strr != null){
			System.out.println(strr);
			return true ;
		}
		return true;
	}
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse response, Object arg2, ModelAndView model)
			throws Exception {
	}

}
