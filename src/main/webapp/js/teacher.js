//折叠菜单效果
$(function(){
    var Accordion = function(el, multiple) {
        this.el = el || {};
        this.multiple = multiple || false;

        var links = this.el.find('.link');

        links.on('click',{el: this.el, multiple: this.multiple}, this.dropdown)
    }

    Accordion.prototype.dropdown = function(e){
        var $el = e.data.el;
        $this = $(this),
            $next = $this.next();

        $next.slideToggle();
        $this.parent().toggleClass('open');

        if(!e.data.multiple){
            $el.find('.submenu').not($next).slideUp().parent().removeClass('open');
        }
        ;
    }
    var accordion = new Accordion($('#accordion'),false);
});

/*----------老师信息----------*/
//TODO：把老师的信息显示出来（或者算了）
function query_teacher(teacherNo){
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
    var url = "/mis/query_all_user?action=teacher_Info&teacherNo=" + teacherNo;
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}
/*----------课程操作----------*/
//查询课程平均分信息
function show_course_avg(teacherNo){
    var xmlhttp;
    if(window.XMLHttpRequest){
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlhttp = new XMLHttpRequest();
    }else{
        // IE6, IE5 浏览器执行代码
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    //TODO:只显示老师教授的课程的平均成绩。
    xmlhttp.onreadystatechange = function(){
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
            document.getElementById("result").innerHTML = xmlhttp.responseText;
        }
    }
    console.log("function");
    var url = "/mis/query_all_user?action=course_avg&teacher_no=" + teacherNo;
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}
function fail_rate(teacherNo){
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
    //TODO:只显示老师教授的课程的平均成绩。
    var url = "/mis/query_all_user?action=fail_rate&teacher_no="+ teacherNo;
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}
//显示查询成绩的板块
function show_course_ranking(teacherNo){
    var result = document.getElementById("result");
    var show = "<div id='course_ranking' class='d_form'>"
        +"<h3>请输入课程编号</h3>"
        +"<input id='course_ranking_value' type='text' autofocus='autofocus' name='cid' value placeholder='cid'>"
        +"<input id='submit' onclick='course_ranking(\""+teacherNo+"\")' type='button' name='submit' value='查询'>"
        +"</div>";
    result.innerHTML = show;
}
//显示查询成绩排名信息
function course_ranking(teacherNo){
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
    var Cno = document.getElementById("course_ranking_value").value;
    var url = "/mis/query_all_user?action=course_ranking&cid=" + Cno + "&teacher_no=" + teacherNo;

    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

/*-----------查询指定课程所有学生成绩-----------*/
function query_course_all(){
    var result = document.getElementById("result");
    var show = "<div id='query_course' class='d_form'>"
        +"<h3>请输入课程编号</h3>"
        +"<input id='course_name' type='text' autofocus='autofocus' name='cno' value placeholder='cid'>"
        +"<input id='submit' onclick='query_course()' type='button' name='submit' value='查询'>"
        +"</div>";
    result.innerHTML = show;
}
//指定课程的所有学生成绩
function query_course(){
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
    var Cno = document.getElementById("course_name").value;
    var url = "/mis/query_all_user?action=selected_course_grade&cno=" + Cno;

    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}
//insert的函数方法
function insert(object){
    console.log('insert');
    var xmlhttp;
    if(window.XMLHttpRequest){
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlhttp = new XMLHttpRequest();
    }
    else{
        // IE6, IE5 浏览器执行代码
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function(){
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
            document.getElementById("result").innerHTML = xmlhttp.responseText;
        }
    }
    var url = null;
    //判断执行
    if(object == "sc"){
        var sc = document.getElementById("show_insert_grade").getElementsByTagName("input");
        var sno = sc[0].value;
        var cno = sc[1].value;
        var grade = sc[2].value;
        url = "/mis/insert?action=insert_sc&sno=" + sno+"&cno="+cno+"&grade="+grade;
    }
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}

function show_insert_grade(){
    var result = document.getElementById("result");
    var show = "<div id='show_insert_grade' class='d_form'>"
        +"<h3>请输入要插入的成绩信息</h3>"
        +"<input type='text' autofocus='autofocus' name='sno' value placeholder='学号' required>"
        +"<input type='text' name='cno' value placeholder='cid' required>"
        +"<input type='number' name='grade' value placeholder='成绩'>"
        +"<input id='submit' onclick=insert('sc') type='button' name='submit' value='插入'>"
        +"</div>";
    result.innerHTML = show;
}

function show_alter(object,teacherNo){
    var result = document.getElementById("result");
    var show = null;
    if(object=="sc"){
        show = "<div id='alter_sc' class='d_form'>"
        +"<h3>请输入需要修改的成绩信息</h3>"
        +"<p>修改前</p>"
        +"<input type='text' autofocus='autofocus' name='sno' value placeholder='学号' required>"
        +"<input type='text' name='cno' value placeholder='cid' required>"
        +"<p>修改后</p>"
        +"<input type='number' name='after_grade' value placeholder='成绩'>"
        +"<input id='submit' onclick='alter_sc()' type='button' name='submit' value='修改'>"
    }else if(object=="teacher"){
        show = "<div id='alter_teacher' class='d_form'>"
            +"<h3>请输入需要修改的信息</h3>"
            +"<p>修改前</p>"
            +"<input type='text' autofocus='autofocus' name='tno' value placeholder='教师号' required>"
            +"<p>修改后</p>"
            +"<input type='text' name='after_tname' value placeholder='姓名'>"
            +"<input type='number' name='after_tage' value placeholder='年龄'>"
            +"<input id='submit' onclick='alter_teacher()' type='button' name='submit' value='修改'>"
            +"</div>"
    }
    result.innerHTML = show;
}

//修改成绩
function alter_sc() {
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
    var all = document.getElementById("alter_sc").getElementsByTagName("input");
    var sno = all[0].value.toString();
    var cno = all[1].value.toString();
    var after_grade = all[2].value;
    var url="/mis/alter?action=alter_sc&sno="+sno+"&cno="+cno+"&after_grade="+after_grade;
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}
//修改老师信息
function alter_teacher(){
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
    var all = document.getElementById("alter_teacher").getElementsByTagName("input");
    var tno = all[0].value.toString();
    var after_tname = all[1].value.toString();
    var after_age = all[2].value;
    var url = "/mis/alter?action=alter_teachers&tno="+tno+"&after_tname="+after_tname+"&after_age="+after_age;
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}
//TODO:查询某个老师的教授的课程
function query_all(teacherNo){
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
    var url = "/mis/query_all_user?action=select_teaching_course&teacherNo="+encodeURIComponent(teacherNo);
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}
//TODO:查询某个老师教的课程的同学名单
function show_student(object,teacherNo){
    var result = document.getElementById("result");
    var show = "<div id='query_student_name' class='d_form'>"
        +"<h3>请输入课程编号</h3>"
        +"<input id='course_name' type='text' autofocus='autofocus' name='cno' value placeholder='课程cid'>"
        +"<input id='submit' onclick='query_student_name(\""+teacherNo+"\")' type='button' name='submit' value='查询'>"
        +"</div>";
    result.innerHTML = show;
}
function query_student_name(teacherNo){
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
    var all = document.getElementById("query_student_name").getElementsByTagName("input");
    var cno = all[0].value.toString();
    var url="/mis/query_all_user?action=query_student_name&cid="+cno+"&teacherNo="+teacherNo;
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}