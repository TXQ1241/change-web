$.widget("panel.registerPanel", {
    isTrue: true,
    _create: function () {
        var self = this;
        var el = this.element;
        el.addClass('register-panel');
        this._createLogoContentBox();
    },
    _createLogoContentBox: function () {
        var el = this.element;
        this.logoContentBox = $('<div class="logo-content"></div>').appendTo(el);
        this._createLogo();
        this._createLine();
        this._createMainBox();
    },
    _createLogo: function () {
        this.logoBox = $('<div class="logo-box clearfix"></div>').appendTo(this.logoContentBox);
        var a = $('<a></a>').appendTo(this.logoBox);
        var logoImgSrc = this.options.logo.logoImgSrc;
        if (logoImgSrc) {
            var img = $('<img>').appendTo(a);
            img[0].src = logoImgSrc;
        }
        var textImgSrc = this.options.logo.textImgSrc;
        if (textImgSrc) {
            var textImgBox = $('<span class="text-img"></span>').appendTo(this.logoBox);
            if (textImgSrc.indexOf('/') >= 0) {
                var textImg = $('<img>').appendTo(textImgBox);
                textImg[0].src = textImgSrc;
            } else {
                var text = $('<span></span>').appendTo(textImgBox);
                text.text(this.options.logo.textImgSrc);
            }
        }

        if (this.options.logo.rightText) {
            var rightText = $('<a class="pull-right right-text"></a>').appendTo(this.logoBox);
            rightText.text(this.options.logo.rightText);
            if (this.options.logo.rightTextSrc) {
                rightText[0].href = this.options.logo.rightTextSrc;
            }
        }
    },
    _createLine: function () {
        var el = this.element;
        var line = $('<div class="line"></div>').appendTo(el);
    },
    _createMainBox: function () {
        var el = this.element;
        this.mainBox = $('<div class="main-box"></div>').appendTo(el);
        this._createMainContent();
    },
    _createMainContent: function () { 
        this.mainContent = $('<div class="main-content"></div>').appendTo(this.mainBox);
        this._createUserBox();
    },
    _createUserBox: function () {
        this.userBox = $('<div class="user"></div>').appendTo(this.mainContent);
        this.options.user.forEach(function (value) {
            this.createInput(value).appendTo(this.userBox);
        }, this);
        this.createSubmit();
    },
    createInput: function (data) {
        var self = this;
        var div = $('<div class="input-box"></div>');
        var label = $('<span class="label"></span>').appendTo(div);
        label.text(data.label);
        var input = $('<input>').appendTo(div);
        input[0].placeholder = data.placeholder;
        input[0].id = data.inputId;
        input[0].type = data.type;
        if (data.tip) {
            var tip = $('<div class="tip"></div>').appendTo(div);
            var iconfont = $('<span class="iconfont icon-username"></span>').appendTo(tip);
            var text = $('<p></p>').appendTo(tip);
            text.text(data.tip);
            tip.hide();
            input.on('focus', function () {
                tip.show();
            });
            input.on('blur', function () {
                tip.hide();
            });
        }
        if (data.reg) {
            var reg = data.reg;
            var errorTip = $('<div class="error_tip iconfont icon-warning"></div>').appendTo(div);
            errorTip.text(data.errorText);
            errorTip.hide();
            input.on('input propertychange', function () {
                if (reg.test(input.val())) {
                    errorTip.hide();
                    self.isTrue = true;
                } else {
                    self.isTrue = false;
                    errorTip.show();
                }
            });
        }
        if (!data.isShow) {
            div.hide();
        }
        return div;
    },
    createSubmit: function () {
        var self = this;
        this.submitBtn = $('<button class="submit-btn"></button>').appendTo(this.userBox);
        this.submitBtn.text(this.options.submitBtnText);
        this.submitBtn.on('click', function () {
            var data = {};
            self.options.user.forEach(function (value) {
                var val = $('#' + value.inputId).val();
                data[value.inputId] = val;
            });
            data.isTrue = self.isTrue;
            self.options.submitFuc(data);
        });
    }
});