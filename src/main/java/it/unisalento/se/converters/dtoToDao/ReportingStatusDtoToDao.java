package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.ReportingStatus;
import it.unisalento.se.exceptions.ReportingStatusNotSupported;
import it.unisalento.se.models.ReportingStatusModel;

public class ReportingStatusDtoToDao {
    public static ReportingStatus convert(ReportingStatusModel model) throws ReportingStatusNotSupported {
        ReportingStatus status = new ReportingStatus();

        if (model.equals(ReportingStatusModel.REPORTED)) {
            status.setId(1);
            status.setName(Constants.REPORTED);
        } else if (model.equals(ReportingStatusModel.WORKING_ON_IT)) {
            status.setId(2);
            status.setName(Constants.WORKING_ON_IT);
        } else if (model.equals(ReportingStatusModel.FIXED)) {
            status.setId(3);
            status.setName(Constants.FIXED);
        } else if (model.equals(ReportingStatusModel.NOT_FIXED)) {
            status.setId(4);
            status.setName(Constants.NOT_FIXED);

        } else if (model.equals(ReportingStatusModel.NOT_A_PROBLEM)) {
            status.setId(5);
            status.setName(Constants.NOT_A_PROBLEM);

        } else if (model.equals(ReportingStatusModel.DELAYED)) {
            status.setId(6);
            status.setName(Constants.DELAYED);

        } else
            throw new ReportingStatusNotSupported("Reporting Status cannot be " + status.getName());


        return status;
    }


}
