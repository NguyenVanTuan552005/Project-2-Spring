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
	public List<BuildingResponseDTO> findBuilding(Map<String, String> params, List<String> typeCodes) throws SQLException {
		List<BuildingResponseDTO> buildingReponseDTOs = new ArrayList<>();
		Connection con = DriverManager.getConnection(Config.DB_URL, Config.USER, Config.PASS);
		Statement st = con.createStatement();
		String sql = "WITH building_tmp AS (\r\n"
				+ "	SELECT sub.*, bui_rent.renttypeid\r\n"
				+ "	FROM (SELECT b.*, a.staffid FROM building AS b LEFT JOIN assignmentbuilding AS a ON b.id = a.buildingid) AS sub\r\n"
				+ "	JOIN buildingrenttype AS bui_rent ON sub.id = bui_rent.buildingid\r\n"
				+ ")\r\n"
				+ "SELECT root.*, d.name AS districtname, r.value AS rentarea, u.fullname AS staffname\r\n"
				+ "FROM building_tmp as root\r\n"
				+ "JOIN district AS d ON d.id = root.districtid\r\n"
				+ "JOIN rentarea AS r ON r. buildingid = root.id\r\n"
				+ "LEFT JOIN user AS u ON u.id = root.staffid\r\n"
				+ "WHERE 1 = 1 ";
		
		if (typeCodes != null) {
			sql += " AND r_t.code IN (";
			for (int i = 0; i < typeCodes.size(); i++)
				if (i == typeCodes.size() - 1)
					sql += String.format("'%s'", typeCodes.get(i));
				else
					sql += String.format("'%s',", typeCodes.get(i));
			sql += ") ";
		}
		
		if (check(params.get("areaFrom")) || check(params.get("areaTo"))) {
			sql += " AND ";
			if (check(params.get("areaFrom")))
				sql += String.format(" r.value >= %s ", params.get("areaFrom"));
			if (check(params.get("areaTo"))) {
				if (check(params.get("areaFrom")))
					sql += " AND ";
				sql += String.format(" r.value <= %s ", params.get("areaTo"));
			}
			params.remove("areaFrom");
			params.remove("areaTo");
		}
		
		if (check(params.get("rentPriceFrom")) || check(params.get("rentPriceTo"))) {
			sql += " AND ";
			if (check(params.get("rentPriceFrom")))
				sql += String.format(" rentprice >= %s ", params.get("rentPriceFrom"));
			if (check(params.get("rentPriceTo"))) {
				if (check(params.get("rentPriceFrom")))
					sql += " AND ";
				sql += String.format(" rentprice <= %s ", params.get("rentPriceTo"));
			}
			params.remove("rentPriceFrom");
			params.remove("rentPriceTo");
		}
		
		if (params.containsKey("typeCode"))
			params.remove("typeCode");
		params.put("managerphonenumber", params.get("managerPhone"));
		params.remove("managerPhone");
		for (Map.Entry<String, String> entry : params.entrySet())
			if (!entry.getValue().equals("")) {
				if (entry.getKey().equals("name") || entry.getKey().equals("street") || entry.getKey().equals("ward") || entry.getKey().equals("managerName"))
					sql += String.format(" AND %s LIKE '%%%s%%' ", entry.getKey().toLowerCase(), entry.getValue());
				else
					sql += String.format(" AND %s = %s ", entry.getKey().toLowerCase(), entry.getValue());
			}
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			BuildingResponseDTO building = new BuildingResponseDTO();
			building.setName(rs.getString("name"));
			building.setAddress(String.format("%s, %s, %s", rs.getString("street"), rs.getString("ward"),
					rs.getString("districtname")));
			building.setNumberOfBasement(rs.getInt("numberofbasement"));
			building.setMangerName(rs.getString("managername"));
			building.setManagerPhone(rs.getString("managerphonenumber"));
			building.setFloorArea(rs.getInt("floorarea"));
			building.setRentArea(rs.getInt("rentarea"));
			building.setRentPrice(rs.getInt("rentprice"));
			building.setServiceFee(rs.getString("servicefee"));
			building.setBrokerageFee(rs.getDouble("brokeragefee"));
			buildingReponseDTOs.add(building);
		}
		return buildingReponseDTOs;
	}
	
	private boolean check(String s) {
		if (s != null)
			return !s.equals("");
		return false;
	}
}
