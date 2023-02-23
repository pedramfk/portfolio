package se.pedramfk.etl.framework.commons.immutable.fs;

import java.io.InputStream;
import java.util.concurrent.TimeoutException;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import se.pedramfk.etl.framework.commons.SampleData;
import se.pedramfk.etl.framework.commons.SampleDataLayer;
import se.pedramfk.etl.framework.conf.RuntimeConfiguration;


@TestInstance(Lifecycle.PER_CLASS)
public final class TestStreamImmutableFsLayer {

    private static final InputStream SPARK_CONF = ClassLoader.getSystemResourceAsStream("spark-conf.yaml");
    private static final InputStream DATA_CONF = ClassLoader.getSystemResourceAsStream("data-conf.yaml");

    private StreamImmutableFsLayer<SampleData> streamImmutableFsLayer;

    private static final RuntimeConfiguration getSampleConfiguration() throws ClassNotFoundException {
        return new RuntimeConfiguration(SPARK_CONF, DATA_CONF);
    }

    private static final SampleDataLayer<SampleData> getSampleDataLayer() throws ClassNotFoundException {
        return new SampleDataLayer<SampleData>(getSampleConfiguration(), SampleData.ENCODER(), SampleData.SCHEMA());
    }

    private static final StreamImmutableFsLayer<SampleData> getStreamSampleDataLayer() throws ClassNotFoundException {
        return new StreamImmutableFsLayer<SampleData>(getSampleDataLayer()) {
            @Override
            public void startStreamingQuery() throws StreamingQueryException, TimeoutException {
                getStreamingQuery().awaitTermination(20000);
            }
        };
    }

    @BeforeAll
    public void setup() throws ClassNotFoundException {
        streamImmutableFsLayer = getStreamSampleDataLayer();
    }

    @AfterAll
    public void cleanup() {
        streamImmutableFsLayer.getDataLayer().getConfiguration().closeSparkSession();
    }

    @Test
    public void testStartStreamingQuery() throws TimeoutException, StreamingQueryException {
        streamImmutableFsLayer.startStreamingQuery();
    }

}
