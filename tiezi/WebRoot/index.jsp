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
   <form action="${ctx}/query" method="post">
     <table border="1">
        <tr>
          <td colspan="5"><h2>帖子列表</h2></td>
        </tr>
       
       <tr>
         <td>帖子标题:</td>
         <td colspan="2"><input type="text" name="title" value="${title}"></td>
         <td colspan="2"><input type="submit" value="搜索"></td>
       </tr>
     
       <tr>
          <th>标题</th>
          <th>内容摘要</th>
          <th>作者</th>
          <th>发布时间</th>
          <th>操作</th>
       </tr>
       
       <c:forEach items="${list}" var="lists">
         <tr>
           <td>${lists.title}</td>
           <td>${lists.summary}</td>
           <td>${lists.author}</td>
           <td>${lists.createdate}</td>
           <td>
             <a href="${ctx}/querys?invid=${lists.id}">查看回复</a>
             <a href="javaScript:void(0);" onclick="return shan(${lists.id})">删除</a>
            <%--  ${ctx}/delete?id=${lists.id} --%>
           </td>
         </tr>
       </c:forEach>
     
       <tr>
         <td colspan="5">
            <a href="${ctx}/query?num=1">首页</a>
            <a href="${ctx}/query?num=${page.currNo-1}&title=${title}">上一页</a>
            <a href="${ctx}/query?num=${page.currNo+1}&title=${title}">下一页</a>
            <a href="${ctx}/query?num=${page.countCurrNo}">末页</a>
            
            <input type="text" name="shu" style="width: 30px" value="${page.currNo}">
            <input type="button" value="搜索" id="shu">
            
           
              <select id="xia">
               <c:forEach begin="1" end="${page.countCurrNo}" var="num">
                <option value="${num}">${num}</option>
             <c:if test="${page.currNo==num}">selected</c:if>
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
      alert(1)
      location="${ctx}/query?num="+num+"&title=${title}";
    });
    
    
     $("#shu").click(function(){
        var num = $("[name=shu]").val();
     location="${ctx}/query?num="+num+"&title=${title}";
    })
  })
  
  function shan(id){
      if(confirm("确认删除吗?")){
        $.post("${ctx}/delete","id="+id,function(data){
      if(data=="true"){
                alert("删除成功");
                location="${ctx}/query";
             }else  if(data=="false"){
               alert("删除失败");
             }
     })
      }
  
    
  }

</script>
</body>
</html>
