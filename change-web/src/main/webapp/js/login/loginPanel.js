$.widget("panel.loginPanel", {
    _create: function () {
        var self = this;
        var el = this.element;
        el.addClass('login-panel');
        this._createLogoContentBox();
        this._createMainBox();
    },
    _createLogoContentBox: function () {
        var el = this.element;
        this.logoContentBox = $('<div class="logo-content"></div>').appendTo(el);
        this._createLogo();
    },
    _createLogo: function () {
        this.logoBox = $('<div class="logo-box"></div>').appendTo(this.logoContentBox);
        var a = $('<a></a>').appendTo(this.logoBox);
        var logoImgSrc = this.options.logo.logoImgSrc;
        if (logoImgSrc) {
            var img = $('<img>').appendTo(a);
            img[0].src = logoImgSrc;
        }
        var textImgSrc = this.options.logo.textImgSrc;
        if (textImgSrc) {
            var textImgBox = $('<span class="text-img"></span>').appendTo(this.logoBox);
            var textImg = $('<img>').appendTo(textImgBox);
            textImg[0].src = textImgSrc;
        }
    },
    _createMainBox: function () {
        var el = this.element;
        this.mainBox = $('<div class="main-box"></div>').appendTo(el);
        this._createMainContent();
    },
    _createMainContent: function () { 
        this.mainContent = $('<div class="main-content clearfix"></div>').appendTo(this.mainBox);
        this.userBox = $('<div class="user pull-right"></div>').appendTo(this.mainContent);
        if (this.options.user.isTab) {
            this._createUserTab();
        } else {
            this._createUser();
        }
    },
    _createUserTab: function () {
        var self = this;
        var loginTab = $('<div class="login-tab"></div>').appendTo(this.userBox);
        var leftTab = $('<div class="left-tab pull-left active"></div>').appendTo(loginTab);
        leftTab.text(this.options.user.leftText);
        var rightTab = $('<div class="right-tab pull-right"></div>').appendTo(loginTab);
        rightTab.text(this.options.user.rightText);
        $('<div class="tab-line"></div>').appendTo(loginTab);
        leftTab.on('click', function () {
           $(this).addClass('active');
           rightTab.removeClass('active'); 
           self.rightTabContent.hide();
           self.leftTabContent.show();
        });
        rightTab.on('click', function () {
            $(this).addClass('active');
            leftTab.removeClass('active'); 
            self.leftTabContent.hide();
            self.rightTabContent.show();
         });

        this._createLeftTabContent();
        this._createRightTabContent();
    },
    _createLeftTabContent: function () {
        this.leftTabContent = $('<div class="left-tab-content"></div>').appendTo(this.userBox);
        var img = $('<img>').appendTo(this.leftTabContent);
        img[0].src = './images/login/show.png';
    },
    _createRightTabContent: function () {
        var self = this;
        this.rightTabContent = $('<div class="right-tab-content"></div>').appendTo(this.userBox);
        var errorMsg = $('<div class="error-message"><span class="iconfont icon-error-circle"></span><div class="text"></div></div>').appendTo(this.rightTabContent);
        var userName = $('<div class="user-name"></div>').appendTo(this.rightTabContent);
        var userNameInputBox = $('<div class="user-name-input-box"></div>').appendTo(userName);
        var userNameIcon = $('<span class="iconfont icon-username label"></span>').appendTo(userNameInputBox);
        var userNameinput = $('<input type="text">').appendTo(userNameInputBox);
        var userNameClose = $('<span class="iconfont icon-close close"></span>').appendTo(userNameInputBox);
        userNameinput[0].placeholder = this.options.userNamePlaceholder;
        userNameClose.on('click', function () {
            $(this).parent().children('input').val('');
        });

        var password = $('<div class="password"></div>').appendTo(this.rightTabContent);
        var passwordInputBox = $('<div class="password-input-box"></div>').appendTo(password);
        var passwordIcon = $('<span class="iconfont icon-password label"></span>').appendTo(passwordInputBox);
        var passwordinput = $('<input type="password">').appendTo(passwordInputBox);
        var passwordClose = $('<span class="iconfont icon-close close"></span>').appendTo(passwordInputBox);
        passwordinput[0].placeholder = this.options.passwordPlaceholder;
        passwordClose.on('click', function () {
            $(this).parent().children('input').val('');
        });

        var forgetPwdBox = $('<div class="forget-pwd-box clearfix"></div>').appendTo(this.rightTabContent);
        var forgetPwd = $('<a class="forget-pwd pull-right"></a>').appendTo(forgetPwdBox);
        forgetPwd.text(this.options.forgetPwdText);
        forgetPwd[0].href = this.options.forgetPwdSrc;

        var submitBtnBox = $('<div class="submit-btn-box"></div>').appendTo(this.rightTabContent);
        var submitBtn = $('<button>登&nbsp;&nbsp;&nbsp;&nbsp;录</button>').appendTo(submitBtnBox);
        submitBtn.on('click', function () {
            var account = userNameinput.val();
            var password = passwordinput.val();
            self.options.submitFuc(account, password);
        });
        this.rightTabContent.hide();
    },
    _createUser: function () {
        var title = $('<div class="login-title active"></div>').appendTo(this.userBox);
        title.text(this.options.user.text);
        this._createRightTabContent();
        this.rightTabContent.show();
    }
});