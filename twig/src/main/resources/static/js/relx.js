
function checkPassword() {
    var password = document.getElementById("password").value;
    var password0 = document.getElementById("password0").value;

    if(password==password0){
        document.getElementById("notice").innerHTML="<br><font color='#7fffd4'>RIGHT</font>";
        document.getElementById("submit").disabled=false;
    } else{
        document.getElementById("notice").innerHTML="<br><font color='#dc143c'>Wrong</font>";
        document.getElementById("submit").disabled=true;
    }
}

function addPa(addPaId){
    var commentId = "commentId" + addPaId;
    var img1Id = "img1" + addPaId;
    var img2Id = "img2" + addPaId;
    $.ajax({
        //send request
        url:"/addPa",
        data:{"commentId":$("#"+commentId).val()},
        //gain back-end information
        success:function(data) {
            var str = data.toString();
            var subStr = str.split("-");
            if (subStr[0] == "Success") {
                $("#"+addPaId).text(subStr[1]);
                alert("Success!!!");
                //thumbs up image shows and hides
                var img1=document.getElementById(img1Id);
                var img2=document.getElementById(img2Id);
                img1.setAttribute("hidden",true);
                img2.removeAttribute("hidden");
            } else {
                alert("Fail!!!");
            }
        }
    })
}

function subPa(subPaId) {
    var commentId = "commentId" + subPaId;
    var img1Id = "img1" + subPaId;
    var img2Id = "img2" + subPaId;
    alert(commentId);
    $.post({
        url: "/subPa",
        data: {"commentId": $("#" + commentId).val()},
        success: function (data) {
            var str = data.toString();
            var subStr = str.split("-");
            if (subStr[0] == "Success") {
                $("#" + subPaId).text(subStr[1]);
                alert("Success!!!");
                var img1=document.getElementById(img1Id);
                var img2=document.getElementById(img2Id);
                img2.setAttribute("hidden",true);
                img1.removeAttribute("hidden");
            } else {
                alert("Fail!!!");
            }
        }
    })
}

$(function () {
    $('#connectMer').bind('click',function () {
        if ($('.merEmail').is(':visible')){
            $('.merEmail').hide()
        }else {
            $('.merEmail').show()
        }
    })
})




