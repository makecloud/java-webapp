//验证码
function rnum(min, max) {
  return Math.floor(Math.random() * (max - min + 1) + min);
}
function rc(min, max) {
  var r = rnum(min, max);
  var g = rnum(min, max);
  var b = rnum(min, max);
  return 'rgb(' + r + "," + g + "," + b + ')';
//    return `rgb(${r},${g},${b})`;
}
var chars = [];
var i;
for (i = 48; i < 57; i++) {
  chars.push(String.fromCharCode(i))
}
for (i = 65; i < 90; i++) {
  chars.push(String.fromCharCode(i))
}
for (i = 97; i < 122; i++) {
  chars.push(String.fromCharCode(i))
}
function getCode() {
  var codes = '';
  while (codes.length < 4) {
    var rn = Math.floor(Math.random() * (chars.length));
    codes += chars[rn];
  }
  return codes;
}
var codes = getCode();
var codespace = $('.code_click');
codespace.html(codes);
codespace.css({'color': rc(120, 255), 'background-color': rc(0, 120)});
$('.code_click').click(function () {
  codespace.html(getCode());
  codespace.css({'color': rc(120, 255), 'background-color': rc(0, 120)});
})

//记住密码
var r_label = $('.r_pwd_btn');
var r_bg = $('.r_pwd_btn i');
r_label.click(function () {
  if (r_bg.hasClass('r_pwd_yes')) {
    r_bg.removeClass('r_pwd_yes');
  } else {
    r_bg.addClass('r_pwd_yes');
  }
});

//检查是否有cookie
var uname = $('#uname');
var upwd = $('#upwd');
var cookieName = 'username';
var cookiePwd = 'userpwd';
if ($.cookie(cookieName) && $.cookie(cookiePwd)) {
  uname.val($.cookie(cookieName));
  upwd.val($.cookie(cookiePwd));
}

//验证姓名.密码.验证码
var ucode = $('#ucode');
var login_btn = $('.login_btn a');
var regName = /^[0-9a-zA-Z]{2,12}$/; //由字母和数字组成
var regPwd = /^[0-9]{6,12}$/;
var error = $('.error');
var error_text = $('.error .error_text');
login_btn.click(function (e) {
  if (!(uname.val()) || !regName.test(uname.val())) {
    e.preventDefault();
    error_text.html('用户名不正确!');
    error.css({'top': '140px'});
    error.stop().animate({'opacity': '1'}, 300);
    timer();
  } else if (!(upwd.val()) || !regPwd.test(upwd.val())) {
    e.preventDefault();
    error_text.html('密码填写不正确!');
    error.css('top', '208px');
    error.stop().animate({'opacity': '1'}, 300);
    timer();
  } else if (ucode.val().toUpperCase() != codespace.html().toUpperCase()) {
    e.preventDefault();
    error_text.html('验证码填写不正确!');
    error.css('top', '275px');
    error.stop().animate({'opacity': '1'}, 300);
    timer();
  } else {
    e.preventDefault();
    alert('填写正确!ajax后台检测是否有该用户,密码是否正确!');
    //ajax请求后台验证用户名是否存在,密码是否正确
    //如果密码正确
    $.ajax({
      url: 'platform/login',
      data: {
        uname: uname.val(),
        upwd: upwd.val()
      },
      type: 'post',
      //请求成功
      success: function (data) {
        //如果用户不存在:提示用户不存在;如果用户存在:则判断密码正确性
        if (data.code == 0) {
          e.preventDefault();
          alert('该用户名不存在,请先去注册!');
        } else if (data.code == 1) {//存在:密码不正确,则提示密码不正确
          e.preventDefault();
          error_text.html('密码不正确,请重新输入!');
          error.css('top', '358px');
          error.animate({'opacity': '1'}, 300);
          timer();
        }else{
          //密码正确,则判断是否勾选了记住密码:如勾选,则把用户名密码写入cookie
          if (r_bg.hasClass('r_pwd_yes')) {
            $.cookie('username', uname.val(), {expires: 30});
            $.cookie('userpwd', upwd.val(), {expires: 30});
          } else {
            $.cookie('username', null);
            $.cookie('userpwd', null);
          }
        }
      },
      error:function () {
        e.preventDefault();
        alert('网络繁忙,请稍后再试!');
      }

    });

  }

});
function timer() {
  setTimeout(function () {
    error.stop(true).animate({'opacity': '0'}, 300);
  }, 5000);

}









