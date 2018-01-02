//导航菜单:hover:字体:蓝色+蓝色下划线
$(".nav_stairlist").bind("click", function () {
  $(this).children("a").addClass("on");
  $(this).siblings("li").children('a').removeClass("on");
});
//产品体系二级菜单
$('.pro').hover(function () {
  $('.pro_menu').css('display','block');
},function () {
  $('.pro_menu').css('display','none');
})



//banner:淡入淡出的轮播图
$(function () {
  var img_text = $(".img_text");//得到img和text外层的div
  var b_text = $("b_text");//得到所有图片上的两层文字外层的div
  var b_btn = $(".b_btn");
  var index =1;

  var autoChange = window.setInterval(function () {
    if (index < img_text.length - 1) {
      index = ++index;
    } else {
      index = 0;
    }
    change(index);
  }, 5000);

  //自动播放图片
  function change(num) {
    //传入的num为当前点击的数字按钮
    //所有的图片移除on_up,当前编号的图片添加on_up并且显示.
    img_text.eq(num).siblings(".img_text").children('.i_up').removeClass("on_up").stop(true).fadeOut(2000);
    img_text.eq(num).children(".i_up").addClass("on_up").stop(true).fadeIn(1500);
    b_btn.children("span").removeClass("on_btn").eq(num).addClass("on_btn");//123翻页按钮
    //当前编号对应的文字进入:下至上
    img_text.eq(num).children('.b_text').children(".b_text1").stop(true).animate({top: "0"}, 1500);
    img_text.eq(num).children('.b_text').children(".b_text2").stop(true).animate({top: "0"}, 1500);
    img_text.eq(num).children('.b_text').stop(true).fadeIn(1000);

    //其余的文字出去:上至下
    img_text.eq(num).siblings(".img_text").children('.b_text').children(".b_text1").stop(true).animate({top: "80px"}, 500);
    img_text.eq(num).siblings(".img_text").children('.b_text').children(".b_text2").stop(true).animate({top: "30px"}, 500);
    img_text.eq(num).siblings(".img_text").children('.b_text').stop(true).fadeOut(500);
  }

//点击哪个数字,哪个添加class:on_up,并且切换对应的图片和文字
  $(".b_btn>span").bind('click', function () {
      clearInterval(autoChange);
      var item = $(this).attr("index") - 1;
      console.log(item);
      index = item;
      change(index);
      autoChange = window.setInterval(function () {
        if (index < img_text.length - 1) {
          index = ++index;
        } else {
          index = 0;
        }
        change(index);//console.log(index);
      }, 5000);
    }
  );
//问题就是第一个文字不是从下至上进入(是从原位置淡入)
});





//合作伙伴:左向右的轮播图
$(function () {
  var partnerBox = $(".partner_box");//ul外层div
  var partnerSlide = $(".partner_slide");//ul
  var partnerBtn = $('.partner_btn');//1234按钮
  var p_span = $('.partner_btn>span');
  var firstimg = $(".partner_img").first().clone();
  var i = 0;
  var partner_timer = null;
  //将第一张图片追加到最后一张图片之后,总宽度=图片数量*图片长度
  partnerSlide.append(firstimg).width($(".partner_img").length * ($(".partner_img img").width()));
  //计时器,每3s调用一次partnerSlider函数
  var partner_timer = window.setInterval(function () {
    i++;
    if (i == $(".partner_img").length) {
      i = 1;
      partnerSlide.css({left: 0});
    }
    partnerSlider(i);
  }, 3000);
  //自动循环播放图片
  function partnerSlider(num) {
    partnerSlide.stop().animate({left: -num * 1200}, 500);
    if (i == $(".partner_img").length - 1) {
      p_span.eq(0).addClass('pbtnColor').siblings().removeClass('pbtnColor');
    } else {
      p_span.eq(num).addClass('pbtnColor').siblings().removeClass('pbtnColor');
    }
  }

  //点击按钮:click事件
  p_span.bind('click', function () {
    var pbtn = $(this).index();//返回当前元素的下标
    $(this).eq(pbtn).addClass('pbtnColor');
    $(this).eq(pbtn).siblings('span').removeClass('pbtnColor');
    clearInterval(partner_timer);
    i = pbtn;
    partnerSlider(i);
    partner_timer = window.setInterval(function () {
      i++;
      if (i == $(".partner_img").length) {
        i = 1;
      }
      partnerSlider(i);
    }, 3000);
  });

  //鼠标放上去:暂停轮播;鼠标移开:继续轮播
  partnerBox.hover(
    function () {
      clearInterval(partner_timer);
    },
    function () {
      partner_timer = setInterval(function () {
        i++;
        if (i == $(".partner_img").length) {
          i = 1;
          partnerSlide.css({left: 0});
        }
        partnerSlider(i);
      }, 3000)
    }
  );
});


//微信二维码
var wxShare=$('.wxShare');
function wxClick() {
  if (wxShare.hasClass('QR_dis')) {
    wxShare.removeClass('QR_dis');
  } else {
    wxShare.addClass('QR_dis');
  }
};
function QRClick() {
  wxShare.addClass('QR_dis');
}
//微博分享
function sharetosina(title, picurl) {
  var url = window.location.href;
  var sharesinastring = 'http://v.t.sina.com.cn/share/share.php?title=' + title + '&url =' + url + '&content=utf-8&sourceUrl=' + url + '&pic=' + picurl;
  window.open(sharesinastring, 'newwindow', 'height=400,width=400,top=100,left=100');
}
//分享到空间
function sharetoqqzone(title, summary, picurl) {
  var url = window.location.href ;
  var shareqqzonestring = 'http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url='
    + url + '&title=' + title + '&summary=' + summary + '&pics=' + picurl;
  window.open(shareqqzonestring, 'newwindow', 'height=400,width=400,top=100,left=100');
}
//分享到豆瓣
function sharetodb(title, picurl) {
  var url = window.location.href ;
  var sharedbstring = 'http://www.douban.com/recommend/?url='+ url + '&title=' + title;
  window.open(sharedbstring, 'newwindow', 'height=400,width=400,top=100,left=100');
}

//分享鼠标移上去变背景图
var wbbg=$('.wb');
var wxbg=$('.wx');
var qqbg=$('.qq');
var dbbg=$('.db');
wbbg.hover(function () {
  wbbg.css('background-position','-3311px 0');
},function () {
  wbbg.css('background-position','-3361px 0');
})
wxbg.hover(function () {
  wxbg.css('background-position','-3411px 0');
},function () {
  wxbg.css('background-position','-3461px 0');
})
qqbg.hover(function () {
  qqbg.css('background-position','-3211px 0');
},function () {
  qqbg.css('background-position','-3261px 0');
})
dbbg.hover(function () {
  dbbg.css('background-position','-3111px 0');
},function () {
  dbbg.css('background-position','-3161px 0');
})

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














