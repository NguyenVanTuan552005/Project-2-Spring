package com.nvt.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nvt.web.dto.response.BuildingResponseDTO;
import com.nvt.web.reponsitory.BuildingReponsitory;
import com.nvt.web.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	BuildingReponsitory buildingReponsitory;
	
	@Override
	public List<BuildingResponseDTO> reponseBuilding(Map<String, String> params, List<String> typeCodes) throws SQLException {
		return buildingReponsitory.findBuilding(params, typeCodes);
	}
}
