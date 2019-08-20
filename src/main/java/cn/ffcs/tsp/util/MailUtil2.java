/**
 * MailUtil.java	  V1.0   2018年10月22日 下午2:08:26
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package cn.ffcs.tsp.util;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;

public class MailUtil2 {
	//private static Logger logger = Logger.getLogger(MailUtil2.class);

	public static boolean sendMail(String subject, String toMail,  
            String content, String file) {  
        boolean isFlag = false;  
        try {  
  
            //String smtpFromMail = PropUtil.getProperty("mail.username");  //账号  
        	String smtpFromMail = "18271735561@163.com";
        	//String smtpFromMail = "18772977110@163.com";
        	//String pwd = PropUtil.getProperty("mail.password"); //密码  
        	String pwd = "hjj19950812kl18z";
        	//String pwd = "li20130530918";
        	//           int port = 25; //端口  
            //String host = PropUtil.getProperty("mail.smtphost"); //端口  
        	String host = "smtp.163.com";
        	Properties props = new Properties();  
            props.put("mail.transport.protocol","smtp");
            //props.put("mail.smtp.host", PropUtil.getProperty("mail.smtphost"));
            props.put("mail.smtp.host",host);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.port", 25);
            props.put("mail.smtp.socketFactory.port", 465);

//            props.put("mail.smtp.host", host);  
//            props.put("mail.smtp.auth", "true");  
//            props.put("mail.smtp.ssl.enable", "true");  
            Session session = Session.getInstance(props);  
//            session.setDebug(false);  
  
            MimeMessage message = new MimeMessage(session);  
            try {  
                message.setFrom(new InternetAddress(smtpFromMail));
                message.addRecipients(Message.RecipientType.CC,InternetAddress.parse("921621172@qq.com"));
                message.addRecipient(Message.RecipientType.TO,  
                        new InternetAddress(toMail));  
                //message.setSubject(MimeUtility.encodeText(subject, PropUtil.getProperty("mail.encoding", new String[0]), "B"));  
                message.setSubject(MimeUtility.encodeText(subject,"UTF-8", "B"));
                //                message.addHeader("charset", "UTF-8");  
                message.addHeader("X-Priority","3");
                message.addHeader("X-MSMail-Priority","Normal");
                message.addHeader("Normal","Microsoft Outlook Express 6.00.2900.2869");
                message.addHeader("X-MimeOLE","Produced By Microsoft MimeOLE V6.00.2900.2869");
                message.addHeader("ReturnReceipt","1");
                
                /*添加正文内容*/  
                Multipart multipart = new MimeMultipart();  
                BodyPart contentPart = new MimeBodyPart();  
                contentPart.setText(content);  
  
                contentPart.setHeader("Content-Type", "text/html; charset=UTF-8");  
                multipart.addBodyPart(contentPart);  
                  
                /*添加附件*/  
                //File usFile = new File(file);  
                //MimeBodyPart fileBody = new MimeBodyPart();  
                //DataSource source = new FileDataSource(file);  
                //fileBody.setDataHandler(new DataHandler(source));  
                //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();  
                //fileBody.setFileName("=?GBK?B?"  
                //		+ enc.encode(usFile.getName().getBytes()) + "?=");  
                //multipart.addBodyPart(fileBody);  
  
                message.setContent(multipart);  
                message.setSentDate(new Date());  
                message.saveChanges();  
                Transport transport = session.getTransport("smtp");  
  
                transport.connect(host, smtpFromMail, pwd);  
                transport.sendMessage(message, message.getAllRecipients());  
                transport.close();
                //logger.info("【success】");
                isFlag = true;  
            } catch (Exception e) {  
            	//logger.info("【error】"+e);
            	e.printStackTrace();
                isFlag = false;  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return isFlag;  
    } 
	
	public static void main(String[] args) {
		//boolean flag = MailUtil.sendMail("东南汽车","lizhijie@ffcs.cn","东南汽车", "http://27.155.67.27:59058/group1/M00/00/0A/rBTcKlvOv-qAJwTtAABrLVXsuvk686_big.pdf");
		boolean flag = MailUtil2.sendMail("东南汽车","921621172@qq.com","东南汽车", "");
		System.out.println(flag);
	}
}
