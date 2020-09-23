<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <link rel="stylesheet" href="css/admin.css">
    <link rel="stylesheet" href="css/component.css">
</head>

<body>
    <div class="rbody" id="app">
        <div class="top">
            当前位置：后台<i class="fa fa-fw fa-angle-right"></i>>添加课程
        </div>
        <div class="main">
        <form action="addCourse" method="post">
            <div class="tbodyList">

                <div class="tabBody tactive">
                    <!-- <p class="tip mrb10">常规设置</p> -->
                    <div class="twoBox">
                        <div class="leftbox">
                            <div class="ios-form-group">
                                <div class="ios-title">
                                    <em class="ios-tip-must">*</em>
                                    <span class="ios-tip-name">课程编号：</span>
                                </div>
                                <div class="ios-input-box">
                                    <input type="text" class="ios-input" placeholder="请输入" name="course.cno" required>
                                </div>
                            </div>
                        </div>
                        <div class="rightbox">
                            <div class="ios-form-group">
                                <div class="ios-title">
                                    <em class="ios-tip-must">*</em>
                                    <span class="ios-tip-name">课程名称：</span>
                                </div>
                                <div class="ios-input-box">
                                    <input type="text" class="ios-input" placeholder="请输入" name="course.cname" required>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="twoBox">
                        <div class="leftbox">
                           <div class="ios-form-group">
                                <div class="ios-title">
                                    <em class="ios-tip-must">*</em>
                                    <span class="ios-tip-name">课程学分：</span>
                                </div>
                                <div class="ios-input-box">
                                    <input type="text" class="ios-input" placeholder="请输入" name="course.ccredit">
                                </div>
                            </div>
                        </div>

                        <div class="rightbox">
                            <div class="ios-form-group">
                                <div class="ios-title">
                                    <em class="ios-tip-must">*</em>
                                    <span class="ios-tip-name">课程学时：</span>
                                </div>
                                <div class="ios-input-box">
                                    <input type="text" class="ios-input" placeholder="请输入" name="course.chours">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="twoBox">
                        <div class="leftbox">
                           <div class="ios-form-group">
                                <div class="ios-title">
                                    <em class="ios-tip-must">*</em>
                                    <span class="ios-tip-name">开课学期：</span>
                                </div>
                                <div class="ios-input-box">
                                    <input type="text" class="ios-input" placeholder="请输入" name="course.cterm">
                                </div>

                            </div>
                        </div>

                        <div class="rightbox">
                            <div class="ios-form-group">
                                <div class="ios-title">
                                </div>
                                   <div class="ios-input-box">
                                </div>
                            </div>
                        </div>
                    </div>
                  
                </div>
               
               
            </div>
            <div class="addTeacherSubmit">
            <input type="submit" value="添加课程">
            </div>
          </form>
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