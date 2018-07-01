package it.unisalento.se.iservices;

import it.unisalento.se.dao.ReportingStatus;
import it.unisalento.se.exceptions.ReportingStatusNotSupported;

public interface IReportingStatusService {
    ReportingStatus getReportingStatusDaoByName(String name) throws ReportingStatusNotSupported;
}
