// 推送消息
function getUnreadMessageCount() {
    $.ajax({
        url:'../../../../pushmsg',
        datatype:'get',
        success:function (response) {
            if(response.code==0){
                var count=0;
                if(Utils.isNotNull(response.data)){
                    count = response.data;
                    $('#msgInfo').html("<a href='javascript:void(0);' onclick='showMsgPage();'>"+count+"</a>");
                }else{
                    showErrorMsg(response.code, response.message);
                }
            }
        }
    });
}
