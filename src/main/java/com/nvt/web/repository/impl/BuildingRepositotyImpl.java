package com.nvt.web.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.nvt.web.repository.BuildingRepository;
import com.nvt.web.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositotyImpl implements BuildingRepository {
	public static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	public static final String USER = "root";
	public static final String PASS = "TuanTuan552005;";

	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params) {
		List<BuildingEntity> list = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS); Statement stm = con.createStatement();) {
			String query = "SELECT * FROM building WHERE 1=1";
			int i = 1;
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				if (entry.getValue().toString().matches("\\d+"))
					query += String.format(" %s = %s", entry.getKey(), entry.getValue().toString());
				else
					query += String.format(" %s like '%%%s%%'", entry.getKey(), entry.getValue().toString());
				if (i++ < params.size())
					query += " AND ";
			}
			ResultSet res = stm.executeQuery(query);
			while (res.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setId(res.getInt("id"));
				building.setName(res.getString("name"));
				building.setDistrictId(res.getString("districtId"));
				building.setWard(res.getString("ward"));
				building.setNumberOfBasement(res.getInt("numberofbasement"));
				building.setStreet(res.getString("street"));
				list.add(building);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
