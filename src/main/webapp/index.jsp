<%--
  Created by IntelliJ IDEA.
  User: facedamon
  Date: 2018/3/5
  Time: 上午1:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    Hello World

<form name="form1" action="/manage/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file" />
    <input type="submit" value="springmvc 文件上传" />
</form>
</body>
</html>
