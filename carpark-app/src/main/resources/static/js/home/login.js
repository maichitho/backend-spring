/**
 * 
 */
 function doLogin(e) {
      var username = $("input[name='email']").val();
      var password = $("input[name='password']").val();
      $.ajax({
        url: "/member/authenticate",
        method: "POST",
        crossDomain: true,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
          "email": username,
          "password": password
        })
      }).done(function (resp) {
        if (resp.data != null) {
          localStorage.setItem('CarparkAuth', JSON.stringify(resp.data));
          window.location = "/web/page2";

        } else {
          if (username == '' || password == '') {
            document.getElementById("error").innerHTML = "IDもしくはパスワードが違います";
          }
          else {

            document.getElementById("error").innerHTML = "";
            if (resp.status == 4001) {
              document.getElementById("errorusername").innerHTML = "IDもしくはパスワードが違います";
            }
            else {
              document.getElementById("errorusername").innerHTML = "";
            }
            if (resp.status == 5000) {
              document.getElementById("errorpass").innerHTML = "間違ったパスワード";
            } else {
              document.getElementById("errorpass").innerHTML = "";
            }
          }
        }
      }).fail(function (resp) {

      });

    }