<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'add.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
       <table>
         <tr>
           <td colspan="2"><h3>添加回复</h3></td>
         </tr>
         
          <tr>
           <td>头像:</td>
           <td><input type="file" name="pathFile"></td>
         </tr>
         
          <tr>
           <td>回复内容:</td>
           <td><input type="text" name="content"></td>
         </tr>
         
          <tr>
           <td>作者:</td>
           <td><input type="text" name="author" value="匿名用户"></td>
         </tr>
         
          <tr>
           <td><input type="button" value="添加" onclick="return add()"></td>
           <td><input type="button" value="返回"></td>
         </tr>
       </table>
     <script src="${ctx}/js/jquery-1.12.4.js"></script>
       <script type="text/javascript">
         function add(){
           var content = $("[name=content]").val();
           var author = $("[name=author]").val();
           $.post("${ctx}/add","content="+content+"&author="+author,function(data){
             if(data=="true"){
                alert("添加回复成功");
                location="${ctx}/querys?invid=${invid}";
             }else  if(data=="false"){
               alert("添加回复失败");
             }
           
           })
           
         }

       </script>
  </body>
</html>
