
//新闻
var pageSize = 5;//每页显示的条目
$(document).ready(function () {
  $('.news_menu_list li a').click(function () {
    $(this).addClass('menubg');
    $(this).parent('li').siblings('li').find('a').removeClass('menubg');
  });
  queryNews(1);
});
function queryNews(pageIndex, categoryId) {
  var data = {};
  if (typeof (categoryId) == "undefined" || categoryId == null) {
  } else {
    data['categoryId'] = categoryId;
  }

  if (typeof (pageIndex) == "undefined" || pageIndex == null) {
    pageIndex = 1;
  }
  data['startIndex'] = (pageIndex - 1) * pageSize; //0
  data['pageSize'] = pageSize; //5


  $.ajax({
    type: 'post',
    url: 'http://www.oohlink.com/home/queryArticle',
    data: data,
    dataType: 'jsonp',
    jsonp: "callback",
    cache: false,//第二次请求直接从缓存中请求,不用再到服务器
    async: false, // false 异步
    beforeSend: function () {
      // alert("sss");
    },
    success: function (response) {
      alert(response);
      if (response.code == 0) {
        $(".news_main_list li").remove();
        $("#pageDiv_News").html("");

        var count = response.data.totalCount;
        alert(count);
        if (count == null || count == 0) {
          return false;
        }
        var list = response.data.list;
        if (list != null && list.length > 0) {
          for (var i in list) {
            var html = "<li><dl><dt class='newsl'>";
            html += "<a href='news-center-details.html?id=" + list[i].id + "'>";
            html += "<img src='" + list[i].titlePic + "'>";
            html += "</a>";
            html += "</dt>";

            html += "<dd>";
            html += "<h4>";
            html += "<a href='news-center-details.html?id=" + list[i].id + "'>" + list[i].title + "</a>";
            html += "</h4>";
            html += "<div class='time'>" + list[i].pushTime + "</div>";
            html += "<p class='sdhappy'>";
            html += "<a href='news-center-details.html?id=" + list[i].id + "'>" + list[i].description + "</a>";
            html += "</p>";
            html += "</dd></dl></li>";
            console.log(html);
            $('.news_main_list').append(html);
          }
        }
      }
      layui.use(['laypage', 'layer'], function () {
        var laypage = layui.laypage, layer = layui.layer;//获取模块
        laypage.render({
          elem: 'pageDiv_News',
          curr: pageIndex || 1,//起始页:一般用于刷新类型的跳页以及HASH跳页。
          count: 10,  //数据总数，从服务端得到
          prev: '<em><</em>',
          next: '<em>></em>',
          skip: true, // //是否开启跳页
          groups: 5, //连续显示分页数
          jump: function (obj, first) {//触发分页后的回调
            //obj包含了当前分页的所有参数:例如:
            //得到当前页，以便向服务端请求对应页的数据。
            //console.log(obj.curr);
            //得到每页显示的条数
            //console.log(obj.limit);
            //首次不执行,一定要加此判断，否则初始时会无限刷新
            if (!first) {
              queryNews(obj.curr, categoryId);
            }
          }
        });
      });
      //showErrorMsg(response.code,response.message);
    },
    error: function (err) {
      console.log(err);
      return false;
    }
  });


}



//右侧悬浮
$('#right_xf span').hover(
    function () {
        $(this).children('a').css('background', '#007fff');
        $(this).children('.xf_c').stop(false,true).animate({'left': '-160px'}, 500);
        $(this).siblings('span').children('.xf_c').stop(false,true).animate({'left': '0'}, 1000);
    }, function () {
        $(this).children('a').css('background', 'rgb(13, 31, 65)');
        $(this).children('.xf_c').stop(false,true).animate({'left': '0'}, 500);
    }
)

















































