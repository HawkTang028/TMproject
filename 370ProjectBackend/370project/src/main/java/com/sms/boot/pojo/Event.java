package com.sms.boot.pojo;

/**
 *  Event table ORM
 */
import java.io.Serializable;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

@TableName("event")
public class Event extends Model<Event> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TableId(value = "event_id", type = IdType.AUTO)
	private Integer eventId;
	
	@TableField("user_id")
	private Integer userId;
	
	@TableField("event_title")
	private String eventTitle;
	
	@TableField("note")
	private String note;
	
	@TableField("priority")
	private Integer priority;
	
	@TableField("start_time")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private String startTime;
	
	@TableField("end_time")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private String endTime;
	
	@TableLogic
	@TableField("is_del")
	private Integer isDel;

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	
	
	
}
