package se.pedramfk.etl.framework.commons.immutable.fs;

import scala.Function1;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.types.StructType;
import se.pedramfk.etl.framework.conf.RuntimeConfiguration;
import se.pedramfk.etl.framework.commons.DataType;
import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.lit;


public final class SampleDataLayer<T extends DataType> extends ImmutableFsLayer<SampleData> {

    public SampleDataLayer(RuntimeConfiguration conf, Encoder<SampleData> encoder, StructType schema) {
        super(conf, encoder, schema);
    }

    @Override
    public Function1<Dataset<SampleData>, Dataset<SampleData>> filter() {
        return (df) -> df.where(col("active").notEqual(lit(false)));
    }

}
