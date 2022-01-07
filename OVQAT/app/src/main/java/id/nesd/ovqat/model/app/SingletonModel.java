package id.nesd.ovqat.model.app;

import id.nesd.ovqat.model.UserModel;

public class SingletonModel {
    private static SingletonModel INSTANCE = null;
    private UserModel user;

    private SingletonModel() {}

    public static SingletonModel getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingletonModel();
        }
        return(INSTANCE);
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public void clear(){
        INSTANCE = null;
    }
}
