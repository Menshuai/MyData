package com.hnzy.pds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
	public void print(){
		yhMessages=yhMessageService.find();
		for(int i=0;i<yhMessages.size();i++){
			String yhbh=yhMessages.get(i).getYhbh();
			String yhbhS = Integer.toHexString(Integer.valueOf(yhbh));//16进制     
			String cgbh=yhMessages.get(i).getCgbh();
			String cg=cgbh.substring(cgbh.length()-2);
			String zgb=cgbh.substring(0,cgbh.length()-2);
			Integer ld=yhMessages.get(i).getLdh();
			Integer dyh=yhMessages.get(i).getDyh();
			Integer fpdz=yhMessages.get(i).getfpdz();
			String zl=zgb+"F010B5"+cg+yhbhS+"0"+fpdz+"0"+ld+"0"+dyh+"FFFFFFFF";
			System.out.println("zl------"+zl);
			YhMessage yhmess=yhMessageService.findJzq(yhbh);
			String ip =yhmess.getCg().getJzq().getJzqip();
			String port=yhmess.getCg().getJzq().getJzqport();
			// IP地址和端口号
			String pt = "/" + ip + ":" + port; 
			System.out.println("pt-------------"+pt);
			boolean sessionmap = cz(zl, pt);
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
						System.out.println("自动抄表发送数据----------"+mString);
						// 解码
						byte[] b = CzUtil.jm(mString);
						ServerSessionMap sessionMap = ServerSessionMap.getInstance();
						boolean sessionmap = sessionMap.sendMessage(keys, b);
						return sessionmap;
					}
}
