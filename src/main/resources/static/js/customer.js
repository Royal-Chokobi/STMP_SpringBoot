$(document).ready(function () {
    $('#content').click(function(e){
        var $tgPoint = $(e.target);
        var $selectBtn = $tgPoint.hasClass('select-btn');
        var $selectArea = $tgPoint.hasClass('select-box');

        if ( !$selectBtn && !$selectArea ) {
            $('.animate-div').removeClass('animate-on');
            $('.animate-side-div').removeClass('animate-side-on');
        }
    });

    dataTablePage();
});

var dataTablePage =  function(){
    $('#dataTable').DataTable({
            "order": [[ 0, "desc" ]]
        });
};

var addNewCustomer = function () {
    var customer_name = $(".modal-body input[name=customer_name]").val();
    var manager_arr = new Array();
    var checked_val = true;

    if(!customer_name){
        alert("고객사명을 입력하세요.");
        return false;
    }

    $(".modal-body input[name=manager_name]").each(function(idx){
        var obj_info = new Object();
        obj_info.manager_name = $(".modal-body input[name=manager_name]:eq(" + idx + ")").val() ;
        obj_info.manager_email = $(".modal-body input[name=manager_email]:eq(" + idx + ")").val() ;

        if(!obj_info.manager_name || !obj_info.manager_email){
            checked_val = false;
            return false;
        }

        manager_arr.push(obj_info);
    });

    if(!checked_val){
        alert("고객 정보가 비어있습니다.");
        return false;
    }

    if (confirm("고객사를 등록하시겠습니까?")) {
        $.ajax({
            url: "/customer/createcustomer",
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            dataType: 'JSON',
            data: { customer_name : customer_name, manager_info : JSON.stringify(manager_arr)},
            type: 'POST',
            success: function(retval){
                alert(retval.message);
                location.reload();
            }
        });
    }
};

var addCustomerLine = function (id) {
    var cus_add = "<div class='row no-gutters label-form-group align-items-center m-3 add-cus-line'>" +
        "<div class='col-3'><input class='form-control bg-white' type='text' name='manager_name' placeholder='담당자' ></div>" +
        "<div class='col'><input class='form-control bg-white' type='text' name='manager_email' placeholder='email@catenoid.net' ></div>" +
        "<div class=\"col-auto\"><span class=\"tag tag__mobile tooltip-message pointer\" onclick='rmCustomerLine(this);' >삭제</span></div>" +
        "</div>";
    $('#'+id).append(cus_add);
};

var rmCustomerLine = function (e) {
    var clickedLine = $(e).parent().parent();
    clickedLine.remove();
};

var addCustomer = function () {
    var manager_arr = new Array();
    var cus_code = $("#sp_cus_code").text();

    if(!cus_code){
        alert("고객사 코드를 가져올 수 없습니다.");
        return false;
    }

    $("#customer_detail input[name=manager_name]").each(function(idx){
        var obj_info = new Object();
        obj_info.manager_name = $("#customer_detail input[name=manager_name]:eq(" + idx + ")").val() ;
        obj_info.manager_email = $("#customer_detail input[name=manager_email]:eq(" + idx + ")").val() ;

        if(obj_info.manager_name && obj_info.manager_email){
            manager_arr.push(obj_info);
        }
    });

    if (confirm("고객을 추가 하시겠습니까?")) {
        $.ajax({
            url: "/customer/addcustomer",
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            dataType: 'JSON',
            data: { customer_code : cus_code, manager_info : JSON.stringify(manager_arr)},
            type: 'POST',
            success: function(retval){
                alert(retval.message);
                location.reload();
            }
        });
    }
};

var detailCustomerInfo = function (customerCode, customerNM) {
    var has_on_cls = $('.animate-side-div').hasClass("animate-side-on");
    if(!has_on_cls){
        $('.animate-div').addClass("animate-on");
        $('.animate-side-div').addClass("animate-side-on");
    }

    $("#customer_nm").val(customerNM);
    $("#sp_cus_code").html(customerCode);
    var nm_edit = "<span class='tag tag__mail tooltip-message pointer' onclick=\"changeCustomerNM('"+customerCode+"')\">수정</span>";
    $("#cus_edit").html(nm_edit);
    $.ajax({
        url: "/customer/detailinfo",
        contentType: "application/x-www-form-urlencoded;charset=UTF-8",
        dataType: 'JSON',
        data: { customerCode : customerCode},
        type: 'POST',
        success: function(retval){
            var div_dt = "";
            for(var i = 0; i < retval.length; i++){
                div_dt += "<div class='row no-gutters label-form-group align-items-center m-3 add-cus-line '>";
                div_dt += "<div class='col-3'><input class='form-control bg-white' type='text' id='p_charge_"+retval[i].customer_index+"' placeholder='담당자' value='"+retval[i].customer_name+"'></div>";
                div_dt += "<div class='col'><input class='form-control bg-white' type='text' id='p_email_"+retval[i].customer_index+"' placeholder='email@catenoid.net' value='"+retval[i].customer_email+"' ></div>";
                div_dt += "<div class='col-auto'>";
                div_dt += "<span class='tag tag__mail tooltip-message pointer' onclick='editCustomerInfo(\""+retval[i].customer_index+"\")'>수정</span>";
                div_dt += "<span class='tag tag__mobile tooltip-message pointer' onclick='delCustomerInfo(\""+retval[i].customer_index+"\")'>삭제</span>";
                div_dt += "</div></div>";
            }
            $("#customer_detail").html(div_dt);
        }
    });
   /* $('.animate-div').toggleClass("animate-on");
    $('.animate-side-div').toggleClass("animate-side-on");*/

};

var changeCustomerNM = function(customerCode){
    console.log(customerCode);
    var customerNM = $("#customer_nm").val();
    if(!customerNM){
        alert('고객사를 입력하세요.');
        return false;
    }
    if (confirm("고객사명을 변경하시겠습니까?")) {
        $.ajax({
            url: "/customer/editname",
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            dataType: 'JSON',
            data: { customerCode : customerCode, customerNM : customerNM},
            type: 'POST',
            success: function(retval){
                alert(retval.message);
                location.reload();
            }
        });
    }
};

var editCustomerInfo = function (index) {
    var cus_name = $("#p_charge_"+index).val();
    var cus_email = $("#p_email_"+index).val();

    if(!cus_name || !cus_email){
        alert('변경할 항목을 입력하세요.');
        return false;
    }

    if (confirm("담당자 정보를 변경하시겠습니까?")) {
        $.ajax({
            url: "/customer/editcusinfo",
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            dataType: 'JSON',
            data: {index : index, cus_name : cus_name, cus_email : cus_email},
            type: 'POST',
            success: function(retval){
                alert(retval.message);
            }
        });
    }

};

var delCustomerInfo = function (index) {
    if (confirm("담당자 정보를 삭제하시겠습니까?")) {
        $.ajax({
            url: "/customer/delcusinfo",
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            dataType: 'JSON',
            data: {index : index},
            type: 'POST',
            success: function(retval){
                alert(retval.message);
                location.reload();
            }
        });
    }
};

var delCustomerCode = function (customerCode) {
    if (confirm("고객사 정보를 삭제하시겠습니까?\n연결된 담당자들 정보도 모두 삭제됩니다.")) {
        $.ajax({
            url: "/customer/delcustomer",
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            dataType: 'JSON',
            data: {customerCode : customerCode},
            type: 'POST',
            success: function(retval){
                alert(retval.message);
                location.reload();
            }
        });
    }
};
