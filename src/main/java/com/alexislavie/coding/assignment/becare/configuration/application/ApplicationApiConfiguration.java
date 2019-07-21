package com.alexislavie.coding.assignment.becare.configuration.application;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ToString
@Configuration
public class ApplicationApiConfiguration {

    private String restBasePath;

    private String webSocketEndPoint;

    private String webSocketTopicDestinationPrefix;
}
