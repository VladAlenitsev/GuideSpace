$(document).ready(function() {
    var search = $('#submitsearch');
    var searchText = $('#searchtext').text;
    alert(searchText);
    search.click(function() {
        $.ajax({
            url: "/getAllQuestionsWithAnswers"
            success: function(result) {
            console.log(result);
                 for (var i = 0, keys = Object.keys(result), ii = keys.length; i < ii; i++) {
                    console.log(keys);
                    console.log('key : ' + keys[i] + ' val : ' + result[keys[i]]);
                 }
            }
        });
    });
});