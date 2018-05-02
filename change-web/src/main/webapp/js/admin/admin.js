//JavaScript代码区域
var $;
layui.use(['table'], function () {
    var table = layui.table;
    $ = layui.$;
    var laytpl = layui.laytpl;
    var element = layui.element;
    var tableTitle = {
        account: '账号',
        password: '密码',
        userName: '用户名',
        email: '邮箱',
        phoneNum: '手机号',
        idCard: '身份证号'
    };
    var userInfo;
    //获取用户信息
    ServerUtil.api('change-web/user/', 'getUserInfo', {}, function (data) {
        userInfo = data;
        $('#userLoginAccount').text(data.account);
        $('#userLogin').show();
    });
    $('#changeUserInfo').on('click', function () {
        var userList = [];
        for (var attr in userInfo) {
            if (tableTitle[attr]) {
                var dataObj = {};
                dataObj.title = tableTitle[attr];
                dataObj.val = userInfo[attr] || '';
                dataObj.field = attr;
                dataObj.className = 'table-edit-input';
                userList.push(dataObj);
            }
        }
        var getTpl = tableEdit.innerHTML,
            view = document.getElementById('tableBox');
        laytpl(getTpl).render(userList, function (html) {
            view.innerHTML = html;
        });
        layer.open({
            title: '修改信息',
            type: 1,
            skin: 'layui-layer-molv layer-btn-class',
            resize: false,
            btn: ['确定', '取消'],
            yes: function (index, layero) {
                //按钮【按钮一】的回调
                $('.table-edit-input').each(function (index, val) {
                    userInfo[val.dataset.type] = $(val).val();
                });
                ServerUtil.api('change-web/user/', 'save', userInfo, function () {
                    layer.close(index);
                });
            },
            btn2: function (index, layero) {
                //按钮【按钮二】的回调
                layer.close(index);
            },
            content: $('#tableBox')
        });
    });
    $('#logout').on('click', function () {
        window.location.href = window.location.origin + '/change-web/login/logout';
    });
    //第一个实例
    table.render({
        elem: '#datalist',
        // height: 315,
        url: window.location.origin + '/change-web/user/userList',
        method: 'post',
        response: {
            statusCode: 1,
            dataName: 'datalist'
        },
        request: {
            pageName: 'pageNum', //页码的参数名称，默认：page
            limitName: 'pageSize', //每页数据量的参数名，默认：limit
            page: 0,
            limit: 10
        },
        where: {
            userType: 1
        },
        page: {
            limits: [5, 10, 20, 50, 100]
        },
        id: 'poemUsers',
        done: function (res) {

        },
        cols: [
            [ //表头
                {
                    type: 'checkbox'
                },
                {
                    field: 'account',
                    title: '账号',
                    // width: 150
                }, {
                    field: 'password',
                    title: '密码',
                    width: 80
                }, {
                    field: 'userName',
                    title: '用户名',
                    // width: 150
                }, {
                    field: 'email',
                    title: '邮箱',
                    // width: 200
                }, {
                    field: 'phoneNum',
                    title: '手机号',
                    sort: true,
                    // width: 200
                }, {
                    field: 'idCard',
                    title: '身份证号',
                    sort: true,
                    // width: 200
                }, {
                    fixed: 'right',
                    title: '操作',
                    minWidth: 163,
                    // width: 178,
                    align: 'center',
                    toolbar: '#tableBtn'
                }
            ]
        ]
    });
    //表格重载函数
    function tableReload(conditions) {
        var obj = {
            page: {
                curr: 1 //重新从第 1 页开始
            }
        };
        if (conditions) {
            obj.where = conditions;
        }
        table.reload('poemUsers', obj);
    }
    //查看、编辑、删除按钮功能
    table.on('tool(tableBtn)', function (obj) {
        var data = obj.data;
        if (obj.event === 'detail') {
            var userList = [];
            for (var attr in data) {
                if (tableTitle[attr]) {
                    var dataObj = {};
                    dataObj.title = tableTitle[attr];
                    dataObj.val = data[attr] || '';
                    userList.push(dataObj);
                }
            }
            var getTpl = tableDetail.innerHTML,
                view = document.getElementById('tableBox');
            laytpl(getTpl).render(userList, function (html) {
                view.innerHTML = html;
            });
            layer.open({
                title: '用户详情',
                type: 1,
                skin: 'layui-layer-molv',
                shadeClose: true,
                resize: false,
                // area: ['500px', '300px'],
                content: $('#tableBox')
            });
        } else if (obj.event === 'del') {
            layer.confirm('真的删除么', {
                skin: 'layui-layer-molv'
            }, function (index) {
                ServerUtil.api('change-web/user/', 'delete', {
                    ids: data.id
                }, function () {
                    // obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    tableReload();
                    layer.close(index);
                });
            });
        } else if (obj.event === 'edit') {
            var userList = [];
            for (var attr in data) {
                if (tableTitle[attr]) {
                    var dataObj = {};
                    dataObj.title = tableTitle[attr];
                    dataObj.val = data[attr] || '';
                    dataObj.field = attr;
                    dataObj.className = 'table-edit-input';
                    userList.push(dataObj);
                }
            }
            var getTpl = tableEdit.innerHTML,
                view = document.getElementById('tableBox');
            laytpl(getTpl).render(userList, function (html) {
                view.innerHTML = html;
            });
            layer.open({
                title: '编辑',
                type: 1,
                skin: 'layui-layer-molv layer-btn-class',
                resize: false,
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    //按钮【按钮一】的回调
                    $('.table-edit-input').each(function (index, val) {
                        data[val.dataset.type] = $(val).val();
                    });
                    ServerUtil.api('change-web/user/', 'save', data, function () {
                        //同步更新缓存对应的值
                        obj.update(data);
                        // tableReload();
                        layer.close(index);
                    });
                },
                btn2: function (index, layero) {
                    //按钮【按钮二】的回调
                    layer.close(index);
                },
                content: $('#tableBox')
            });
        }
    });

    //按条件搜索
    $('#searchBtn').on('click', function () {
        // var type = $(this).data('type');
        var obj = {};
        var account = $('#accountReload').val();
        var userName = $('#userNameReload').val();
        var age = $('#ageReload').val();
        obj.account = account;
        obj.userName = userName;
        obj.age = age;
        obj.userType = 1;
        tableReload(obj);
    });
    //批量删除
    $('#deleteUsers').on('click', function () {
        var checkStatus = table.checkStatus('poemUsers'); //获取复选框信息
        if (checkStatus.data.length == 0) {
            layer.confirm('请选择要删除的行');
            return;
        }
        var str = '确定删除这' + checkStatus.data.length + '条信息吗';
        layer.confirm(str, function (index) {
            var userIdsArr = [];
            checkStatus.data.forEach(function (val) {
                userIdsArr.push(val.id);
            });
            var userIdsStr = userIdsArr.join(',');
            ServerUtil.api('change-web/user/', 'delete', {
                ids: userIdsStr
            }, function () {
                layer.close(index);
                tableReload();
            });
        });
    });
    //新增
    $('#addUser').on('click', function () {
        var userList = [];
        var obj = {};
        for (var attr in tableTitle) {
            obj[attr] = '';
            var dataObj = {};
            dataObj.title = tableTitle[attr];
            dataObj.val = '';
            dataObj.field = attr;
            dataObj.className = 'table-add-input';
            userList.push(dataObj);
        }
        var getTpl = tableEdit.innerHTML,
            view = document.getElementById('tableBox');
        laytpl(getTpl).render(userList, function (html) {
            view.innerHTML = html;
        });
        layer.open({
            title: '新增',
            type: 1,
            skin: 'layui-layer-molv layer-btn-class',
            resize: false,
            btn: ['确定', '取消'],
            yes: function (index, layero) {
                //按钮【按钮一】的回调
                $('.table-add-input').each(function (index, val) {
                    obj[val.dataset.type] = $(val).val();
                });
                obj.userType = '1';
                ServerUtil.api('change-web/user/', 'save', obj, function () {
                    tableReload();
                    layer.close(index);
                });
            },
            btn2: function (index, layero) {
                //按钮【按钮二】的回调
                layer.close(index);
            },
            content: $('#tableBox')
        });
    });

    //菜单跳转
    $('#averageUser').on('click', function () {
        window.location.href = window.location.origin + window.location.pathname + '?userType=2';
    });
    $('#goosEdit').on('click', function () {
        window.location.href = window.location.origin + window.location.pathname + '?userType=3';
    });
    $('#loginLog').on('click', function () {
        window.location.href = window.location.origin + window.location.pathname + '?userType=4';
    });
    $('#passworkChangeLog').on('click', function () {
        window.location.href = window.location.origin + window.location.pathname + '?userType=5';
    });
});