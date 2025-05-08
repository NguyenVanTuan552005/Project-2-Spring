package com.nvt.web.dto;

public class BuildingDTO {
	private String name;
/*	
 	@JsonProperty("district_id")
 	private String districtId;
	//Json có key: district_id thì sẽ được hứng bởi districtId
*/
	private String districtId;
	private String ward;
	private Integer numberOfBasement;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}

	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
}
