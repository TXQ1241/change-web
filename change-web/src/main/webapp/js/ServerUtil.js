if (window.pageConfig == null) {
    window.pageConfig = {
        urlPrex: '',
    };
}
ServerUtil = {
    origin: function () {
        var u = window.location.origin;
        return u;
    },

    url: function () {
        var u = window.location.origin;
        u += pageConfig.urlPrex;
        if (!u.endsWith('/')) {
            u += '/';
        }
        return u;
    },

    api: function (model, method, data, success, error) {

        var url = ServerUtil.url() + model + method;
        $.ajax({
            type: "post",
            contentType: 'application/json; charset=UTF-8',
            url: url,
            data: JSON.stringify(data),
            dataType: "json",
            success: function (result) {
                success && success(result);
            },
            error: function (a, b, c) {
                if (error) {
                    error(a, b, c);
                } else {
                    console.error(a.responseText);
                }
            },
        })
    }
};
function getQueryVariable(variable)
{
       var query = window.location.search.substring(1);
       var vars = query.split("&");
       for (var i=0;i<vars.length;i++) {
               var pair = vars[i].split("=");
               if(pair[0] == variable){return pair[1];}
       }
       return(false);
}