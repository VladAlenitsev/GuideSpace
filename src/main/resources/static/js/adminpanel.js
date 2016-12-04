$(document).ready(function() {

    $.ajax({url: "/getPersons", success: function(result){
        for (i = 0; i < result.length; i++) {
            var opt = document.createElement('option');
            opt.value = result[i].id;
            opt.innerHTML = result[i].username;
            $("#userSelection").append(opt);
        }
    }});
    $('#userSelection').change(function() {
        var username = $( "#userSelection option:selected" ).text();
        $.ajax({
            url: "/getPerson/"+username,
            success: function (result) {
                $('#username').text(result["username"]);
                $('#name').text(result["name"]);
                $('#surname').text(result["surname"]);
                $('#emailAddress').text(result["emailAddress"]);
                $('#userBirthDate').text(new Date(result["userBirthDate"]).customFormat("#DD#/#MM#/#YYYY#"));
                $('#certWorkLangs').text(result["certWorkLangs"]);
                $('#active_cert_location').text(result["active_cert_location"]);
                $('#cert_exp_date').text(new Date(result["cert_exp_date"]).customFormat("#DD#/#MM#/#YYYY#"));
                $('#user_role_id').text(translateRole(result["user_role_id"]));
                $('#registered_exam_id').text(result["registered_exam_id"]);
                $('#exam_register_date').text(result["exam_register_date"]);
            }
        });
    });

    var submit = $('#changePermissions');
    submit.click(function() {
        var permissionSelection = $('#permissionSelection');
        var map = userSel();
        var role = $('#permissionSelection').find(":selected").text();
        var cookie = JSON.parse($.cookie('CSRF'));
        $.ajax({
         data: JSON.stringify(map),
         headers: {'X-CSRF-TOKEN': cookie.csrf},
         contentType: 'application/json',
         timeout: 5000,
         type: 'POST',
         url: perSel(),
         success: function(data){
            $('#user_role_id').text(role);
            $.notify("User right has been successfully changed", "success");
         },
         error: function(errorThrown){
            $.notify("There has been a problem with submitting the change", "error");
         }
        })
    });
});

function translateRole(roleId){
    if(roleId=="2") return "Admin";
    if(roleId=="1") return "Unverified user";
    if(roleId=="6") return "Info ADDER";
    if(roleId=="4") return "Verified user";
    else return "No role id found. " + roleId;
}

function perSel(){
    return $('#permissionSelection').val();
}
function userSel(){
    return $('#userSelection').val().valueOf();
}

Date.prototype.customFormat = function(formatString){
  var YYYY,YY,MMMM,MMM,MM,M,DDDD,DDD,DD,D,hhhh,hhh,hh,h,mm,m,ss,s,ampm,AMPM,dMod,th;
  YY = ((YYYY=this.getFullYear())+"").slice(-2);
  MM = (M=this.getMonth()+1)<10?('0'+M):M;
  MMM = (MMMM=["January","February","March","April","May","June","July","August","September","October","November","December"][M-1]).substring(0,3);
  DD = (D=this.getDate())<10?('0'+D):D;
  formatString = formatString.replace("#YYYY#",YYYY).replace("#YY#",YY).replace("#MMMM#",MMMM).replace("#MMM#",MMM).replace("#MM#",MM).replace("#M#",M).replace("#DDDD#",DDDD).replace("#DDD#",DDD).replace("#DD#",DD).replace("#D#",D).replace("#th#",th);
  h=(hhh=this.getHours());
  if (h==0) h=24;
  if (h>12) h-=12;
  hh = h<10?('0'+h):h;
  hhhh = hhh<10?('0'+hhh):hhh;
  return formatString.replace("#hhhh#",hhhh).replace("#hhh#",hhh).replace("#hh#",hh).replace("#h#",h).replace("#mm#",mm).replace("#m#",m).replace("#ss#",ss);
};