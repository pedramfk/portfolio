package se.pedramfk.etl.framework.commons.immutable.fs;

import java.io.InputStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import se.pedramfk.etl.framework.commons.SampleData;
import se.pedramfk.etl.framework.commons.SampleDataLayer;
import se.pedramfk.etl.framework.conf.RuntimeConfiguration;


@TestInstance(Lifecycle.PER_CLASS)
public final class TestImmutableFsLayer {

    private static final InputStream SPARK_CONF = ClassLoader.getSystemResourceAsStream("spark-conf.yaml");
    private static final InputStream DATA_CONF = ClassLoader.getSystemResourceAsStream("data-conf.yaml");

    private SampleDataLayer<SampleData> immutableFsLayer;

    @BeforeAll
    public void setup() throws ClassNotFoundException {
        immutableFsLayer = new SampleDataLayer<>(
            new RuntimeConfiguration(SPARK_CONF, DATA_CONF), 
            SampleData.ENCODER(), 
            SampleData.SCHEMA());
    }

    @AfterAll
    public void cleanup() {
        immutableFsLayer.getConfiguration().closeSparkSession();
    }

    @Test
    public void testGetData() {
        System.out.println(immutableFsLayer.getConfiguration().getBaseLayerConfiguration().getPath());
        immutableFsLayer.getData().show(false);
    }
    
}