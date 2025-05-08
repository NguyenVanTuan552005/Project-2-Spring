package com.nvt.web.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nvt.web.dto.response.BuildingResponseDTO;
import com.nvt.web.repository.BuildingRepository;
import com.nvt.web.repository.entity.BuildingEntity;
import com.nvt.web.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	private BuildingRepository buildingRepository;

	@Override
	public List<BuildingResponseDTO> findAll(Map<String, Object> params) throws SQLException {
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(params);
		List<BuildingResponseDTO> result = new ArrayList<>();
		buildingEntities.forEach(b -> {
			BuildingResponseDTO buildingResponseDTO = new BuildingResponseDTO();
			
			buildingResponseDTO.setId(b.getId());
			buildingResponseDTO.setName(b.getName());
			buildingResponseDTO.setNumberOfBasement(b.getNumberOfBasement());
			buildingResponseDTO.setDistrictId(b.getDistrictId());
			buildingResponseDTO.setWard(b.getWard());
			buildingResponseDTO.setStreet(b.getStreet());
			
			result.add(buildingResponseDTO);
		});
		return result;
	}
}
