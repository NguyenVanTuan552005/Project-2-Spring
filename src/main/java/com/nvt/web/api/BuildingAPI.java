package com.nvt.web.api;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nvt.web.customexceptions.InvalidDataException;
import com.nvt.web.dto.BuildingDTO;
import com.nvt.web.dto.response.BuildingResponseDTO;
import com.nvt.web.service.BuildingService;

@RestController
public class BuildingAPI {
	@Autowired
	private BuildingService buildingService;

	@GetMapping("/api/buildings")
	public List<BuildingResponseDTO> getBuilding(@RequestParam Map<String, Object> params) throws SQLException {
		return buildingService.findAll(params);
	}
	
	@PostMapping("/api/buildings")
	public BuildingDTO createBuilding(@RequestBody BuildingDTO body) {
		validate(body);
		return body;
	}
	
	@DeleteMapping("/api/buildings/{ids}")
	public void deleteBuilding(@PathVariable List<String> ids) {
		System.out.println("Success");
	}
	
	private void validate(BuildingDTO building) {
		if (building.getName() == null || building.getName().equals("") || building.getNumberOfBasement() == null)
			throw new InvalidDataException("Name or NumberOfBasement Not Empty!");
	}
}
