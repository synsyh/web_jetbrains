/**
 * Created by MISAKA on 2016/11/27.
 */
    function countdown ()
    {
        var now = new Date ();

        document.getElementById ("clock").innerHTML = "当前时间："+ now.getTime();
        setTimeout ('countdown()', 1000); //一秒刷新一次计时器
    }
window.onload = function ()
    {
        countdown ();
    }
