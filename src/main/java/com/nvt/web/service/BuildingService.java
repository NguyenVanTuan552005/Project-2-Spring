package com.nvt.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.nvt.web.dto.response.BuildingResponseDTO;

public interface BuildingService {
	public List<BuildingResponseDTO> findAll(Map<String, Object> params) throws SQLException;
}
