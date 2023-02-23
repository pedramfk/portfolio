package se.pedramfk.etl.framework.conf;

import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.util.ArrayList;
import org.yaml.snakeyaml.Yaml;



@Getter 
@Setter
public final class DataConfiguration {

    private static final InputStream DEFAULT_CONF_FILE = ClassLoader.getSystemResourceAsStream("data-conf.yaml");

    private BaseLayerConf baseLayerConf;
    private EvolvedLayerConf evolvedLayerConf;

    public static final DataConfiguration create(InputStream conf) { return new Yaml().loadAs(conf, DataConfiguration.class); }
    public static final DataConfiguration create() { return new Yaml().loadAs(DEFAULT_CONF_FILE, DataConfiguration.class); }

    @Getter 
    @Setter
    public static final class BaseLayerConf {
        private String path;
        private String format;
        private StreamConf streamConf;
    }
    @Getter 
    @Setter
    public static final class EvolvedLayerConf {
        private String path;
        private String format;
        private ArrayList<String> partitioning;
        private StreamConf streamConf;
    }
    @Getter 
    @Setter
    public static final class StreamConf {
        private String format;
        private String outputMode;
        private String trigger;
    }

}


