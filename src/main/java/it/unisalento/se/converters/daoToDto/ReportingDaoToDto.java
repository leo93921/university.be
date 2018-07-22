package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.dao.Reporting;
import it.unisalento.se.exceptions.ReportingStatusNotSupported;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.ReportingModel;

public class ReportingDaoToDto {


    public static ReportingModel convert(Reporting dao) throws UserTypeNotSupported, ReportingStatusNotSupported {
        ReportingModel model = new ReportingModel();
        model.setID(dao.getId());
        if (dao.getSupportDevice() != null) {
            model.setSupportDevice(SupportDeviceDaoToDto.convert(dao.getSupportDevice()));
        }
        model.setNote(dao.getNote());
        model.setLastModified(dao.getLastModified());
        model.setDoneBy(UserDaoToDto.convert(dao.getUser()));
        model.setReportingStatus(ReportingStatusDaoToDto.convert(dao.getReportingStatus()));
        model.setClassroom(ClassroomDaoToDto.convert(dao.getClassroom()));
        model.setProblemDescription(dao.getProblemDescription());

        return model;
    }
}
