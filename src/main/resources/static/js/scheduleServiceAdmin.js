function submitJob() {
	var submitType = $("#submitType").val();
	var postData = {
		jobName : $("#jobName").val(),
		cornExpr : $("#cronExpr").val(),
		httpMehtod : $("#httpMehtod").text(),
		appUrl : $("#appUrl").val(),
		successfulCode : $("#successfulCode").val()
	}
	if("create"==submitType){
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
	$.ajax({
		url : "/ScheduleJobs",
		type : "GET",
		async : true,
		success : function(data) {
			var tableData = new Array();
			$('#scheduleJobsListTable').dataTable().fnClearTable();
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
					actionHtml = actionHtml
							+ "<input type=\"button\" class=\"btn btn-danger btn-sm \" style=\"margin:0 10px;\" onclick=\"changeStatus("
							+ data[i].jobId + ",0)\" value=\"Suspend\"/>";
				} else {
					actionHtml = actionHtml
							+ "<input type=\"button\" class=\"btn btn-primary btn-sm \" style=\"margin:0 10px;\" onclick=\"changeStatus("
							+ data[i].jobId + ",1)\" value=\"Active\"/>";
				}
				array.push(actionHtml);
				tableData.push(array);
			}
			if (tableData.length > 0) {
				$('#scheduleJobsListTable').dataTable().fnAddData(tableData,
						true);
			}
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
		url : "/ScheduleJobs/"+id,
		type:"GET",
		async:false,
		success:function(resp){
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