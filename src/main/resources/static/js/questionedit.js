$(document).ready(function() {
    var search = $('#submitsearch');
    search.click(function() {
        var searchText = document.getElementById("searchtext").value;
        console.log(searchText);
        $.ajax({
            url: "/getAllQuestionsWithAnswers",
            success: function(result) {
            console.log(result);
                 for (var i = 0, keys = Object.keys(result), ii = keys.length; i < ii; i++) {

                 }
            }
        });
    });
});