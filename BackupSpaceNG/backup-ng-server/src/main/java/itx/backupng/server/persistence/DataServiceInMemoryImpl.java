package itx.backupng.server.persistence;

import java.util.ArrayList;

public class DataServiceInMemoryImpl implements DataService {

    private PersistentData persistentData;

    public DataServiceInMemoryImpl() {
        persistentData = new PersistentData(new ArrayList<>());
    }

    @Override
    public PersistentData getData() {
        return persistentData;
    }

    @Override
    public void persistData() {
        //NOOP
    }

}
