package se.pedramfk.etl.framework.commons;

import java.util.concurrent.TimeoutException;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;


public interface StreamDataLayer<T extends DataSchema> {
    
    public StreamingQuery getStreamingQuery() throws TimeoutException;

    public void startStreamingQuery() throws TimeoutException, StreamingQueryException;

}
