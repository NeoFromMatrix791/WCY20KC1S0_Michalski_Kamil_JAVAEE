package pl.edu.wat.configuration;

import javax.enterprise.context.Dependent;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.config.inject.ConfigProperties;

@Getter
@Setter
@ConfigProperties
@Dependent
public class DatabaseConfiguration {

    private String url;

    private String name;
}
