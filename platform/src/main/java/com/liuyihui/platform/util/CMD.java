package com.liuyihui.platform.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 执行win下的cmd脚本的工具类，在win运行的java程序适用，linux服务器上运行的java程序暂没写
 * @author liuyh
 */
public class CMD {
	
	/**
	 * 
	 * @param cmdLine cmd下的命令字符行
	 * @return 
	 */
	public static String exec(String cmdLine){
		cmdLine = "cmd.exe /C "+cmdLine;
		Runtime rt = Runtime.getRuntime();
		Process p = null ;
		
		try {
			//发起cmd进程
			p=rt.exec(cmdLine);
			
			//获取输入流（包括一个错误流）
			BufferedReader bf = new BufferedReader( new InputStreamReader(p.getInputStream(),"GBK"));
			BufferedReader bfe = new BufferedReader( new InputStreamReader(p.getErrorStream(),"GBK"));
			
			//打印cmd命令执行的输出
			String line = null;
			while((line =  bf.readLine())!=null){
				System.out.println("cmd info:"+line);
			}
			while((line = bfe.readLine())!=null){
				System.out.println("cmd error:"+line);
			}
			
			//等待进程执行完
			p.waitFor();
			int exitv = p.exitValue();
			System.out.println("cmd exitValue:"+exitv);
			return "execute success.";
		} catch (IOException e) {
			e.printStackTrace();
			return "excute failed.";
		} catch (InterruptedException e) {
			e.printStackTrace();
			return "excute failed.";
		} finally{
			if(p!=null){
				p.destroy();
			}
		}
		
	}
	
}
