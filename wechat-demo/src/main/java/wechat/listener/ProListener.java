package wechat.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ProListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent paramServletContextEvent) {
		System.out.println("web容器关闭.");
	}

	@Override
	public void contextInitialized(ServletContextEvent paramServletContextEvent) {
		
		new AccessTokenCache().initToken();
		System.out.println("web容器启动,取得token:" + AccessTokenCache.getToken());

	}

}
