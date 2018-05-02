
/**
 * 
 * 基于layui的分页
 * @param {Object} params - 需要传入的参数obj
 * @param {string} params.model - ajax请求的模块
 * @param {string} params.method - ajax请求的方法
 * @param {object} params.data - ajax请求的数据
 * @param {string} params.elemId - 分页容器的id
 * @param {number} params.pageSize - 每页的个数
 * @param {number} params.pageNum - 当前页数
 * @param {function} params.success - 请求成功执行的方法
 * @param {object} params.laypage - laypage对象
 * @returns 
 */
function addPage(pageObj) {
    var pageNum = pageObj.pageNum || 1; //向服务端传的参数
    var url = ServerUtil.url() + pageObj.model + pageObj.method;
    var sendData = {};
    $.extend(sendData, pageObj.data);
    sendData.pageSize = pageObj.pageSize;
    sendData.pageNum = pageNum;
    $.ajax({
        type: "post",
        contentType: 'application/json; charset=UTF-8',
        url: url,
        data: JSON.stringify(sendData),
        dataType: "json",
        success: function (result) {
            pageObj.success && pageObj.success(result);
            //显示分页
            pageObj.laypage.render({
                elem: pageObj.elemId, //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
                count: result.count, //通过后台拿到的总页数
                limit: pageObj.pageSize,
                groups: 5,
                curr: pageNum, //当前页
                layout: ['prev', 'page', 'next', 'limit', 'skip'],
                theme: '#F03726',
                jump: function (obj, first) { //触发分页后的回调
                    if (!first) { //点击跳页触发函数自身，并传递当前页
                        addPage({
                            model: pageObj.model,
                            method: pageObj.method,
                            data: pageObj.data,
                            elemId: pageObj.elemId,
                            pageSize: obj.limit || pageObj.pageSize,
                            pageNum: obj.curr,
                            success: pageObj.success,
                            laypage:pageObj.laypage
                        });
                    }
                }
            });
        }
    });
};