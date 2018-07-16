package com.hnzy.hot.socket.server;

import java.math.BigDecimal;
import java.net.SocketAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hnzy.hot.socket.util.CzUtil;
import com.hnzy.hot.socket.util.DatabaseUtil;
import com.hnzy.hot.socket.util.MapUtils;
import com.hnzy.hot.socket.util.MapUtilsDf;
import com.hnzy.hot.socket.util.Utils;
import com.hnzy.pds.pojo.Data;
import com.hnzy.pds.pojo.Fp;
import com.hnzy.pds.pojo.Jzq;
import com.hnzy.pds.pojo.SbSuc;
import com.hnzy.pds.pojo.YhMessage;
import com.hnzy.pds.service.DataService;
import com.hnzy.pds.service.FpService;
import com.hnzy.pds.service.JzqService;
import com.hnzy.pds.service.SbSucService;
import com.hnzy.pds.service.YhMessageService;


public class ServerHandler2  extends IoHandlerAdapter{
	PreparedStatement ps;
	ResultSet rst;
	int rs = 0;
	
	@Autowired
	private JzqService jzqService;
	@Autowired
	private DataService  dataService;
	@Autowired
	private YhMessageService yhMessageService;
	
	@Autowired
	private SbSucService sbSucService;
	boolean sessionmap;
	String param;
	@Autowired
	private FpService fpService;
//	private final static Logger logs = LoggerFactory.getLogger(ServerHandler2.class);
	// 日志文件
	private static Log logs = LogFactory.getLog(ServerHandler2.class);
	ServerSessionMap sessionMap=ServerSessionMap.getInstance();
	private Integer jzqport;
	
	/**
	 * 当一个新客户端连接后触发此方法
	 */
	public void sessionCreated(IoSession session)
	{
		logs.info("服务器创建链路成功!" + session.getRemoteAddress());
	}
	
	/**
	 * 当链接打开时调用
	 */
	@Override
	public void sessionOpened(IoSession session) throws Exception
	{
		
		 logs.info("服务器打开了的连接，Session ID为" + session.getRemoteAddress() + session.getId());
		 SocketAddress remoteAddress = (SocketAddress) session.getRemoteAddress();
		 String clientIp = remoteAddress.toString();
		 
		 sessionMap.add(clientIp, session);
		 int port = 0;
		 String Ip = null;
		 String id = null;
		 
		 DatabaseUtil dbUtil = DatabaseUtil.getInstance();//实例化
		 Connection connc = dbUtil.getConnection();		//获取连接
		 
		 String[] ipPortString = clientIp.split(":");
		 String IP = ipPortString[0];
		 
		 String[] ip = IP.split("/");
		 port = Integer.valueOf(ipPortString[1]);
		 Ip = ip[1];
		 // }
		 SimpleDateFormat Sdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 // 获取发送的时间
		 String time = Sdate.format(new Date());
		 
		 // 查找集中器ID
			 String sqlcx = "select  id from k_jzqb5 where jzqip='" + Ip + "'";//jzqnet
			 ps = connc.prepareStatement(sqlcx);//执行语句
			 rst = ps.executeQuery();			//结果
			 int col = rst.getMetaData().getColumnCount();
			 while (rst.next())					//遍历结果集
			 {
			 	id = rst.getString("id");
			 }
	
        // 如果集中器ID不为空
        if (id != null)
        {
        	String jzqip = null;
			String jzqnet = null;
			jzqService.updateIpPort(jzqip, jzqport, jzqnet);
			
        	String sql = "update k_jzqb5 set jzqport='" + port + "',UpdateTime='" + time
        			+ "' where jzqip='" + Ip + "'";
        	ps = connc.prepareStatement(sql);
        	rs = ps.executeUpdate();
        	
        	String date = "F00A0100AAAAAAAA";
        	String jString = CzUtil.jyh(date);//先转换为十进制相加  在转换为十六进制
        	String mString = date + "" + jString + "" + "FF";
        	// 解码
        	byte[] b = CzUtil.jm(mString);
        	String[] keys = new String[]
        	{ clientIp };
        	logs.info("集中器ID存在发送数据" + mString);
        	// 发送数据
        	sessionMap.sendMessage(keys, b);
        } else{
			String mString = "F00A0100AAAAAAAAA3FF";
			// 解码
			byte[] b = CzUtil.jm(mString);
			String[] keys = new String[]
			{ clientIp };
			logs.info("集中器ID不存在发送数据" + mString);
			// 发送数据
			sessionMap.sendMessage(keys, b);
			try
			{
				Thread.sleep(2000);
	
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			// 当动态IP和端口号连接是发送查找区管地址指令
			String SetQgID = "F00A0500AAAAAAAAB4FF";// 改为区管地址 F0 0A 05 00 AA AA
													// AA AA XX FF
			// 解码
			byte[] bQg = CzUtil.jm(SetQgID);
			// 给所有区管发送数据
			sessionMap.sendMessage(keys, bQg);
        }
        	DatabaseUtil.close(rst, ps, connc);   //关闭连接对象

	}
	/**
	 * 当实现IOHandlerer的类抛出异常时调用
	 */
	
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception
	{
		cause.printStackTrace();
		logs.info("{}出现异常{}" + session.getRemoteAddress() + cause);
		sessionMap.remove(session);
	}
	
	/**
	 * 当接受了一个消息时调用
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
	{
		SocketAddress remoteAddress = (SocketAddress) session.getRemoteAddress();
		String clientIp = remoteAddress.toString();
		DatabaseUtil dbUtil = DatabaseUtil.getInstance(); 
		Connection connc = dbUtil.getConnection(); 
		byte[] base = (byte[]) message;
		String stringMR = Utils.bytesToHexString(base);
		String md = null;
		System.out.println("------stringMR--接收数据----"+stringMR); 
		// 接收数据不能为空并且长度大于15
		if (stringMR != null && stringMR.length() > 15)
		{
			for (int i = 0; i < stringMR.length() - 1; i++)
			{
				md = stringMR.substring(4, 6);
			}
			// 开关阀门，批量开关
		   if (md.equals("05"))// 查询状态01
			{
				jzqCx(base, connc,clientIp);
			}/*else if(md.equals("b5")){
				jzqCx(base, connc,clientIp);
			}*/else if(md.equals("a1")){//对某一户的单个风盘
				System.out.println("-------web端返回------"+md);
				//中央空调
				sbfs(base, connc);
				}
		 	else if(md.equals("0a")){//对某一户的所有风盘返回
				System.out.println("-------web端返回------"+md);
				SF(base, connc);
				} else if(md.equals("f0")){
			 		wxkg(base); //接收微信数据并转发设备指令
			 	}else if(md.equals("a3")){
			 		wxfh(base,connc);//微信接收数据
			 	}
		}
		try
		{
			DatabaseUtil.close(ps, connc);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	private void wxfh(byte[] base,Connection connc)
	{
		logs.info("中央空调微信接收数据：" + Utils.bytesToHexString(base));
		// 接收数据
		String stringH = Utils.bytesToHexString(base);
		// 转换为大写
		String stringHandler = CzUtil.Uppercase(stringH).toString();
		// 截取效验数据
		String jy = CzUtil.getJy(stringHandler);
	 
		// 判断开始和结束
		String start = null;
		String end = null;
		start = stringHandler.charAt(0) + "" + stringHandler.charAt(1);
		end = stringHandler.charAt(stringHandler.length() - 2) + ""+ stringHandler.charAt(stringHandler.length() - 1);
		
		// 判断和校验
		String je = CzUtil.getJe(stringHandler);
		System.out.println("----------je-----"+je);
		System.out.println("---------jy------"+jy);
		
		if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
		{
			//用户编码
			String  yhbh=stringHandler.substring(8, 14);
			System.out.println("yhbh---------"+yhbh);
			int yhbhS = Integer.parseInt("" + yhbh + "", 16);
			System.out.println("yhbhS---------"+yhbhS);
			
			 
			//风盘地址
			String  fpid=stringHandler.substring(14, 16);
//			int Fpid = Integer.parseInt("" + fpid + "", 16);
//			System.out.println("fpid---------"+Fpid);
			
			//风盘模式，00制冷01制热
			String ms=stringHandler.substring(16,18);
			System.out.println("ms------"+ms);
			
			//档位
			String dw=stringHandler.substring(18,20);
			System.out.println("dw------"+dw);
			//高档运行时间高           00停止 01低档 02中档03高档
			String gdgS=stringHandler.substring(20,26);
			System.out.println("gdgS-"+gdgS);
			int gdgJS = Integer.parseInt("" + gdgS + "", 16);
			System.out.println(gdgJS);
			double gdg=jsMin(gdgJS);
			System.out.println("gdg------"+gdg);
			//中档运行时间
			String zdSS=stringHandler.substring(26,32);
			
			int zdSJS = Integer.parseInt("" + zdSS + "", 16);
			double zdS=jsMin(zdSJS);
			System.out.println("zdS------"+zdS);
			//低档运行时间
			String gddS=stringHandler.substring(32,38);
	
			int gddJS = Integer.parseInt("" + gddS + "", 16);
			double gdd=jsMin(gddJS);
			System.out.println("gdd------"+gdd);
			
			//(制热)高档运行时间高           00停止 01低档 02中档03高档
			String dgdgS=stringHandler.substring(38,44);
			int dgdgSD = Integer.parseInt("" + dgdgS + "", 16);
			double dgdg=jsMin(dgdgSD);
			
			//(制热)中档运行时间
			String dzdSS=stringHandler.substring(44,50);
			int dzdSJS = Integer.parseInt("" + dzdSS + "", 16);
			double dzdS=jsMin(dzdSJS);
			
			//(制热)低档运行时间
			String dgddS=stringHandler.substring(50,56);
			int dgddJS = Integer.parseInt("" + dgddS + "", 16);
			double dgdd=jsMin(dgddJS);
			
			//计费模式             00计费01允许计费
			String Jf=stringHandler.substring(56,58);
			System.out.println("计费模式-------"+Jf );
			
			//设定温度
			String sdwS=stringHandler.substring(58,60);
			int sdw = Integer.parseInt("" + sdwS + "", 16);
			System.out.println("设定温度----------"+sdwS);
			
			//室内温度 实时温度
			String swS=stringHandler.substring(60,62);
			int sw = Integer.parseInt("" + swS + "", 16);
			System.out.println("室内温度 --------"+swS);
			
			// 远程开关
			String kg=stringHandler.substring(62,64);
			System.out.println("远程开关-----------"+kg);//FF
			//报警
			String bjs=stringHandler.substring(64,66);
			System.out.println("报警信息 --------"+bjs);
			
			//季节
			String jj=stringHandler.substring(66,68);
			System.out.println("季节 --------"+jj);
		
			//转换为时间格式   方便地修改日期格式
			Date now = new Date(); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String time = dateFormat.format( now ); 
			Data data=new Data();
			String gdString =String.valueOf(gdg);
			String ztString=String.valueOf(zdS);
			String gddString=String.valueOf(gdd);
			String dgdgString=String.valueOf(dgdg);
			String dgzgString=String.valueOf(dzdS);
			String dgddString=String.valueOf(dgdd);
			String sdwString=String.valueOf(sdw);
			String swString=String.valueOf(sw);
			data.setGdtime(gdString);
			data.setZdtime(ztString);
			data.setDdtime(gddString);
			data.setDgdtime(dgdgString);
			data.setDzdtime(dgzgString);
			data.setDddtime(dgddString);
			data.setJf(Jf);
			data.setMs(ms);
			data.setSdwd(sdwString);
			data.setDw(dw);
			data.setSnwd(swString);
			data.setBj(bjs);
			data.setTime(time);
			data.setKg(kg);
			data.setJj(jj);
			data.setYhbh(yhbh);
			data.setFpdz(fpid);
			dataService.updateYhbhF(data);//更新实时表
			//根据用户编码查找风盘编号
			
		    Fp fp=fpService.findfpbh(yhbh);
		    data.setFpbh(fp.getFpbh());
			dataService.InsertYh(data);//插入历史表
		}
	}

	private void wxkg(byte[] base)
	{
		logs.info("中央空调微信开关接收数据：" + Utils.bytesToHexString(base));
		// 接收数据
		String stringH = Utils.bytesToHexString(base);
		// 转换为大写
		String stringHandler = CzUtil.Uppercase(stringH).toString();
		// 截取效验数据
		String jy = CzUtil.getJy(stringHandler);
	 
		// 判断开始和结束
		String start = null;
		String end = null;
		start = stringHandler.charAt(4) + "" + stringHandler.charAt(5);
		end = stringHandler.charAt(stringHandler.length() - 2) + ""+ stringHandler.charAt(stringHandler.length() - 1);
		
		// 判断和校验
		String je = CzUtil.getJeS(stringHandler);
		System.out.println("----------je-----"+je);
		System.out.println("---------jy------"+jy);
		
		if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
		{

			//用户编码
			String yhbm=stringHandler.substring(12,18);
			int  yhm = Integer.parseInt(yhbm,16);
			String yhString=String.valueOf(yhm);
			System.out.println("--yh--"+yhString);
			YhMessage yhmess=yhMessageService.findJzq(yhString);
			String ip =yhmess.getCg().getJzq().getJzqip();
			String port=yhmess.getCg().getJzq().getJzqport();
			// IP地址和端口号
			String pt = "/" + ip + ":" + port; 
			System.out.println("pt-------------"+pt);
			String[] keys = new String[] { pt };
			System.out.println("------stringHandler----"+stringHandler);
			// 解码
			byte[] b = CzUtil.jm(stringHandler);
			ServerSessionMap sessionMap = ServerSessionMap.getInstance();
			boolean sessionmap = sessionMap.sendMessage(keys, b);
		}
		
	  }
	/**
	 *  集中器查询
	 * @param base
	 * @param connc
	 * @param clientIp
	 */
	
	public void jzqCx(byte[] base, Connection connc,String clientIp)
	{
		 logs.info("集中器查询状态接收数据：" + Utils.bytesToHexString(base));
		 String jzqip = null;
		 
		 String[] ipPortString = clientIp.split(":");
		 String IP = ipPortString[0];
		 
		 String[] ip = IP.split("/");
		 Integer port = Integer.valueOf(ipPortString[1]);
		 String Ip = ip[1];
		 SimpleDateFormat Sdate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 // 获取发送的时间
		 String time = Sdate.format(new Date());
		 
		 // 接收的数据
		 String stringH = Utils.bytesToHexString(base);
		 
		 // 转换为大写
		 String stringHandler = CzUtil.Uppercase(stringH).toString();
		 
		 // 截取jzqnet
		 String jzqnet = stringHandler.substring(8, 16);
		 
		 // 截取效验数据
		 String jy = CzUtil.getJy(stringHandler);
		 // 判断开始和结束
		 String start = null;
		 String end = null;
		 String id =null;
		 start = stringHandler.charAt(0) + "" + stringHandler.charAt(1);
		 end = stringHandler.charAt(stringHandler.length() - 2) + "" + stringHandler.charAt(stringHandler.length() - 1);
		 String je = CzUtil.getJe(stringHandler);
		 System.out.println("前面数据相加je---------"+je);
		 System.out.println("校验数据jy-----"+jy);
		 if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))//开始F0       结束FF校验数据3A
		 {
		 	//根据集中器IP和端口号查找集中器ID
		 	Jzq jzq=jzqService.findJzqnet(jzqip, jzqport);
		 	// 如果集中器ID不为空
		 	jzqService.updateIpPort(jzqip, jzqport, jzqnet);
		 	MapUtils.getMapUtils().add("jzq", "success");
		 	logs.info("集中器查询状态成功接收数据：" + stringHandler);
		 }
		MapUtils.getMapUtils().add("jzq", "fail");
	 
}

	
	/**
	 * 发送数据  设备返回   对某户某一个风盘
	 * @param base
	 * @param connc
	 */
	private void sbfs(byte[] base, Connection connc)
	{
		logs.info("中央空调接收数据：" + Utils.bytesToHexString(base));
		// 接收数据
		String stringH = Utils.bytesToHexString(base);
		// 转换为大写
		String stringHandler = CzUtil.Uppercase(stringH).toString();
		// 截取效验数据
		String jy = CzUtil.getJy(stringHandler);
	 
		// 判断开始和结束
		String start = null;
		String end = null;
		start = stringHandler.charAt(0) + "" + stringHandler.charAt(1);
		end = stringHandler.charAt(stringHandler.length() - 2) + ""+ stringHandler.charAt(stringHandler.length() - 1);
		
		// 判断和校验
		String je = CzUtil.getJe(stringHandler);
		System.out.println("----------je-----"+je);
		System.out.println("---------jy------"+jy);
		
		if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
		{
			//用户编码
			String  yhbh=stringHandler.substring(8, 14);
			System.out.println("yhbh---------"+yhbh);
			String yhbhS = String.valueOf(Integer.parseInt("" + yhbh + "", 16));
			System.out.println("yhbhS---------"+yhbhS);
			
			//风盘地址
			String  fpid=stringHandler.substring(14, 16);
			
			//风盘模式，00制冷01制热
			String ms=stringHandler.substring(16,18);
			System.out.println("ms------"+ms);
			
			//档位
			String dw=stringHandler.substring(18,20);
			System.out.println("dw------"+dw);
			//高档运行时间高           00停止 01低档 02中档03高档
			String gdgS=stringHandler.substring(20,26);
			System.out.println("gdgS-"+gdgS);
			int gdgJS = Integer.parseInt("" + gdgS + "", 16);
			System.out.println(gdgJS);
			double gdg=jsMin(gdgJS);
			System.out.println("gdg------"+gdg);
			//中档运行时间
			String zdSS=stringHandler.substring(26,32);
			
			int zdSJS = Integer.parseInt("" + zdSS + "", 16);
			double zdS=jsMin(zdSJS);
			System.out.println("zdS------"+zdS);
			//低档运行时间
			String gddS=stringHandler.substring(32,38);
	
			int gddJS = Integer.parseInt("" + gddS + "", 16);
			double gdd=jsMin(gddJS);
			System.out.println("gdd------"+gdd);
			
			//(制热)高档运行时间高           00停止 01低档 02中档03高档
			String dgdgS=stringHandler.substring(38,44);
			int dgdgSD = Integer.parseInt("" + dgdgS + "", 16);
			double dgdg=jsMin(dgdgSD);
			
			//(制热)中档运行时间
			String dzdSS=stringHandler.substring(44,50);
			int dzdSJS = Integer.parseInt("" + dzdSS + "", 16);
			double dzdS=jsMin(dzdSJS);
			
			//(制热)低档运行时间
			String dgddS=stringHandler.substring(50,56);
			int dgddJS = Integer.parseInt("" + dgddS + "", 16);
			double dgdd=jsMin(dgddJS);
			
			//计费模式             00计费01允许计费
			String Jf=stringHandler.substring(56,58);
			System.out.println("计费模式-------"+Jf );
			
			//设定温度
			String sdwS=stringHandler.substring(58,60);
			int sdw = Integer.parseInt("" + sdwS + "", 16);
			System.out.println("设定温度----------"+sdwS);
			
			//室内温度 实时温度
			String swS=stringHandler.substring(60,62);
			int sw = Integer.parseInt("" + swS + "", 16);
			System.out.println("室内温度 --------"+swS);
			
			// 远程开关
			String kg=stringHandler.substring(62,64);
			System.out.println("远程开关-----------"+kg);//FF
			//报警
			String bjs=stringHandler.substring(64,66);
			System.out.println("报警信息 --------"+bjs);
			
			//季节
			String jj=stringHandler.substring(66,68);
			System.out.println("季节 --------"+jj);
		
			//转换为时间格式   方便地修改日期格式
			Date now = new Date(); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String time = dateFormat.format( now ); 
			Data data=new Data();
			String gdString =String.valueOf(gdg);
			String ztString=String.valueOf(zdS);
			String gddString=String.valueOf(gdd);
			String dgdgString=String.valueOf(dgdg);
			String dgzgString=String.valueOf(dzdS);
			String dgddString=String.valueOf(dgdd);
			String sdwString=String.valueOf(sdw);
			String swString=String.valueOf(sw);
			data.setGdtime(gdString);
			data.setZdtime(ztString);
			data.setDdtime(gddString);
			data.setDgdtime(dgdgString);
			data.setDzdtime(dgzgString);
			data.setDddtime(dgddString);
			data.setJf(Jf);
			data.setMs(ms);
			data.setSdwd(sdwString);
			data.setDw(dw);
			data.setSnwd(swString);
			data.setBj(bjs);
			data.setTime(time);
			data.setKg(kg);
			data.setJj(jj);
			data.setYhbh(yhbhS);
			data.setFpdz(fpid);
			System.out.println("time----------"+time);
//			dataService.updateYhbhF(data);
			dataService.updateYhbhF(data);//更新实时表
			 Fp fp=fpService.findfpbh(yhbhS);
			data.setFpbh(fp.getFpbh());
			dataService.InsertYh(data);//插入历史表
			SbSuc sbSuc =new SbSuc();
			sbSuc.setSbSuc(yhbh);
			
			
			sbSucService.update(sbSuc);
			//dataService.updateS(ms, dw, gdg, zdS, gdd, dgdg, dzdS, dgdd, Jf, sdw, sw, kg, bj, jj, time);
			 // 更新
//			String Upsql = "update k_data10 set gdtime='" +gdg + "',zdtime='" +zdS + "',ddtime='" +gdd + "',dgdtime='" +dgdg + "',dzdtime='" +dzdS + "',dddtime='" +dgdd + "', "
//					+ "jf='" +Jf + "',"+"sdwd='" +sdw + "',"+"ms='" + ms+ "',"+"dw='" + dw+ "',"
//					+ "snwd='" +sw + "',"+"bj='" +bjs + "',"+"time='" + time+ "',"+"kg='" + kg + "',"+"jj='"+jj 
//							+ "'where yhbh='" + yhbhS + "' and fpdz='"+fpid+"'";
//			System.out.println("Upsql===web--"+Upsql);
			MapUtilsDf.getMapUtils().add("kt", "success");
//		 	try
//			{
//				ps = connc.prepareStatement(Upsql);
//				rs = ps.executeUpdate();
//			} catch (SQLException e)
//			{
//				e.printStackTrace();
//			} 
		}else{
			MapUtilsDf.getMapUtils().add("kt", "fail");
		}
	}
	
	
	
	/**
	 * 某一户的所有风盘操作
	 * @param base
	 * @param connc
	 */
	private void SF(byte[] base, Connection connc)
	{
		logs.info("对多个风盘操作---" + Utils.bytesToHexString(base));
		// 接收数据
		String stringH = Utils.bytesToHexString(base);
		// 转换为大写
		String stringHandler = CzUtil.Uppercase(stringH).toString();
		// 截取效验数据
		String jy = CzUtil.getJy(stringHandler);
	 
		// 判断开始和结束
		String start = null;
		String end = null;
		start = stringHandler.charAt(0) + "" + stringHandler.charAt(1);
		end = stringHandler.charAt(stringHandler.length() - 2) + ""+ stringHandler.charAt(stringHandler.length() - 1);
		
		// 判断和校验
		String je = CzUtil.getJe(stringHandler);
		
		if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
		{
			MapUtilsDf.getMapUtils().add("dg", "success");
		}else{
			MapUtilsDf.getMapUtils().add("dg", "fail");
		}
		}
	//long value;
	
	
	//标识需要精确到小数点以后两位  返回的是两个参数的商
	 public double jsMin(int minute) {
	      BigDecimal b1 = new BigDecimal(Double.toString(minute));
	      BigDecimal b2 = new BigDecimal(Double.toString(60));
	      return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();//ROUND_HALF_UP: 遇到.5的情况时往上近似,例: 1.5 ->;2
	 }
	 
	
	/**
	 * 当连接进入空闲状态时调用̬
	 */
	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception
	{
		logs.info("当前连接" + session.getRemoteAddress() + "处于空闲状态:{}" + status);
	}

	/**
	 * 当消息已经发送给客户端后触发此方法
	 */
	@Override
	public void messageSent(IoSession session, Object message) throws Exception
	{
		logs.info("服务器发送给" + session.getRemoteAddress() + "的消息:" + message);
	}

	/**
	 * 当关闭时调用
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception
	{
		@SuppressWarnings("deprecation")
		CloseFuture closeFuture = session.close(true);
		closeFuture.addListener(new IoFutureListener<IoFuture>()
		{
			public void operationComplete(IoFuture future)
			{
				if (future instanceof CloseFuture)
				{
					((CloseFuture) future).setClosed();
					logs.info("sessionClosed CloseFuture setClosed-->" + future.getSession().getId());
				}
			}
		});
		sessionMap.remove(session);
		logs.info("关闭当前session：" + session.getId() + session.getRemoteAddress() + "..已移除");
	}

}
