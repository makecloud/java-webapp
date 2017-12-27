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
var rname = $('#rname');
var rpwd = $('#rpwd');
var rpwdSure=$('#rpwdSure');
var ruser=$('#ruser');
var rphone=$('#rphone');
var rcode = $('#verifyimg');

var rcodes = getCode();
var rcodespace = $('.rcode');
rcodespace.html(rcodes);
rcodespace.css({'color': rc(120, 255), 'background-color': rc(0, 120)});
rcodespace.click(function () {
  rcodespace.html(getCode());
  rcodespace.css({'color': rc(120, 255), 'background-color': rc(0, 120)});
});

//验证姓名.密码.验证码

var login_btn = $('.register_btn a');
var regName = /^[0-9a-zA-Z]{2,12}$/; //由字母和数字组成
var regPwd = /^[0-9]{6,12}$/;
var regPhone=/^1[34578]\d{9}$/;

var error = $('.error');
var error_text = $('.error .error_text');
login_btn.click(function (e) {
  if (!(rname.val()) || !regName.test(rname.val())) {
    e.preventDefault();
    error_text.html('用户名格式不正确!');
    error.css({'top': '132px'});
    error.stop().animate({'opacity': '1'}, 300);
    timer();
  } else if (!(rpwd.val()) || !regPwd.test(rpwd.val())) {
    e.preventDefault();
    error_text.html('密码格式不正确!');
    error.css('top', '177px');
    error.stop().animate({'opacity': '1'}, 300);
    timer();
  } else if(rpwd.val() != rpwdSure.val()){
    e.preventDefault();
    error_text.html('密码填写不一致!');
    error.css('top', '223px');
    error.stop().animate({'opacity': '1'}, 300);
    timer();
  }else if(!(ruser.val())){
    e.preventDefault();
    error_text.html('联系人姓名不能为空!');
    error.css('top', '271px');
    error.stop().animate({'opacity': '1'}, 300);
    timer();
  }else if(!(rphone.val()) || !regPhone.test(rphone.val())){
    e.preventDefault();
    error_text.html('手机号码格式不正确!');
    error.css('top', '318px');
    error.stop().animate({'opacity': '1'}, 300);
    timer();
  } else if (rcode.val().toUpperCase() != rcodespace.html().toUpperCase()) {
    e.preventDefault();
    error_text.html('验证码填写不正确!');
    error.css('top', '365px');
    error.stop().animate({'opacity': '1'}, 300);
    timer();
  } else {
    e.preventDefault();
    alert('填写正确!ajax后台检测是否存在该用户,是否成功注册!');
    //ajax请求后台验证用户名是否存在
    $.ajax({
      url: 'login',
      data: {
        rname: rname.val(),
        rpwd: rpwd.val(),
        ruser:ruser.val(),
        rphone:rphone.val()
      },
      type: 'post',
      //请求成功
      success: function (data) {
        //如果用户存在:提示用户直接去登录
        if (data.code == 0) {
          alert('该用户名已存在,为'+data.msg+',请直接登录!');
        } else if (data.code == 1) {//如果用户不存在:则成功写入数据库
          alert('恭喜!注册成功,请点击登录进入登录页面!');
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
    error.stop().animate({'opacity': '0'}, 300);
  }, 3000);
}

//获取短信验证码
var phonemsg=$('.phonemsg');
function countDown() {
  //判断手机号是否正确,正确则发送验证码
  if (!(rphone.val()) || !regPhone.test(rphone.val())) {
    error_text.html('手机号格式不正确!');
    error.css({'top': '318px'});
    error.stop().animate({'opacity': '1'}, 300);
    timer();
  }else{
    phonemsg.unbind('click');
    var ftime=10;
    var secondtime=setInterval(function () {
      ftime--;
      phonemsg.html(ftime+'s');
      if(ftime==-1){
        clearInterval(secondtime);
        phonemsg.html('重新获取');
        phonemsg.bind('click',countDown);
      }
    },1000);
  }
}
phonemsg.bind('click',countDown);