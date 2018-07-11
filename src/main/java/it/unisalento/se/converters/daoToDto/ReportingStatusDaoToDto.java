package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.ReportingStatus;
import it.unisalento.se.exceptions.ReportingStatusNotSupported;
import it.unisalento.se.models.ReportingStatusModel;

public class ReportingStatusDaoToDto {

    public static ReportingStatusModel convert(ReportingStatus status) throws ReportingStatusNotSupported {

        if (status.getName().equalsIgnoreCase(Constants.RECEIVED))
            return ReportingStatusModel.RECEIVED;
        if (status.getName().equalsIgnoreCase(Constants.IN_PROGRESS))
            return ReportingStatusModel.IN_PROGRESS;
        if (status.getName().equalsIgnoreCase(Constants.SOLVED))
            return ReportingStatusModel.SOLVED;
        if (status.getName().equalsIgnoreCase(Constants.REFUSED))
            return ReportingStatusModel.REFUSED;

        else
            throw new ReportingStatusNotSupported("Reporting Status cannot be " + status.getName());
    }
}


