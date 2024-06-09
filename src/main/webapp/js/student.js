$(function () {
    var Accordion = function (el, multiple) {
        this.el = el || {};
        this.multiple = multiple || false;

        var links = this.el.find('.link');

        links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
    }

    Accordion.prototype.dropdown = function (e) {
        var $el = e.data.el;
        $this = $(this),
            $next = $this.next();

        $next.slideToggle();
        $this.parent().toggleClass('open');

        if (!e.data.multiple) {
            $el.find('.submenu').not($next).slideUp().parent().removeClass('open');
        }
        ;
    }

    var accordion = new Accordion($('#accordion'), false);
});
/*------学生信息--------*/
function query_all_information(studentNo){
    var xmlhttp;
    if (window.XMLHttpRequest) {
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlhttp = new XMLHttpRequest();
    }
    else {
        // IE6, IE5 浏览器执行代码
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            document.getElementById("result").innerHTML = xmlhttp.responseText;
        }
    }
    var url = "/mis/query_all_user?action=student&studentNo="+encodeURIComponent(studentNo);
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}
function show_alter_information(){
    var result = document.getElementById("result");
    var show = null;
        show = "<div id='alter_student' class='d_form'>"
            +"<h3>请输入需要修改的成绩信息</h3>"
            +"<p>修改前</p>"
            +"<input type='text' autofocus='autofocus' name='sname' value placeholder='姓名' required>"
            +"<input type='text' autofocus='autofocus' name='sno' value placeholder='学号' required>"
            +"<p>修改后</p>"
            +"<input type='text' autofocus='autofocus' name='ssex' value placeholder='性别'>"
            +"<input type='text' autofocus='autofocus' name='pNumber' value placeholder='电话号码'>"
            +"<input type='text' name='after_password' value placeholder='密码'>"
            +"<input type='number' name='after_sage' value placeholder='年龄'>"
            +"<input id='submit' onclick='alter_student()' type='button' name='submit' value='修改'>"
    result.innerHTML = show;
}
function alter_student(){
    var xmlhttp;
    if(window.XMLHttpRequest){
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlhttp = new XMLHttpRequest();
    }else {
        // IE6, IE5 浏览器执行代码
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            document.getElementById("result").innerHTML = xmlhttp.responseText;
        }
    }
    var all = document.getElementById("alter_student").getElementsByTagName("input");
    var sname = all[0].value.toString();
    var sno = all[1].value.toString();
    var ssex = all[2].value.toString();
    var pNumber = all[3].value.toString();
    var after_password = all[4].value.toString();
    var after_sage = all[5].value;
    var url="/mis/alter?action=alter_student&sname="+sname+"&sno="+sno+"&ssex"+ssex+"&pNumber"+pNumber+"&after_password="+after_password+"&after_sage"+after_sage;
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}
/*------第一部分--------*/
//TODO：查看已选的课程
function query_all(studentNo){
    var xmlhttp;
    if (window.XMLHttpRequest) {
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlhttp = new XMLHttpRequest();
    }
    else {
        // IE6, IE5 浏览器执行代码
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            document.getElementById("result").innerHTML = xmlhttp.responseText;
        }
    }
    console.log("12");
    var url = "/mis/query_all_user?action=S_select_course&studentNo="+encodeURIComponent(studentNo);
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}

//查看可选修的课程
function query_all_course() {
    var xmlhttp;
    if (window.XMLHttpRequest) {
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlhttp = new XMLHttpRequest();
    }
    else {
        // IE6, IE5 浏览器执行代码
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            document.getElementById("result").innerHTML = xmlhttp.responseText;
        }
    }
    url = "/mis/query_all_user?action=course";
    console.log(url);
    xmlhttp.open("GET", url , true);
    xmlhttp.send();
}
//TODO:申请选课
function show_apply_course(studentNo){
    console.log("进入选课信息模块")
    var result = document.getElementById("result");
    var show = "<div id='show_insert_sc'  class='sc_form'>"
        + "<h3>请输入选课信息</h3>"
        + "<input type='text' autofocus='autofocus' id = 'Cno' name='Cno' value placeholder='课程号' required>"
        + "<input type='text' id = 'cid' name='cid' value placeholder='课程CID'>"
        + "<input id='submit' onclick='insert(\""+studentNo+"\",\"sc\")' type='button' name='submit' value='插入'>"
        + "</div>";
    result.innerHTML = show;
}

function insert(studentNo,object){
    console.log("进入传输模块")
    var xmlhttp;
    if (window.XMLHttpRequest) {
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlhttp = new XMLHttpRequest();
    }
    else {
        // IE6, IE5 浏览器执行代码
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            document.getElementById("result").innerHTML = xmlhttp.responseText;
        }
    }
    var url = null;
    var courseNo = document.getElementById("Cno").value.toString();
    var cid = document.getElementById("cid").value.toString();
    console.log(courseNo);
    console.log(cid);
    console.log(studentNo);
    url = '/mis/insert?action=insert_scInfo&courseNo='+courseNo+"&cid="+cid+"&sno="+studentNo;
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}
//TODO:删除课程
function delete_course(studentNo){
    var result = document.getElementById("result");
    var show = "<div id='show_delete_sc'  class='sc_dform'>"
        + "<h3>请输入退课信息</h3>"
        + "<input type='text' autofocus='autofocus' id = 'Cno' name='Cno' value placeholder='课程号' required>"
        + "<input type='text' id = 'cid' name='cid' value placeholder='课程CID'>"
        + "<input id='submit' onclick='delete_sc(\""+studentNo+"\",\"sc\")' type='button' name='submit' value='插入'>"
        + "</div>";
    result.innerHTML = show;
}
function delete_sc(studentNo,object){
    console.log("进入delete传输模块")
    var xmlhttp;
    if (window.XMLHttpRequest) {
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlhttp = new XMLHttpRequest();
    }
    else {
        // IE6, IE5 浏览器执行代码
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            document.getElementById("result").innerHTML = xmlhttp.responseText;
        }
    }
    var url = null;
    var courseNo = document.getElementById("Cno").value.toString();
    var cid = document.getElementById("cid").value.toString();
    url = '/mis/delete?action=delete_scInfo&courseNo='+courseNo+"&cid="+cid+"&sno="+studentNo;
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}
/*-------第二部分--------*/
//TODO:查看所有课程成绩
function query_all_grade(studentNo){
    console.log("进入查询已选修课模块")
    var xmlhttp;
    if (window.XMLHttpRequest) {
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlhttp = new XMLHttpRequest();
    }
    else {
        // IE6, IE5 浏览器执行代码
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            document.getElementById("result").innerHTML = xmlhttp.responseText;
        }
    }
    var url = null;
    url = '/mis/query_all_user?action=query_self_grade&sno='+studentNo;
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}
//TODO:查看某一门课的成绩
function show_course_grade(studentNo){
    var result = document.getElementById("result");
    var show = "<div id='show_One_sc'  class='sc_dform'>"
        + "<h3>请输入查询的课程信息</h3>"
        + "<input type='text' autofocus='autofocus' id = 'Cno' name='Cno' value placeholder='课程号' required>"
        + "<input type='text' id = 'cid' name='cid' value placeholder='课程CID'>"
        + "<input id='submit' onclick='query_One_course(\""+studentNo+"\",\"sc\")' type='button' name='submit' value='插入'>"
        + "</div>";
    result.innerHTML = show;
}

function query_One_course(studentNo,object){
    console.log("进入查询单个选修成绩模块")
    var xmlhttp;
    if (window.XMLHttpRequest) {
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlhttp = new XMLHttpRequest();
    }
    else {
        // IE6, IE5 浏览器执行代码
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            document.getElementById("result").innerHTML = xmlhttp.responseText;
        }
    }
    var url = null;
    var courseNo = document.getElementById("Cno").value.toString();
    var cid = document.getElementById("cid").value.toString();
    url = '/mis/query_all_user?action=query_One_grade&courseNo='+courseNo+"&cid="+cid+"&sno="+studentNo;

    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}