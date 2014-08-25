package edu.utdallas.videoOnDemand.reportManagementSvc;
/**
 * @author : Mahalakshmi Balasubramanian;
 * @date 7/23/2014;
 * @version 1;
 * @job ReportDTOValidator;
 */
import java.util.ArrayList;
import java.util.List;

import edu.utdallas.videoOnDemand.entities.Report;
public class ReportDTOValidator {
	static public List<ReportDTO> convertToDTO(List<Report> usersOrMoviesReportDTO) 
	{
		List<ReportDTO> results = new ArrayList<ReportDTO>();
		for(Report userOrMovie: usersOrMoviesReportDTO) {
			ReportDTO reportDTO = new ReportDTO(userOrMovie);
			results.add(reportDTO);
		}
		return results;
	}


}
