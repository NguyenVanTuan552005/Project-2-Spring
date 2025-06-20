package com.nvt.web.controlleradvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nvt.web.customexceptions.InvalidDataException;
import com.nvt.web.dto.ErrorBuildingDTO;

@ControllerAdvice
public class ControllerAdvisor {
	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<ErrorBuildingDTO> handleInvalidException(InvalidDataException ex) {
		ErrorBuildingDTO error = new ErrorBuildingDTO();
		
		error.setError(ex.getMessage());
		List<String> details = new ArrayList<>();
		details.add("Name must not null!");
		details.add("NumberOfBase must not null!");
		error.setDetail(details);
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
