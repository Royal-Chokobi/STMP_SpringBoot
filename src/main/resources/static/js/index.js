$(document).ready(function () {
    getMenuList();
});

function getMenuList() {
    var menulist = '<li class="nav-item"><a class="nav-link" href="/sendmailpage"><span>메일 등록</span></a></li>'+
                    '<li class="nav-item"><a class="nav-link" href="/customer"><span>고객사 리스트</span></a></li>'+
                    '<li class="nav-item "><a class="nav-link" href="/sendlist"><span>전송 리스트</span></a></li>'+
                    '<li class="nav-item "><a class="nav-link" href="/reservationList"><span>예약 리스트</span></a></li>';

    $('#sidebarMenu').html(menulist);
}