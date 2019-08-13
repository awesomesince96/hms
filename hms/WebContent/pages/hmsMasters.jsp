 <!DOCTYPE html>
<html lang="en">

<head>
<%@include file="common/commonLib.jsp"%>
</head>
<body>
	<div id="wrapper">
		<%@include file="common/header.jsp"%>

		<div id="page-wrapper">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Masters</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-4"></div>
								<div class="col-xs-4">
									<i class="fa fa-flag fa-5x"></i>
								</div>
								<div class="col-xs-4"></div>
							</div>
						</div>
						<a href="./contryMasterList.jsp">
							<div class="panel-footer">
								<span class="pull-left">View Contry Master</span> <span
									class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-green">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-4"></div>
								<div class="col-xs-4">
									<i class="fa fa-tasks fa-5x"></i>
								</div>
								<div class="col-xs-4"></div>
							</div>
						</div>
						<a href="./stateMasterList.jsp">
							<div class="panel-footer">
								<span class="pull-left">View State Master</span> <span
									class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-yellow">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-4"></div>
								<div class="col-xs-4">
									<i class="fa fa-shopping-cart fa-5x"></i>
								</div>
								<div class="col-xs-4"></div>
							</div>
						</div>
						<a href="./cityMasterList.jsp">
							<div class="panel-footer">
								<span class="pull-left">View City Master</span> <span
									class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-red">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-4"></div>
								<div class="col-xs-4">
									<i class="fa fa-support fa-5x"></i>
								</div>
								<div class="col-xs-4"></div>
							</div>
						</div>
						<a href="./serviceMasterList.jsp">
							<div class="panel-footer">
								<span class="pull-left">View Service Master</span> <span
									class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

</body>
</html>
