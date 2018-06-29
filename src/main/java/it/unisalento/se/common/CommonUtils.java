package it.unisalento.se.common;

import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

public class CommonUtils {

    private static CommonUtils istance = null;

    private CommonUtils() {
    }

    public static synchronized CommonUtils getCommonUtils() {
        if (istance == null) {
            istance = new CommonUtils();
        }
        return istance;
    }

    public ViewResolver getCommonResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
