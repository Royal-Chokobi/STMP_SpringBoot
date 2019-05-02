var detailSendMail = function(group_code, group_title) {

    $('#modalLabel').html("["+group_code+"]"+group_title);
    if(!group_code){
        alert("조회 가능한 Group_code가 없습니다.");
        return false;
    }

    $.ajax({
        url: "/getSendMailDetail",
        contentType: "application/x-www-form-urlencoded;charset=UTF-8",
        dataType: 'JSON',
        data: { groupCdoe : group_code},
        type: 'POST',
        success: function(retval){
            $('.mail-form').html(retval.email_form);
            $('.send-customer').html(retval.customer);
        }
    });
};