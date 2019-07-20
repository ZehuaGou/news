var topSearch = {
    search :function () {
        var keyWords = $("#keyWords").val();

        window.location.href = getContextPath() + "admin/article/" + keyWords + "/1/search";
    }
};