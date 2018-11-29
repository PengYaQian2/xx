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

<title>My JSP 'index.jsp' starting page</title>
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
   <form action="${ctx}/querys" method="post">
     <table border="1">
        <tr align="center">
          <td colspan="3"><h2>帖子回复列表</h2></td>
        </tr>
       
       <tr>
         <td colspan="2"><a href="add.jsp">添加回复</a></td>
         <td><a href="${ctx}/query">返回帖子列表</a></td>
       </tr>
     
       <tr>
          <th>回复内容</th>
          <th>作者</th>
          <th>发布时间</th>
       </tr>
       
       <c:forEach items="${list}" var="lists">
         <tr>
           <td>${lists.content}</td>
           <td>${lists.author}</td>
           <td>${lists.createdate}</td>
         </tr>
       </c:forEach>
     
       <tr>
         <td colspan="3">
            <a href="${ctx}/querys?num=1&invid=${invid}">首页</a>
            <a href="${ctx}/querys?num=${page.currNo-1}&invid=${invid}">上一页</a>
            <a href="${ctx}/querys?num=${page.currNo+1}&invid=${invid}">下一页</a>
            <a href="${ctx}/querys?num=${page.countCurrNo}&invid=${invid}">末页</a>
            
            <input type="text" name="shu" style="width: 30px" value="${page.currNo}">
            <input type="button" value="搜索" id="shu">
            
             <select id="xia">
            <c:forEach begin="1" end="${page.countCurrNo}" var="num">
           
                <option value="${num}" <c:if test="${page.currNo==num}">selected</c:if>>${num}</option>
            </c:forEach>
            </select>
         </td>
        
       </tr>
     
     </table>
   
   </form>

<script src="${ctx}/js/jquery-1.12.4.js"></script>
<script type="text/javascript">
 
  
  $(function(){
    $("#xia").change(function(){
      var num = $(this).val();
      location="${ctx}/querys?num="+num+"&invid=${invid}";
    });
    
    $("#shu").click(function(){
       var num = $("[name=shu]").val();
     location="${ctx}/querys?num="+num+"&invid=${invid}";
    })
  })

</script>
</body>
</html>
