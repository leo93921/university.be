package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.ReportingStatus;
import it.unisalento.se.exceptions.ReportingStatusNotSupported;
import it.unisalento.se.models.ReportingStatusModel;

public class ReportingStatusDtoToDao {
    public static ReportingStatus convert(ReportingStatusModel model) throws ReportingStatusNotSupported {
        ReportingStatus status = new ReportingStatus();

        if (model.equals(ReportingStatusModel.RECEIVED)) {
            status.setId(1);
            status.setName(Constants.RECEIVED);
        } else if (model.equals(ReportingStatusModel.IN_PROGRESS)) {
            status.setId(2);
            status.setName(Constants.IN_PROGRESS);
        } else if (model.equals(ReportingStatusModel.SOLVED)) {
            status.setId(3);
            status.setName(Constants.SOLVED);
        } else if (model.equals(ReportingStatusModel.REFUSED)) {
            status.setId(4);
            status.setName(Constants.REFUSED);


        } else
            throw new ReportingStatusNotSupported("Reporting Status cannot be " + status.getName());


        return status;
    }


}
