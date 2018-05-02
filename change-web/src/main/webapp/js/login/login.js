$('#login').loginPanel({
    logo: {
        // logoImgSrc: './images/login/logo.png',
        textImgSrc: './images/login/l-icon.png'
    },
    // user: {
    //     isTab: true,
    //     leftText: '扫码登录',
    //     rightText: '账户登录'
    // },
    user: {
        isTab: false,
        text: '欢迎登陆'
    },
    userNamePlaceholder: '用户名/邮箱',
    passwordPlaceholder: '密码',
    forgetPwdText: '忘记密码',
    forgetPwdSrc: 'javascript:void(0)',
    submitFuc: function (account, password) {
        ServerUtil.api('change-web/login/', 'login', {
            account: account,
            password: password
        }, function (data) {
            if (data && data.status && data.status == '1') {
                if (data.userType == '0') {
                    window.location.href = window.location.origin + '/change-web/user/view';
                } else {
                    window.location.href = window.location.origin + '/change-web/index.html';
                }
            } else {
                $('.error-message').show();
                $('.error-message .text').text(data.msg);
            }
        });
    }
});