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
            当前位置：后台<i class="fa fa-fw fa-angle-right"></i>>注册学生
        </div>
        <div class="main">
        <form action="studentRegist" method="post">
            <div class="tbodyList">

                <div class="tabBody tactive">
                    <!-- <p class="tip mrb10">常规设置</p> -->
                    <div class="twoBox">
                        <div class="leftbox">
                            <div class="ios-form-group">
                                <div class="ios-title">
                                    <em class="ios-tip-must">*</em>
                                    <span class="ios-tip-name">学生学号：</span>
                                </div>
                                <div class="ios-input-box">
                                    <input type="text" class="ios-input" placeholder="请输入" name="stu.sno" required>
                                </div>
                            </div>
                        </div>
                        <div class="rightbox">
                            <div class="ios-form-group">
                                <div class="ios-title">
                                    <em class="ios-tip-must">*</em>
                                    <span class="ios-tip-name">学生姓名：</span>
                                </div>
                                <div class="ios-input-box">
                                    <input type="text" class="ios-input" placeholder="请输入" name="stu.sname" required>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="twoBox">
                        <div class="leftbox">
                           <div class="ios-form-group">
                                <div class="ios-title">
                                    <em class="ios-tip-must">*</em>
                                    <span class="ios-tip-name">联系电话：</span>
                                </div>
                                <div class="ios-input-box">
                                    <input type="text" class="ios-input" placeholder="请输入" name="stu.sphone">
                                </div>
                            </div>
                        </div>

                        <div class="rightbox">
                            <div class="ios-form-group">
                                <div class="ios-title">
                                    <em class="ios-tip-must">*</em>
                                    <span class="ios-tip-name">身份证号：</span>
                                </div>
                                <div class="ios-input-box">
                                    <input type="text" class="ios-input" placeholder="请输入" name="stu.sidentityNum">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="twoBox">
                        <div class="leftbox">
                           <div class="ios-form-group">
                                <div class="ios-title">
                                    <em class="ios-tip-must">*</em>
                                    <span class="ios-tip-name">性别：</span>
                                </div>
                                <div class="ios-radio-box">
                                    <label class="ios-radio-box-label">
                                        <input type="radio" name="stu.ssex" class="ios-radio" value="male">
                                        <span class="ios-radio-span">男</span>
                                    </label>
                                    <label class="ios-radio-box-label">
                                        <input type="radio" name="stu.ssex" class="ios-radio" value="female">
                                        <span class="ios-radio-span">女</span>
                                    </label>
                                </div>

                            </div>
                        </div>

                        <div class="rightbox">
                            <div class="ios-form-group">
                                <div class="ios-title">
                                    <em class="ios-tip-must">*</em>
                                    <span class="ios-tip-name">登录密码(默认123456)</span>
                                </div>
                                   <div class="ios-input-box">
                                    <input type="text" class="ios-input" placeholder="请输入" name="stu.spassword" value="123456">
                                </div>
                            </div>
                        </div>
                    </div>
                  
                </div>
               
                <div class="tabBody">
                    <div class="textareaBox mrb10" id="createColumnBody">
                    </div>
                </div>
            </div>
            <div class="addTeacherSubmit">
            <input type="submit" value="注册学生">
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