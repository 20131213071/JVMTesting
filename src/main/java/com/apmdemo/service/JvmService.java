package com.apmdemo.service;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.lang.management.RuntimeMXBean;

import com.alibaba.fastjson.JSONObject;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import com.apmdemo.common.ToolsUtil;

public class JvmService{
	
	//初始化
	public static Map<Long,Long> map= new HashMap<Long,Long>();//key:value对应fgc次数,fgc时间(ms)

	public static DecimalFormat df = new DecimalFormat("0.00%");
	public static JSONObject getHeapRateJson = new JSONObject();//存储堆使用率
	public static JSONObject getNonHeapRateJson = new JSONObject();//存储NON堆使用率
	
	/**
	 * HEAP USER RATE
	 */
	public static void jvmHeapRate(int sleepTime){
		while(true){
			try{
				RuntimeMXBean rmb=(RuntimeMXBean)ManagementFactory.getRuntimeMXBean(); 
				MemoryUsage mu=ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();  
			    long getInit = mu.getInit();//初始的总内存
			    long getUsed = mu.getUsed();//已使用的内存
			    long max = mu.getMax();//最大可用内存
			    getHeapRateJson.put(ToolsUtil.getSysDate(), df.format((float)getUsed/(max)));
			    System.out.println(ToolsUtil.getSysDate() + ",最大可用Heap=" + max + ",已使用的内存=" + getUsed + ",HeapRate=" + df.format((float)getUsed/(max)));
			    Thread.sleep(sleepTime);
			}catch(Exception e){
				e.printStackTrace();}
		}
	}
	
	/**
	 * HEAP USER RATE
	 */
	public static void jvmNonHeapRate(int sleepTime){
		while(true){
			try{
				MemoryUsage mu=ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage();  
			    long getInit = mu.getInit();//初始的总内存
			    long getUsed = mu.getUsed();//已使用的内存
			    long max = mu.getMax();//最大可用内存
			    getNonHeapRateJson.put(ToolsUtil.getSysDate(), df.format((float)getUsed/(max)));
			    System.out.println(ToolsUtil.getSysDate() + ",最大可用NonHeap=" + max + ",已使用的内存=" + getUsed + ",NonHeapRate=" + df.format((float)getUsed/(max)));
			    Thread.sleep(sleepTime);
			}catch(Exception e){
				e.printStackTrace();}
		}
	}
	
	/**
	 * 获取fgc回收信息
	 */
	public static void jvmFgcInfo(int sleepTime){
		while(true){
			try{
				List<GarbageCollectorMXBean> gcmList=ManagementFactory.getGarbageCollectorMXBeans();  
			    for(GarbageCollectorMXBean gcm:gcmList){
			    	map.put(gcm.getCollectionCount(), gcm.getCollectionTime());
			    }
			    Thread.sleep(sleepTime);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	

	public static void main(String[] args) {
//		jvmHeapRate(5000);
		jvmNonHeapRate(5000);
		
	}
		
		

}
