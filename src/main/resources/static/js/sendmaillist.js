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
            var setCustomerHtml = "";
            if(retval.customer != null && retval.customer !=""){
                var customArr= retval.customer.split(",");
                var sendState= retval.state.split(",");
                for(var i =0 ; i < customArr.length; i++){
                    if(sendState[i] == "C"){
                        setCustomerHtml += "<span class='tag tag_c_modal tooltip-message'><i class='glyphicon glyphicon-tag'></i>"+customArr[i]+"</span>";
                    }else{
                        setCustomerHtml += "<span class='tag tag__modal tooltip-message'><i class='glyphicon glyphicon-tag'></i> "+customArr[i]+"</span>";
                    }
                }
            }

            $('.mail-form').html(retval.email_form);
            $('.send-customer').html(setCustomerHtml);

        }
    });
};