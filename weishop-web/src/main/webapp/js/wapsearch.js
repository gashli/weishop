/* suning.com 1434124557458 */function queryHotWrods(){var o=$("#channel").val(),r="/hotwords/hotwordsApp.html?channel=6";o&&(r="/hotwords/vchannel/"+o+".html"),$(".otherProBox").hide(),$.ajax({url:r,type:"post",timeout:5e3,async:!1,success:function(o){$(".keywords-err").hide(),o.hotWord.length>0&&($("#hotwords").show(),constructHotWords(o.hotWord))},error:function(){$("#hotwords").show(),$(".keywords-err").show(),$("#hotwordsBox").hide()},complete:function(o,r){"timeout"==r&&($("#hotwords").show(),$(".keywords-err").show(),$("#hotwordsBox").hide())}})}function constructHotWords(o){var r=$("#hotwordsBox");r.empty(),r.show();var e="";$.each(o,function(o,r){var s="";if(s=buildresulturl(r.hotwords,$("#channel").val(),$("#terminalFlag").val()),r.url){s=r.url;var t=$("#terminalFlag").val();t&&(s=s.indexOf("?")>0?s+"&terminal="+$("#terminalFlag").val():s+"?terminal="+$("#terminalFlag").val()),e+='<a name="wapsssry_none_rsc_rsc'+(o+1)+'" href="'+s+'">'+r.hotwords+"</a>"}else e+='<a name="wapsssry_none_rsc_rsc'+(o+1)+'"  href="javascript:searchHistoryOrHotword(\''+r.hotwords+"');\">"+r.hotwords+"</a>"}),r.html(e)}function queryHistory(){if(null==$.cookie("sn_search_history")||"null"==$.cookie("sn_search_history"))$("#historyEmpty").show();else{$("#historyEmpty").hide(),$("#historyCleanSuccess").hide();var o="",r=$.cookie("sn_search_history"),e=r.split(",");for(i=0;i<e.length;i++)o+='<a name="wapsssry_none_lsc_lsc'+(i+1)+'" href="javascript:searchHistoryOrHotword(\''+e[i]+"');\">"+e[i]+"</a>";$("#historyList").html(o),$("#historyList").show(),$("#historyClean").show()}}function addHistory(o){var r,e;if(null==$.cookie("sn_search_history")||"null"==$.cookie("sn_search_history")){r=new Array,r.push(o),e=r.join(",");var s=new Date;s.setTime(s.getTime()+432e5),$.cookie("sn_search_history",e,{path:"/",domain:cookieDomain,expires:s})}else{e=$.cookie("sn_search_history"),r=e.split(",");var t=!1;for(a=0;a<r.length;a++)r[a]==o&&(t=!0);if(!t){var a=r.unshift(o);a>10&&r.splice(10,r.length-1),e=r.join(","),$.cookie("sn_search_history",e,{path:"/",domain:cookieDomain,expires:s})}}}function delHistory(){$.cookie("sn_search_history",null,{path:"/",domain:cookieDomain,expires:0}),$("#historyList").empty(),$("#historyCleanSuccess").show(),$("#historyEmpty").hide(),$("#historyList").hide(),$("#historyClean").hide()}function querySuggest(o){var r=$("#searchType").attr("data-rel");"0"==r&&$.ajax({url:"/queryAssociateWords/jsonp/"+encodeURI(o)+".html",type:"post",success:function(r){r&&""==r.errorCode&&r.associateWords.length>0?($("#keywordsList").empty(),$("#typesList").empty(),$("body").addClass("pop-color"),$("#searchAss").show(),$("#searchHot").hide(),constructSuggest(r.associateWords,r.associateTypes,o)):($("body").removeClass("pop-color"),$("#searchAss").hide(),$("#searchHot").show())},error:function(){}})}function constructSuggest(o,r,e){var s=$("#keywordsList"),t=$("#typesList");r.length>0&&$.each(r,function(o,r){var e="";e+="<div class='list-ass'>",e+="<p><span>"+r.dirName+"</span>分类中搜索</p>",e+="<em></em>",e+="</div>";var a=$('<div class="list-ass"></div>'),i=$("<p><span>"+r.dirName+"</span>分类中搜索</p><em></em>"),n="/v3/list/"+r.dirId+"-0.html?keyword="+r.keyword;a.on("click",function(){$("#wapSearchInput").val(r.keyword),addHistory(r.keyword),location.href=n,s.empty()}),a.append(i),t.append(a)}),$.each(o,function(o,r){if(o>=10)return!1;var t=r.keyword;t=t.replace(e,"<span>"+e+"</span>");var a=$("<li></li>"),i=buildresulturl(r.keyword,$("#channel").val(),$("#terminalFlag").val()),n=$('<a href="'+i+'">'+t+"</a>");a.on("click",function(){$("#wapSearchInput").val(r.keyword),addHistory(r.keyword),location.href=i,s.empty()}),a.append(n),s.append(a)})}function buildresulturl(o,r,e){var s=$("#searchType").attr("data-rel");return"0"==s?"/v3/search/result.html?keyword="+wapFormatURL(trim(o))+(r?"&channel="+r:"")+(e?"&terminal="+e:""):"1"==s?"/v3/search/shopresult.html?keyword="+wapFormatURL(trim(o)):void 0}function searchHistoryOrHotword(o){var r=buildresulturl(o,$("#channel").val(),$("#terminalFlag").val());document.location.href=r}$(function(){$("#hotwords").hide(),$("#hotwordsBox").hide(),$(".keywords-err").hide(),$("#historyList").hide(),$("#historyEmpty").hide(),$("#historyCleanSuccess").hide(),$("#historyClean").hide(),queryHotWrods(),queryHistory(),$("#wapSearchURL").on("click",function(){var o=$(".searchInp").val();if(!trim(o))return!1;addHistory(o);var r=buildresulturl(o,$("#channel").val(),$("#terminalFlag").val());document.location.href=r})});