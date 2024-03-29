<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>babasport-list</title>

    <script type="text/javascript">
        //全选
        function checkBox(name, checked){
            $("input[name=" + name + "]").attr("checked", checked);
        }
        //批量删除
        function optDelete(name, isDisplay,pageNo){

            //至少选择一个
            var size = $("input[name=ids]:checked").size();
            if(0 == size){
                alert("请至少选择一个商品");
                return;
            }
            //确认框
            if(!confirm("确认删除")){
                return;
            }

            //设置跳转的参数
            $("#jvForm").attr("action", "/brand/deletes.do?name=" + encodeURIComponent(name) + "&isDisplay" + isDisplay + "&pageNo=" + pageNo);
            //提交
            $("#jvForm").attr("method", "post").submit();
        }

    </script>

</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 品牌管理 - 列表</div>
	<form class="ropt">
		<input class="add" type="button" value="添加" onclick="javascript:window.location.href='add.jsp'"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
<form action="/brand/list.do" method="post" style="padding-top:5px;">
品牌名称: <input type="text" name="name" value="${name}"/>
	<select name="isDisplay">
        <option value="1" <c:if test="${isDisplay == 1}">selected="selected"</c:if>>是</option>
		<option value="0" <c:if test="${isDisplay == 0}">selected="selected"</c:if>>否</option>
	</select>
	<input type="submit" class="query" value="查询"/>
</form>

<form id="jvForm">
<table cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"><input type="checkbox" onclick="checkBox('ids',this.checked)"/></th>
			<th>品牌ID</th>
			<th>品牌名称</th>
			<th>品牌图片</th>
			<th>品牌描述</th>
			<th>排序</th>
			<th>是否可用</th>
			<th>操作选项</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">

        <c:forEach items="${pagination.list }" var="brand">
            <tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'" onmouseover="this.bgColor='#eeeeee'">
                <td><input type="checkbox" value="${brand.id}" name="ids"/></td>
                <td align="center">${brand.id}</td>
                <td align="center">${brand.name}</td>
                <td align="center"><img width="40" height="40" src="${brand.imgUrl}"/></td>
                <td align="center"></td>
                <td align="center">99</td>
                <td align="center">
                    <c:if test="${brand.isDisplay==1}">是</c:if>
                    <c:if test="${brand.isDisplay==0}">否</c:if>
                </td>
                <td align="center">
                    <a class="pn-opt" href="/brand/toEdit.do?id=${brand.id}">修改</a> | <a class="pn-opt" onclick="if(!confirm('您确定删除吗？')) {return false;}" href="#">删除</a>
                </td>
            </tr>
        </c:forEach>

	</tbody>
</table>
</form>

<div class="page pb15">
	<span class="r inb_a page_b">

        <c:forEach items="${pagination.pageView}" var="page">
            ${page}

        </c:forEach>

	</span>
</div>
<div style="margin-top:15px;"><input class="del-button" type="button" value="删除" onclick="optDelete('${name}', '${isDisplay}', '${pagination.pageNo}');"/></div>
</div>
</body>
</html>