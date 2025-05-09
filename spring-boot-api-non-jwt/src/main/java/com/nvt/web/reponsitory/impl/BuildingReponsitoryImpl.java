package com.nvt.web.reponsitory.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.nvt.web.config.Config;
import com.nvt.web.dto.response.BuildingResponseDTO;
import com.nvt.web.reponsitory.BuildingReponsitory;

@Repository
public class BuildingReponsitoryImpl implements BuildingReponsitory {
	@Override
	public List<BuildingResponseDTO> findBuilding(Map<String, Object> params, List<String> typeCode) throws SQLException {
		List<BuildingResponseDTO> buildingReponseDTOs = new ArrayList<>();
		Connection con = DriverManager.getConnection(Config.DB_URL, Config.USER, Config.PASS);
		Statement st = con.createStatement();
		String sql = "SELECT b.name, b.street, b.ward, d.name, b.numberofbasement, sub.managername, sub.managerphone, b.floorarea, r.value, b.rentprice, b.servicefee, b.brokeragefee "
				+ "FROM building as b " + "JOIN district as d on b.districtid = d.id "
				+ "JOIN rentarea as r on r.buildingid = b.id "
				+ "JOIN (SELECT a.buildingid, u.fullname as managername, u.phone as managerphone FROM assignmentbuilding as a JOIN user as u on u.id = a.staffid) as sub on sub.buildingid = b.id "
				+ "WHERE 1 = 1 ";
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			BuildingResponseDTO building = new BuildingResponseDTO();
			building.setName(rs.getString("b.name"));
			building.setAddress(String.format("%s, %s, %s", rs.getString("b.street"), rs.getString("b.ward"), rs.getString("d.name")));
			building.setNumberOfBasement(rs.getInt("b.numberofbasement"));
			building.setMangerName(rs.getString("managername"));
			building.setManagerPhone(rs.getString("managerphone"));
			building.setFloorArea(rs.getInt("b.floorarea"));
			building.setRentArea(rs.getInt("r.value"));
			building.setRentPrice(rs.getInt("b.rentprice"));
			building.setServiceFee(rs.getString("b.servicefee"));
			building.setBrokerageFee(rs.getDouble("b.brokeragefee"));
			buildingReponseDTOs.add(building);
		}
		return buildingReponseDTOs;
	}
}
