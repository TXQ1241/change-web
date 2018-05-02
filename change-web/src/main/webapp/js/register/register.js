$('#register').registerPanel({
    logo: {
        // logoImgSrc: './images/login/logo.png',
        textImgSrc: '欢迎注册',
        rightText: '已有账号？请登录',
        rightTextSrc: './login.html'
    },
    user: [
        {
            label: '账号',
            placeholder: '您的账号和登录名',
            tip: '支持中文、字母、数字、"-"、"_"的组合，4-20个字符',
            isShow: true,
            inputId: 'account',
            type: 'text'
        },
        {
            label: '用户名',
            placeholder: '您的用户名',
            tip: '支持中文、字母、数字、"-"、"_"的组合，4-20个字符',
            isShow: true,
            inputId: 'userName',
            type: 'text'
        },
        {
            label: '设置密码',
            placeholder: '请输入密码',
            tip: '建议使用字母、数字和符号两种及以上的组合，6-20个字符',
            isShow: true,
            inputId: 'password',
            type: 'password'
        },
        {
            label: '确认密码',
            placeholder: '请再次输入密码',
            tip: '请再次输入密码',
            isShow: true,
            inputId: 'ackPassword',
            type: 'password'              
        },
        {
            label: '手机号',
            placeholder: '您的手机号',
            tip: '请输入手机号',
            isShow: true,
            inputId: 'phoneNum',
            type: 'text',
            reg: /^1[3|4|5|7|8][0-9]{9}$/,
            errorText: '请输入正确的手机号'          
        },
        {
            label: '邮箱',
            placeholder: '建议使用常用邮箱',
            tip: '您可以用该邮箱登录和找回密码',
            isShow: true,
            inputId: 'email',
            type: 'text',
            reg: /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/i,
            errorText: '请输入正确的邮箱'              
        },
        {
            label: '身份证号',
            placeholder: '您的身份证号',
            tip: '请输入身份证号',
            isShow: true,
            inputId: 'idCard',
            type: 'text'                 
        },
    ],
    submitBtnText: '立即注册',
    submitFuc: function (data) {
        if (data.password == data.ackPassword && data.isTrue) {
            delete data.ackPassword;
            data.userType = '2';
            ServerUtil.api('change-web/user/', 'save', data, function (data) {
                window.location.href = window.location.origin + '/change-web/login.html';
            });
        }
    }
});