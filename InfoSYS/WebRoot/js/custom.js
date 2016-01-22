/**
 * @author Liwei
 */
$.MsgBox = {
	loading: $('<div id="loading"><div id="loading_loader">正在加载...</div></div>'),	
	alert : function(data,callback,code) {
		$.MsgBox.dialog({
			title:data.title,
			name:'alert',
			content:data.msg,
			type:data.type,
			buttons:[{
				text:'确定',
				btnClass:'btn btn-primary',
				handler:function(){
					$.MsgBox.removeDialog("modal-alert");
					if($.isFunction(callback)) callback.call(this,true);
				}
			}]
		})
	},
	confirm : function(title,msg,callback) {
		$.MsgBox.dialog({
			title:title,
			name:'confirm',
			content:msg,
			icon : 'fa fa-question',
			buttons:[{
				text:'确定',
				btnClass:'btn btn-primary',
				handler:function() {
					$.MsgBox.removeDialog("modal-confirm");
					if($.isFunction(callback)) callback.call(this,true);
				}
			},{
				text:'取消',
				btnClass:'btn btn-default',
				handler:function(){$.MsgBox.removeDialog("modal-confirm")}
			}]
		});
	},
	promit : function(data,callback) {
		var content = "";
		$.each(data.form_input,function(i,item){
			content += "<div class='form-group'><label class='col-sm-2 control-label'>"+eval("'" + data.form_input[i][2] + "'")+"</label><div class='col-sm-7'><input type="+data.form_input[i][1]+" name="+data.form_input[i][0]+" class='form-control'></div></div>";
		});
		var c = "<form method='post' id="+data.form_id+" class='form-horizontal'>"+content+"</form>";
		
		$.MsgBox.dialog({
			title:data.title,
			name:'promit',
			content:c,
			buttons:[{
				text:'确定',
				btnClass:'btn btn-primary',
				handler:function() {
					console.log("form#"+data.form_id+"");
					return callback.call(this,$("form#"+data.form_id+"").serialize(),data.action);
					$.MsgBox.removeDialog("modal-promit");
				}
			},{
				text:'取消',
				btnClass:'btn btn-default',
				handler:function(){
					$.MsgBox.removeDialog("modal-promit");
					//return callback.call(this,false);
				}
			}]
		});
	},
	redirect : function(url,callback) {
		if(typeof url != 'undefined' && url != '') {
			url = url.replace('&amp;','&');
			$.MsgBox.loading.appendTo("body");
			var win = top || window;
				win.location.replace(url);
		}
	},
	dialog : function(options,callback) {
		return $("body").dialog(options,callback);
	},
	removeDialog : function(name) {
		var dialog = $.data(document,name);
		dialog = dialog ? dialog : ".modal." + name + "";
		var masked = $(".modaldrop");
		$(dialog).remove();
		masked.remove();
	},
	select : function(name,options,callback) {
		return $(name).selectBox(name,options,callback);
	},
	//统一处理Ajax返回结果
	ajaxSuccess : function(data,status,xhr) {
		var code = data.code;	//提示方式
		switch(code) {
			case "alert" : case "success" : case "error" :
				$.MsgBox.alert(data,function(){
					$.MsgBox.redirect(data.url);
				},code);
				break;
			case "confirm" :
				$.MsgBox.confirm(data.title,data.msg,function(r){
					if(r) {
						//处理确定后事件
						$.MsgBox.ajaxSubmit(data.data,data.url);
					}
				});
				break;
			case "promit" : 
				$.MsgBox.promit(data,function(data,url){
					//console.log($("#"+data.form_id+"").serialize());
					$.MsgBox.ajaxSubmit(data,url);
					//alert($("form[name='"+data.form_name+"']").serialize())
					//$.MsgBox.ajaxSubmit($("#"+data.form_id+"").serialize(),data.action)
				});
				break;
			case "redirect" : 
				$.MsgBox.redirect(data.url);
				break;
			default:
				break;
		}
	},
	//Ajax提交事件  统一处理
	ajaxSubmit : function(data,url) {
		$.ajax({
			dataType:"json",
			type: "POST",
			timeout : 6000,
			url:url,
			data:data,
			success:function(data,status,xhr){
				$.MsgBox.ajaxSuccess(data,status,xhr);
			}
		});
	}
}
//获取选中值
function chk() {
	var _data = [];
	$("input[name='chk_list']:checked").each(function(i){
		_data.push($(this).val());
	});
	return _data;
}
//提交选中值
function submit_chk(url,method) {
	var chk_data = chk();
	$.MsgBox.ajaxSubmit({
		chk_list:chk_data
	},url+"?method="+method);
}
	
//弹窗事件绑定
$(".btn-group",'body').each(function(i){
	var _this = $(this);
	var url,u = "";
	if (url=='' || typeof url=='undefined') url = self.location.href.replace(/(.*?)(admin.*?)(\?.*)/,"$1");
	
	//编辑
	$("table.table.dbc tr").dblclick(function(){
		var cid = $(this).find("input[type='checkbox']").attr("value");	
		if(cid=='' || typeof cid=='undefined') {
			return false;
		}else {
			self.location.href = url+"?method=edit&id="+cid;
		}
	});
	
	$("button[name]",_this).click(function(e){
		var _button = $(this);
		var _bname = _button.attr("name");
		switch(_bname) {
			case "back" :
				//返回事件
				$.MsgBox.redirect(history.go(-1));
				break;
			case "refresh" : 
				//刷新事件
				$.MsgBox.redirect(location.href.replace(/#$/,""));
				break;
			case "delete" : 
				//删除事件
				submit_chk(url,_bname);
				break;
			case "add" : 
				//添加事件
				url2 = location.href.replace(/(.*?\?method=)(.*?)/,"$2");
				if(url2=="d2") $.MsgBox.redirect(url+"?method="+_bname+"2");
				else $.MsgBox.redirect(url+"?method="+_bname);
				break;
			case "sign" :
				$(".sign-dropdown li").each(function(){
					var _drop = $(this);
					$("a[name]",_drop).unbind('click');	//防止click多次绑定
					$("a[name]",_drop).bind("click",function(){
						var _a = $(this);
						var _aname = _a.attr("name");
						switch(_aname){
							case "died" : case "pending" : case "active" :
								submit_chk(url,_aname);
								break;
							default:break;
						}
					});
				});
				break;
				default:break;
		}
	});
	
});

//Ajax全局设置
$.ajaxSetup({
	url : "admin/statistics",
	type : "POST",
	cache:false,
	traditional:true,
	error : function(data,xhr,status) {
		$.MsgBox.loading.remove();
		var title = data.title || "系统错误";
		var msg = data.msg || "系统错误，请重试！";
		if(status == "timeout") {
			var d = {
				title : title,
				msg : "连接超时，请重试"
			}
			$.MsgBox.alert(d);
		}else {
			var d = {
				title : title,
				msg : msg
			}
			$.MsgBox.alert(d);
		}
		//console.log(status);
	},
	complete : function(xhr,status) {
		$.MsgBox.loading.remove();
	}
});

//弹窗扩展
(function(){
	$.fn.dialog = function(options,callback) {
		var opts = $.extend({
			title: '',
			name:null,
			content: '',
			buttons:[],
			cblank:'normal',	//点击空白区域部分dialog动作
			masked:true,	//是否需要罩层
			drag : true,	//拖拽
			animate : true,	//动画效果
			type : '',	//提示类型
			icon : 'fa fa-info-circle',	//font图标
			close : true,	//关闭按钮
			remove : function(){$.MsgBox.removeDialog("modal-"+opts.name);$.MsgBox.removeDialog("modaldrop");}
		},options||{});
		
		ani = opts.animate?"ani-scale":"";
		var dialog = $('<div class="modal modal-'+opts.name+'" style="display:block"><div class="modal-dialog"><div class="modal-content '+ani+'"><div class="modal-header"><h4 class="modal-title"><i class="'+opts.icon+'"></i>'+opts.title+'</h4></div><div class="modal-body"><p><span class="text-'+opts.type+'">'+opts.content+'</span></p></div><div class="modal-footer"></div></div></div></div>');

		var btnLength = opts.buttons.length;

		dialog.removeDialog = opts.remove;
		
		if(opts.close) {
			if($(".close",dialog).is("button")) {
				$(".close",dialog).click(function(){
					dialog.removeDialog();
				});
			}else {
				$('<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>').click(dialog.removeDialog).insertBefore($("h4",dialog));
			}
		}else {
			$(".close",dialog).remove();
		}

		for(var i=0;i<btnLength;i++) {
			var button = $("<button class='"+opts.buttons[i].btnClass+"'>"+opts.buttons[i].text+"</button>");
			$(".modal-footer",dialog).append(button);
			(function(i){
				button.click(function(){
					if($.isFunction(opts.buttons[i].handler)) opts.buttons[i].handler.call(dialog,opts);
					return false;
				});
			})(i);
		}
		
		$.pos = function pos(){
			this.step=this.step?this.step:0;
			this.step=this.step==4?0:this.step;
			var set={
				0:{x:0},
				1:{x:-1},
				2:{x:0},
				3:{x:1}
			}
			return set[this.step++]
		}
		switch(opts.cblank) {
			case 'normal' :
				break;
			case 'shake': 
				$(document).click(function(e){
					$(".modal-content").click(function(e){
						e.stopPropagation();
					});
					var r = true;
					if(r) {
						r = false;
						var len=8,//晃动的距离，单位像素
						c=8,//晃动次数，4次一圈
						step=0,	//计数器
						shakeModal = $(".modal-dialog"),
						off = shakeModal.offset();
						console.log(off);
						this.step = 0;
						timer = setInterval(function(){
							var set = $.pos();
							console.log(set);
							shakeModal.offset({left:off.left+set.x*len});
							if(step++ >= c) {
								shakeModal.offset({left:off.left});
								clearInterval(timer);
							}
						},45);
					}
					setTimeout(function(){
		                r = true;
		            },1000);
				});
				break;
		}
		
		//添加罩层
		var maskdrop = $('<div class="modaldrop maskdrop"></div>');
		if(opts.masked) {
			maskdrop.appendTo('body');
		}
		var _move = false;	//移动标记
		var _x,_y;	//鼠标离控件左上角的相对位置
		if(opts.drag) {
			$(".modal-content",dialog).css({"top":0,"left":0});
			$(".modal-header",dialog).css("cursor","move");
			$(".modal-header",dialog).click(function(){
			}).mousedown(function(e){
				//alert(111);
				if(!$(this).is(":animated")) {
					_move = true;
					_x = e.pageX - parseInt($(".modal-content",dialog).css("left"));
					_y = e.pageY - parseInt($(".modal-content",dialog).css("top"));
					$(".modal-content",dialog).fadeTo(20,0.6);		//透明显示
				}
			});

			$(".modal-header",dialog).mousemove(function(e){
				if(_move && !$(this).is(":animated")) {
					var x = e.pageX - _x;
					var y = e.pageY - _y;
					$(".modal-content",dialog).css({"top":y,"left":x});	//新位置
				}
			}).mouseup(function(){
				_move = false;
				$(".modal-content",dialog).fadeTo("fast",1);
			});
		}
		
		dialog.appendTo("body");
	}
	
	//selectBox扩展
	$.fn.selectBox = function(name,options,callback) {
		var opts = $.extend({
			selectname : '',	//select的名称
			items : []	//options
		},options||{});
		
		var selectarea = $('<div class="chartselect select-inline-block"><div class="button-caption select-inline-block">'+opts.selectname+'</div><div class="button-dropdown select-inline-block">&nbsp;</div></div>');
		var itemsLength = opts.items.length;
		var option = "";
		for(var i=0;i<itemsLength;i++) {
			option += '<div class="select-menuitem"><div class="select-menuitem-content" selectid="'+opts.items[i].selectid+'">'+opts.items[i].text+'</div></div>';
		}
		var selectitems = $('<div class="select-menu" style="display: none;">'+option+'</div>');
		selectarea.click(function(){
			var selectmenu = $(this).siblings(".select-menu");
			if(selectmenu.css("display")=="none") {
				selectitems.show();
				var midY = selectitems.height()/2;
				selectmenu.css("top",-midY);
			}else {
				selectitems.hide();
			}
		});
		$(".select-menuitem",selectitems).click(function(){
			var _this = $(this).find(".select-menuitem-content");
			var txt = _this.text();
			var _caption = $(this).parent().siblings(".chartselect").find(".button-caption");
			_caption.html(txt);
			var value = _this.attr("selectid");
			_caption.attr("selectedid",value);
			_caption.attr("aria-posinset",txt);
			$(".select-menu").hide();
		});
		
		
		selectarea.appendTo(name);
		selectitems.appendTo(name);
	}
})(jQuery);