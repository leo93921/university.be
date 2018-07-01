package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.ReportingStatus;
import it.unisalento.se.exceptions.ReportingStatusNotSupported;
import it.unisalento.se.models.ReportingStatusModel;

public class ReportingStatusDaoToDto {

    public static ReportingStatusModel convert(ReportingStatus status) throws ReportingStatusNotSupported {

        if (status.getName().equalsIgnoreCase(Constants.REPORTED))
            return ReportingStatusModel.REPORTED;
        if (status.getName().equalsIgnoreCase(Constants.WORKING_ON_IT))
            return ReportingStatusModel.WORKING_ON_IT;
        if (status.getName().equalsIgnoreCase(Constants.FIXED))
            return ReportingStatusModel.FIXED;
        if (status.getName().equalsIgnoreCase(Constants.NOT_FIXED))
            return ReportingStatusModel.NOT_FIXED;
        if (status.getName().equalsIgnoreCase(Constants.NOT_A_PROBLEM))
            return ReportingStatusModel.NOT_A_PROBLEM;
        if (status.getName().equalsIgnoreCase(Constants.DELAYED))
            return ReportingStatusModel.DELAYED;
        else
            throw new ReportingStatusNotSupported("Reporting Status cannot be " + status.getName());
    }
}


