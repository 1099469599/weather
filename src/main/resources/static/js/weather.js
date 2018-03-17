/*
* 城市下拉框事件
* */
$(function () {
    $("#citySelect").change(function () {
        var city = $("#citySelect").val();
        var url = "/weather/" + city;
        window.location.href = url;
    })
});