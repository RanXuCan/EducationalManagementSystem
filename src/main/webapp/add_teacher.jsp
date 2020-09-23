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
            当前位置：后台<i class="fa fa-fw fa-angle-right"></i>>注册教师
        </div>
        <div class="main">
        <form action="teacherRegist" method="post">
            <div class="tbodyList">

                <div class="tabBody tactive">
                    <!-- <p class="tip mrb10">常规设置</p> -->
                    <div class="twoBox">
                        <div class="leftbox">
                            <div class="ios-form-group">
                                <div class="ios-title">
                                    <em class="ios-tip-must">*</em>
                                    <span class="ios-tip-name">教师工号：</span>
                                </div>
                                <div class="ios-input-box">
                                    <input type="text" class="ios-input" placeholder="请输入" name="tea.tno" required>
                                </div>
                            </div>
                        </div>
                        <div class="rightbox">
                            <div class="ios-form-group">
                                <div class="ios-title">
                                    <em class="ios-tip-must">*</em>
                                    <span class="ios-tip-name">教师姓名：</span>
                                </div>
                                <div class="ios-input-box">
                                    <input type="text" class="ios-input" placeholder="请输入" name="tea.tname" required>
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
                                    <input type="text" class="ios-input" placeholder="请输入" name="tea.tphone">
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
                                    <input type="text" class="ios-input" placeholder="请输入" name="tea.tidentityNum">
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
                                        <input type="radio" name="tea.tsex" class="ios-radio" value="male">
                                        <span class="ios-radio-span">男</span>
                                    </label>
                                    <label class="ios-radio-box-label">
                                        <input type="radio" name="tea.tsex" class="ios-radio" value="female">
                                        <span class="ios-radio-span">女</span>
                                    </label>
                                </div>

                            </div>
                        </div>

                        <div class="rightbox">
                            <div class="ios-form-group">
                                <div class="ios-title">
                                    <em class="ios-tip-must">*</em>
                                    <span class="ios-tip-name">职称</span>
                                </div>
                                   <div class="ios-select-box" style="width:85px;">
                                    <select class="ios-select" name="ttitle">
                                        <option value="1">无</option>
                                        <option value="2">讲师</option>
                                        <option value="3">副教授</option>
                                        <option value="4">教授</option>
                                    </select>
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
            <input type="submit" value="注册教师">
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