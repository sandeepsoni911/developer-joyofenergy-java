/**
 * 
 */
package uk.tw.energy.service;

import uk.tw.enery.dto.CostDetailsDto;

/**
 * @author nc23370_sandeep_kumar_soni
 *
 */
public interface UsageCostService {
	
	/**
	 * To get usage cost for given
	 * smartmeterId and for given days
	 * @param days
	 * @return
	 */
	CostDetailsDto getUsageCostByDays(Integer days, String smartMeterId);

}
