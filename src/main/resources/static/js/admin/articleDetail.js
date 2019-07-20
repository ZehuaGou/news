var editor = null;

var articleDetail = {
    /**
     * 创建富文本编辑器editor对象
     */
    createEditor: function () {
        var E = window.wangEditor;
        //这里的id为<div id="editor">中的id.
        editor = new E('#editor');
        // 配置服务器端地址,也就是controller的请求路径，不带项目路径，前面没有/
        editor.customConfig.uploadImgServer = getContextPath() + 'admin/article/upload/image';
        //配置属性名称，绑定请求的图片数据
        //controller会用到，可以随便设置，但是一定要与controller一致
        editor.customConfig.uploadFileName = 'images';
        //关闭过滤掉复制文本中自带的样式
        editor.customConfig.pasteFilterStyle = false;

        //创建编辑器
        editor.create();
        //将数据库中内容显示到编辑器中
        editor.txt.html($("#content-hidden").val());
    },

    URL: {
        save: getContextPath() + "admin/article/" + $("#articleId").val() + "/update",
    },

    save: function () {

        var articleId = $("#articleId").val();
        var title = $("#title").val();
        var content = editor.txt.html();
        var oneCategoryId = $("#oneCategoryId").val();
        //图片路径数组
        var imgNames = [];

        $("#editor img").each(function () {
            var src = $(this).attr("src");
            imgNames.push(src);
        });

        var form = {
            articleId: articleId,
            title: title,
            content: content,
            oneCategoryId: oneCategoryId,
            imgNames: imgNames
        };

        $.ajax({
            type: 'POST',
            contentType: "application/json",
            url: articleDetail.URL.save,
            data: JSON.stringify(form),
            dataType: 'json',
            success: function (result) {
                if (result && result['code'] === 0) {
                    window.location.href = getContextPath() + "admin/article/" + articleId + "/detail";
                } else {
                    alert("保存失败！");
                }
            },
        });
    },
};