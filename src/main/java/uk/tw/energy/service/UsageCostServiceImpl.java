/**
 * 
 */
package uk.tw.energy.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.tw.energy.domain.ElectricityReading;
import uk.tw.enery.dto.CostDetailsDto;

/**
 * @author nc23370_sandeep_kumar_soni
 *
 */
@Service

public class UsageCostServiceImpl implements UsageCostService{
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	MeterReadingService meterReadingService;
	
	

	@Override
	public CostDetailsDto getUsageCostByDays(Integer days, String smartMeterId) {
		CostDetailsDto costDetailsDto = new CostDetailsDto();
		
		if(days == null || smartMeterId == null) {
			throw new RuntimeException("smartMeterId and days cannot be null. ");
		}
		//get pricing plan
		String pricePlan = 	accountService.getPricePlanIdForSmartMeterId(smartMeterId);
		if(pricePlan == null) {
			
			throw new RuntimeException("No pricing plan available for this meter. ");
			
		}
		
		Optional<List<ElectricityReading>> readings = 	meterReadingService.getReadings(smartMeterId);
		
		if(readings.isPresent()) {
			List<ElectricityReading> meterReading = readings.get();
			
			BigDecimal totalTimeElapsedForDays =	calculateTimeElapsed(meterReading, days);
			
			BigDecimal average = calculateAverageReading(meterReading, days);

		        BigDecimal averagedCost = average.divide(totalTimeElapsedForDays, RoundingMode.HALF_UP);
		        BigDecimal priceUnitCost = new BigDecimal(0.2);
		        BigDecimal cost =     averagedCost.multiply(priceUnitCost);
		        costDetailsDto.setDays(days);
		        costDetailsDto.setSmartMeterid(smartMeterId);
		        costDetailsDto.setTotalCost(cost);
			
			
		}
		
		
		
		
		return costDetailsDto;
	}
	
	
	/**
	 * Get total time elapsed 
	 * from current time to given days in past
	 * 
	 * @param electricityReadings
	 * @param days
	 * @return
	 */
	 public BigDecimal calculateTimeElapsed(List<ElectricityReading> electricityReadings, Integer days) {
		 
		 Calendar cal = new GregorianCalendar();
		 cal.add(Calendar.DAY_OF_MONTH, -days);
		 Date previousDaysDate = cal.getTime();
		Long timeInMilis =  previousDaysDate.getTime();
		Instant pastTime = Instant.ofEpochMilli(timeInMilis);
		 
	        ElectricityReading first = electricityReadings.stream().filter(
	        		l -> l.getTime().isAfter(pastTime))
	                .min(Comparator.comparing(ElectricityReading::getTime))
	                .get();
	        ElectricityReading last = electricityReadings.stream()
	                .max(Comparator.comparing(ElectricityReading::getTime))
	                .get();

	        return BigDecimal.valueOf(Duration.between(first.getTime(), last.getTime()).getSeconds() / 3600.0);
	    }
	 
	 
	 /**
	  * 
	  * @param electricityReadings
	  * @return
	  */
	 private BigDecimal calculateAverageReading(List<ElectricityReading> electricityReadings, Integer days) {
		 Calendar cal = new GregorianCalendar();
		 cal.add(Calendar.DAY_OF_MONTH, -days);
		 Date previousDaysDate = cal.getTime();
		Long timeInMilis =  previousDaysDate.getTime();
		Instant pastTime = Instant.ofEpochMilli(timeInMilis);
	        BigDecimal summedReadings = electricityReadings.stream()
	        		.filter(
	    	        		l -> l.getTime().isAfter(pastTime))
	                .map(ElectricityReading::getReading)
	                .reduce(BigDecimal.ZERO, (reading, accumulator) -> reading.add(accumulator));

	        return summedReadings.divide(BigDecimal.valueOf(electricityReadings.size()), RoundingMode.HALF_UP);
	    }
	    
	    
	   

}
