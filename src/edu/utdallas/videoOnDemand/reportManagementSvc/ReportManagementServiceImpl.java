package edu.utdallas.videoOnDemand.reportManagementSvc;
/**
 * @author : Mahalakshmi Balasubramanian;
 * @date 7/10/2014;
 * @version 1;
 * @job ReportManagementServiceImpl;
 */
import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;

import edu.utdallas.videoOnDemand.dao.ReportDAO;
import edu.utdallas.videoOnDemand.entities.Report;
import edu.utdallas.videoOnDemand.services.ReportManagementService;
import edu.utdallas.videoOnDemand.services.ServiceException;

public class ReportManagementServiceImpl implements ReportManagementService {
	private ReportDAO reportDAO;
	private static final Logger logger = Logger
			.getLogger(ReportManagementServiceImpl.class);
	
	public void setReportDAO(ReportDAO reportDAO) {
		this.reportDAO = reportDAO;
	}
	@Override
	public List<ReportDTO> retrieveUsersReport(Date startDate, Date endDate) throws ServiceException {
		logger.info("Retrieving AllUsersInfo");
		try {
			List<Report> usersReport = reportDAO.retrieveUsersReport(startDate, endDate);
			List<ReportDTO> usersReportDTO = ReportDTOValidator.convertToDTO(usersReport);
			return usersReportDTO; 
		} catch (Exception ex) {
		throw new ServiceException(ex.getMessage(), ex);
		}
	}

	@Override
	public List<ReportDTO> retrieveMoviesReport(Date startDate, Date endDate) throws ServiceException {
		logger.info("Retrieving AllMoviesInfo");
		try {
			List<Report> moviesReport = reportDAO.retrieveMoviesReport(startDate, endDate);
			List<ReportDTO> moviesReportDTO = ReportDTOValidator.convertToDTO(moviesReport);
			return moviesReportDTO; 
		} catch (Exception ex) {
		throw new ServiceException(ex.getMessage(), ex);
		}
	}


}
