package pl.edu.wat.control;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import org.eclipse.microprofile.config.inject.ConfigProperties;
import pl.edu.wat.configuration.DatabaseConfiguration;
import pl.edu.wat.configuration.DeploymentConfiguration;
import pl.edu.wat.configuration.DeploymentStage;
import pl.edu.wat.entity.HashEntity;

@ApplicationScoped
public class DatabaseProvider {

    @Inject
    @ConfigProperties(prefix = "database.dev")
    private DatabaseConfiguration databaseDevConfiguration;

    @Inject
    @ConfigProperties(prefix = "database.prod")
    private DatabaseConfiguration databaseProdConfiguration;

    @Inject
    @ConfigProperties(prefix = "deployment")
    private DeploymentConfiguration deployment;

    @Produces
    @ApplicationScoped
    public Datastore createDatastore() {
        String databaseUrl;
        String databaseName;

        if (DeploymentStage.DEV.equals(deployment.getStage())) {
            databaseUrl = databaseDevConfiguration.getUrl();
            databaseName = databaseDevConfiguration.getName();
        } else {
            databaseUrl = databaseProdConfiguration.getUrl();
            databaseName = databaseProdConfiguration.getName();
        }

        final Morphia morphia = new Morphia();
        morphia.mapPackage(HashEntity.class.getPackage().getName());
        final Datastore datastore = morphia.createDatastore(new MongoClient(new MongoClientURI(databaseUrl)), databaseName);
        datastore.ensureIndexes();

        return datastore;
    }
}
