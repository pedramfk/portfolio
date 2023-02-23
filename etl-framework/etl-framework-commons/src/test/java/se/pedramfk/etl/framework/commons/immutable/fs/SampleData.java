package se.pedramfk.etl.framework.commons.immutable.fs;

import se.pedramfk.etl.framework.commons.DataType;

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

/*
id,active,name,age,timestamp
1,true,pedram,30,2023-02-14T04:00:00.245
2,false,karin,35,2023-02-14T04:01:00.245
3,true,test1,20,2023-02-14T04:10:00.245
4,false,test2,3,2023-02-15T06:00:00.245
5,true,test3,4,2023-02-16T14:00:00.100
 */

@Getter 
@Setter
public final class SampleData implements DataType {
    
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