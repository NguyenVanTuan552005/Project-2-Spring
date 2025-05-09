package com.nvt.web.controlleradvice;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nvt.web.dto.ErrorDTO;

@ControllerAdvice
public class ControllerAdvisor {
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<ErrorDTO> handleSQLException(SQLException ex) {
		ErrorDTO error = new ErrorDTO();
		error.setError(ex.getMessage());
		error.setDetails(List.of(ex.getStackTrace()).stream().map(s -> s.toString()).collect(Collectors.toList()));
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
