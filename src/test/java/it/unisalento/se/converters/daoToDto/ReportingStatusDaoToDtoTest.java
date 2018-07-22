package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.ReportingStatus;
import it.unisalento.se.exceptions.ReportingStatusNotSupported;
import it.unisalento.se.models.ReportingStatusModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReportingStatusDaoToDtoTest {

    @Test
    public void convert_RECEIVED() throws ReportingStatusNotSupported {
        ReportingStatus s = new ReportingStatus();
        s.setId(1);
        s.setName(Constants.RECEIVED);

        ReportingStatusModel model = ReportingStatusDaoToDto.convert(s);

        assertEquals(ReportingStatusModel.RECEIVED, model);
    }

    @Test
    public void convert_IN_PROGRESS() throws ReportingStatusNotSupported {
        ReportingStatus s = new ReportingStatus();
        s.setId(1);
        s.setName(Constants.IN_PROGRESS);

        ReportingStatusModel model = ReportingStatusDaoToDto.convert(s);

        assertEquals(ReportingStatusModel.IN_PROGRESS, model);
    }

    @Test
    public void convert_SOLVED() throws ReportingStatusNotSupported {
        ReportingStatus s = new ReportingStatus();
        s.setId(1);
        s.setName(Constants.SOLVED);

        ReportingStatusModel model = ReportingStatusDaoToDto.convert(s);

        assertEquals(ReportingStatusModel.SOLVED, model);
    }

    @Test
    public void convert_REFUSED() throws ReportingStatusNotSupported {
        ReportingStatus s = new ReportingStatus();
        s.setId(1);
        s.setName(Constants.REFUSED);

        ReportingStatusModel model = ReportingStatusDaoToDto.convert(s);

        assertEquals(ReportingStatusModel.REFUSED, model);
    }

}