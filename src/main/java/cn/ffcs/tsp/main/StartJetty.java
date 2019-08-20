package cn.ffcs.tsp.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class StartJetty {

	private static StartJetty st = new StartJetty();
	
	private StartJetty(){}
	
	public static StartJetty getInstance(){
		return st ;
	}
	
	public void startJetty(){
		    Server server = new Server(2300);
	        WebAppContext webapp = new WebAppContext();
	        webapp.setContextPath("/mybatis");
	        webapp.setResourceBase("D:/sts_data/spring-mybatis/src/main/webapp/");
	        webapp.setDescriptor("D:/sts_data/spring-mybatis/src/main/webapp/web.xml");
	        webapp.setParentLoaderPriority(true);
	        webapp.setClassLoader(Thread.currentThread().getContextClassLoader());
	        server.setHandler(webapp);
	        try {
	            System.out.println("===========================================================");
	            System.out.println("jetty方式启动访问地址：localhost:2300"+webapp.getContextPath());
	            server.start();
	            System.out.println("===========================================================");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
}
