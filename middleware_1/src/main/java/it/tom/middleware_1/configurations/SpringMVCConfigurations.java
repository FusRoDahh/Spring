package it.tom.middleware_1.configurations;

import it.tom.middleware_1.interceptors.APILoggingInterceptor;
import it.tom.middleware_1.interceptors.LegacyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMVCConfigurations implements WebMvcConfigurer {

    @Autowired
    private APILoggingInterceptor apiLoggingInterceptor;
    @Autowired
    private LegacyInterceptor legacyInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiLoggingInterceptor);
        registry.addInterceptor(legacyInterceptor).addPathPatterns("/**");
    }
}
