$(document).ready(function () {

    $("body").on("input propertychange", ".label-form-group", function(e) {
        $(this).toggleClass("label-form-group-with-value", !!$(e.target).val());
    }).on("focus", ".label-form-group", function() {
        $(this).addClass("label-form-group-with-focus");
    }).on("blur", ".label-form-group", function() {
        $(this).removeClass("label-form-group-with-focus");
    });

    $('#datepicker input').datepicker({
        daysOfWeekDisabled: "0,6",
       // daysOfWeekHighlighted: "0,6",
        daysOfWeekHighlighted: "1,2,3,4,5",
        todayHighlight: true,
        calendarWeeks: false,
        autoclose: true,
        format: "yyyy-mm-dd",
        language: "kr",
        startDate: '+1d'
    });

    //dataTablePage();

});

tinymce.init({
    selector: 'textarea#catenoidMailForm',
    height: 930,
    width: 1200,
    menubar: false,
    plugins: [
        "advlist autolink lists link image charmap print preview anchor",
        'searchreplace visualblocks code fullscreen',
        'insertdatetime media table paste code help wordcount'
    ],
    toolbar: 'undo redo | formatselect | bold italic backcolor | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | removeformat | help',
    content_css: [
        '//fonts.googleapis.com/css?family=Lato:300,300i,400,400i',
        '//www.tiny.cloud/css/codepen.min.css'
    ]
});
/*
var dataTablePage =  function(){
    $('#dataTable').DataTable();
};*/

var sendTextarea = function () {

    var mailForm = tinymce.activeEditor.getContent();
    var emailTitle = $("#emailTitle").val();
    var resDate = $("#resDatepicker").val();
    var cus_length = $('.cus-send-list')[0].children.length;
    var msg = "작성한 메일을 등록하시겠습니까?";
    var selectCusCode = "N";
    if(!emailTitle){
        alert("제목을 입력하세요.");
        return false;
    }

    if(!mailForm){
        alert("메일 내용을 입력하세요.");
        return false;
    }

    if(document.getElementById("reservationCheck").checked && !resDate){
        alert("전송 예약일을 입력하세요.");
        return false;
    }

    if(cus_length > 0){
        msg = "선택된 고객사에게만 메일을 전송 하시겠습니까?";
        var setCusCode = new Array();
        for(var i=0; i < cus_length; i++){
            setCusCode[i] = $('.cus-send-list')[0].children[i].title;
        }
        selectCusCode = setCusCode.toString();
    }

    if (confirm(msg)) {
        $.ajax({
            url: "/schedule/sendMail",
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            dataType: 'JSON',
            data: { mailForm : mailForm, title:emailTitle, resDate: resDate, selectCusCode: selectCusCode},
            type: 'POST',
            success: function(retval){
                alert(retval.message);
            }
        });
    }
};

var sendClose = function () {
    if (confirm("메일 등록을 취소하시겠습니까? 작성된 내용은 삭제 됩니다.")) {
        location.reload();
    }
};

var showpicker = function () {
    if(document.getElementById("reservationCheck").checked){
        $(".date > input").val("");
        $(".date").addClass("hide");
    }else{
        $(".date > input").val("");
        $(".date").removeClass("hide");
    }
};

var selectMailCustomer = function (e) {
    /*console.log($(e)[0].outerHTML);
    console.log($(e).parent()[0].className);*/
    var divClassName = $(e).parent()[0].className;
    divClassName = (divClassName == "cus-list") ? "cus-send-list" : "cus-list";
    $('.'+divClassName).append($(e)[0].outerHTML);
    $(e).remove();
};
/*
var test123 = function () {
    if($('.animate-side-div').hasClass('animate-side-on') === true){
        $('.animate-side-div').removeClass('animate-side-on');
        var cus_length = $('.cus-send-list')[0].children.length;
        if(cus_length > 0){
            for(var i=0; i < cus_length; i++){
                $('.cus-list').append($('.cus-send-list')[0].children[0].outerHTML);
                $('.cus-send-list')[0].children[0].remove();
            }
        }
    }else{
        $('.animate-side-div').addClass('animate-side-on');
    }
};*/
