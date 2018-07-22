package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.ReportingStatus;
import it.unisalento.se.exceptions.ReportingStatusNotSupported;
import it.unisalento.se.models.ReportingStatusModel;
import org.junit.Assert;
import org.junit.Test;

public class ReportingStatusDtoToDaoTest {
    @Test
    public void convert1() throws ReportingStatusNotSupported {


        ReportingStatus dao = ReportingStatusDtoToDao.convert(ReportingStatusModel.RECEIVED);


        Assert.assertEquals(Constants.RECEIVED, dao.getName());

    }

    @Test
    public void convert2() throws ReportingStatusNotSupported {


        ReportingStatus dao = ReportingStatusDtoToDao.convert(ReportingStatusModel.IN_PROGRESS);


        Assert.assertEquals(Constants.IN_PROGRESS, dao.getName());

    }


    @Test
    public void convert3() throws ReportingStatusNotSupported {


        ReportingStatus dao = ReportingStatusDtoToDao.convert(ReportingStatusModel.SOLVED);


        Assert.assertEquals(Constants.SOLVED, dao.getName());

    }

    @Test
    public void convert4() throws ReportingStatusNotSupported {


        ReportingStatus dao = ReportingStatusDtoToDao.convert(ReportingStatusModel.REFUSED);


        Assert.assertEquals(Constants.REFUSED, dao.getName());

    }


}
