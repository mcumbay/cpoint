package com.dfwcomputech.cpoint.service.resource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dfwcomputech.cpoint.common.CPointException;
import com.dfwcomputech.cpoint.integration.model.Chore;
import com.dfwcomputech.cpoint.service.IChoreService;
import com.dfwcomputech.cpoint.service.resource.dto.ChoreDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(CPointEndPoints.CHORES)
@Slf4j
public class ChoreController {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private IChoreService choreService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ChoreDto post(@RequestBody ChoreDto choreDto) {
		try {
			Chore chore = choreService.createChore(mapper.map(choreDto, Chore.class));
			return chore != null ? mapper.map(chore, ChoreDto.class) : null;
		} catch (CPointException e) {
			log.error("Validation Error: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, e.getMessage(), e);
		}
	}
}
