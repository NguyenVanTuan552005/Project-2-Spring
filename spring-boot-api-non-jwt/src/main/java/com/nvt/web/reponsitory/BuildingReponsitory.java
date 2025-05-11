package com.nvt.web.reponsitory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.nvt.web.dto.response.BuildingResponseDTO;

public interface BuildingReponsitory {
	public List<BuildingResponseDTO> findBuilding(@RequestParam Map<String, String> params, @RequestParam List<String> typeCodes) throws SQLException;
}
