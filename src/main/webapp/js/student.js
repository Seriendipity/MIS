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

/*------第一部分--------*/
//TODO：查看已选的课程
function query_all(){

}
//TODO:申请选课
function show_apply_course(){

}
//TODO:删除课程
function delete_course(){

}
/*-------第二部分--------*/
//TODO:查看所有课程成绩
function query_all_grade(){

}
//TODO:查看某一门课的成绩
function show_course_grade(){

}