<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<jsp:useBean id="sn" scope="page" class="com.bean.SystemBean" />
<jsp:useBean id="jiezhanghsgb" scope="page" class="com.bean.JiezhangBean" /> 

<%
response.setContentType("application/vnd.ms-excel");
response.addHeader("Content-Disposition", "attachment;filename=jiezhang.xls");
%>
<html>
  <head>
    <title>����</title>
  </head>

  <body >
 <%
			String sql="select * from jiezhang  order by id desc";

			
			%>
<table width="100%" border="1" align="center" cellpadding="3" cellspacing="1" bordercolor="00FFFF" style="border-collapse:collapse">  
  <tr>
    <td width="30" align="center" bgcolor="CCFFFF">���</td>
    <td align='center'>�ͷ����</td>
    <td align='center'>�ͷ�����</td>
    <td align='center'>�۸�</td>
    <td align='center'>�ͻ����</td>
    <td align='center'>�ͻ�����</td>
    <td align='center'>��סʱ��</td>
    <td align='center'>��ס����</td>
    <td align='center'>�ܽ��</td>
    
	
    <td width="120" align="center" bgcolor="CCFFFF">���ʱ��</td>
    
  </tr>
   <%		
				List pagelist3=jiezhanghsgb.getAllJiezhang(10);
			if(!pagelist3.isEmpty()){
				for(int i=0;i<pagelist3.size();i++){
					List pagelist2 =(ArrayList)pagelist3.get(i);
			%>
  <tr>
    <td  align="center"><%=i+1 %></td>
    <td>${u.kefangbianhao}</td>
    <td>${u.kefangleixing}</td>
    <td>${u.jiage}</td>
    <td>${u.lvkebianhao}</td>
    <td>${u.lvkexingming}</td>
    <td>${u.ruzhushijian}</td>
    <td>${u.ruzhutianshu}</td>
    <td>${u.zongjine}</td>
    
	
	
   
  </tr>
  	<%
  }}
   %>
   
</table>
<br>
  </body>
</html>

