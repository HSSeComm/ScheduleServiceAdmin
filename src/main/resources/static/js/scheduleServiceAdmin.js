function submitJob() {
	var submitType = $("#submitType").val();
	var postData = {
		jobName : $("#jobName").val(),
		cornExpr : $("#cronExpr").val(),
		httpMehtod : $("#httpMehtod").text(),
		appUrl : $("#appUrl").val(),
		successfulCode : $("#successfulCode").val()
	}
	if ("create" == submitType) {
		$.ajax({
			url : "/ScheduleJobs",
			type : "POST",
			async : true,
			contentType : "application/json",
			data : JSON.stringify(postData),
			success : function(resp) {
				if (resp == "success") {
					refreshData();
				} else {
					alert("Job submit failed");
				}
				$("#schedulerJobModal").modal('toggle');
			}
		})
	} else {
		$.ajax({
			url : "/ScheduleJobs/" + $("#jobId").val(),
			type : "PUT",
			async : true,
			contentType : "application/json",
			data : JSON.stringify(postData),
			success : function(resp) {
				if (resp == "success") {
					refreshData();
				} else {
					alert("Job submit failed");
				}
				$("#schedulerJobModal").modal('toggle');
			}
		})
	}
}

function changeHttpMethod(method) {
	$("#httpMehtod").text(method);
	if (method == "POST") {
		$("#postBody").show();
	} else {
		$("#postBody").hide();
	}
}

$('#scheduleJobsListTable').DataTable({
	// "ajax" : "/ScheduleJobs",
	"paging" : true,
	"lengthChange" : false,
	"searching" : false,
	"ordering" : true,
	"info" : true,
	"autoWidth" : false
});

function refreshData() {
	$
			.ajax({
				url : "/ScheduleJobs",
				type : "GET",
				async : true,
				success : function(data) {
					var tableData = new Array();
					$('#scheduleJobsListTable').dataTable().fnClearTable();
					var normalJobCount = 0;
					var suspendJobCount = 0;
					for (var i = 0; i < data.length; i++) {
						var array = new Array();
						array.push(data[i].jobId);
						array.push(data[i].jobName);
						array.push(data[i].cornExpr);
						array.push(data[i].status);
						var actionHtml = "<input type=\"button\" class=\"btn btn-success btn-sm \" style=\"margin:0 10px;\" onclick=\"editJobPage("
								+ data[i].jobId + ")\" value=\"Edit\"/>";
						actionHtml = actionHtml
								+ "<input type=\"button\" class=\"btn btn-warning btn-sm \" style=\"margin:0 10px;\" onclick=\"deleteJob("
								+ data[i].jobId + ")\" value=\"Delete\"/>";
						if (data[i].status == "NORMAL") {
							normalJobCount++;
							actionHtml = actionHtml
									+ "<input type=\"button\" class=\"btn btn-danger btn-sm \" style=\"margin:0 10px;\" onclick=\"changeStatus("
									+ data[i].jobId
									+ ",0)\" value=\"Suspend\"/>";
						} else {
							suspendJobCount++;
							actionHtml = actionHtml
									+ "<input type=\"button\" class=\"btn btn-primary btn-sm \" style=\"margin:0 10px;\" onclick=\"changeStatus("
									+ data[i].jobId
									+ ",1)\" value=\"Active\"/>";
						}
						array.push(actionHtml);
						tableData.push(array);
					}
					if (tableData.length > 0) {
						$('#scheduleJobsListTable').dataTable().fnAddData(
								tableData, true);
					}
					$("#NormalJobCount").text(normalJobCount);
					$("#SuspendJobCount").text(suspendJobCount);
					PieData[0].value = suspendJobCount;
					PieData[1].value = normalJobCount;
					pieChart.Doughnut(PieData, pieOptions);
				}
			});
	$
	.ajax({
		url : "/ScheduleLogs",
		type : "GET",
		async : true,
		success : function(data) {
			var failCount = 0;
			for (var i = 0; i < data.length; i++) {
				failCount = failCount + data[i].failCount;
			}
			$("#failureCount").text(failCount);
		}
	});
}

refreshData();

function createNewJobPage() {
	$("#submitType").val("create");
	$("#jobId").val("");
	$("#jobName").val("");
	$("#cronExpr").val("");
	$("#httpMehtod").text("GET");
	$("#appUrl").val("");
	$("#successfulCode").val("");
	$("#myModalLabel").text("Create Scheduler Job");
	$("#schedulerJobModal").modal('show');
}

function editJobPage(id) {
	$("#submitType").val("edit");
	$.ajax({
		url : "/ScheduleJobs/" + id,
		type : "GET",
		async : false,
		success : function(resp) {
			$("#jobId").val(resp.jobId);
			$("#jobName").val(resp.jobName);
			$("#cronExpr").val(resp.cornExpr);
			$("#httpMehtod").text(resp.httpMehtod);
			$("#appUrl").val(resp.appUrl);
			$("#successfulCode").val(resp.successfulCode);
			$("#myModalLabel").text("Edit Scheduler Job");
			$("#schedulerJobModal").modal('show');
		}
	})
}

function deleteJob(id) {
	$.ajax({
		url : "/ScheduleJobs/" + id,
		type : "DELETE",
		async : true,
		success : function(resp) {
			if (resp == "success") {
				refreshData();
			} else {
				alert("Job delete failed");
			}
		}
	})
}

function changeStatus(id, targetStatus) {
	var postData = {
		status : (targetStatus == 0) ? "PAUSE" : "NORMAL"
	}
	$.ajax({
		url : "/ScheduleJobs/" + id,
		type : "PATCH",
		async : true,
		contentType : "application/json",
		data : JSON.stringify(postData),
		success : function(resp) {
			if (resp == "success") {
				refreshData();
			} else {
				alert("Job " + (targetStatus == 0) ? "suspend" : "active"
						+ " failed");
			}
		}
	})
}

// -------------
// - PIE CHART -
// -------------
// Get context with jQuery - using jQuery's .get() method.
var pieChartCanvas = $("#pieChart").get(0).getContext("2d");
var pieChart = new Chart(pieChartCanvas);
var PieData = [ {
	value : 0,
	color : "#f39c12",
	highlight : "#f39c12",
	label : "Suspend Job"
}, {
	value : 0,
	color : "#00a65a",
	highlight : "#00a65a",
	label : "Running Job"
} ];
var pieOptions = {
	// Boolean - Whether we should show a stroke on each segment
	segmentShowStroke : true,
	// String - The colour of each segment stroke
	segmentStrokeColor : "#fff",
	// Number - The width of each segment stroke
	segmentStrokeWidth : 2,
	// Number - The percentage of the chart that we cut out of the middle
	percentageInnerCutout : 50, // This is 0 for Pie charts
	// Number - Amount of animation steps
	animationSteps : 100,
	// String - Animation easing effect
	animationEasing : "easeOutBounce",
	// Boolean - Whether we animate the rotation of the Doughnut
	animateRotate : true,
	// Boolean - Whether we animate scaling the Doughnut from the centre
	animateScale : false,
	// Boolean - whether to make the chart responsive to window resizing
	responsive : true,
	// Boolean - whether to maintain the starting aspect ratio or not when
	// responsive, if set to false, will take up entire container
	maintainAspectRatio : true,
	// String - A legend template
	legendTemplate : "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>"
};
// Create pie or douhnut chart
// You can switch between pie and douhnut using the method below.
pieChart.Doughnut(PieData, pieOptions);

// -------------
// - BAR CHART -
// -------------
var areaChartData = {
	      labels: ["January", "February", "March", "April", "May", "June", "July"],
	      datasets: [
	        {
	          label: "Success Calls",
	          fillColor: "#00a65a",
	          strokeColor: "#00a65a",
	          pointColor: "#00a65a",
	          pointStrokeColor: "#c1c7d1",
	          pointHighlightFill: "#fff",
	          pointHighlightStroke: "rgba(220,220,220,1)",
	          data: [65, 59, 80, 81, 56, 55, 40]
	        },
	        {
	          label: "Failure Calls",
	          fillColor: "#f56954",
	          strokeColor: "#f56954",
	          pointColor: "#f56954",
	          pointStrokeColor: "rgba(60,141,188,1)",
	          pointHighlightFill: "#fff",
	          pointHighlightStroke: "rgba(60,141,188,1)",
	          data: [28, 48, 40, 19, 86, 27, 90]
	        }
	      ]
	    };
var barChartCanvas = $("#barChart").get(0).getContext("2d");
var barChart = new Chart(barChartCanvas);
var barChartData = areaChartData;
var barChartOptions = {
  // Boolean - Whether the scale should start at zero, or an order of
	// magnitude down from the lowest value
  scaleBeginAtZero: true,
  // Boolean - Whether grid lines are shown across the chart
  scaleShowGridLines: true,
  // String - Colour of the grid lines
  scaleGridLineColor: "rgba(0,0,0,.05)",
  // Number - Width of the grid lines
  scaleGridLineWidth: 1,
  // Boolean - Whether to show horizontal lines (except X axis)
  scaleShowHorizontalLines: true,
  // Boolean - Whether to show vertical lines (except Y axis)
  scaleShowVerticalLines: true,
  // Boolean - If there is a stroke on each bar
  barShowStroke: true,
  // Number - Pixel width of the bar stroke
  barStrokeWidth: 2,
  // Number - Spacing between each of the X value sets
  barValueSpacing: 5,
  // Number - Spacing between data sets within X values
  barDatasetSpacing: 1,
  // String - A legend template
  legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].fillColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>",
  // Boolean - whether to make the chart responsive
  responsive: true,
  maintainAspectRatio: true
};

barChartOptions.datasetFill = false;
barChart.Bar(barChartData, barChartOptions);
