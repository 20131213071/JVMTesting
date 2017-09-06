package com.apmdemo.view;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.apmdemo.service.JvmService;

@Controller
public class JvmMonitor {
	
	@RequestMapping("/getopt.html")
	@ResponseBody
	public String getOpt(@RequestParam String t){
		int sleepTime = 5000;//每3秒采集次数据
		JSONObject optReturnMsg = new JSONObject();//初始化返回值
		optReturnMsg.put("opt", "Success");
		int t1 = Integer.valueOf(t);
		try{
			switch(t1){
			case 1:
				JvmService.jvmFgcInfo(sleepTime);
				break;
			case 2:
				JvmService.jvmHeapRate(sleepTime);
				break;
			case 3:
				JvmService.jvmNonHeapRate(sleepTime);
				break;
			default:
				optReturnMsg.put("opt", "param 1 to 3");
			}
		}catch(Exception e){
			e.printStackTrace();}
		return optReturnMsg.toString();
	}

	@RequestMapping("/fgcinfo.html")
	@ResponseBody
	public String getFgcInfo(){
		String result = "";
		JSONObject json = new JSONObject();
		
		try{
			for (Map.Entry<Long, Long> entry : JvmService.map.entrySet()) {
				json.put("fgcNum",entry.getKey());
				json.put("fgcTime",entry.getValue());
				
				System.out.println((float)entry.getKey());
				System.out.println((float)entry.getValue());
				System.out.println((float)entry.getKey() / (float)entry.getValue());
				float fgcAvgTime = (float)entry.getKey() / (float)entry.getValue();
				json.put("fgcAvgTime", fgcAvgTime);
				result = json.toString();
			}
		}catch(Exception e){
			e.printStackTrace();}
		return result;
	}
	
	private int Long(Long key) {
		// TODO Auto-generated method stub
		return 0;
	}

	//对外提供heapRate.html接口
	@RequestMapping("/heapRate.html")
	@ResponseBody
	public String getHeapRate(){
		return JvmService.getHeapRateJson.toString();
	}
	
	//对外提供nheapRate.html接口
	@RequestMapping("/nheapRate.html")
	@ResponseBody
	public String getNonHeapRate(){
		return JvmService.getNonHeapRateJson.toString();
	}
	
	//对外提供oom.html接口
	@RequestMapping("/oom.html")
	@ResponseBody
	public void getoom(){
		List<String> list = new ArrayList<String>();
		while(1==1){
			list.add("www.zhubajie.com Apm test");
		}
	}
	
	//对外提供fgc.html接口
	@RequestMapping("/fgc.html")
	@ResponseBody
	public void getfgc(){
		try{
			List<Object> l = new ArrayList<Object>();
			int count =0;
	        for (int i =0; i< 10000;i++)
	        {
	            l.add(new byte[1024]);

	            if (i % 10 ==0)
	            {
	            	count++;
	                System.gc();
	                Thread.sleep(5000);
	                System.out.println(count);
	            }
	        }

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@RequestMapping("/aa.html")
	@ResponseBody
	public int test(){
		return 1;
	}
}
