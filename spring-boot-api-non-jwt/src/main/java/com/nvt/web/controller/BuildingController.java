package com.nvt.web.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nvt.web.dto.response.BuildingResponseDTO;
import com.nvt.web.service.BuildingService;

@RestController
public class BuildingController {
	@Autowired
	BuildingService buildingService;
	
	@GetMapping("/building-list")
	public List<BuildingResponseDTO> getBuilding(@RequestParam(required = false) Map<String, String> params, 
												@RequestParam(value = "typeCode", required = false) List<String> typeCodes) throws SQLException {
		return buildingService.reponseBuilding(params, typeCodes);
	}
}
