package it.unisalento.se.test.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisalento.se.common.CommonUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class TestUtils {

    public static String toJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static MockMvc getMockMvc(Object controller) {
        return MockMvcBuilders
                .standaloneSetup(controller)
                .setViewResolvers(CommonUtils.getCommonUtils().getCommonResolver())
                .build();
    }

}
