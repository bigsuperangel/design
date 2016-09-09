package fj.linyu.design;

/**
 * Created by linyu on 2016/9/7.
 */
public abstract class CompositeSpecification implements IUserSpecification {
    //是否满足条件由实现类实现
    public abstract boolean isSatisfiedBy(User user);
    //and操作
    public IUserSpecification and(IUserSpecification spec) {
        return new AndSpecification(this,spec);
    }
    //not操作
    public IUserSpecification not() {
        return new NotSpecification(this);
    }
    //or操作
    public IUserSpecification or(IUserSpecification spec) {
        return new OrSpecification(this,spec);
    }
}
