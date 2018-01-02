// 推送消息
function getUnreadMessageCount() {
    alert("开始");
    $.ajax({
        url:'../../../../../pushmsg',
        datatype:'get',
        beforeSend:function () {
            alert(111);
        },
        success:function (response) {
            if(response.code==0){
                var count=0;
                if(response.data!=null){
                    console.log(response.data);
                    count = response.data;
                    $('#msgInfo').html("<a href='javascript:void(0);' onclick='showMsgPage();'>"+count+"</a>");
                }else{
                    console.log(response.code, response.message);
                }
            }
        },
        error:function (response) {
            console.log('系统错误！');
        }
    });
    alert('结束');
}
getUnreadMessageCount();