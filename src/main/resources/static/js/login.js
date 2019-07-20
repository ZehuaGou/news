function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0, index + 1) + "/";
    return result;
}

var login = {

    URL: {
        user: getContextPath() + "user/login",
        admin: getContextPath() + "admin/login"
    },

    userAjax: function (username, password) {

        var form = {
            'username': username,
            'password': password
        };

        $.ajax({
            type: 'POST',
            contentType: "application/json",
            url: login.URL.user,
            data: JSON.stringify(form),
            dataType: 'json',
            success: function (result) {
                if (result && result['success']) {
                    window.location.href = getContextPath() + "article/index";
                } else {
                    $("#message").text(result['message']);
                }
            },
        });
    },

    adminAjax: function (jobNumber, password) {
        var form = {
            'jobNumber': jobNumber,
            'password': password
        };

        $.ajax({
            type: 'POST',
            contentType: "application/json",
            url: login.URL.admin,
            data: JSON.stringify(form),
            dataType: 'json',
            success: function (result) {
                if (result && result['success']) {
                    window.location.href = getContextPath() + "admin/index";
                } else {
                    $("#message").text(result['message']);
                }
            },
        });
    }
};