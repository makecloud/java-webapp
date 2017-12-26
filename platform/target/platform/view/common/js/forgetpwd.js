var fphone=$('#fphone');
var fnewpwd = $('#fnewpwd');
var fpwdSure=$('#fpwdSure');
var regPwd = /^[0-9]{6,12}$/;
var regPhone=/^1[34578]\d{9}$/;
var sureChange=$('.sureChange');

var error = $('.error');
var error_text = $('.error .error_text');
sureChange.click(function (e) {
  if (!(fphone.val()) || !regPhone.test(fphone.val())) {
    e.preventDefault();
    error_text.html('手机号格式不正确!');
    error.css({'top': '128px'});
    error.stop().animate({'opacity': '1'}, 300);
    timer();
  } else if (!(fnewpwd.val()) || !regPwd.test(fnewpwd.val())) {
    e.preventDefault();
    error_text.html('密码格式不正确!');
    error.css('top', '224px');
    error.stop().animate({'opacity': '1'}, 300);
    timer();
  } else if(fnewpwd.val() != fpwdSure.val()){
    e.preventDefault();
    error_text.html('密码填写不一致!');
    error.css('top', '271px');
    error.stop().animate({'opacity': '1'}, 300);
    timer();
  } else {
    e.preventDefault();
    alert('填写正确!ajax后台检测是否存在该用户,是否成功修改密码!');
    //ajax请求后台验证用户名是否存在
    $.ajax({
      url: 'aa',
      data: {
        fphone: fphone.val(),
        fnewpwd: fnewpwd.val()
      },
      type: 'post',
      //请求成功
      success: function (data) {
        //如果用户存在,后台修改对应的密码,修改密码成功返回0:
        if (data.code == 0) {
          alert('密码修改成功!请直接登录!');
        } else if (data.code == 1) {//如果用户不存在,返回1
          alert('该用户不存在,请先去注册!');
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
  if (!(fphone.val()) || !regPhone.test(fphone.val())) {
    error_text.html('手机号格式不正确!');
    error.css({'top': '282px'});
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


























