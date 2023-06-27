package pl.edu.wat.configuration;

import javax.enterprise.context.Dependent;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.config.inject.ConfigProperties;

@Getter
@Setter
@ConfigProperties(prefix = "deployment")
@Dependent
public class DeploymentConfiguration {

    private DeploymentStage stage;
}
