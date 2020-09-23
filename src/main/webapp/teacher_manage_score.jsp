<%@ page contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.rxc.entity.Course" %>
<%@ page import="com.rxc.entity.Student" %>
<%@ page import="com.rxc.entity.Grade" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    
    <link href="css/teacher_index.css" rel="stylesheet" />
    
    <title>教师教务管理系统</title>
</head>
<body>
    <div class="PublicHead clearfix">
        <div class="leftBox clearfix">
            <div class="storeLogo">
                <img src="images/storelogo.png" />
            </div>

            <div class="storeText">
                教师教务管理系统
            </div>
        </div>
        <div class="RightBox clearfix">
            <div class="ManagerPhotoBox">
                <div class="ManagerPic">
                </div>
                <div class="ManagerName">
                    欢迎您，教师<h5 style="display:inline;">${teachername}</h5>
                </div>
            </div>
            <a href="teacher_login.jsp">
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
                <a href="teacher_index.jsp"> <li class=""><i class=""></i><span>我要选课</span></li></a>
                <a href="teacherSeeCourse">
                    <li><i class=""></i><span>我教的课</span></li>
                </a>
                <a href="teacher_manage_score.jsp"> <li class="Select"><i class=""></i><span>录入成绩</span></li></a>
                <a href="#"> <li class=""><i class=""></i><span>成绩统计</span></li></a>
            </ul>
        </div>
        <!--右侧-->
        <div class="RightBox">
            <div class="PublicContentBox">
                <!--公用指向页面名字-->
                <div class="PublicPointToAgeText">
                    <span class="span1">教务管理  </span> <span class="span2">教师教务管理列表</span>
                </div>
                
                <!--查询-->
                <div class="InquireBox clearfix">
                  <form action="teacherSeeDetail" method="post">
                    <div class="InquireleftBox">
                        <div class="Text">根据课程</div>
                        <div class="InputDiv">
                            <i class=""></i>
                            <select class="phoneInput" style="width:105px;" name="selectItem">
                                <option value="1">名称</option>
                            </select>
                        </div>
                    </div>
                    <div class="InquireleftBox">
                        <div class="InputDiv">   <input class="phoneInput" placeholder="请输入课程名称" name="cname" required /></div>
                    </div>
                    <div class="PublicBtnIcon Color2Btn ">
                        <i class=""></i>
                        <span><input type="submit" value="查询" style="color:white"/></span>
                    </div>
                    </form>
                </div>
             
             
                <div class="InquireTableBox">
                    <div class="headbox">
                        <div class="headboxtext">
                            <span class="span1">教师教务管理列表</span>
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
                                    <td>课程名称</td>
                                    <td>学生学号</td>
                                    <td>学生姓名</td>
                                    <td>课程分数</td>
                                    <td>录入/修改成绩</td>
                                </tr>

                            </thead>
                            <tbody>
<%
     List<Student> stulist = (List<Student>)request.getAttribute("stulist");
     List<Grade> gradelist = (List<Grade>)request.getAttribute("gradelist");
     Course cou = (Course)request.getAttribute("course");
     if(stulist != null && !stulist.isEmpty()){
    	 for(int i = 0;i<stulist.size();i++){
    		 Student stu = stulist.get(i);
    		 Grade grade = gradelist.get(i);
%>
                                <tr>
                                    <td><%= cou.getCname() %></td>
                                    <td><%= stu.getSno() %></td>
                                    <td><%= stu.getSname() %></td>
                                    <td><%= grade.getCscore() %></td>
                               
                                    <td>
                                    <div style="margin:2px;">
                                        <form action="teacherUpdateScore?tcid=<%= grade.getTcid() %>&sno=<%= stu.getSno() %>" method="post">
                                          <div class="" style="display:inline-block">
                                             <div class="InputDiv">   <input class="phoneInput" value="<%= grade.getCscore() %>" name="cscore" /></div>
                                          </div>
                                        <div class="PublicTableBtnIcon Color3Btn"  style="display:inline-block">
                                            <i class=""></i>
                                            <span><input type="submit" value="录入/修改" style="color:white"/></span>
                                        </div>
                                        </form>
                                    </div>
                                    </td>
                                </tr>
<%
    	 }
    }else{
%>
                            <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>
                                     
                                         <div class="PublicTableBtnIcon Color3Btn">
                                            <i class=""></i>
                                            <span><a href="#" style="color:white">录入</a></span>
                                        </div>
                                    </td>
                                </tr>
<%
         } 
%> 
                            </tbody>
                        </table>
   <br><br><br>
                    </div>
                    
                </div>
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