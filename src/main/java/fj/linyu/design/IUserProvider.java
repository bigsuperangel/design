package fj.linyu.design;

import java.util.ArrayList;

/**
 * Created by linyu on 2016/9/7.
 */
public interface IUserProvider {
    public ArrayList<User> findUser(IUserSpecification userSpec);

}
