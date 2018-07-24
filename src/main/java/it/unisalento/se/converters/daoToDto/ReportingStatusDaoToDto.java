package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.ReportingStatus;
import it.unisalento.se.exceptions.ReportingStatusNotSupported;
import it.unisalento.se.models.ReportingStatusModel;

import java.util.HashMap;
import java.util.Map;

public class ReportingStatusDaoToDto {

    public static ReportingStatusModel convert(ReportingStatus status) throws ReportingStatusNotSupported {

        Map<String, ReportingStatusModel> map = new HashMap<>();
        map.put(Constants.RECEIVED, ReportingStatusModel.RECEIVED);
        map.put(Constants.IN_PROGRESS, ReportingStatusModel.IN_PROGRESS);
        map.put(Constants.SOLVED, ReportingStatusModel.SOLVED);
        map.put(Constants.REFUSED, ReportingStatusModel.REFUSED);

        String upperCaseStatus = status.getName().toUpperCase();

        if (!map.keySet().contains(upperCaseStatus)) {
            throw new ReportingStatusNotSupported("Reporting Status cannot be " + status.getName());
        }

        return map.get(upperCaseStatus);

    }
}


