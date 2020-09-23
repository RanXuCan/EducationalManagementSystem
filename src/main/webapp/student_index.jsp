<%@ page contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.rxc.entity.Course" %>
<%@ page import="com.rxc.entity.Teacher" %>
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
                <a href="student_index.jsp"> <li class="Select"><i class=""></i><span>我要选课</span></li></a>
                <a href="studentSeeCourse">
                    <li><i class=""></i><span>我的课程</span></li>
                </a>
                <a href="studentSeeScore"> <li class=""><i class=""></i><span>我的成绩</span></li></a>
                <a href="studentSeeInfor"> <li class=""><i class=""></i><span>个人信息</span></li></a>
            </ul>
        </div>
        <!--右侧-->
        <div class="RightBox">
            <div class="PublicContentBox">
                <!--公用指向页面名字-->
                <div class="PublicPointToAgeText">
                    <span class="span1">教务管理  </span> <span class="span2">学生教务管理列表</span>
                </div>
                
                <!--查询-->
                <div class="InquireBox clearfix">
                  <form action="findCourseFromStudent" method="post">
                    <div class="InquireleftBox">
                        <div class="Text">根据课程</div>
                        <div class="InputDiv">
                            <i class=""></i>
                            <select class="phoneInput" style="width:105px;" name="selectItem">
                                <option value="1">名称</option>
                                <option value="2">开课教师</option>
                            </select>
                        </div>
                    </div>
                    <div class="InquireleftBox">
                        <div class="InputDiv">   <input class="phoneInput" placeholder="请输入查询信息" name="inputInfor" required /></div>
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
                            <span class="span1">学生教务管理列表</span>
                        </div>
                        
                        
                        <div class="PublicBtnIcon Color6Btn">
                            <span><a href="getAllTC" style="color:white">查询所有已开课程</a></span>
                        </div>
                    </div>

                    <!--查询到的表格-->
                    <div class="InquireSelectTable">

                        <table class="PublicTableCss">
                            <thead>
                                <tr>
                                    <td>课程名称</td>
                                    <td>课程学分</td>
                                    <td>课程学时</td>
                                    <td>任课教师</td>
                                    <td>教师职称</td>
                                    <td>操作</td>
                                </tr>

                            </thead>
                            <tbody>
<%
     List<Course> courselist = (List<Course>)request.getAttribute("courselist");
     List<Teacher> teacherlist = (List<Teacher>)request.getAttribute("teacherlist");
     List<List<Teacher>> coutealist = (List<List<Teacher>>)request.getAttribute("coutealist");
     if(coutealist != null && !coutealist.isEmpty()){
    	 for(int i = 0;i<coutealist.size();i++){
    		 List<Teacher> tealist = coutealist.get(i);
    		     for(Teacher tea : tealist){
%>
                                <tr>
                                    <td><%= courselist.get(i).getCname() %></td>
                                    <td><%= courselist.get(i).getCcredit() %></td>
                                    <td><%= courselist.get(i).getChours() %></td>
                                    <td><%= tea.getTname() %></td>
                                    <td><%= tea.getTtitle() %></td>
                                
                                    <td>
                                        <div class="PublicTableBtnIcon Color3Btn">
                                            <i class=""></i>
                                            <span><a href="studentChooseCourse?cno=<%= courselist.get(i).getCno() %>&tno=<%= tea.getTno() %>" style="color:white">选课</a></span>
                                        </div>
                                        <div class="PublicTableBtnIcon Color4Btn">
                                            <i class=""></i>
                                            <span><a href="studentQuitCourse?cno=<%= courselist.get(i).getCno() %>&tno=<%= tea.getTno() %>" style="color:white">退课</a></span>
                                        </div>
                                    </td>
                                </tr>
<%           }
    	 }
    }else if(courselist!=null && teacherlist!=null && !teacherlist.isEmpty()){
    	for(int j = 0;j<courselist.size();j++){
%>
                                <tr>
                                    <td><%= courselist.get(j).getCname() %></td>
                                    <td><%= courselist.get(j).getCcredit() %></td>
                                    <td><%= courselist.get(j).getChours() %></td>
                                    <td><%= teacherlist.get(j).getTname() %></td>
                                    <td><%= teacherlist.get(j).getTtitle() %></td>
                                
                                    <td>
                                        <div class="PublicTableBtnIcon Color3Btn">
                                            <i class=""></i>
                                            <span><a href="studentChooseCourse?cno=<%= courselist.get(j).getCno() %>&tno=<%= teacherlist.get(j).getTno() %>" style="color:white">选课</a></span>
                                        </div>
                                        <div class="PublicTableBtnIcon Color4Btn">
                                            <i class=""></i>
                                            <span><a href="studentQuitCourse?cno=<%= courselist.get(j).getCno() %>&tno=<%= teacherlist.get(j).getTno() %>" style="color:white">退课</a></span>
                                        </div>
                                    </td>
                                </tr>

<%      }
    }else{
%>
                            <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>
                                        <div class="PublicTableBtnIcon Color3Btn">
                                            <i class=""></i>
                                            <span>选课</span>
                                        </div>
                                         <div class="PublicTableBtnIcon Color4Btn">
                                            <i class=""></i>
                                            <span><a href="#" style="color:white">退课</a></span>
                                        </div>
                                    </td>
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
<% String message = (String)request.getAttribute("message");
if(message != null){
%>
<script type="text/javascript">
alert("<%= message%>");
</script>
<%} %>
</body>
</html>