package se.pedramfk.etl.framework.commons;

import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.types.StructField;
import static org.apache.spark.sql.types.DataTypes.LongType;
import static org.apache.spark.sql.types.DataTypes.IntegerType;
import static org.apache.spark.sql.types.DataTypes.StringType;
import static org.apache.spark.sql.types.DataTypes.BooleanType;
import static org.apache.spark.sql.types.DataTypes.TimestampType;
import static org.apache.spark.sql.types.DataTypes.createStructField;


@Getter 
@Setter
public final class SampleData implements DataSchema {
    
    private static final long serialVersionUID = 100L;

    private long id;
    private boolean active;
    private String name;
    private int age;
    private Timestamp timestamp;

    private static final StructType STRUCT_TYPE = new StructType(new StructField[] {
        createStructField("id", LongType, false), 
        createStructField("active", BooleanType, false), 
        createStructField("name", StringType, false), 
        createStructField("age", IntegerType, false), 
        createStructField("timestamp", TimestampType, false), 
    }); 

    public static final Encoder<SampleData> ENCODER() { 
        return Encoders.bean(SampleData.class); 
    }
    
    public static final StructType SCHEMA() { 
        return STRUCT_TYPE;
    }

}