package com.project.handly.Configuration;

import com.project.handly.Middelwares.VerifiedInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final VerifiedInterceptor verifiedInterceptor;

    public WebConfig(VerifiedInterceptor verifiedInterceptor) {
        this.verifiedInterceptor = verifiedInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(verifiedInterceptor)
                .addPathPatterns("/api/authorized/verified/**");
    }
}
