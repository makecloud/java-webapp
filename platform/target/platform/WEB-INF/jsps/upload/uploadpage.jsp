<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我不是真正的慕课网</title>

<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/commoncss/main.commoncss" type="text/commoncss" /> --%>
</head>
<body>
<div align="center">

<h1>上传附件</h1>
<form method="post" action="<%= request.getContextPath()%>/course/doUpload" enctype="multipart/form-data" >
<input type="file" name="file"/>
<input type="submit"/>
</form>
</div>
</body>
</html>