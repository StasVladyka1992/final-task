package by.vladyka.epam.dao.util;

import java.util.ResourceBundle;

import static by.vladyka.epam.dao.util.DBParameter.BUNDLE_NAME;

/**
 * Created by Vladyka Stas
 * on 06.03.2019 at 3:09
 **/
public class DBResourceManager {
    private final static DBResourceManager instance = new DBResourceManager();
    private ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME);

    private DBResourceManager(){}

    public static DBResourceManager getInstance (){
        return instance;
    }

    public String getValue (String resourceKey){
        return bundle.getString(resourceKey);
    }
}
