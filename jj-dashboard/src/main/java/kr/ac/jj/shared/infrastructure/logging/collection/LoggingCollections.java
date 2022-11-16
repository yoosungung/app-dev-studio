package kr.ac.jj.shared.infrastructure.logging.collection;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;

public class LoggingCollections {

    public final NitriteCollection tbSysLog;
    public final NitriteCollection tbSysLogQuery;
    public final NitriteCollection tbSysLogError;
    public final NitriteCollection tbSysLogLogin;
    public final NitriteCollection tbSysLogMenu;

    public LoggingCollections(Nitrite loggingDatabase) {
        this.tbSysLog = loggingDatabase.getCollection("tbSysLog");
        this.tbSysLogQuery = loggingDatabase.getCollection("tbSysLogQuery");
        this.tbSysLogError = loggingDatabase.getCollection("tbSysLogError");
        this.tbSysLogLogin = loggingDatabase.getCollection("tbSysLogLogin");
        this.tbSysLogMenu = loggingDatabase.getCollection("tbSysLogMenu");
    }

}
