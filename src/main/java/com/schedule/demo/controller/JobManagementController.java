package com.schedule.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.schedule.demo.vo.ScheduleJob;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ScheduleJobs")
public class JobManagementController {

	@ApiOperation(value = "Get All Jobs list", notes = "")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<ScheduleJob> getJobs() {

		return null;
	}

	@ApiOperation(value = "Get job detail information", notes = "Get ScheduleJobs detail information by ScheduleJobs id")
	@ApiImplicitParam(name = "id", value = "ScheduleJobs id", required = true, dataType = "Long")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ScheduleJob getScheduleJobs(@PathVariable Long id) {
		ScheduleJob ScheduleJobs = null;

		return ScheduleJobs;
	}

	@ApiOperation(value = "Create ScheduleJobs", notes = "Create ScheduleJobs by ScheduleJobs object")
	@ApiImplicitParam(name = "ScheduleJobs", value = "ScheduleJobs entity", required = true, dataType = "ScheduleJobs")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String postScheduleJobs(@RequestBody ScheduleJob ScheduleJobs) {

		return "success";
	}

	@ApiOperation(value = "Update ScheduleJobs", notes = "Update ScheduleJobs by id")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ScheduleJobs id", required = true, dataType = "long"),
			@ApiImplicitParam(name = "ScheduleJobs", value = "ScheduleJobs entity", required = true, dataType = "ScheduleJobs")

	})
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String updateScheduleJobs(@PathVariable long id, @RequestBody ScheduleJob ScheduleJobs) {

		return "success";
	}

	@ApiOperation(value = "Delete ScheduleJobs", notes = "Delete ScheduleJobs by ScheduleJobs id")
	@ApiImplicitParam(name = "id", value = "ScheduleJobs id", required = true, dataType = "Long")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteScheduleJobs(@PathVariable Long id) {

		return "success";
	}
}
