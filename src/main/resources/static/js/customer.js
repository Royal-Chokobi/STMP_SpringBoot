var editCustomer = function (customerCode, customerNM) {
    
};

var detailCustomerInfo = function (customerCode, customerNM) {
    var has_on_cls = $('.animate-side-div').hasClass("animate-side-on");
    if(!has_on_cls){
        $.ajax({
            url: "/customer/detailinfo",
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            dataType: 'JSON',
            data: { customerCode : customerCode},
            type: 'POST',
            success: function(retval){
                console.log(retval);
                $("#customer_nm").val(customerNM);
                var div_dt = "";
                for(var i = 0; i < retval.length; i++){
                    div_dt += "<div class='card border-left-warning shadow'>";
                    div_dt += "<div class='row no-gutters label-form-group align-items-center m-3'>";
                    div_dt += "<div class='col'><input class='form-control bg-white' type='text' name='p_charge' disabled placeholder='담당자' value='"+retval[i].customer_name+"'></div>";
                    div_dt += "<div class='col-auto'><input class='form-control bg-white' type='text' name='p_email' disabled placeholder='email@catenoid.net' value='"+retval[i].customer_email+"' ></div>";
                    div_dt += "<div class='col-auto'>";
                    div_dt += "<span class='tag tag__mail tooltip-message'>수정</span><span class='tag tag__mobile tooltip-message' >삭제</span>";
                    div_dt += "</div></div></div>";
                }
                $("#customer_detail").html(div_dt);
            }
        });
    }
    $('.animate-div').toggleClass("animate-on");
    $('.animate-side-div').toggleClass("animate-side-on");

};