<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>秒杀详情页</title>

<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
 
<!-- 可选的Bootstrap主题文件（一般不使用） -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>
 
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.0.0/jquery.min.js"></script>
 
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.js"></script>
<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="/seckill/resource/seckill.js" type="text/javascript"></script>
</head>
  <body>
   	<div class="container">
   		<div class="panel panel-default text-center">
   			<div class="panel-heading">${seckill.name}</div>
   			<div class="panel-danger">
   				<h2 class="text-danger">
   					<span class="glyphicon glyphicon-time"></span>
   					<span class="glyphicon" id="seckill-box"></span>
   				</h2>
   			</div>
   		</div>
   	</div>
   	<div id="killPhoneModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title text-center">
						<span class="glyphicon glyphicon-phone"></span>秒杀电话:
					</h3>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-8 col-xs-offset-2">
							<input type="text" name="killPhone" id="killPhoneKey" placeholder="填手机号" class="form-control"/>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<span id="killPhoneMessage" class="glyphicon"></span>
					<button type="button" id="killPhoneBtn" class="btn btn-success">
						<span class="glyphicon glyphicon-phone"></span>
						Submit
					</button>
				</div>
			</div>
		</div>
	</div>
  </body>
  <script type="text/javascript">
  
  $(document).ready(function(){
		seckill.detail.init({
			seckillId : ${seckill.seckill_id},
			startTime : ${seckill.start_time.time},
			endTime : ${seckill.end_time.time}
		});
	});
</script>
</html>