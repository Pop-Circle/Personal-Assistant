package pop_circle.personalassistant;

import android.app.Application;

/**
 * Created by Stashie on 2015/09/27.
 */
public class MyApplication extends Application {
    //This class lets me make a global var of whos logged in. Cool right!?
    private int userId = -1;

    public int getLoggedUser(){
        return userId;
    }
    // use with: String s = ((MyApplication) this.getApplication()).getLoggedUser();

    public void setUserId(int _user)
    {
        this.userId = _user;
    }
}
