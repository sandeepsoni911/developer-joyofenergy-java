/**
 * 
 */
package uk.tw.enery.dto;

import java.math.BigDecimal;

/**
 * @author nc23370_sandeep_kumar_soni
 *
 */
public class CostDetailsDto {
	
	private String smartMeterid;
	private Integer days;
	
	private BigDecimal totalCost;
	/**
	 * @return the totalCost
	 */
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	/**
	 * @param totalCost the totalCost to set
	 */
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	/**
	 * @return the smartMeterid
	 */
	public String getSmartMeterid() {
		return smartMeterid;
	}
	/**
	 * @param smartMeterid the smartMeterid to set
	 */
	public void setSmartMeterid(String smartMeterid) {
		this.smartMeterid = smartMeterid;
	}
	/**
	 * @return the days
	 */
	public Integer getDays() {
		return days;
	}
	/**
	 * @param days the days to set
	 */
	public void setDays(Integer days) {
		this.days = days;
	}

	
	
}
