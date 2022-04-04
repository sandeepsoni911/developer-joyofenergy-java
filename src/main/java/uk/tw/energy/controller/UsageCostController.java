package uk.tw.energy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import uk.tw.energy.service.UsageCostService;
import uk.tw.enery.dto.ApiResponse;

/**
 * To get usage cost based on usage
 * @author nc23370_sandeep_kumar_soni
 * @param <CostDetailsDto>
 *
 */
@RestController
public class UsageCostController<CostDetailsDto> {
	
	@Autowired
	UsageCostService usageCostService;
	
	
	@GetMapping(value="/usagecost/{smartMeterId}{/days}")
	public ApiResponse<CostDetailsDto> getUsageCostByDays(@PathVariable Integer days, @PathVariable String smartMeterId) {
		ApiResponse<CostDetailsDto> response = null;
		CostDetailsDto costDetailsDto = (CostDetailsDto) usageCostService.getUsageCostByDays(days, smartMeterId);
		if( costDetailsDto != null) {
			response = new ApiResponse<CostDetailsDto>("SUCCESS", 200, costDetailsDto);
		}else {
			response = new ApiResponse<CostDetailsDto>("FAILED", 200, costDetailsDto);
		}
		
		return response;
	}

}
