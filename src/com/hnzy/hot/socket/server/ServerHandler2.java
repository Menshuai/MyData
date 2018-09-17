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
import org.springframework.beans.factory.annotation.Autowired;

import com.hnzy.hot.socket.util.CzUtil;
import com.hnzy.hot.socket.util.DatabaseUtil;
import com.hnzy.hot.socket.util.MapUtilsDf;
import com.hnzy.hot.socket.util.Utils;
import com.hnzy.pds.pojo.Data;
import com.hnzy.pds.pojo.Fp;
import com.hnzy.pds.pojo.Jf;
import com.hnzy.pds.pojo.Jzq;
import com.hnzy.pds.pojo.Price;
import com.hnzy.pds.pojo.YhMessage;
import com.hnzy.pds.service.DataService;
import com.hnzy.pds.service.FpService;
import com.hnzy.pds.service.JfService;
import com.hnzy.pds.service.JzqService;
import com.hnzy.pds.service.PriceService;
import com.hnzy.pds.service.YhMessageService;

public class ServerHandler2 extends IoHandlerAdapter
{
	PreparedStatement ps;
	ResultSet rst;
	int rs = 0;

	@Autowired
	private JzqService jzqService;
	@Autowired
	private DataService dataService;
	@Autowired
	private YhMessageService yhMessageService;
	@Autowired
	public JfService jfServce;
	boolean sessionmap;
	String param;
	@Autowired
	private FpService fpService;
	@Autowired
	private PriceService priceService;
	// 日志文件
	private static Log logs = LogFactory.getLog(ServerHandler2.class);
	ServerSessionMap sessionMap = ServerSessionMap.getInstance();
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
		if (remoteAddress != null)
		{
			String clientIp = remoteAddress.toString();
			// 判断连接的ip是否为空
			sessionMap.add(clientIp, session);
			int port = 0;
			String Ip = null;
			String id = null;

			DatabaseUtil dbUtil = DatabaseUtil.getInstance();// 实例化
			Connection connc = dbUtil.getConnection(); // 获取连接

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
			String sqlcx = "select  id from k_jzqb5 where jzqip='" + Ip + "'";// jzqnet
			ps = connc.prepareStatement(sqlcx);// 执行语句
			rst = ps.executeQuery(); // 结果
			int col = rst.getMetaData().getColumnCount();
			while (rst.next()) // 遍历结果集
			{
				id = rst.getString("id");
			}

			// 如果集中器ID不为空
			if (id != null)
			{
				String jzqip = null;
				String jzqnet = null;
				jzqService.updateIpPort(jzqip, jzqport, jzqnet);

				String sql = "update k_jzqb5 set jzqport='" + port + "',UpdateTime='" + time + "' where jzqip='" + Ip
						+ "'";
				ps = connc.prepareStatement(sql);
				rs = ps.executeUpdate();
			}
			DatabaseUtil.close(rst, ps, connc); // 关闭连接对象
		}
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

		DatabaseUtil dbUtil = DatabaseUtil.getInstance();
		Connection connc = dbUtil.getConnection();
		byte[] base = (byte[]) message;
		String stringMR = Utils.bytesToHexString(base);
		String md = null;
		System.out.println("------stringMR--接收数据----" + stringMR);
		// 接收数据不能为空并且长度大于15
		if (stringMR != null && stringMR.length() > 15)
		{
			for (int i = 0; i < stringMR.length() - 1; i++)
			{
				md = stringMR.substring(4, 6);
			}
			System.out.println("md-----"+md);
			
			// 开关阀门，批量开关
			if (md.equals("0c"))// 查询状态01
			{
				SocketAddress remoteAddress = (SocketAddress) session.getRemoteAddress();
				String clientIp = remoteAddress.toString();
				jzqCx(base, connc, clientIp);
			} else if (md.equals("a1"))
			{// 对某一户的单个风盘
				SocketAddress remoteAddress = (SocketAddress) session.getRemoteAddress();
				String clientIp = remoteAddress.toString();
				// 中央空调
				sbfs(base, connc,clientIp,session);
			} else if (md.equals("0a"))
			{// 对某一户的所有风盘返回
				SF(base, connc);
			} else if (md.equals("f0"))
			{
				wxkg(base); // 接收微信数据并转发设备指令
			} else if (md.equals("a3"))
			{
				SocketAddress remoteAddress = (SocketAddress) session.getRemoteAddress();
				String clientIp = remoteAddress.toString();
				wxfh(base, connc,clientIp, session);// 微信接收数据
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

	private void wxfh(byte[] base, Connection connc,String clientIp,IoSession session)
	{
		logs.info("中央空调微信接收数据：" + Utils.bytesToHexString(base));
		String[] ipPortString = clientIp.split(":");
		String IP = ipPortString[0];

		String[] ip = IP.split("/");
		Integer port = Integer.valueOf(ipPortString[1]);
		String Ip = ip[1];
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
		end = stringHandler.charAt(stringHandler.length() - 2) + "" + stringHandler.charAt(stringHandler.length() - 1);

		// 判断和校验
		String je = CzUtil.getJe(stringHandler);
		System.out.println("----------je-----" + je);
		System.out.println("---------jy------" + jy);

		if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
		{
			// 用户编码
			String yhbh = stringHandler.substring(8, 14);
			System.out.println("yhbh---------" + yhbh);
			String yhbhS = String.valueOf(Integer.parseInt("" + yhbh + "", 16));
			System.out.println("yhbhS---------" + yhbhS);
			// 风盘地址
			String fpidS = stringHandler.substring(14, 16);
			System.out.println("fpidS-" + fpidS);
			String fpid = String.valueOf(Integer.parseInt(fpidS, 16));

			// 风盘地址
			// String fpid = stringHandler.substring(14, 16);
			Integer fpdz = Integer.valueOf(fpid);
			// 根据用户编码和风盘地址查找用户
			Data findData = dataService.findData(yhbhS, fpdz);
			System.out.println("--fpid-" + fpid);
			// 风盘模式，00制冷01制热
			String ms = stringHandler.substring(16, 18);

			if (ms.equals("FF"))
			{
				ms = findData.getMs();
			}
			System.out.println("ms------" + ms);
			// 档位
			String dw = stringHandler.substring(18, 20);
			if (dw.equals("FF"))
			{
				dw = findData.getDw();
			}
			System.out.println("dw------" + dw);
			// 高档运行时间高 00停止 01低档 02中档03高档
			String gdgS = stringHandler.substring(20, 26);
			System.out.println("gdgS-" + gdgS);
			int gdgJS = Integer.parseInt("" + gdgS + "", 16);
			System.out.println(gdgJS);
			double gdg = jsMin(gdgJS);
			System.out.println("gdg------" + gdg);
			// 中档运行时间
			String zdSS = stringHandler.substring(26, 32);

			int zdSJS = Integer.parseInt("" + zdSS + "", 16);
			double zdS = jsMin(zdSJS);
			System.out.println("zdS------" + zdS);
			// 低档运行时间
			String gddS = stringHandler.substring(32, 38);

			int gddJS = Integer.parseInt("" + gddS + "", 16);
			double gdd = jsMin(gddJS);
			System.out.println("gdd------" + gdd);

			// (制热)高档运行时间高 00停止 01低档 02中档03高档
			String dgdgS = stringHandler.substring(38, 44);
			int dgdgSD = Integer.parseInt("" + dgdgS + "", 16);
			double dgdg = jsMin(dgdgSD);

			// (制热)中档运行时间
			String dzdSS = stringHandler.substring(44, 50);
			int dzdSJS = Integer.parseInt("" + dzdSS + "", 16);
			double dzdS = jsMin(dzdSJS);

			// (制热)低档运行时间
			String dgddS = stringHandler.substring(50, 56);
			int dgddJS = Integer.parseInt("" + dgddS + "", 16);
			double dgdd = jsMin(dgddJS);

			// 计费模式 00计费01允许计费
			String Jf = stringHandler.substring(56, 58);
			System.out.println("Jf-------------" + Jf);
			if (Jf.equals("FF"))
			{
				Jf = findData.getJf();
			}
			System.out.println("计费模式-------" + Jf);

			// 设定温度
			String sdwS = stringHandler.substring(58, 60);
			System.out.println("sdwS-------------" + sdwS);
			if (sdwS.equals("FF"))
			{
				sdwS = findData.getSdwd();
			}
			int sdw = Integer.parseInt("" + sdwS + "", 16);
			System.out.println("设定温度----------" + sdwS);

			// 室内温度 实时温度
			String swS = stringHandler.substring(60, 62);
			System.out.println("swS-------------" + swS);
			if (swS.equals("FF"))
			{
				swS = findData.getSnwd();
			}
			int sw = Integer.parseInt("" + swS + "", 16);

			System.out.println("室内温度 --------" + swS);

			// 远程开关
			String kg = stringHandler.substring(62, 64);
			System.out.println("远程开关-----------" + kg);// FF
			if (kg.equals("FF"))
			{
				kg = findData.getKg();
			}
			System.out.println("远程开关-----------" + kg);// FF
			System.out.println("kg-------------" + kg);
			// 报警
			String bjs = stringHandler.substring(64, 66);
			System.out.println("远程开关-----------" + bjs);// FF
			if (bjs.equals("FF"))
			{
				bjs = findData.getBj();
			}
			System.out.println("报警信息 --------" + bjs);

			// 季节
			String jj = stringHandler.substring(66, 68);
			if (jj.equals("FF"))
			{
				jj = findData.getJj();
			}
			System.out.println("季节 --------" + jj);
			String  jzqId=stringHandler.substring(74,76);
			if(jzqId.equals("FF")){
				jzqId="01";
			}
			jzqService.updateIpPort(Ip, port, jzqId);
			// 转换为时间格式 方便地修改日期格式
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = dateFormat.format(now);
			Data data = new Data();
			String gdString = String.valueOf(gdg);
			String ztString = String.valueOf(zdS);
			String gddString = String.valueOf(gdd);
			String dgdgString = String.valueOf(dgdg);
			String dgzgString = String.valueOf(dzdS);
			String dgddString = String.valueOf(dgdd);
			String sdwString = String.valueOf(sdw);
			String swString = String.valueOf(sw);
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
			data.setFpdz(fpdz);
			dataService.updateYhbhF(data);// 更新实时表
			Data find =null ;
			Jf jf = jfServce.findYf(yhbhS);
			Integer type=jf.getType(); //业主类型
			
			//按流量计算
			if(type.equals("1")){
				
			String yzbh=jf.getYzbh();//业主编号
			// 根据用户编号和风盘地址更新，实时表计算，已用当量
			find = dataService.findYh(yhbhS, fpdz);
			// 已用当量
			double yydl = find.getYydlS();

			// 更新实时表已用当量
			Data datajf = new Data();
			datajf.setYydl(yydl);
			datajf.setYhbh(yhbhS);
			datajf.setFpdz(fpdz);
			dataService.updateJf(datajf);
			// 根据实时表查找月份
		    int yf=find.getYf();
			data.setYydl(yydl);
			data.setYf(yf);
			dataService.InsertYh(data);// 插入历史表
			//根据用户编号查找用户的总已用当量
		   Double zyydl=dataService.findZyydl(yzbh);
		   //用户面积
		   //double mj = find.getYhMessage().getMj();st
		   //计算价格
			 Price price=  priceService.byPrice(5);
			double dlPrice=price.getPrice();
			 //用户已用金额
			 double yyje= zyydl*dlPrice;
			 
			 //总金额
			 double jfzje=jfServce.findzje(yzbh).getHjje();
			 //剩余金额
			 double syje=jfzje-yyje;
			 //根据业主编号更新缴费信息
			 Jf jfs=new Jf();
			 jfs.setYyje(yyje);
			 jfs.setSyje(syje);
			 jfs.setGetime(time);
			 jfServce.updateJf(jfs); 
			 //如果剩余金额为负数则进行关
			 if(syje<0){
				 String ld =find.getYhMessage().getLdh();
				 String dy=find.getYhMessage().getDyh();
				 String cgbh =find.getYhMessage().getCgbh();
				
				 if(ld.length()==1){
						ld=0+ld;
					}
					if(dy.length()==1){
						dy=0+dy;
					}
				String cg=cgbh.substring(4);
			    String ja =ld+dy+"F010B1"+cg+""+yhbh+fpidS+"00FFFF"+ld+dy+"FF";
				 // 解码
				boolean sessionmap = cz(ja, clientIp);
				
				try
				{
					Thread.sleep(2000);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 // 解码
				boolean sessionmap1 = cz(ja, clientIp);
			 }
			}
			dataService.InsertYh(data);// 插入历史表
		} else if (stringHandler.length() > 82)
		{
			String[] str = stringHandler.split("F029A1");
			for (int i = 0; i < str.length; i++)
			{
				String iString = "F029A1" + str[i];
				if (iString.length() == 82)
				{
					Cb(iString, Ip, port, clientIp, session);
				}

			}

		}
	}

	private void wxkg(byte[] base)
	{
		logs.info("中央空调微信开关接收数据---------------------------：" + Utils.bytesToHexString(base));
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
		end = stringHandler.charAt(stringHandler.length() - 2) + "" + stringHandler.charAt(stringHandler.length() - 1);

		// 判断和校验
		String je = CzUtil.getJeS(stringHandler);
		System.out.println("----------je-----" + je);
		System.out.println("---------jy------" + jy);

		if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
		{
			String fpdzS;
			// 用户编码
			String yhbm = stringHandler.substring(12, 18);
			
			int yhm = Integer.parseInt(yhbm, 16);
			String yhString = String.valueOf(yhm);
			System.out.println("--yh--" + yhString);
			YhMessage yhmess = yhMessageService.findJzq(yhString);
			String ip = yhmess.getCg().getJzq().getJzqip();
			String port = yhmess.getCg().getJzq().getJzqport();
			// IP地址和端口号
			String pt = "/" + ip + ":" + port;
			//风盘地址
			 String fpdz=stringHandler.substring(18,20);
			 String fpid = String.valueOf(Integer.parseInt(fpdz, 16));
			Integer fp=Integer.valueOf(fpid);
			if(fp>9){
				fpdzS="0"+Integer.toHexString(fp);//16进制   
			}else{
				fpdzS=fpdz;
			}
			
			//层管
//			String cg=yhmess.getCgbh();
//			String cgbh=cg.substring(4);
//			String ld=stringHandler.substring(26, 28);
//			String dy=stringHandler.substring(28, 30);

			logs.info("微信接收数据+pt：" + pt);
			logs.info("微信接收数据：" + stringHandler);
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}

			System.out.println("pt-------------" + pt);
			String[] keys = new String[]
			{ pt };
			System.out.println("------stringHandler----" + stringHandler);
			// 解码
			byte[] b = CzUtil.jm(stringHandler);
			ServerSessionMap sessionMap = ServerSessionMap.getInstance();
			sessionMap.sendMessage(keys, b);
			try
			{
				Thread.sleep(4000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}

			sessionMap.sendMessage(keys, b);
			
			try
			{
				Thread.sleep(4000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			sessionMap.sendMessage(keys, b);
			
//			String yhbh=Integer.toHexString(Integer.valueOf(yhString));
//			//三秒后读取数据
//			String ja =ld+dy+"F010B5"+cgbh+""+yhbh+fpdz+ld+dy+"FFFFFFFF";//起始到结束  01终端	
//			
//				cz(ja, pt);
			
			
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
			logs.info("用户欠费发送关阀指令---------------"+mString);
			// 解码
			byte[] b = CzUtil.jm(mString);
			ServerSessionMap sessionMap = ServerSessionMap.getInstance();
			boolean sessionmap = sessionMap.sendMessage(keys, b);
			return sessionmap;
		}
		
	/**
	 * 集中器查询
	 * 
	 * @param base
	 * @param connc
	 * @param clientIp
	 */

	public void jzqCx(byte[] base, Connection connc, String clientIp)
	{
		logs.info("集中器查询状态接收数据：" + Utils.bytesToHexString(base));

		String[] ipPortString = clientIp.split(":");
		String IP = ipPortString[0];

		String[] ip = IP.split("/");
		Integer port = Integer.valueOf(ipPortString[1]);
		String Ip = ip[1];
		SimpleDateFormat Sdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 获取发送的时间
		String time = Sdate.format(new Date());

		// 接收的数据
		String stringH = Utils.bytesToHexString(base);

		// 转换为大写
		String stringHandler = CzUtil.Uppercase(stringH).toString();

		// 截取jzqnet
		String jzqnet = stringHandler.substring(6, 8);

		// 截取效验数据
		String jy = CzUtil.getJy(stringHandler);
		// 判断开始和结束
		String start = null;
		String end = null;
		String id = null;
		start = stringHandler.charAt(0) + "" + stringHandler.charAt(1);
		end = stringHandler.charAt(stringHandler.length() - 2) + "" + stringHandler.charAt(stringHandler.length() - 1);
		String je = CzUtil.getJe(stringHandler);
		System.out.println("前面数据相加je---------" + je);
		System.out.println("校验数据jy-----" + jy);
		if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))// 开始F0
																				// 结束FF校验数据3A
		{

			// 根据集中器IP和端口号查找集中器ID
			Jzq jzq = jzqService.findJzqnet(Ip, port);
			// 如果集中器ID不为空
			if (jzq == null)
			{
				jzqService.updateIpPort(Ip, port, jzqnet);
				logs.info("集中器查询状态成功接收数据：" + stringHandler);
			}
		}
	}

	/**
	 * 发送数据 设备返回 对某户某一个风盘
	 * 
	 * @param base
	 * @param connc
	 */
	private void sbfs(byte[] base, Connection connc, String clientIp,IoSession session)
	{
		
		logs.info("中央空调接收数据：" + Utils.bytesToHexString(base));
		
		String[] ipPortString = clientIp.split(":");
		String IP = ipPortString[0];

		String[] ip = IP.split("/");
		Integer port = Integer.valueOf(ipPortString[1]);
		String Ip = ip[1];
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
		end = stringHandler.charAt(stringHandler.length() - 2) + "" + stringHandler.charAt(stringHandler.length() - 1);

		// 判断和校验
		String je = CzUtil.getJe(stringHandler);
		System.out.println("----------je-----" + je);
		System.out.println("---------jy------" + jy);

		if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
		{
			// 用户编码
			String yhbh = stringHandler.substring(8, 14);
			System.out.println("yhbh---------" + yhbh);
			String yhbhS = String.valueOf(Integer.parseInt("" + yhbh + "", 16));
			System.out.println("yhbhS---------" + yhbhS);

			// 风盘地址
			String fpidS = stringHandler.substring(14, 16);
			System.out.println("fpidS-" + fpidS);
			String fpid = String.valueOf(Integer.parseInt(fpidS, 16));

			Integer fpdz = Integer.valueOf(fpid);
			System.out.println("fpdz--" + fpdz);
			// 根据用户编码和风盘地址查找用户
			Data findData = dataService.findData(yhbhS, fpdz);
			System.out.println("--fpid-" + fpid);
			// 风盘模式，00制冷01制热
			String ms = stringHandler.substring(16, 18);

			if (ms.equals("FF"))
			{
				ms = findData.getMs();
			}
			System.out.println("ms------" + ms);
			// 档位
			String dw = stringHandler.substring(18, 20);
			if (dw.equals("FF"))
			{
				dw = findData.getDw();
			}
			System.out.println("dw------" + dw);
			// 高档运行时间高 00停止 01低档 02中档03高档
			String gdgS = stringHandler.substring(20, 26);
			System.out.println("gdgS-" + gdgS);
			int gdgJS = Integer.parseInt("" + gdgS + "", 16);
			System.out.println(gdgJS);
			double gdg = jsMin(gdgJS);
			System.out.println("gdg------" + gdg);

			// 中档运行时间
			String zdSS = stringHandler.substring(26, 32);
			int zdSJS = Integer.parseInt("" + zdSS + "", 16);
			double zdS = jsMin(zdSJS);
			System.out.println("zdS------" + zdS);
			// 低档运行时间
			String gddS = stringHandler.substring(32, 38);

			int gddJS = Integer.parseInt("" + gddS + "", 16);
			double gdd = jsMin(gddJS);
			System.out.println("gdd------" + gdd);

			// (制热)高档运行时间高 00停止 01低档 02中档03高档
			String dgdgS = stringHandler.substring(38, 44);
			int dgdgSD = Integer.parseInt("" + dgdgS + "", 16);
			double dgdg = jsMin(dgdgSD);

			// (制热)中档运行时间
			String dzdSS = stringHandler.substring(44, 50);
			int dzdSJS = Integer.parseInt("" + dzdSS + "", 16);
			double dzdS = jsMin(dzdSJS);

			// (制热)低档运行时间
			String dgddS = stringHandler.substring(50, 56);
			int dgddJS = Integer.parseInt("" + dgddS + "", 16);
			double dgdd = jsMin(dgddJS);

			// 计费模式 00计费01允许计费
			String Jf = stringHandler.substring(56, 58);
			if (Jf.equals("FF"))
			{
				Jf = findData.getJf();
			}
			System.out.println("计费模式-------" + Jf);

			// 设定温度
			String sdwS = stringHandler.substring(58, 60);
			System.out.println("sdwS---" + sdwS);
			if (sdwS.equals("FF"))
			{
				sdwS = findData.getSdwd();
			}
			int sdw = Integer.parseInt("" + sdwS + "", 16);
			System.out.println("设定温度----------" + sdwS);

			// 室内温度 实时温度
			String swS = stringHandler.substring(60, 62);
			System.out.println("swS---" + swS);
			if (swS.equals("FF"))
			{
				swS = findData.getSnwd();
			}
			int sw = Integer.parseInt("" + swS + "", 16);

			System.out.println("室内温度 --------" + swS);
			System.out.println("室内温度十六进制" + sw);

			// 远程开关
			String kg = stringHandler.substring(62, 64);
			System.out.println("kg---" + kg);
			if (kg.equals("FF"))
			{
				kg = findData.getKg();
			}
			System.out.println("远程开关-----------" + kg);// FF

			// 报警
			String bjs = stringHandler.substring(64, 66);
			System.out.println("bjs---" + bjs);
			if (bjs.equals("FF"))
			{
				bjs = findData.getBj();
			}
			System.out.println("报警信息 --------" + bjs);

			// 季节
			String jj = stringHandler.substring(66, 68);
			System.out.println("jj---" + jj);
			if (jj.equals("FF"))
			{
				jj = findData.getJj();
			}
			System.out.println("季节 --------" + jj);
			String  jzqId=stringHandler.substring(74,76);
			if(jzqId.equals("FF")){
				jzqId="01";
			}
			jzqService.updateIpPort(Ip, port, jzqId);
			// 转换为时间格式 方便地修改日期格式
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = dateFormat.format(now);
			Data data = new Data();
			String gdString = String.valueOf(gdg);
			String ztString = String.valueOf(zdS);
			String gddString = String.valueOf(gdd);
			String dgdgString = String.valueOf(dgdg);
			String dgzgString = String.valueOf(dzdS);
			String dgddString = String.valueOf(dgdd);
			String sdwString = String.valueOf(sdw);
			String swString = String.valueOf(sw);
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
			data.setFpdz(fpdz);
			dataService.updateYhbhF(data);// 更新实时表

			Data find =null ;
			Jf jf = jfServce.findYf(yhbhS);
			Integer type=jf.getType(); //业主类型
			
			//按流量计算
			if(type==1){
				
			String yzbh=jf.getYzbh();//业主编号
			// 根据用户编号和风盘地址更新，实时表计算，已用当量
			find = dataService.findYh(yhbhS, fpdz);
			// 已用当量
			double yydl = find.getYydlS();

			// 更新实时表已用当量
			Data datajf = new Data();
			datajf.setYydl(yydl);
			datajf.setYhbh(yhbhS);
			datajf.setFpdz(fpdz);
			dataService.updateJf(datajf);
			// 根据实时表查找月份
		    int yf=find.getYf();
			data.setYydl(yydl);
			data.setYf(yf);
			dataService.InsertYh(data);// 插入历史表
			//根据用户编号查找用户的总已用当量
		   Double zyydl=dataService.findZyydl(yzbh);
		   //用户面积
		   //double mj = find.getYhMessage().getMj();st
		   //计算价格
			 Price price=  priceService.byPrice(5);
			double dlPrice=price.getPrice();
			 //用户已用金额
			 double yyje= zyydl*dlPrice;
			 //总金额
			 double jfzje=jfServce.findzje(yzbh).getHjje();
			 //剩余金额
			 double syje=jfzje-yyje;
			 //根据业主编号更新缴费信息
			 Jf jfs=new Jf();
			 jfs.setYyje(yyje);
			 jfs.setSyje(syje);
			 jfs.setGetime(time);
			 jfs.setYydl(zyydl);
			 jfs.setYzbh(yzbh);
			 jfServce.updateJf(jfs); 
			 
			 if(syje<0){
				 String ld =find.getYhMessage().getLdh();
				 String dy=find.getYhMessage().getDyh();
				 String cgbh =find.getYhMessage().getCgbh();
				
				 if(ld.length()==1){
						ld=0+ld;
					}
					if(dy.length()==1){
						dy=0+dy;
					}
				String cg=cgbh.substring(4);
			    String ja =ld+dy+"F010B1"+cg+""+yhbh+fpidS+"00FFFF"+ld+dy+"FF";
				 // 解码
				boolean sessionmap = cz(ja, clientIp);
				try
				{
					Thread.sleep(2000);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 // 解码
				boolean sessionmap1 = cz(ja, clientIp);
			 }
			 
			}
			dataService.InsertYh(data);// 插入历史表
			if(bjs.equals("00")){
				MapUtilsDf.getMapUtils().add("kt", yhbhS);
			}
			
			
		} else if (stringHandler.length() > 82)
		{
			String[] str = stringHandler.split("F029A1");
			for (int i = 0; i < str.length; i++)
			{
				String iString = "F029A1" + str[i];
				if (iString.length() == 82)
				{
					Cb(iString,Ip,port, clientIp, session);
				}

			}

		} else
		{
			MapUtilsDf.getMapUtils().add("kt", "fail");
		}
	}

	public void Cb(String stringHandler,String Ip,Integer port, String clientIp,IoSession session)
	{

		// 截取效验数据
		String jy = CzUtil.getJy(stringHandler);

		// 判断开始和结束
		String start = null;
		String end = null;
		start = stringHandler.charAt(0) + "" + stringHandler.charAt(1);
		end = stringHandler.charAt(stringHandler.length() - 2) + "" + stringHandler.charAt(stringHandler.length() - 1);

		// 判断和校验
		String je = CzUtil.getJe(stringHandler);
		System.out.println("----------je-----" + je);
		System.out.println("---------jy------" + jy);

		if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
		{
			// 用户编码
			String yhbh = stringHandler.substring(8, 14);
			System.out.println("yhbh---------" + yhbh);
			String yhbhS = String.valueOf(Integer.parseInt("" + yhbh + "", 16));
			System.out.println("yhbhS---------" + yhbhS);
			// 风盘地址
			String fpidS = stringHandler.substring(14, 16);
			System.out.println("fpidS-" + fpidS);
			String fpid = String.valueOf(Integer.parseInt(fpidS, 16));
			// 风盘地址
			// String fpid = stringHandler.substring(14, 16);
			Integer fpdz = Integer.valueOf(fpid);
			// 根据用户编码和风盘地址查找用户
			Data findData = dataService.findData(yhbhS, fpdz);
			System.out.println("--fpid-" + fpid);
			// 风盘模式，00制冷01制热
			String ms = stringHandler.substring(16, 18);

			if (ms.equals("FF"))
			{
				ms = findData.getMs();
			}
			System.out.println("ms------" + ms);
			// 档位
			String dw = stringHandler.substring(18, 20);
			if (dw.equals("FF"))
			{
				dw = findData.getDw();
			}
			System.out.println("dw------" + dw);
			// 高档运行时间高 00停止 01低档 02中档03高档
			String gdgS = stringHandler.substring(20, 26);
			System.out.println("gdgS-" + gdgS);
			int gdgJS = Integer.parseInt("" + gdgS + "", 16);
			System.out.println(gdgJS);
			double gdg = jsMin(gdgJS);
			System.out.println("gdg------" + gdg);

			// 中档运行时间
			String zdSS = stringHandler.substring(26, 32);
			int zdSJS = Integer.parseInt("" + zdSS + "", 16);
			double zdS = jsMin(zdSJS);
			System.out.println("zdS------" + zdS);
			// 低档运行时间
			String gddS = stringHandler.substring(32, 38);

			int gddJS = Integer.parseInt("" + gddS + "", 16);
			double gdd = jsMin(gddJS);
			System.out.println("gdd------" + gdd);

			// (制热)高档运行时间高 00停止 01低档 02中档03高档
			String dgdgS = stringHandler.substring(38, 44);
			int dgdgSD = Integer.parseInt("" + dgdgS + "", 16);
			double dgdg = jsMin(dgdgSD);

			// (制热)中档运行时间
			String dzdSS = stringHandler.substring(44, 50);
			int dzdSJS = Integer.parseInt("" + dzdSS + "", 16);
			double dzdS = jsMin(dzdSJS);

			// (制热)低档运行时间
			String dgddS = stringHandler.substring(50, 56);
			int dgddJS = Integer.parseInt("" + dgddS + "", 16);
			double dgdd = jsMin(dgddJS);

			// 计费模式 00计费01允许计费
			String Jf = stringHandler.substring(56, 58);
			if (Jf.equals("FF"))
			{
				Jf = findData.getJf();
			}
			System.out.println("计费模式-------" + Jf);

			// 设定温度
			String sdwS = stringHandler.substring(58, 60);
			System.out.println("sdwS---" + sdwS);
			if (sdwS.equals("FF"))
			{
				sdwS = findData.getSdwd();
			}
			int sdw = Integer.parseInt("" + sdwS + "", 16);
			System.out.println("设定温度----------" + sdwS);

			// 室内温度 实时温度
			String swS = stringHandler.substring(60, 62);
			System.out.println("swS---" + swS);
			if (swS.equals("FF"))
			{
				swS = findData.getSnwd();
			}
			int sw = Integer.parseInt("" + swS + "", 16);

			System.out.println("室内温度 --------" + swS);
			System.out.println("室内温度十六进制" + sw);

			// 远程开关
			String kg = stringHandler.substring(62, 64);
			System.out.println("kg---" + kg);
			if (kg.equals("FF"))
			{
				kg = findData.getKg();
			}
			System.out.println("远程开关-----------" + kg);// FF

			// 报警
			String bjs = stringHandler.substring(64, 66);
			System.out.println("bjs---" + bjs);
			if (bjs.equals("FF"))
			{
				bjs = findData.getBj();
			}
			System.out.println("报警信息 --------" + bjs);

			// 季节
			String jj = stringHandler.substring(66, 68);
			System.out.println("jj---" + jj);
			if (jj.equals("FF"))
			{
				jj = findData.getJj();
			}
			System.out.println("季节 --------" + jj);
			String  jzqId=stringHandler.substring(74,76);
			if(jzqId.equals("FF")){
				jzqId="01";
			}
			jzqService.updateIpPort(Ip, port, jzqId);
			
			// 转换为时间格式 方便地修改日期格式
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = dateFormat.format(now);
			Data data = new Data();
			String gdString = String.valueOf(gdg);
			String ztString = String.valueOf(zdS);
			String gddString = String.valueOf(gdd);
			String dgdgString = String.valueOf(dgdg);
			String dgzgString = String.valueOf(dzdS);
			String dgddString = String.valueOf(dgdd);
			String sdwString = String.valueOf(sdw);
			String swString = String.valueOf(sw);
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
			data.setFpdz(fpdz);
			dataService.updateYhbhF(data);// 更新实时表

			Data find =null ;
			Jf jf = jfServce.findYf(yhbhS);
			Integer type=jf.getType(); //业主类型
			
			//按流量计算
			if(type==1){
				
			String yzbh=jf.getYzbh();//业主编号
			// 根据用户编号和风盘地址更新，实时表计算，已用当量
			find = dataService.findYh(yhbhS, fpdz);
			// 已用当量
			double yydl = find.getYydlS();

			// 更新实时表已用当量
			Data datajf = new Data();
			datajf.setYydl(yydl);
			datajf.setYhbh(yhbhS);
			datajf.setFpdz(fpdz);
			dataService.updateJf(datajf);
			// 根据实时表查找月份
		    int yf=find.getYf();
			data.setYydl(yydl);
			data.setYf(yf);
			dataService.InsertYh(data);// 插入历史表
			//根据用户编号查找用户的总已用当量
		   Double zyydl=dataService.findZyydl(yzbh);
		   //用户面积
		   //double mj = find.getYhMessage().getMj();st
		   //计算价格
			 Price price=  priceService.byPrice(5);
			double dlPrice=price.getPrice();
			 //用户已用金额
			 double yyje= zyydl*dlPrice;
			 //总金额
			 double jfzje=jfServce.findzje(yzbh).getHjje();
			 //剩余金额
			 double syje=jfzje-yyje;
			 //根据业主编号更新缴费信息
			 Jf jfs=new Jf();
			 jfs.setYyje(yyje);
			 jfs.setSyje(syje);
			 jfs.setGetime(time);
			 jfs.setYydl(zyydl);
			 jfs.setYzbh(yzbh);
			 jfServce.updateJf(jfs); 
			 
			 if(syje<0){
				 String ld =find.getYhMessage().getLdh();
				 String dy=find.getYhMessage().getDyh();
				 String cgbh =find.getYhMessage().getCgbh();
				
				 if(ld.length()==1){
						ld=0+ld;
					}
					if(dy.length()==1){
						dy=0+dy;
					}
				String cg=cgbh.substring(4);
			    String ja =ld+dy+"F010B1"+cg+""+yhbh+fpidS+"00FFFF"+ld+dy+"FF";
				 // 解码
				boolean sessionmap = cz(ja, clientIp);
				try
				{
					Thread.sleep(2000);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 // 解码
				boolean sessionmap1 = cz(ja, clientIp);
			 }
			 
			}
			dataService.InsertYh(data);// 插入历史表
//			SbSuc sbSuc = new SbSuc();
//			sbSuc.setSbSuc(yhbhS);
//			sbSucService.update(sbSuc);
			if(bjs.equals("00")){
				MapUtilsDf.getMapUtils().add("kt", yhbhS);
			}

		}
	}

	/**
	 * 某一户的所有风盘操作
	 * 
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
		end = stringHandler.charAt(stringHandler.length() - 2) + "" + stringHandler.charAt(stringHandler.length() - 1);

		// 判断和校验
		String je = CzUtil.getJe(stringHandler);
		System.out.println(je);
		System.out.println(jy);
		if (start.equals("F0") && end.equals("FF") && je.equals("" + jy + ""))
		{
			logs.info("-----------------------对多个风盘操作接收数据成功---------------");
			MapUtilsDf.getMapUtils().add("dg", "success");
		} else
		{
			MapUtilsDf.getMapUtils().add("dg", "fail");
		}
	}
	// long value;

	// 标识需要精确到小数点以后两位 返回的是两个参数的商
	public double jsMin(int minute)
	{
		BigDecimal b1 = new BigDecimal(Double.toString(minute));
		BigDecimal b2 = new BigDecimal(Double.toString(60));
		return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();// ROUND_HALF_UP:
																		// 遇到.5的情况时往上近似,例:
																		// 1.5
																		// ->;2
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

	public static double sub(double d1, double d2)
	{
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.subtract(bd2).doubleValue();
	}
}
