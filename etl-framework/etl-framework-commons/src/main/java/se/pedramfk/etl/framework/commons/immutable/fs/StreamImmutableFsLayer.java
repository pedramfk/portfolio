package se.pedramfk.etl.framework.commons.immutable.fs;

import java.util.concurrent.TimeoutException;
import org.apache.spark.sql.streaming.Trigger;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;

import se.pedramfk.etl.framework.commons.DataSchema;
import se.pedramfk.etl.framework.commons.immutable.ImmutableDataLayer;
import se.pedramfk.etl.framework.commons.immutable.StreamImmutableDataLayer;
import se.pedramfk.etl.framework.conf.DataConfiguration.StreamConf;


public class StreamImmutableFsLayer<T extends DataSchema> implements StreamImmutableDataLayer<T> {

    private final ImmutableFsLayer<T> layer;

    public StreamImmutableFsLayer(ImmutableFsLayer<T> layer) {
        this.layer = layer;
    }

    @Override
    public StreamingQuery getStreamingQuery() throws TimeoutException {

        final StreamConf streamConf = layer.getConfiguration().getBaseLayerConfiguration().getStreamConf();

        return layer
            .getDataStream()
            .writeStream()
            .format(streamConf.getFormat())
            .option("outputMode", streamConf.getOutputMode())
            .trigger(Trigger.ProcessingTime(streamConf.getTrigger()))
            .start();

    }

    @Override
    public void startStreamingQuery() throws TimeoutException, StreamingQueryException {

        StreamingQuery streamingQuery = getStreamingQuery();

        streamingQuery.awaitTermination();

    }

    @Override
    public ImmutableDataLayer<T> getDataLayer() {
        return layer;
    }

    
}
