package com.runMyErrand.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.runMyErrand.model.MasterTaskInfo;

public class MasterTaskRowMapper implements RowMapper<MasterTaskInfo>{

	public MasterTaskInfo mapRow(ResultSet rs, int index) throws SQLException {
			
			MasterTaskInfo task = new MasterTaskInfo();
			task.setMasterid(rs.getInt(1));
			task.setMastertaskdesc(rs.getString(2));
			task.setPoints(rs.getFloat(4));
			task.setRoom(rs.getString(3));
			task.setDefaultdays(rs.getInt(5));

			return task;
		}

}
