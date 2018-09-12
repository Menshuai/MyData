	$(function(){
	var jjrmodelidlist;  //鐢ㄤ簬瀛樻斁浠庢暟鎹簱鍙栧嚭鐨勬墍鏈夎妭鍋囨棩鐨刬d
	var jjrmodeltimelist; //鐢ㄤ簬瀛樻斁浠庢暟鎹簱鍙栧嚭鐨勬墍鏈夎妭鍋囨棩鐨則ime
	var jjrmodelztlist; //鐢ㄤ簬瀛樻斁浠庢暟鎹簱鍙栧嚭鐨勬墍鏈夎妭鍋囨棩鐨勭姸鎬�
		
	createSelectYear();  //鍒涘缓骞翠唤涓嬫媺,骞剁粰瀵瑰簲浜嬩欢
	createMonthSelect();  //鍒涘缓鏈堜唤涓嬫媺锛屽苟缁欏搴斾簨浠�
	getjjrszModelByYear(withID("aboluo-yearSelect").value); //浠庢暟鎹簱鍙栧嚭宸茬粡璁剧疆浜嗙殑鑺傚亣鏃ョ殑鏁版嵁锛屼緥锛氫紤鎭紝涓婄彮绛�
	//鏍规嵁骞达紝鏈堬紝鐢╰able缁樺埗鏃ュ巻銆� 骞存湀鍙樺姩鍒� 閲嶆柊缁樺埗
	createTabledate(parseInt(withID("aboluo-yearSelect").value),parseInt(withID("aboluo-selectmonth").value));
	//涓婃湀涓嬫湀鐨刟鏍囩缁欎簨浠�
	leftrightclick();
	//璁剧疆鍙宠竟鏄剧ず鏍忔樉绀哄唴瀹癸紝鏄剧ず鏍忚繕鍙互璁剧疆鑺傚亣鏃ョ殑鐘舵€佺瓑
	setRigth(new Date().getFullYear(),new Date().getMonth()+1,new Date().getDate());
	});

//闃绘鍐掓场
function stopBubble(e){
	if(e && e.stopPropagation){// 鍒殑娴忚鍣�
		e.stopPropagation();
	}else{ //IE
		window.event.cancelBubble=true;
	}
}	
	//瀹氫箟浜唝earselect骞惰祴鍊�,涓旀坊鍔犱簨浠讹紝閫夋嫨鍒欏搴旂殑table鏃ユ湡涔熷皢鏀瑰彉,涓斿凡閫変腑鏃ユ湡浼氳烦鍒板綋鍓嶉€夋嫨鏈堢殑鏃ユ湡锛岀劧鍚庣粰鍙宠竟鏄庣粏鏍忚祴鍊�
function createSelectYear(){
	withClass("aboluo-calendar-select-year").innerHTML='<select name="aboluo-yearSelect" id="aboluo-yearSelect"></select>';
	var yearSelect= withID("aboluo-yearSelect");
	var Nowtime=new Date();
	var currYear=Nowtime.getFullYear();
	for(var i=0;i<=79;i++){
		yearSelect.options.add(new Option((i+1970)+"年",i+1970));
		if(currYear==i+1970){
		yearSelect.options[i].selected=true;
		}
	}
	yearSelect.onchange=function(e){
		var aclick=withClass("aboluo-aclick");
		//閲嶆柊璧嬪€肩粰鍙樺叏灞€鍙橀噺,鎵€鏈夌殑甯︾姸鎬佺殑鏃ユ湡;鐒跺悗涓嬩竴姝ュ皢鍒涘缓table,瀹屾垚鍔ㄦ€佹牱寮�,
		//杩欓噷瑕侀噸璇绘暟鎹氨5涓綅缃�,閫夋嫨骞存椂,涓婁竴涓湀,涓嬩竴涓湀,璁剧疆鑺傚亣鏃utton,杩斿洖浠婂ぉbutton
		getjjrszModelByYear(withID("aboluo-yearSelect").value);
		createTabledate(withID("aboluo-yearSelect").value,withID("aboluo-selectmonth").value);
		if(aclick==""){
			//璇存槑娌￠€�,鎴栭€夌殑褰撳ぉ,绠楀嚭閫夌殑杩欎釜鏈堟湁澶氬皯澶�,涓庡師鏉ョ殑閭ｄ釜鏈堢殑澶╂暟涓€瀵规瘮,濡傛灉鍘熸潵鐨勫ぉ鏁板ぇ浜庣幇鍦ㄧ殑澶╂暟,閭ｄ箞瀵规崲
			//杩欓噷鍏堢畻褰撳墠鏈堝綋鍓嶅ぉ,鐒跺悗绠楀嚭閫夋嫨鐨勯偅涓湀鎬诲ぉ鏁�,鐒跺悗瀵规瘮,濡傛灉褰撳墠澶╁ぇ浜庨€夋嫨鐨勯偅涓湀閭ｅぉ,瀵规崲
		 var pervdays1=getCurrMonthLashDay(withID("aboluo-yearSelect").value,withID("aboluo-selectmonth").value);
		    	if(new Date().getDate()>pervdays1){
					setRigth(withID("aboluo-yearSelect").value,withID("aboluo-selectmonth").value,pervdays1);	
				}else{
					setRigth(withID("aboluo-yearSelect").value,withID("aboluo-selectmonth").value,new Date().getDate());
				}
		}else{
			var adate=aclick.getAttribute("date");
			var aarr=adate.split("-");
			aarr[0]=parseInt(aarr[0]);
			aarr[1]=parseInt(aarr[1]);
			aarr[2]=parseInt(aarr[2]);
			var pervdays=getCurrMonthLashDay(withID("aboluo-yearSelect").value,withID("aboluo-selectmonth").value);
			if(aarr[2]>pervdays){
				aarr[2]=pervdays;
			}
				setRigth(withID("aboluo-yearSelect").value,withID("aboluo-selectmonth").value,aarr[2]);	
		}
		
	};
}

function getjjrszModelByYear(year){
	jjrmodelidlist=['1','2'];
	jjrmodeltimelist=['2015-08-30 00:00:00','2015-08-31 00:00:00']; //杩欓噷鏃堕棿鐨勬牸寮忎负yyyy-MM-dd HH:mm:ss
	jjrmodelztlist=['1','2'];  //1涓轰笂鐝紝2涓轰紤鎭�
//	$.ajax({
//		type:"POST",
//		url:,
//		async:false,
//		data:{"year":year},
//		success:function(json){
//			if(json.code>0){
//				var data=json.data;
//				for(var i=0;i<data.length;i++){
//					jjrmodelidlist.push(data[i].jjr_id);
//					jjrmodeltimelist.push(data[i].jjr_time);
//					jjrmodelztlist.push(data[i].jjr_zt);
//				}
//			}
//		}
//	});
}


//鍒涘缓鏈堢殑涓嬫媺妗嗭紝骞惰祴鍊�,涓旀坊鍔犱簨浠讹紝閫夋嫨鍒欏搴旂殑table鏃ユ湡涔熷皢鏀瑰彉,涓斿凡閫変腑鏃ユ湡浼氳烦鍒板綋鍓嶉€夋嫨鏈堢殑鏃ユ湡锛岀劧鍚庣粰鍙宠竟鏄庣粏鏍忚祴鍊�
function createMonthSelect(){
	var selectmonth=newElement('select');
	selectmonth.name="aboluo-selectmonth";
	selectmonth.id="aboluo-selectmonth";
	selectmonth.onchange=function(e){
		var aclick=withClass("aboluo-aclick");
		createTabledate(withID("aboluo-yearSelect").value,selectmonth.options[selectmonth.selectedIndex].value);
		if(aclick==""){
			//璇存槑娌￠€�,鎴栭€夌殑褰撳ぉ,绠楀嚭閫夌殑杩欎釜鏈堟湁澶氬皯澶�,涓庡師鏉ョ殑閭ｄ釜鏈堢殑澶╂暟涓€瀵规瘮,濡傛灉鍘熸潵鐨勫ぉ鏁板ぇ浜庣幇鍦ㄧ殑澶╂暟,閭ｄ箞瀵规崲
			//杩欓噷鍏堢畻褰撳墠鏈堝綋鍓嶅ぉ,鐒跺悗绠楀嚭閫夋嫨鐨勯偅涓湀鎬诲ぉ鏁�,鐒跺悗瀵规瘮,濡傛灉褰撳墠澶╁ぇ浜庨€夋嫨鐨勯偅涓湀閭ｅぉ,瀵规崲
		 var pervdays1=getCurrMonthLashDay(withID("aboluo-yearSelect").value,selectmonth.options[selectmonth.selectedIndex].value);
		    	if(new Date().getDate()>pervdays1){
					setRigth(withID("aboluo-yearSelect").value,selectmonth.options[selectmonth.selectedIndex].value,pervdays1);	
				}else{
					setRigth(withID("aboluo-yearSelect").value,selectmonth.options[selectmonth.selectedIndex].value,new Date().getDate());
				}
		}else{
			var adate=aclick.getAttribute("date");
			var aarr=adate.split("-");
			aarr[0]=parseInt(aarr[0]);
			aarr[1]=parseInt(aarr[1]);
			aarr[2]=parseInt(aarr[2]);
			var pervdays=getCurrMonthLashDay(withID("aboluo-yearSelect").value,selectmonth.options[selectmonth.selectedIndex].value);
			if(aarr[2]>pervdays){
				aarr[2]=pervdays;
			}
				setRigth(withID("aboluo-yearSelect").value,selectmonth.options[selectmonth.selectedIndex].value,aarr[2]);	
		}
	
	
	
	};
	var Nowtime=new Date();
	var currMonth=Nowtime.getMonth();
    for(var i=0;i<12;i++){
		selectmonth.options.add(new Option((i+1)+"月",i+1));
		if(currMonth==i){
			selectmonth.options[i].selected=true;
		}
	}
    var next=withClass("aboluo-month-a-next");
    var parent=next.parentNode;
    parent.insertBefore(selectmonth,next);
}


//鏍规嵁浼犲叆鐨勫勾鏈堬紝鍒涘缓瀵瑰簲鐨則able鏃ユ湡,骞朵笖鍦ㄦ瘡涓猼d涓垱寤篴鏍囩鐢ㄤ簬浜嬩欢锛屼笌鏍峰紡鍐呰竟妗嗙殑璁剧疆
function createTabledate(year,yue){
	var rilitabledele=withClass("aboluo-rilitbody");
	if(rilitabledele!="" && rilitabledele!=null && rilitabledele!='undefined'){
	rilitabledele.parentNode.removeChild(rilitabledele);
	}
	var rilitable=newElement('tbody');
	rilitable.setAttribute("class","aboluo-rilitbody");
	var rili=withClass("aboluo-rilitable");
	rili.appendChild(rilitable);
	//鍏堝緱鍒板綋鍓嶆湀绗竴澶╂槸鏄熸湡鍑�,鐒跺悗鏍规嵁杩欎釜鏄熸湡绠楀墠闈㈠嚑澶╃殑涓婁釜鏈堟渶鍚庡嚑澶�.
	var date=setdateinfo(year,yue,1);
	var weekday=date.getDay();
	var pervLastDay;
	if(weekday!=0){
		pervLastDay=weekday-1;
	}else{
		pervLastDay=weekday+6;
	}
	//寰楀埌涓婁釜鏈堟渶鍚庝竴澶�;
	var pervMonthlastDay=getPervMonthLastDay(year,yue);
	//涓婃湀鏈€鍚庡嚑澶╁惊鐜�
	var lastdays=pervMonthlastDay-pervLastDay+1;
	var tr=newElement('tr');
	tr.style.borderBottom="1px solid #e3e4e6";
	for(var i=lastdays;i<=pervMonthlastDay;i++){
		var td=newElement("td");
		var a=getA(parseInt(yue)-1==0?parseInt(year)-1:year,parseInt(yue)-1==0?12:parseInt(yue)-1,i);
		a.style.color="#BFBFC5";
//		a.href ='javascript:pervA('+parseInt(yue)-1==0?parseInt(year)-1:year+','+parseInt(yue)-1==0?12:parseInt(yue)-1+','+i+');';
		td.appendChild(a);
		td.setAttribute("class","aboluo-pervMonthDays");
		tr.appendChild(td);
	}
	//杩欎釜鏈堝紑濮嬬殑寰幆
	var startDays=8-weekday==8?1:8-weekday;
	for(var i=1;i<=startDays;i++){
		var td=newElement("td");
		var b=getA(year,yue,i);
		td.appendChild(b);
		tr.appendChild(td);
	}
	rilitable.appendChild(tr);
	//鎸囧畾骞存湀鏈€鍚庝竴澶�
	var currMonthLashDay=getCurrMonthLashDay(year,yue);
	//褰撴湀闄ゅ紑绗竴琛岀殑璧风偣
	var currmonthStartDay=currMonthLashDay-(currMonthLashDay-startDays)+1;
	//褰撴湀杩樺墿浣欑殑澶╂暟
	var syts=currMonthLashDay-startDays;
	//寰幆娆℃暟
	var xhcs=0;
	if(check(syts/7)){
	//鏄皬鏁�
	xhcs=Math.ceil(syts/7);//鍚戜笂鍙栨暣
	}else{
	xhcs=syts/7;	
	}
	
	//杩欐槸涓嬩釜鏈堝紑濮嬬殑鍙橀噺;
	var jilvn=1;
	for(var i=0;i<xhcs;i++){
		var tr1=newElement('tr');
		if(i!=xhcs-1){
			tr1.style.borderBottom="1px solid #e3e4e6";
		}
		for(var n=1;n<=7;n++){
			var td=newElement('td');
			if(startDays==0){
				var c=getA(parseInt(yue)+1==parseInt(13)?parseInt(year)+1:year,parseInt(yue)+1==parseInt(13)?1:parseInt(yue)+1,jilvn);
				c.style.color="#BFBFC5";
				td.appendChild(c);
				td.setAttribute("class","aboluo-nextMonthDays");
				jilvn++;
				tr1.appendChild(td);
				continue;
			}else{
			startDays++;
			var d=getA(year,yue,startDays);
			td.appendChild(d);
				if(startDays==currMonthLashDay){
					startDays=0;
				}
			tr1.appendChild(td);	
			}
		
		}
		rilitable.appendChild(tr1);
	}
	setHolidayred();//璁剧疆鏄熸湡鍏槦鏈熷ぉ鐨勬牱寮�
	setTrHeight();//璁剧疆table鏃ユ湡鐨勮楂�
	setA(); //璁剧疆td涓璦鐨勪簨浠�
}



//缁欎笂涓€涓湀鏈€鍚庡嚑澶╃偣鍑昏烦杞湀浠�
function pervA(year,yue,day){
	createTabledate(year,yue);  //鍒涘缓瀵瑰簲鐨則able(鏃ユ湡)
	setRigth(year,yue,day);    //璁剧疆鍙宠竟鏄庣粏鏍忓唴瀹�
	updateSelect(year,yue);    //鏀瑰彉骞存湀select鍊�
}

//缁欎笂涓€涓湀鏈€鍚庡嚑澶╃偣鍑昏烦杞湀浠�
function nextA(year,yue,day){
	createTabledate(year,yue);
	setRigth(year,yue,day);
	updateSelect(year,yue);
}

function updateSelect(year,yue){
	var selectmonth=withID("aboluo-selectmonth");
	var selectyear=withID("aboluo-yearSelect");
	selectmonth.value=yue;
	selectyear.value=year;
}



//閬嶅巻table灏哾ate浜嬩欢绛�
function setHolidayred(){
	var rows=withClass("aboluo-rilitbody").rows;
	for(var i=0;i<rows.length;i++){
		for(var j=0;j<rows[i].cells.length;j++){
			var cell=rows[i].cells[j];
			var a=rows[i].cells[j].childNodes[0];
			var adate=a.getAttribute("date");
			var arr=adate.split("-");
			var date=new Date();
			var year=date.getFullYear();
			var month=date.getMonth();
			var day=date.getDate();
			if(arr[0]==year && arr[1]==month+1 && arr[2]==day){
				cell.setAttribute("class","aboluo-tdcurrToday");
				a.setAttribute("class","aboluo-currToday");
			}
			if(j>=rows[i].cells.length-2 ){
				if(cell.getAttribute("class")!="aboluo-nextMonthDays" && cell.getAttribute("class")!="aboluo-pervMonthDays"){
					a.style.color="red";
				}
			}
		}
	}
}

//缁檙ightdiv鍒涘缓鍏冪礌骞惰祴鍊硷紝鏍规嵁浼犲叆鐨勫勾鏈堟棩缁欏唴閮ㄧ殑鍏冪礌璧嬪€� ,鏈堜唤鏄� 1-12
function setRigth(year,yue,day){
	//鍏堟竻绌�
	withClass("aboluo-xssj").innerHTML="";
	withClass("aboluo-ssjjr").innerHTML="";
	year=year.toString();
	yue=yue.toString();
	day=day.toString();
	//璁剧疆rigthdiv鐨刴arginleft;
	var rigthdiv=withClass("aboluo-rightdiv");
	var w=withClass("aboluo-w-700");
	rigthdiv.style.marginLeft=(w.offsetWidth*0.7+4)+"px";  //璁剧疆margin-left
	//缁檖涓坊鍔爏pan鏄剧ず鍊�
	var span=newElement('span');
	var date=setdateinfo(year,yue,day);
	span.innerHTML=formatByYearyueday(year,yue,day);
	var span1=newElement('span');
	var week=getWeek(date.getDay());
	span1.innerHTML=week;
	var aboluoxssj=withClass("aboluo-xssj");
	aboluoxssj.appendChild(span);
	aboluoxssj.appendChild(span1);
	var currday=withClass("aboluo-currday");
	currday.innerHTML=day;
	currday.style.lineHeight=currday.offsetHeight+"px";    //瀹為檯鍦ㄥ緱鍒伴暱瀹芥椂涓嶈兘鐢╯tyle.height锛屽緱鐢�.offsetHeight,浣嗘槸璁剧疆鐨勬椂鍊欒鐢╯tyle.height=...
	var szrq=withClass("aboluo-ssjjr");
	szrq.style.marginTop="20px";
	var span2=newElement('span');
	span2.innerHTML="设置日志状态:";
	szrq.appendChild(span2);
	var szrqselect=newElement("select");
	szrqselect.style.width=(withClass("aboluo-rightdiv").offsetWidth*0.9)+"px";
	szrqselect.options.add(new Option("无","0")); //0浠ｈ〃杩樺師
	//杩欓噷瑕佸垽鏂竴涓嬪鏋滄槸鏄熸湡67灏卞彧鑳借缃笂鐝�,濡傛灉鏄槦鏈�1-5灏卞彧鑳借缃紤鎭�
	var bool=isweekend(year,yue,day);
	if(bool){
	szrqselect.options.add(new Option("无","1"));
	}else{
	szrqselect.options.add(new Option("休息","2"));
	}
	szrq.appendChild(szrqselect);
	var szrqbutton=newElement('input');
	szrqbutton.type="button";
	szrqbutton.className="btn";  //璁剧疆class
	szrqbutton.value="确认";
	szrqbutton.setAttribute("onclick","javascript:aboluoSetrq();");
	szrq.appendChild(szrqbutton);
	setaclass(year,yue,day);
}

function formatByYearyueday(year,yue,day){
	year=year.toString();
	yue=yue.toString();
	day=day.toString();
	return year+"-"+(yue.length<2?'0'+yue:yue)+"-"+(day.length<2?'0'+day:day);
}

function formatByDate(date){
	date=date.substring(0,10);
	var daxx=date.toString().split("-");
	return daxx[0]+"-"+(daxx[1].length<2?'0'+daxx[1]:daxx[1])+"-"+(daxx[2].length<2?'0'+daxx[2]:daxx[2]);
}

//缁檛body涓殑td涓殑A璁剧疆浜嬩欢锛屼笂涓湀鐨勫ぉ鏁�,杩欎釜鏈堢殑澶╂暟,涓嬩釜鏈堢殑澶╂暟涓夌瀵瑰簲鐨勪簨浠�
//杩欓噷杩樻湁涓姛鑳藉氨鏄垽鏂綋鍓嶇殑A涓棩鏈熸槸涓嶆槸鏁版嵁搴撲腑鏈夊甫鐘舵€佺殑鏃ユ湡,濡傛灉鏄氨缁欑浉褰撶殑鏍峰紡
function setA(){
	var tbody=withClass("aboluo-rilitbody");
	var arr=tbody.getElementsByTagName("a");
	for(var i=0;i<arr.length;i++){
		var date=arr[i].getAttribute("date");
		var datearr=date.split("-");
			if(arr[i].parentNode.className=="aboluo-pervMonthDays"){
			arr[i].setAttribute("onclick","javascript:pervA("+datearr[0]+","+datearr[1]+","+datearr[2]+",this);javascript:stopBubble(this);")
			}else if(arr[i].parentNode.className=="aboluo-nextMonthDays"){
				arr[i].setAttribute("onclick","javascript:nextA("+datearr[0]+","+datearr[1]+","+datearr[2]+",this);javascript:stopBubble(this);")	
			}else{
			arr[i].setAttribute("onclick","javascript:setRigth("+datearr[0]+","+datearr[1]+","+datearr[2]+");javascript:stopBubble(this);");
			}
		for(var n=0;n<jjrmodelidlist.length;n++){
			if(formatByDate(jjrmodeltimelist[n])==formatByDate(date)){
				if(jjrmodelztlist[n]==1){ //1涓婄彮
					var span=newElement('span');
					span.setAttribute("class","aboluo-td-a-ban");
					arr[i].style.background="#f5f5f5";
					arr[i].setAttribute("ztid",jjrmodelidlist[n]);
					arr[i].setAttribute("jjrzt",jjrmodelztlist[n]);
					span.innerHTML="鐝�";
					arr[i].appendChild(span);
				}else if(jjrmodelztlist[n]==2){ //2浼戞伅
					var span=newElement('span');
					span.setAttribute("class","aboluo-td-a-xiu");
					arr[i].setAttribute("ztid",jjrmodelidlist[n]);
					arr[i].setAttribute("jjrzt",jjrmodelztlist[n]);
					arr[i].style.background="#fff0f0";
					span.innerHTML="浼�";
					arr[i].appendChild(span);
				}else if(jjrmodelztlist[n]==0){ // 杩欓噷涓轰簡淇濊瘉鎿嶄綔杩囩殑鑺傚亣鏃ョ殑鍞竴鎬�,涓嶇粰鏍峰紡鍙缃產鐨剒tid
					arr[i].setAttribute("ztid",jjrmodelidlist[n]);
					arr[i].setAttribute("jjrzt",jjrmodelztlist[n]);
				}
			}
		}	
	}
}

//a鐐瑰嚮閫変腑鏍峰紡,鍏堟竻闄ゅ啀璁剧疆閫変腑鏍峰紡
function setaclass(year,yue,day){
	var a=withClass("aboluo-aclick");
		a.className="";
		var date=new Date();
		var year1=date.getFullYear();
		var month1=date.getMonth();
		var day1=date.getDate();
		if(year1==year && yue==month1+1 && day1==day){
		}else{
			var tbody=withClass("aboluo-rilitbody");
			var arr=tbody.getElementsByTagName("a");
			for(var i=0;i<arr.length;i++){
				var date=arr[i].getAttribute("date");
				var datearr=date.split("-");
				if(datearr[0]==year && datearr[1]==yue && datearr[2]==day){
					arr[i].setAttribute("class","aboluo-aclick");
				}
			}
		}

}


//鑾峰彇褰撳墠閫夊彇鐨勬棩鏈�
function getAclickDomDate(){
	var aclick=withClass("aboluo-aclick");
	if(aclick==""){
		//璇存槑娌￠€�,閭ｄ箞灏辩粰褰撳ぉ,鎸�12鏈堢畻
		var date=new Date();
		return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	}else{
		return aclick.getAttribute("date");
	}
}

//鑾峰彇褰撳墠閫変腑鐨刟鍏冪礌
function getAclickDom(){
	var aclick=withClass("aboluo-aclick");
	if(aclick==""){
		//璇存槑娌￠€�,閭ｄ箞灏辩粰褰撳ぉ,鎸�12鏈堢畻
		return withClass("aboluo-currToday");
	}else{
		return aclick;
	}
}


//鍒涘缓鍏冪礌
function newElement(val){
	return document.createElement(val);
}

//鍒涘缓date瀵硅薄骞惰祴鍊�
function setdateinfo(year,yue,day){
	var date=new Date();
	date.setFullYear(parseInt(year));
	date.setMonth(parseInt(yue)-1);
	date.setDate(parseInt(day));
	return date;
}

//鏍规嵁骞存湀鏃ュ垽鏂槸涓嶆槸鏄熸湡鍏槦鏈熷ぉ //yue 鎸�12绠�
function isweekend(year,yue,day){
	var date=new Date();
	date.setFullYear(year);
	date.setMonth(yue-1);
	date.setDate(day);
	var week=date.getDay();
	if(week==0 || week==6){
		return true;
	}
	return false;
}

//鏍规嵁getDay()杩斿洖瀵瑰簲鐨勬槦鏈熷瓧绗︿覆
function getWeek(val){
	var weekxq=new Array();
	weekxq[0]="星期日";
	weekxq[1]="星期一";
	weekxq[2]="星期二";
	weekxq[3]="星期三";
	weekxq[4]="星期四";
	weekxq[5]="星期五";
	weekxq[6]="星期六";
	return weekxq[val];
}

//鍒ゆ柇c鏄惁鏄皬鏁�
function check(c){
	var r=/^[+-]?[1-9]?[0-9]*\.[0-9]*$/;
	return r.test(c);
}

//寰楀埌鎸囧畾鏈堢殑涓婁釜鏈堟渶鍚庝竴澶╀紶杩涙潵鎸� 12鏈堢畻
function getPervMonthLastDay(year,yue){
	//褰撴湀灏辨槸  yue-1 涔熷氨鏄绠楁満閲岄潰鐨�0-11鏈堜唤,閭ｄ箞绠椾笂涓湀鐨勬渶鍚庝竴澶╁氨鏄綋鏈堢殑0澶�
	return parseInt(new Date(year,yue-1,0).getDate());
}

//寰楀埌鎸囧畾鏈堟渶鍚庝竴澶� 浼犺繘鏉ユ寜 12鏈堢畻
function getCurrMonthLashDay(year,yue){
	if(yue>=12){
		year=year+1;
		yue=yue-12;
	}
	return parseInt(new Date(year,yue,0).getDate());
}


//鍒涘缓a鏍囩骞惰缃叕鐢ㄥ睘鎬�
function getA(year,yue,day){
	var a=newElement("a");
	a.href="javascript:;";
	a.innerHTML=day;
	a.style.textDecoration="none";
	a.setAttribute("date",year+"-"+yue+"-"+day);
	return a;
}



//缁欏乏鍙崇殑a娣诲姞浜嬩欢
function leftrightclick(){
	var lefta=withClass("aboluo-month-a-perv");
	var righta=withClass("aboluo-month-a-next");
	righta.onclick=function(){
		var monthselect=withID("aboluo-selectmonth");
		var monthvalue=parseInt(monthselect.value);
		var yearselect=withID("aboluo-yearSelect");
		var yearvalue=parseInt(yearselect.value)
		if(monthvalue==12){
			yearvalue+=1;
			//杩欓噷宸茬粡鍙樹簡涓€骞翠簡,鎵€浠ュ氨瑕佹牴鎹勾閲嶈鏁版嵁
			getjjrszModelByYear(yearvalue);
			monthvalue=1;
		}else{
			monthvalue+=1;
		}
		monthselect.value=monthvalue;
		yearselect.value=yearvalue;
		var aclick=withClass("aboluo-aclick");
		createTabledate(yearselect.value,monthselect.value);
		
		//濡傛灉娌℃湁鎵惧埌杩欎釜class璇存槑娌℃湁鐐瑰嚮鎴栨槸鐐瑰嚮鐨勫綋澶�
		if(aclick==""){
		var pervdays1=getCurrMonthLashDay(yearselect.value,monthselect.value+1);
			if(new Date().getDate()>pervdays1){
				setRigth(yearselect.value,monthselect.value,pervdays1);	
			}else{
				setRigth(yearselect.value,monthselect.value,new Date().getDate());
			}
		}else{
		var adate=aclick.getAttribute("date");
		var aarr=adate.split("-");
		aarr[0]=parseInt(aarr[0]);
		aarr[1]=parseInt(aarr[1]);
		aarr[2]=parseInt(aarr[2]);
		var pervdays=getCurrMonthLashDay(aarr[0],aarr[1]+1);
		if(aarr[2]>pervdays){
			aarr[2]=pervdays;
		}
		setRigth(aarr[1]+1==13?aarr[0]+1:aarr[0],aarr[1]+1==13?1:aarr[1]+1,aarr[2]);	
		}
	}
	lefta.onclick=function(){
		var monthselect=withID("aboluo-selectmonth");
		var monthvalue=parseInt(monthselect.value);
		var yearselect=withID("aboluo-yearSelect");
		var yearvalue=parseInt(yearselect.value)
		if(monthvalue==1){
			yearvalue-=1;
			//杩欓噷宸茬粡鍙樹簡涓€骞翠簡,鎵€浠ュ氨瑕佹牴鎹勾閲嶈鏁版嵁
			getjjrszModelByYear(yearvalue);
			monthvalue=12;
		}else{
			monthvalue-=1;
		}
		monthselect.value=monthvalue;
		yearselect.value=yearvalue;
		var aclick=withClass("aboluo-aclick");
		createTabledate(yearselect.value,monthselect.value);
		//濡傛灉娌℃湁鎵惧埌杩欎釜class璇存槑娌℃湁鐐瑰嚮鎴栨槸鐐瑰嚮鐨勫綋澶�
		if(aclick==""){
		//杩欎釜鏃跺€欏悜涓婁竴涓湀,閭ｄ箞	
		var pervdays1=getPervMonthLastDay(yearselect.value,monthselect.value);
			if(new Date().getDate()>pervdays1){
				setRigth(yearselect.value,monthselect.value,pervdays1);	
			}else{
				setRigth(yearselect.value,monthselect.value,new Date().getDate());
			}
		}else{
		var adate=aclick.getAttribute("date");
		var aarr=adate.split("-");
		aarr[0]=parseInt(aarr[0]);
		aarr[1]=parseInt(aarr[1]);
		aarr[2]=parseInt(aarr[2]);
		var pervdays=getPervMonthLastDay(aarr[0],aarr[1]);
			if(aarr[2]>pervdays){
				aarr[2]=pervdays;
			}
		setRigth(aarr[1]-1==0?aarr[0]-1:aarr[0],aarr[1]-1==0?12:aarr[1]-1,aarr[2]);	
		}
	}
	
	var today=withClass("aboluo-toToday");
	today.onclick=function(){
		var monthselect=withID("aboluo-selectmonth");
		var yearselect=withID("aboluo-yearSelect");
		var date=new Date();
		monthselect.value=date.getMonth()+1;
		yearselect.value=date.getFullYear();
		getjjrszModelByYear(date.getFullYear());
		createTabledate(yearselect.value,monthselect.value);
		setRigth(date.getFullYear(),date.getMonth()+1,date.getDate());
		
	}
}

//鍔ㄦ€佽缃畉r楂樺害,鍔ㄦ€佺粰td涓殑A璁剧疆楂樺害涓庡搴�
function setTrHeight(){
	var table=withClass("aboluo-rilidiv");
	var thead=withClass("aboluo-rilithead");
	var tbody=withClass("aboluo-rilitbody");
	var tbodyheight=table.offsetHeight-thead.offsetHeight;
	var rows=tbody.getElementsByTagName('tr');
	for(var i=0;i<rows.length;i++){
		rows[i].style.height=(tbodyheight/rows.length-2)+"px";
		var tds=rows[i].getElementsByTagName("td");
		for(var j=0;j<tds.length;j++){
			var a=tds[j].childNodes[0];
			a.style.width=(tds[j].offsetWidth-10)+"px";
			a.style.height=(tds[j].offsetHeight-7)+"px";
			a.style.lineHeight=(tds[j].offsetHeight-7)+"px";
		}
	}
}
//寰楀埌id瀵硅薄
function withID(id){
	return document.getElementById(id);
}
//寰楀埌浼犲叆鍙傛暟涓篶lass鐨勫璞�(鍚屽悕杩斿洖绗竴涓�)
function withClass(id){
	var targets= targets ||  document.getElementsByTagName("*");
	var list=[];
	for(var k in targets){
		var target=targets[k];
		if(target.className==id){
			return target;
		}
	}
	return "";
}

//璁剧疆閫変腑鐨勬棩鏈熷仛浠€涔堟搷浣�
function aboluoSetrq(){
	//閫変腑鐨勬棩鏈�
	var curra=getAclickDom();
	var date=curra.getAttribute("date");  //寰楀埌鏃ユ湡
	var ztid=curra.getAttribute("ztid"); //寰楀埌ztid锛屽鏋滅┖锛屽氨鏄柊澧�,涓嶄负绌烘槸淇敼
	var jjrzt=curra.getAttribute("jjrzt");  //鑺傚亣鏃ュ綋鍓嶇姸鎬�
	var szjjr=withClass("aboluo-ssjjr");    //瑕佽缃殑褰撳墠鏃ユ湡鐘舵€�
	var a=szjjr.childNodes[1];
	$.ajax({
		   type:"POST",
	   	   url: '',
		   data:{"date":date,"zt":szjjr.childNodes[1].value,"ztid":ztid,"jjrzt":jjrzt}, //杩欓噷鐢╝jax鍙互鏀瑰彉鐘舵€�
		   async:false,
		   success:function(json){
			   if(json.code>0){
				   var date=getAclickDomDate();
				   var datearr=date.split("-");
				    getjjrszModelByYear(datearr[0]);
					createTabledate(datearr[0],datearr[1]);  //鍒涘缓瀵瑰簲鐨則able(鏃ユ湡)
					setRigth(datearr[0],datearr[1],datearr[2]);    //璁剧疆鍙宠竟鏄庣粏鏍忓唴瀹�
			   }else{
				  alert("设置成功");
			   }
		   },
		   error:function(json){
			   alert("设置失败");
		   }
	});
}