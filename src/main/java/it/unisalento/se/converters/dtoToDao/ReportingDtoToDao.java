package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.Reporting;
import it.unisalento.se.exceptions.ReportingStatusNotSupported;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.ReportingModel;

public class ReportingDtoToDao {
    public static Reporting convert(ReportingModel model) throws UserTypeNotSupported, ReportingStatusNotSupported {
        Reporting dao = new Reporting();
        dao.setId(model.getID());
        if (model.getSupportDevice() != null) {
            dao.setSupportDevice(SupportDeviceDtoToDao.convert(model.getSupportDevice()));
        }
        dao.setNote(model.getNote());
        dao.setLastModified(model.getLastModified());
        dao.setUser(UserDtoToDao.convert(model.getDoneBy()));
        dao.setReportingStatus(ReportingStatusDtoToDao.convert(model.getReportingStatus()));
        dao.setClassroom(ClassroomDtoToDao.convert(model.getClassroom()));
        dao.setProblemDescription(model.getProblemDescription());

        return dao;
    }


}

