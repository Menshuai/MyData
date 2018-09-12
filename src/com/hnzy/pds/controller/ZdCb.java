package com.hnzy.pds.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.catalina.tribes.util.Logs;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hnzy.hot.socket.server.ServerHandler2;
import com.hnzy.hot.socket.server.ServerSessionMap;
import com.hnzy.hot.socket.util.CzUtil;
import com.hnzy.pds.pojo.YhMessage;
import com.hnzy.pds.service.YhMessageService;
@Controller
public class ZdCb
{
	
	@Autowired
	private YhMessageService yhMessageService;
	public List<YhMessage> yhMessages;
	private static Log logs = LogFactory.getLog(ZdCb.class);
	public void print(){
		yhMessages=yhMessageService.find();
		for(int i=0;i<yhMessages.size();i++){
			String yhbh=yhMessages.get(i).getYhbh();
			String yhbhS = Integer.toHexString(Integer.valueOf(yhbh));//16进制     
			String cgbh=yhMessages.get(i).getCgbh();
			String cg=cgbh.substring(cgbh.length()-2);
			String zgb=cgbh.substring(0,cgbh.length()-2);
			String ld=yhMessages.get(i).getLdh();
			String dyh=yhMessages.get(i).getDyh();
			String fpdz=yhMessages.get(i).getfpdz().toString();
			Integer fp=yhMessages.get(i).getfpdz();
			if(fp>9){
				fpdz=Integer.toHexString(fp);
			}
			String zl=zgb+"F010B5"+cg+yhbhS+"0"+fpdz+ld+dyh+"FFFFFFFF";
			YhMessage yhmess=yhMessageService.findJzq(yhbh);
			String ip =yhmess.getCg().getJzq().getJzqip();
			String port=yhmess.getCg().getJzq().getJzqport();
			 try {
		 		 	Thread.sleep(3000);
		 		 } catch (InterruptedException e) {
		 		 	e.printStackTrace();
		 		 }
			// IP地址和端口号
			String pt = "/" + ip + ":" + port; 
			boolean sessionmap = cz(zl, pt);
		}
		}
	
	
	public void XgYf(){
		//从数据库用户表查询当前月份 默认第一个月为零以后每月加一
//		int yf=yhMessageService.findyf();
		//获取按流量收费的所有用户
		List<YhMessage> yfType=yhMessageService.findType(1);
		for(int i=0;i<yfType.size();i++){
		int yf=yfType.get(i).getYf();
		String yzbh=yfType.get(i).getYzbh();
		 Date date=new Date();
		int month=date.getMonth()+1;
		if(month==6&&yf!=0){
			yf=yf+1;
		}else if(month==7){
			yf=yf+1;
		}else if(month==8){
			yf=yf+1;
		}else if(month==9){
			yf=yf+1;
		}else if(month==11){
			yf=yf+1;
		}else if(month==12){
			yf=yf+1;
		}else if(month==1){
			yf=yf+1;
		}else if(month==2){
			yf=yf+1;
		}else if(month==3){
			yf=yf+1;
		}
		 //更新用户表当前月份
		yhMessageService.updateYf(yf,yzbh);
		}
	
	}
	
	//包月的用户先在时间大于结束时间发送关阀指令
	
	public void Gf() throws ParseException{
	//类型包月小于现在时间关阀门
	List<YhMessage> yfType=yhMessageService.findType(1);
	 DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	for(int i=0;i<yfType.size();i++){
		String endTime=yfType.get(i).getJf().getEndTime();
		String xzTime=df.format(new Date());
          Date dt2 = df.parse(xzTime);
          Date end = df.parse(endTime);
		if(dt2.getTime()>end.getTime()){
			String fpdz=yfType.get(i).getfpdz().toString();
			String yhbh=yfType.get(i).getYhbh();
			YhMessage  ldh=yhMessageService.finldh(yhbh,fpdz);//楼栋  
	  		String ld=ldh.getLdh();
			if(ld.length()==1){
				ld=0+ld;
			}
			System.out.println("楼栋号"+ld); 
			String cgbh=ldh.getCgbh();
			String cg=cgbh.substring(4);
			System.out.println("cg---"+cg);
			String dy=ldh.getDyh();
			if(dy.length()==1){
				dy=0+dy;
			}
			System.out.println("单元号"+dy);
			 String idsS = Integer.toHexString(Integer.valueOf(yhbh));//转换为十六进制  用户编码
			 YhMessage yhmess=yhMessageService.findJzq(yhbh);
			 String ip =yhmess.getCg().getJzq().getJzqip();
			 String port=yhmess.getCg().getJzq().getJzqport();
			 //空调状态
			 String ja =ld+dy+"F010B1"+cg+""+idsS+fpdz+"0000FF"+ld+dy+"FF";
			 System.out.println("ja----"+ja);
			 // IP地址和端口号
			 String pt = "/" + ip + ":" + port;
			 boolean sessionmap = cz(ja, pt);	
			
		}
	}	
	}
		
		// 抽取相同部分
					public boolean cz(String ja, String pt) {
						// 把十六进制数，转换为十进制相加
						int jia = CzUtil.FsZh(ja);
						// 十进制转换为十六进制
						String hex = Integer.toHexString(jia);
						// 截取相加结果后两位
						String je = null;
						for (int j = 0; j < hex.length() - 1; j++) {
							je = hex.charAt(hex.length() - 2) + "" + hex.charAt(hex.length() - 1);
						}
						String[] keys = new String[] { pt };
						String mString =ja+je+"FF";
						logs.info("自动抄表发送数据----------"+mString);
						// 解码
						byte[] b = CzUtil.jm(mString);
						ServerSessionMap sessionMap = ServerSessionMap.getInstance();
						boolean sessionmap = sessionMap.sendMessage(keys, b);
						return sessionmap;
					}
}
