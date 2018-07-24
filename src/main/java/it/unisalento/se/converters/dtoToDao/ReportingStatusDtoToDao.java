package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.ReportingStatus;
import it.unisalento.se.exceptions.ReportingStatusNotSupported;
import it.unisalento.se.models.ReportingStatusModel;

import java.util.HashMap;
import java.util.Map;

import static it.unisalento.se.models.ReportingStatusModel.*;

public class ReportingStatusDtoToDao {
    public static ReportingStatus convert(ReportingStatusModel model) throws ReportingStatusNotSupported {

        Map<ReportingStatusModel, ReportingStatus> map = new HashMap<>();
        map.put(RECEIVED, getStatus(1, Constants.RECEIVED));
        map.put(IN_PROGRESS, getStatus(2, Constants.IN_PROGRESS));
        map.put(SOLVED, getStatus(3, Constants.SOLVED));
        map.put(REFUSED, getStatus(4, Constants.REFUSED));

        if (!map.keySet().contains(model)) {
            throw new ReportingStatusNotSupported("Reporting Status cannot be " + model.name());
        }

        return map.get(model);

    }

    private static ReportingStatus getStatus(int ID, String statusName) {
        ReportingStatus status = new ReportingStatus();
        status.setId(ID);
        status.setName(statusName);
        return status;
    }


}
