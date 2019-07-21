package com.alexislavie.coding.assignment.becare.configuration;

import com.alexislavie.coding.assignment.becare.configuration.application.ApplicationConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Configuration
public class WebMvcRegistration implements WebMvcRegistrations {

    private final ApplicationConfiguration applicationConfiguration;

    @Override
    @SuppressWarnings("squid:MaximumInheritanceDepth")
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {

        return new RequestMappingHandlerMapping() {

            @Override
            protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
                Class<?> beanType = method.getDeclaringClass();

                // Do not remove this condition!
                if (AnnotationUtils.findAnnotation(beanType, RestController.class) != null) {
                    PatternsRequestCondition apiPattern =
                            new PatternsRequestCondition(applicationConfiguration.getApi().getRestBasePath())
                                    .combine(mapping.getPatternsCondition());

                    mapping = new RequestMappingInfo(mapping.getName(), apiPattern,
                            mapping.getMethodsCondition(), mapping.getParamsCondition(),
                            mapping.getHeadersCondition(), mapping.getConsumesCondition(),
                            mapping.getProducesCondition(), mapping.getCustomCondition());
                }

                super.registerHandlerMethod(handler, method, mapping);
            }
        };
    }
}
