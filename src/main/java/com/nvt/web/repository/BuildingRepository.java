package com.nvt.web.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.nvt.web.repository.entity.BuildingEntity;

public interface BuildingRepository {
	public List<BuildingEntity> findAll(Map<String, Object> params) throws SQLException;
}
