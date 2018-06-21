/**
 * 
 * @author MATTDAMON
 * 
 */
(function(window, undefined) {
	/*
	 * if (!window.console || !console.firebug) { var names = [ "log", "debug",
	 * "info", "warn", "error", "assert", "dir", "dirxml", "group", "groupEnd",
	 * "time", "timeEnd", "count", "trace", "profile", "profileEnd" ];
	 * window.console = {}; for (var i = 0; i < names.length; ++i) {
	 * window.console[names[i]] = function() { }; } }
	 */

	var config = {
		title : "太平网销内容管理平台 - CMS",
		// 域名
		domain : ".cntaiping.com",
		// 二级域名
		subdomain : "baoxian.cntaiping.com"
	};

	var version = {
		neusoft : "1.0",
		jquery : "1.11.0",
		easyui : "1.4",
		requirejs : "2.1.11"
	};

	neusoft = function() {
		return new neusoft.prototype.init();
	};

	neusoft.prototype = {
		constructor : neusoft,
		init : function() {
		}
	// 可在这里添加实例方法
	};

	neusoft.prototype.init.prototype = neusoft.prototype;

	/** 识别浏览器版本* */
	neusoft.browser = {};
	neusoft.browser.mozilla = /firefox/.test(navigator.userAgent.toLowerCase());
	neusoft.browser.webkit = /webkit/.test(navigator.userAgent.toLowerCase());
	neusoft.browser.opera = /opera/.test(navigator.userAgent.toLowerCase());
	neusoft.browser.msie = /msie/.test(navigator.userAgent.toLowerCase());

	neusoft.browser.msieVersion = function() {

		if (neusoft.browser.mozilla || neusoft.browser.webkit
				|| neusoft.browser.opera) {
			return;
		}
		var browser = navigator.appName;
		if (browser == undefined || browser == "" || browser == null) {
			return;
		}
		var b_version = navigator.appVersion;
		var version = b_version.split(";");
		var trim_Version = version[1].replace(/[ ]/g, "");

		if (browser == "Netscape") {
			return trim_Version;
		} else if (browser == "Microsoft Internet Explorer"
				&& trim_Version == "MSIE9.0") {
			return "MSIE9.0";
		} else if (browser == "Microsoft Internet Explorer"
				&& trim_Version == "MSIE8.0") {
			return "MSIE8.0";
		} else if (browser == "Microsoft Internet Explorer"
				&& trim_Version == "MSIE7.0") {
			return "MSIE7.0";
		} else if (browser == "Microsoft Internet Explorer"
				&& trim_Version == "MSIE6.0") {
			return "MSIE6.0";
		}
	};

	neusoft.extend = neusoft.prototype.extend = function() {
		var src, copy, name, options, target = arguments[0] || {}, i = 1, length = arguments.length;

		// extend jquery itself if only one argument is passed
		if (length === i) {
			target = this;
			--i;
		}

		for (; i < length; i++) {
			// Only deal with non-null/undefined values
			if ((options = arguments[i]) != null) {
				// Extend the base object
				for (name in options) {
					src = target[name];
					copy = options[name];

					// Prevent never-ending loop
					if (target === copy) {
						continue;
					}

					// Don't bring in undefined values
					if (copy !== undefined) {
						target[name] = copy;
					}
				}
			}
		}

		// Return the modified object
		return target;
	};

	/** ********************静态方法************************** */

	// context ready事件
	neusoft.onContextReady = function(fn) {
		var _contextReady = neusoft.contextReady || new Array();
		if (fn != undefined && fn != null && fn != "") {
			_contextReady.push(fn);
		}
		neusoft.contextReady = _contextReady;
	};

	// context change事件
	neusoft.onContextChange = function(fn) {
		var _contextChange = neusoft.contextChange || new Array();
		if (fn != undefined && fn != null && fn != "") {
			_contextChange.push(fn);
		}
		neusoft.contextChange = _contextChange;
	};

	neusoft.setPath = function(path) {
		if (path != "/") {
			neusoft.path = path;
		}
	};

	neusoft.css = function(resourceId) {
		importCSSStyle(resourceId);
	};

	neusoft.image = function(libId) {
		return neusoftResourcePath(libId);
	};

	neusoft.theme = function(themeId) {
		if (document.getElementById("theme-link") != undefined) {
			document.getElementById("theme-link").href = neusoft.path
					+ "/view-support/neusoft/" + version.neusoft + "/theme/"
					+ themeId;
		} else {
			importCSSStyle(themeId, "theme-link");
		}
	};

	neusoft.lib = function(libId, callback) {
		importJavaScript(neusoftResourcePath(libId), callback);
	};

	neusoft.extend(neusoft.lib, {

		jquery : function(libId, callback) {
			importJavaScript(jqueryResourcePath(libId), callback);
		},
		handlebars : function(libId, callback) {
			importJavaScript(handlebarsResourcePath(libId), callback);
		},
		easyui : function(libId, callback) {
			importJavaScript(easyuiResourcePath(libId), callback);
		},
		requirejs : function(libId, callback) {
			importJavaScript(requirejsResourcePath(libId), callback);
		}

	});

	// httpclient
	neusoft.httpClient = function(type, url, data, callback) {
		$.ajax({
			type : type,
			url : url,
			cache : false,
			data : JSON.stringify(data),
			contentType : "application/json",
			dataType : "json",
			success : function(result) {
				console.log("neusoft.httpClient>success");
				console.log(result);
				if (result.category == "00") {
					if (callback != undefined) {
						callback(result);
					}
				} else { // exception
					if (result.code == "ERROR-11-000000") {
						// 登录dialog
						neusoft.header.signerOnClickHandler();
					} else {
						// 异常处理
						neusoft.errorWindow("open", result.message);
					}
				}
			},
			error : function() {
				console.log("neusoft.httpClient>error");
			}
		});
	};

	// httpclient form submit
	neusoft.formSubmit = function(form, url, validateFn, callback) {
		$(form).form('submit', {
			url : url,
			onSubmit : function() {
				// do some check
				// return false to prevent submit;
				if (validateFn == undefined) {
					return $(this).form('validate');
				} else {
					if ($(this).form('validate') == true) {
						return validateFn();
					} else {
						return false;
					}
				}
			},
			success : function(result) {
				console.log("neusoft.formSubmit>success");
				console.log(result);
				//浏览器插件会影响返回结果内容
				result = result.replace(/<\/generated>/g, "");
				result = JSON.parse(result);
				console.log(result);
				if (result.category == "00") {
					if (callback != undefined) {
						callback(result);
					}
				} else { // exception
					if (result.code == "ERROR-11-000000") {
						// 登录dialog
						neusoft.header.signerOnClickHandler();
					} else {
						// 异常处理
						neusoft.errorWindow("open", result.message);
					}
				}
			}
		});
	};

	// 获取指定名称的cookie的值
	neusoft.getCookie = function(cookieName) {
		var cookies = this.getCookies();
		if (cookies == null) {
			return null;
		} else {
			for (var i = 0; i < cookies.length; i++) {
				if (cookies[i].cookieName == cookieName) {
					return cookies[i].cookieValue;
				}
			}
			return null;
		}
	};

	// 获取所有cookie
	neusoft.getCookies = function() {
		var cookieStr = document.cookie;
		if (cookieStr == "") {
			return null;
		} else {
			var cookies = [];
			var cookieParts = cookieStr.split("; ");
			for (var i = 0; i < cookieParts.length; i++) {
				var cookiePart = cookieParts[i].split("=");
				var cookie = new Object();
				cookie.cookieName = cookiePart[0];
				cookie.cookieValue = unescape(cookiePart[1]);
				cookies.push(cookie);
			}
			return cookies;
		}
	};

	// 获取字典数据 EX: neusoft.getDict("mp-element-type");
	neusoft.getDict = function(type) {
		if (neusoft.dictionaries == undefined || neusoft.dictionaries == null) {
			neusoft.dictionaries = new Array();
		}
		var dict = new Array();
		for ( var index in neusoft.dictionaries) {
			if (neusoft.dictionaries[index].type == type) {
				dict.push(neusoft.dictionaries[index]);
			}
		}
		return dict;
	};

	// 获取字典指定项目
	neusoft.getDictItem = function(key) {
		if (neusoft.dictionaries == undefined || neusoft.dictionaries == null) {
			neusoft.dictionaries = new Array();
		}
		for ( var index in neusoft.dictionaries) {
			if (neusoft.dictionaries[index].key == key) {
				return neusoft.dictionaries[index];
			}
		}
	};

	neusoft.getQuoteCity = function() {
		if (neusoft.qareas == undefined || neusoft.qareas == null) {
			neusoft.qareas = new Array();
		}
		var citys = new Array();
		for ( var index in neusoft.qareas) {
			for ( var i in neusoft.qareas[index].children) {
				citys.push(neusoft.qareas[index].children[i]);
			}
		}
		return citys;
	};

	neusoft.getDirectCity = function() {
		if (neusoft.dareas == undefined || neusoft.dareas == null) {
			neusoft.dareas = new Array();
		}
		var citys = new Array();
		for ( var index in neusoft.dareas) {
			for ( var i in neusoft.dareas[index].children) {
				citys.push(neusoft.dareas[index].children[i]);
			}
		}
		return citys;
	};

	neusoft.getChannelName = function(channel1lNumber, channel2lNumber,
			channel3lNumber) {
		var channel1lData = neusoft.channels;
		var channel_name = "";
		for ( var i = 0 in channel1lData) {
			if (channel1lData[i].channel_number == channel1lNumber) {
				var channel2lData = channel1lData[i].children;
				for ( var j = 0 in channel2lData) {
					if (channel2lData[j].channel_number == channel2lNumber) {
						var channel3lData = channel2lData[j].children;
						for ( var z = 0 in channel3lData) {
							if (channel3lData[z].channel_number == channel3lNumber) {
								channel_name = channel3lData[z].channel_name;
								break
							}
						}
						break;
					}
				}
				break;
			}
		}
		return channel_name;
	};

	neusoft.getVehiclePackagesType = function(kind) {
		var vehiclePackagesType = neusoft.vehiclePackagesType;
		var types = new Array();
		for ( var index in vehiclePackagesType) {
			if (vehiclePackagesType[index].kind == kind) {
				types.push(vehiclePackagesType[index]);
			}
		}
		return types;
	};

	// 登录窗口
	neusoft.loginDialog = function(option, callback) {

		$("div[id='login_dialog_container'] div[id='login_msg']").hide();

		if (this.loginDG == undefined) {

			$("div[id='login_dialog_container']").show();

			var _loginDialog = $("div[id='login_dialog_container']").dialog({
				title : "登录",
				top : 200,
				width : 400,
				height : 280,
				modal : true,
				closed : true,
				cache : false
			});
			this.loginDG = _loginDialog;

			$("div[id='login_dialog_container'] button[id='login_submit_btn']")
					.on(
							'click',
							function() {
								var username = $(
										"div[id='login_dialog_container'] input[id='username']")
										.val();
								var password = $(
										"div[id='login_dialog_container'] input[id='password']")
										.val();
								$(
										"div[id='login_dialog_container'] div[id='login_msg']")
										.hide();
								if (username == "") {
									$(
											"div[id='login_dialog_container'] div[id='login_msg']")
											.text("请输入用户名");
									$(
											"div[id='login_dialog_container'] div[id='login_msg']")
											.show();
									return;
								}
								if (password == "") {
									$(
											"div[id='login_dialog_container'] div[id='login_msg']")
											.text("请输入密码");
									$(
											"div[id='login_dialog_container'] div[id='login_msg']")
											.show();
									return;
								}

								var loginData = {
									user_name : username,
									password : password
								};

								neusoft
										.httpClient(
												"POST",
												neusoft.path
														+ "/system/environment/login.do",
												loginData,
												function(result) {
													if (result.code == "MSG-00-000002") {// 登录成功
														neusoft
																.loginDialog("close");
														if (callback != undefined) {
															callback(result.data);
														}
													} else if (result.code == "MSG-00-000003") {// 登录失败
														$(
																"div[id='login_dialog_container'] div[id='login_msg']")
																.text(
																		result.message);
														$(
																"div[id='login_dialog_container'] div[id='login_msg']")
																.show();
													}
												});

							});

		}

		if (option == "open" || option == "close") {
			$(this.loginDG).dialog(option);
		}
	};

	// 提示窗口
	neusoft.promptWindow = function(option, promptContent, okCallback) {
		var promptSelector = "div[id='prompt_window_container']";
		var promptContentSelector = "div[id='prompt_window_container'] div[id='prompt_window_content']";
		var promptOkBtnSelector = "div[id='prompt_window_container'] button[id='prompt_window_ok_btn']";
		if (this.promptWin == undefined) {
			$(promptSelector).show();
			var _promptWin = $(promptSelector).window({
				title : "提示",
				iconCls : "icon-ok",
				top : 200,
				width : 320,
				height : 180,
				modal : true,
				closed : true,
				resizable : false,
				minimizable : false,
				maximizable : false,
				collapsible : false,
				cache : false
			});
			this.promptWin = _promptWin;
		}

		if (option == "open") {
			$(promptContentSelector).text("");
			if (promptContent != undefined && promptContent != null) {
				$(promptContentSelector).text(promptContent);
			}
			$(promptOkBtnSelector).unbind();
			$(promptOkBtnSelector).on('click', function() {
				neusoft.promptWindow("close");
				if (okCallback != undefined) {
					okCallback.call();
				}
			});
		}

		if (option == "open" || option == "close") {
			$(this.promptWin).window(option);
		}
	};

	neusoft.warnWindow = function(option, warnContent, okCallback,
			cancelCallback) {
		var warnWinSelector = "div[id='warn_window_container']";
		var warnContentSelector = "div[id='warn_window_container'] div[id='warn_window_content']";
		var warnOkBtnSelector = "div[id='warn_window_container'] button[id='warn_window_ok_btn']";
		var warnCancelSelector = "div[id='warn_window_container'] button[id='warn_window_cancel_btn']";
		if (this.warnWin == undefined) {
			$(warnWinSelector).show();
			var _warnWin = $(warnWinSelector).window({
				title : "警告",
				iconCls : "icon-tip",
				top : 200,
				width : 320,
				height : 180,
				modal : true,
				closed : true,
				resizable : false,
				minimizable : false,
				maximizable : false,
				collapsible : false,
				cache : false
			});
			this.warnWin = _warnWin;
		}
		if (option == "open") {
			$(warnContentSelector).text("");
			if (warnContent != undefined) {
				$(warnContentSelector).text(warnContent);
			}
			$(warnOkBtnSelector).unbind();
			$(warnCancelSelector).unbind();
			$(warnOkBtnSelector).on('click', function() {
				neusoft.warnWindow("close");
				if (okCallback != undefined) {
					okCallback.call();
				}
			});
			$(warnCancelSelector).on('click', function() {
				neusoft.warnWindow("close");
				if (cancelCallback != undefined) {
					cancelCallback.call();
				}
			});
		}
		if (option == "open" || option == "close") {
			$(this.warnWin).window(option);
		}
	};

	neusoft.errorWindow = function(option, errorContent) {

		var errorWinSelector = "div[id='error_window_container']";
		var errorContentSelector = "div[id='error_window_container'] div[id='error_window_content']";
		if (this.errorWin == undefined) {
			$(errorWinSelector).show();
			var _errorWin = $(errorWinSelector).window({
				title : "错误",
				iconCls : "icon-no",
				top : 200,
				width : 320,
				height : 180,
				modal : true,
				closed : true,
				resizable : false,
				minimizable : false,
				maximizable : false,
				collapsible : false,
				cache : false
			});
			this.errorWin = _errorWin;

			$(
					"div[id='error_window_container'] button[id='error_window_close_btn']")
					.on('click', function() {
						neusoft.errorWindow("close");
					});
		}
		if (option == "open") {
			$(errorContentSelector).text("");
			if (errorContent != undefined) {
				$(errorContentSelector).text(errorContent);
			}
		}
		if (option == "open" || option == "close") {
			$(this.errorWin).window(option);
		}
	};

	// 生成菜单
	neusoft.generateMenu = function() {

		var pageMenu = neusoft.header.currentMenus;

		var menuItemTemp = "<a href=\"javascript:void(0);\" name=\"menu-group\" class=\"list-group-item active\">{{text}}</a>";
		var ct = Handlebars.compile(menuItemTemp);

		var subMenuItemTemp = "<a href=\"{{navigater link}}\" class=\"list-group-item\">{{text}}</a>";
		var st = Handlebars.compile(subMenuItemTemp);

		Handlebars.registerHelper("navigater", function(link) {
			return new Handlebars.SafeString(link);
		});

		var menuTemp = "";

		for ( var index in pageMenu) {
			var compiled = ct(pageMenu[index]);
			menuTemp = menuTemp + compiled;
			// 子菜单
			menuTemp = menuTemp + "<div name=\"menu-group\">";
			var subMenu = pageMenu[index].subMenu;
			for ( var subIndex in subMenu) {
				subMenu[subIndex].link = neusoft.path
						+ subMenu[subIndex].relative_path;
				var compiled = st(subMenu[subIndex]);
				menuTemp = menuTemp + compiled;
			}
			menuTemp = menuTemp + "</div>";
		}

		$("div[name='page-left-container'] div[name='menu']").append(menuTemp);

		$("div[name='page-left-container'] a[name='menu-group']").on("click",
				function() {
					$(this).next().toggle(200);
				});

	};

	/** ********************内部方法，不对外公开************************** */
	function neusoftResourcePath(resourceId) {
		return neusoft.path + "/view-support/neusoft/" + version.neusoft + "/"
				+ resourceId;
	}

	function jqueryResourcePath(resourceId) {
		return neusoft.path + "/view-support/jquery/" + version.jquery + "/"
				+ resourceId;
	}

	function handlebarsResourcePath(resourceId) {
		return neusoft.path + "/view-support/handlebars/" + version.handlebars
				+ "/" + resourceId;
	}

	function easyuiResourcePath(resourceId) {
		return neusoft.path + "/view-support/easyui/" + version.easyui + "/"
				+ resourceId;
	}

	function requirejsResourcePath(resourceId) {
		return neusoft.path + "/view-support/requirejs/" + version.requirejs
				+ "/" + resourceId;
	}

	function importJavaScript(path, callback) {
		var script, head = document.head || document.documentElement;
		script = document.createElement("script");
		script.type = "text/javascript";
		// script.async = true;
		script.charset = "utf-8";
		script.src = path;

		// Attach handlers for all browsers
		script.onload = script.onreadystatechange = function(_) {

			if (!script.readyState || /loaded|complete/.test(script.readyState)) {

				// Handle memory leak in IE
				script.onload = script.onreadystatechange = null;

				// Remove the script
				if (script.parentNode) {
					script.parentNode.removeChild(script);
				}

				// Dereference the script
				script = null;

				// Callback
				if (callback) {
					callback.call();
				}
			}
		};

		// Circumvent IE6 bugs with base elements (#2709 and #4378) by
		// prepending
		// Use native DOM manipulation to avoid our domManip AJAX trickery
		head.insertBefore(script, head.firstChild);
	}

	// 引入样式
	function importCSSStyle(resourceId, id) {
		var style = document.createElement("link");
		style.type = "text/css";
		style.rel = "stylesheet";
		if (id == "theme-link") {
			style.id = id;
			style.href = neusoft.path + "/view-support/neusoft/"
					+ version.neusoft + "/theme/" + resourceId;
		} else {
			style.href = neusoft.path + "/view-support/neusoft/"
					+ version.neusoft + "/style/" + resourceId;
		}
		document.getElementsByTagName("HEAD")[0].appendChild(style);
	}

	neusoft.config = config;
	neusoft.header = undefined;
	neusoft.footer = undefined;
	window.version = version;
	window.neusoft = neusoft;
	window.document.title = config.title;

	// 当页面引入该JS之后，可在下面做一些初始化的操作，
	// 但是需要注意的是将影响到所有引用该文件的页面。

	// EXT=============================================================================
	// 当页面引入该JS之后，可在下面做一些初始化的操作，
	// 但是需要注意的是将影响到所有引用该文件的页面。

	// 对Date的扩展，将 Date 转化为指定格式的String
	// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
	// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
	// 例子：
	// (new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
	// (new Date()).format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
	Date.prototype.format = function(fmt) { // author: mattdamon
		var o = {
			"M+" : this.getMonth() + 1, // 月份
			"d+" : this.getDate(), // 日
			"h+" : this.getHours(), // 小时
			"m+" : this.getMinutes(), // 分
			"s+" : this.getSeconds(), // 秒
			"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
			"S" : this.getMilliseconds()
		// 毫秒
		};
		if (/(y+)/.test(fmt))
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		for ( var k in o)
			if (new RegExp("(" + k + ")").test(fmt))
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
						: (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	};

	/* 得到日期年月日等加数字后的日期 */
	Date.prototype.dateAdd = function(interval, number) {
		var d = this;
		var k = {
			'y' : 'FullYear',
			'q' : 'Month',
			'm' : 'Month',
			'w' : 'Date',
			'd' : 'Date',
			'h' : 'Hours',
			'n' : 'Minutes',
			's' : 'Seconds',
			'ms' : 'MilliSeconds'
		};
		var n = {
			'q' : 3,
			'w' : 7
		};
		eval('d.set' + k[interval] + '(d.get' + k[interval] + '()+'
				+ ((n[interval] || 1) * number) + ')');
		return d;
	};

	/* 计算两日期相差的日期年月日等 */
	Date.prototype.dateDiff = function(interval, objDate2) {
		var d = this, i = {}, t = d.getTime(), t2 = objDate2.getTime();
		i['y'] = objDate2.getFullYear() - d.getFullYear();
		i['q'] = i['y'] * 4 + Math.floor(objDate2.getMonth() / 4)
				- Math.floor(d.getMonth() / 4);
		i['m'] = i['y'] * 12 + objDate2.getMonth() - d.getMonth();
		i['ms'] = objDate2.getTime() - d.getTime();
		i['w'] = Math.floor((t2 + 345600000) / (604800000))
				- Math.floor((t + 345600000) / (604800000));
		i['d'] = Math.floor(t2 / 86400000) - Math.floor(t / 86400000);
		i['h'] = Math.floor(t2 / 3600000) - Math.floor(t / 3600000);
		i['n'] = Math.floor(t2 / 60000) - Math.floor(t / 60000);
		i['s'] = Math.floor(t2 / 1000) - Math.floor(t / 1000);
		return i[interval];
	};

	// EXT=============================================================================
})(window);
