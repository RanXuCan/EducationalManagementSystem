<%@ page contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ page import="com.rxc.entity.Student" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    
    <link href="css/teacher_index.css" rel="stylesheet" />
    
    <title>学生教务管理系统</title>
</head>
<body>
    <div class="PublicHead clearfix">
        <div class="leftBox clearfix">
            <div class="storeLogo">
                <img src="images/storelogo.png" />
            </div>

            <div class="storeText">
                学生教务管理系统
            </div>
        </div>
        <div class="RightBox clearfix">
            <div class="ManagerPhotoBox">
                <div class="ManagerPic">
                </div>
                <div class="ManagerName">
                    欢迎你，学生<h5 style="display:inline;">${studentname}</h5>
                </div>
            </div>
            <a href="student_login.jsp">
                <div class="dropOutBox">
                    <i class="">
                    </i>
                    <span>退出</span>
                </div>
            </a>
        </div>
    </div>

    <div class="PublicDownWhole clearfix">
        <!--左侧-->
        <div class="leftBox">
            <ul>
                <a href="student_index.jsp"> <li class=""><i class=""></i><span>我要选课</span></li></a>
                <a href="studentSeeCourse">
                    <li class=""><i class=""></i><span>我的课程</span></li>
                </a>
                <a href="studentSeeScore"> <li class=""><i class=""></i><span>我的成绩</span></li></a>
                <a href="studentSeeInfor"> <li class="Select"><i class=""></i><span>个人信息</span></li></a>
            </ul>
        </div>
        <!--右侧-->
        <div class="RightBox">
            <div class="PublicContentBox">
                <!--公用指向页面名字-->
                <div class="PublicPointToAgeText">
                    <span class="span1">教务管理  </span> <span class="span2">学生教务管理列表</span>
                </div>
                
             
             
                <div class="InquireTableBox">
                    <div class="headbox">
                        <div class="headboxtext">
                            <span class="span1">学生信息如下</span>
                        </div>
                        
                        
                         <div class="PublicBtnIcon">
                            
                            <span>&nbsp;</span>
                        </div>
                    </div>

                    <!--查询到的表格-->
                    <div class="InquireSelectTable">

                        <table class="PublicTableCss">
                            <thead>
                                <tr>
                                    <td>学生姓名</td>
                                    <td>学生学号</td>
                                    <td>身份证号</td>
                                    <td>学生性别</td>
                                    <td>所在班级</td>
                                    <td>联系电话</td>
                                    <td>已修学分</td>
                                </tr>

                            </thead>
                            <tbody>
<%
    Student stu = (Student)request.getSession().getAttribute("stu");
    if(stu!=null){
%>
                                <tr>
                                    <td><%= stu.getSname() %></td>
                                    <td><%= stu.getSno() %></td>
                                    <td><%= stu.getSidentityNum() %></td>
                                    <td><%= stu.getSsex() %></td>
                                    <td><%= stu.getClassno() %></td>
                                    <td><%= stu.getSphone() %></td>
                                    <td><%= stu.getStotalcredit() %></td>
                                </tr>

<%     }
    else{
%>
                            <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                     <td></td>
                                    <td></td>
                                    <td></td>
                                     <td></td>
                                </tr>
<%
         } 
%> 
                            </tbody>
                        </table>
                        <br><br>
                    </div>
                    
                </div>
            </div>

        </div>
    </div>
</body>
</html>