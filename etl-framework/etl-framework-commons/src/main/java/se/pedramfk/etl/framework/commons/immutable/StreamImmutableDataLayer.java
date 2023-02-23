package se.pedramfk.etl.framework.commons.immutable;

import se.pedramfk.etl.framework.commons.DataSchema;
import se.pedramfk.etl.framework.commons.StreamDataLayer;


public interface StreamImmutableDataLayer<T extends DataSchema> extends StreamDataLayer<T> {
    
    public ImmutableDataLayer<T> getDataLayer();

}
