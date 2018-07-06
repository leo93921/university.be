package it.unisalento.se.configurations;

import it.unisalento.se.common.Constants;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebAppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        super.customizeRegistration(registration);

        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
                Constants.TMP_SAVE_LOCATION,
                Constants.MAX_FILE_SIZE,
                Constants.MAX_REQUEST_SIZE,
                Constants.FILE_SIZE_THRESHOLD);

        registration.setMultipartConfig(multipartConfigElement);
    }
}
