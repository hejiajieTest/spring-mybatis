package cn.ffcs.tsp.main;

public class Application {

	public static void main(String[] args){
//		ApplicationContext ds = new ClassPathXmlApplicationContext("classpath:dubbo-provider.xml");
//	    // 闃诲褰撳墠杩涚▼锛屽惁鍒欑▼搴忎細鐩存帴鍋滄
//	    try {
//			System.in.read();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:configs/applicationContext.xml");
//		ctx.start();
//        System.out.println(ctx);
//        //鏌ヨ鎵�鏈�
//		ManualInfoDubboImpl ds = (ManualInfoDubboImpl) ctx.getBean("manualInfoDubboImpl",ManualInfoDubboImpl.class);
//        List<ManualInfo> list = ds.findAll();
//        System.out.println(list.size());
        //System.in.read();
        StartJetty.getInstance().startJetty();
	}
}
