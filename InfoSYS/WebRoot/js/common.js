$(function(){
	
	$("th.th-header .chk_handle").hide();
	//checkbox
	$("table.table input[name='chk_all']").click(function(){
		if(this.checked) {
			$(".dataTables_wrapper input[type='checkbox']").prop("checked",true);
			$("table").find("tr").addClass("checked");
		}else {
			$(".dataTables_wrapper input[type='checkbox']").prop("checked",false);
			$("table").find("tr").removeClass("checked");
		}
		var chked_len = $(".dataTables_wrapper input[name='chk_list']:checked").length;
		if(chked_len!=0) {
			$(".th-header span.chk_tips").text("，已选中"+chked_len+"条记录");
			$("th.th-header .chk_handle").show();
			$("th.th-header .com_handle").hide();
		}else {
			$(".th-header span.chk_tips").text("");
			$("th.th-header .chk_handle").hide();
			$("th.th-header .com_handle").show();
		}
	});
	$("form.search input[name='chk_all']").click(function(){
		if(this.checked) {
			$("form.search input[type='checkbox']").prop("checked",true);
		}else {
			$("form.search input[type='checkbox']").prop("checked",false);
		}
		
	});
	
	$("form.search input[name='chk_list']").click(function(){
		$(this).each(function(i){
			if($(this).not(':checked')) {
				$("form.search input[name='chk_all']").prop("checked",false);	
			}
		});
	});
	
		
	$(".dataTables_wrapper input[name='chk_list']").click(function(){
		if(".dataTables_wrapper input[name='chk_list']:checked") {
			$(this).parents("tr").toggleClass("checked");
		}
		var chked_len = $(".dataTables_wrapper input[name='chk_list']:checked").length;
		if(chked_len!=0) {
			$(".th-header span.chk_tips").text("，已选中"+chked_len+"条记录");
			$("th.th-header .chk_handle").show();
			$("th.th-header .com_handle").hide();
		}else {
			$(".th-header span.chk_tips").text("");
			$("th.th-header .chk_handle").hide();
			$("th.th-header .com_handle").show();
		}
	});
	
	//提交
	$("button.submit").click(function(){
		var _form = $(this).parents("form");
		var url = _form.attr("action");
		var _formdata = _form.serialize();
		$.MsgBox.ajaxSubmit(_formdata,url);
		return false;
	});
	
	//clipboard
	$('a.clipboard').each(function() {
    var clip = new ZeroClipboard($(this), {moviePath: "../js/zeroclipboard/ZeroClipboard.swf"});
    clip.on('mouseover', function(client) {
        $(this).text('单击复制');
    });
    clip.on('mouseout', function(client) {
        $(this).text($(this).attr("title"));
    });
    clip.on('complete', function(client, args) {
        $(this).text('复制成功');
    });
});

//工具提示
$('[data-toggle="tooltip"]').tooltip();

//展开所有
$(".collapse-all").click(function(){
	$("table.table.dbc .td-collapse div.collapse").each(function(){
		$(this).toggleClass("in");
		if($(this).hasClass("in")) {
			$(".table-collapse").addClass("collapse");
		}
		
	});
});


//Daterangepicker
/*
$('#daterange-btn').daterangepicker(
	  {
	      ranges: {
	          '今天': [moment(), moment()],
	          '昨天': [moment().subtract('days', 1), moment().subtract('days', 1)],
	          '最近7天': [moment().subtract('days', 6), moment()],
	          '最近30天': [moment().subtract('days', 29), moment()],
	          '当前月份': [moment().startOf('month'), moment().endOf('month')],
	          '上个月份': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
	      },
	      showDropdowns : true,
	      startDate: moment().subtract('days', 29),
	      endDate: moment(),
	      minDate: '01/01/2015',    //最小时间
	      maxDate : moment(),	//最大时间
	      locale : {
	          applyLabel : '确定',
	          cancelLabel : '取消',
	          fromLabel : '起始时间',
	          toLabel : '结束时间',
	          customRangeLabel : '自定义',
	          daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],
	          monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月',
	              '七月', '八月', '九月', '十月', '十一月', '腊月' ],
	          firstDay : 1
	      }
	  },
	function(start, end) {
	    $('#reportrange span').html(start.format('MMMM D, YYYY') + ' / ' + end.format('MMMM D, YYYY'));
	    $("input[name='timerange']").val(start.format('YYYY-MM-DD') + ' / ' + end.format('YYYY-MM-DD'));
	}
);
*/

// Bootstrap modal
$("#compose-modal").modal({
	backdrop : "static",
	show : false
});

//option
	$(".slan,.sdns").change(function(){
		if($(this).find("option:selected").index()==1) {
			var action = $(this).val();
			$.MsgBox.ajaxSubmit("",action);
		}
	});
	
//load more 
$("#loadMore").unbind().bind("click",function(){
	$("#loadMore>span").hide();
	$("#loadMore>em").addClass("np-load-more-loading").show();
});

//logout
$("a.logout").bind("click",function(){
	var url = $(this).attr("href");
	$.MsgBox.ajaxSubmit("",url);
	return false;
});
});
