//灞忚斀鍙抽敭鐩稿叧
var jsArgument = document.getElementsByTagName("script")[document.getElementsByTagName("script").length-1].src;	//鑾峰彇浼犻€掔殑鍙傛暟
rightButton = jsArgument.substr(jsArgument.indexOf("rightButton=") + "rightButton=".length, 1);
if (rightButton == "1")
{
	document.oncontextmenu = function(e){return false;}
	document.onselectstart = function(e){return false;}
	if (navigator.userAgent.indexOf("Firefox") > 0)
	{
		document.writeln("<style>body {-moz-user-select: none;}</style>");
	}
}

////璁句负棣栭〉
//function setHomePage()
//{
//	if(document.all)
//	{
//		var obj = document.links(0);
//		if (obj)
//		{
//			obj.style.behavior = 'url(#default#homepage)';
//			obj.setHomePage(window.location.href);
//		}
//  	}
//	else
//	{
//		if(window.netscape)
//		{
//			try
//			{
//				netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
//			}
//			catch (e)
//			{
//				window.alert("姝ゆ搷浣滆娴忚鍣ㄦ嫆缁濓紝璇烽€氳繃娴忚鍣ㄨ彍鍗曞畬鎴愭鎿嶄綔锛�");
//			}
//		}
//   	}
//}
//
////鍔犲叆鏀惰棌
//function addFavorite()
//{
//	var url		= document.location.href;
//	var title	= document.title;
//	if (document.all)
//	{
//		window.external.addFavorite(url,title);
//	}
//	else if (window.sidebar)
//	{
//		window.sidebar.addPanel(title, url,"");
//	}
//}

// 璁句负棣栭〉
function setHome(obj, vrl)
{
	try
	{
		obj.style.behavior='url(#default#homepage)';
		obj.setHomePage(vrl);
	}
	catch(e)
	{
		if(window.netscape)
		{
			try 
			{
				netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
			}
			catch (e) 
			{
				alert("姝ゆ搷浣滆娴忚鍣ㄦ嫆缁濓紒\n璇峰湪娴忚鍣ㄥ湴鍧€鏍忚緭鍏モ€渁bout:config鈥濆苟鍥炶溅\n鐒跺悗灏哰signed.applets.codebase_principal_support]鐨勫€艰缃负'true',鍙屽嚮鍗冲彲銆�");
			return;
			}
			var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
			prefs.setCharPref('browser.startup.homepage', vrl);

		}
	}
} 


// 鍔犲叆鏀惰棌
function addFavorite(sURL, sTitle)
{
	try
	{
		window.external.addFavorite(sURL, sTitle);
	}
	catch (e)
	{
		try
		{
			window.sidebar.addPanel(sTitle, sURL, "");
		}
		catch (e)
		{
			alert("鎮ㄤ娇鐢ㄧ殑娴忚鍣ㄤ笉鏀寔姝ゅ姛鑳斤紝璇蜂娇鐢–trl+D杩涜娣诲姞");
		}
	}
}

//宸﹀彸绛夐珮
function equalHeight(){
	var a = $(".sidebar").height();
	var b = $(".main").height();
	if ( a >= b){
		$(".main").height(a);
	}
	else if ( a <= b){
		$(".sidebar").height(b);
	}
}

//绾靛悜鑿滃崟
function Nav(){
	var mst;
	$(".nav li a").filter(".current").parent("li").addClass("cur").siblings().removeClass("cur");
	//$(".nav li:last").css("border","none");
	$(".nav li").hover(function(){
		var _this = $(this);
		$(this).find("a:eq(0)").addClass("current");
		mst = setTimeout(function(){
			_this.find(".subNav").slideDown(300);
			mst = null;
		},300)
	},function(){
		if(mst!=null) {clearTimeout(mst)};
		$(this).not(".cur").find("a:eq(0)").removeClass("current");
		$(this).find(".subNav").slideUp(300);
	})
}