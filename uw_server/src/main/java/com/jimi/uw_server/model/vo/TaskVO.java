package com.jimi.uw_server.model.vo;

import java.util.Date;

import com.jimi.uw_server.constant.TaskState;
import com.jimi.uw_server.constant.TaskType;
import com.jimi.uw_server.model.Task;

/**
 * 任务表示层对象
 * @author HardyYao
 * @createTime 2018年10月13日  上午10:01:54
 */

@SuppressWarnings("serial")
public class TaskVO extends Task{

	private String typeString;
	
	private String stateString;

	public TaskVO(Integer id, Integer state, Integer type, String fileName, Date createTime, Integer priority) {
		this.setId(id);
		this.setState(state);
		this.setStateString(state);
		this.set("stateString", getStateString());
		this.setType(type);
		this.setTypeString(type);
		this.set("typeString", getTypeString());
		this.set("fileName", fileName);
		this.set("createTimeString", createTime);
		this.setPriority(priority);
	}

	public String getTypeString() {
		return typeString;
	}

	public void setTypeString(Integer type) {
		if (type == TaskType.IN) {
			this.typeString = "入库";
		} else if (type == TaskType.OUT) {
			this.typeString = "出库";
		} else if (type == TaskType.INVENTORY) {
			this.typeString = "盘点";
		}  else if (type == TaskType.LOCATIONOPTIZATION) {
			this.typeString = "位置优化";
		}
	}

	public String getStateString(Integer state) {
		return stateString;
	}
	
	public String getStateString() {
		return stateString;
	}

	public void setStateString(Integer state) {
		if (state == TaskState.UNREVIEWED) {
			this.stateString = "未审核";
		} else if (state == TaskState.TOBESTARTED) {
			this.stateString = "未开始";
		} else if (state == TaskState.PROCESSING) {
			this.stateString = "进行中";
		}  else if (state == TaskState.FINISHED) {
			this.stateString = "已完成";
		} else if (state == TaskState.CANCELED) {
			this.stateString = "已作废";
		}
	}


}