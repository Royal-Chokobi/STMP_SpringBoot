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

var sendTextarea = function () {

    var mailForm = tinymce.activeEditor.getContent();
    var emailTitle = $("#emailTitle").val();

    if(!emailTitle){
        alert("제목을 입력하세요.");
        return false;
    }

    if(!mailForm){
        alert("메일 내용을 입력하세요.");
        return false;
    }

    $.ajax({
        url: "/sendMail",
        contentType: "application/x-www-form-urlencoded;charset=UTF-8",
        dataType: 'JSON',
        data: { mailForm : mailForm, title:emailTitle},
        type: 'POST',
        success: function(retval){
            //console.log(retval);
            alert(retval.message);
        }
    });
};

var sendClose = function () {
    if (confirm("메일 등록을 취소하시겠습니까? 작성된 내용은 삭제 됩니다.")) {
        location.reload();
    }
};

(function($) {
    $(function() {
        $("body").on("input propertychange", ".label-form-group", function(e) {
            $(this).toggleClass("label-form-group-with-value", !!$(e.target).val());
        }).on("focus", ".label-form-group", function() {
            $(this).addClass("label-form-group-with-focus");
        }).on("blur", ".label-form-group", function() {
            $(this).removeClass("label-form-group-with-focus");
        });
    });

})(jQuery);