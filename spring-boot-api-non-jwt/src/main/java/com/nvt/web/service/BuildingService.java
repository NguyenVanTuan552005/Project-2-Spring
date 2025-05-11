package com.nvt.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.nvt.web.dto.response.BuildingResponseDTO;

public interface BuildingService {
	public List<BuildingResponseDTO> reponseBuilding(@RequestParam Map<String, String> params, List<String> typeCodes) throws SQLException;
}
