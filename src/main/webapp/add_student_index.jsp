<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <link rel="stylesheet" href="css/admin.css">
</head>

<body>
    <div class="admin">
        <div class="top">
            <div class="left">
                后台管理
            </div>
            <div class="right">
                <a href="student_login.jsp">
                    <i class="fa fa-home"></i>
                    <span>前台主页</span>
                </a>

                <a href="#">
                    <i class="fa fa-highlighter"></i>
                    <span>清除缓存</span>
                </a>
                <a href="#">
                    <i class="fa fa-cog"></i>
                    <span>网站设置</span>
                </a>
                <a href="manager_login.jsp">
                    <i class="fa fa-fw fa-power-off"></i>
                    <span>退出后台</span>
                </a>
            </div>
        </div>
        <div class="body">
            <div class="left">
                <div class="menu">
                    <div class="title">
                            <i class="fa fa-fw fa-home"></i> <a href="manager_index.jsp" style="color:#fff;text-decoration:none;">后台主页</a>
                    </div>
                </div>
                <!-- 分割线 -->
                <div class="menu">
                    <div class="title">
                            <i class="fa fa-fw fa-navicon"></i> <a href="add_teacher_index.jsp" style="color:#fff;text-decoration:none;">注册教师</a>
                    </div>
                </div>
                <!-- 分割线 -->
                <div class="menu">
                    <div class="title">
                            <i class="fa fa-fw fa-file-powerpoint-o"></i><a href="#" style="color:#fff;text-decoration:none;">管理教师</a>
                    </div>
                </div>
                <!-- 分割线 -->                
                <div class="menu">
                    <div class="title">
                            <i class="fa fa-fw fa-image"></i><a href="add_student_index.jsp" style="color:#fff;text-decoration:none;">注册学生</a>
                    </div>
                </div>
                <!-- 分割线 -->                
                <div class="menu">
                    <div class="title">
                            <i class="fa fa-fw fa-image"></i><a href="#" style="color:#fff;text-decoration:none;">管理学生</a>
                    </div>
                </div>
                
                <div class="menu">
                    <div class="title">
                            <i class="fa fa-fw fa-image"></i><a href="add_course_index.jsp" style="color:#fff;text-decoration:none;">添加课程</a>
                    </div>
                </div>
            </div>
            <!-- 分割线 -->
            <div class="right">
                <iframe src="add_student.jsp" frameborder="0" id="rightiframe" name="rightiframe"></iframe>
            </div>
        </div>
    </div>
<% String message = (String)request.getAttribute("message");
if(message != null){
%>
<script type="text/javascript">
alert("<%= message%>");
</script>
<%} %>
</body>

</html>