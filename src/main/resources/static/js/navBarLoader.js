
$(document).ready(function() {
    $.ajax({
        url :"/voteInsight/navBar",
        method :"GET",
        success : function (data) {

            $("#navBar").html(data);
            
        },
        error : function (e) {
            console.log(JSON.stringify(e));
        }
    });
    $.ajax({
        url :"/voteInsight/footer",
        method :"GET",
        success : function (data) {

            $("#footer").html(data);
            
        },
        error : function (e) {
            console.log(JSON.stringify(e));
        }
    });
});