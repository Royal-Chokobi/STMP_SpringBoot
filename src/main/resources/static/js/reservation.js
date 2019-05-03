var detailReservation = function(group_code, group_title) {

    $('#modalLabel').html("["+group_code+"]"+group_title);
    if(!group_code){
        alert("조회 가능한 Group_code가 없습니다.");
        return false;
    }

    $.ajax({
        url: "/getReservationDetail",
        contentType: "application/x-www-form-urlencoded;charset=UTF-8",
        dataType: 'JSON',
        data: { groupCdoe : group_code},
        type: 'POST',
        success: function(retval){
            var setResTbHtml = "";
            if(retval.length > 0){
                for(var i =0 ; i < retval.length; i++){
                    setResTbHtml += "<tr>";
                    setResTbHtml += "<td>"+retval[i].customer+"</td>";
                    setResTbHtml += "<td>"+retval[i].email_title+"</td>";
                    setResTbHtml +="<td>";
                    var customArr= retval[i].customer_address.split(",");
                    for(var j =0 ; j < customArr.length; j++){
                        setResTbHtml += "<span class='tag tag__mail tooltip-message'>"+customArr[j]+"</span>";
                    }
                    setResTbHtml +="</td>";
                    setResTbHtml += "<td>"+retval[i].state+"</td>";
                    setResTbHtml += "<td>";
                    if(retval[i].state == "R"){
                        setResTbHtml += "<span class='tag tag__mobile tooltip-message td_div' onclick='cancelResCustomer(\"one\", \""+group_code+"\", \""+retval[i].reservation_code+"\")'>취소</span>";
                    }
                    setResTbHtml += "</td>";
                    setResTbHtml += "</tr>";
                }
            }

            $('.res-Modal-List').html(setResTbHtml);
        }
    });
};

var cancelResCustomer = function (kind, group_code, res_code) {
    if(!res_code || !group_code){
        alert("취소 가능한 코드가 없습니다.");
        return false;
    }
    if (confirm("예약을 취소하시겠습니까?")) {
        $.ajax({
            url: "/cancelReservation",
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            dataType: 'JSON',
            data: {kind : kind, group_code : group_code, res_code : res_code},
            type: 'POST',
            success: function(retval){
                alert(retval.message);
                location.reload();
            }
        });
    }
};