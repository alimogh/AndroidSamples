package com.sdwfqin.sample.dagger.model;

import android.content.Context;

import com.sdwfqin.sample.dagger.Controller.MoneyController;
import com.sdwfqin.sample.dagger.Controller.OrderController;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sdwfqin on 2017/4/24.
 * <p>
 * 通过这个Module绑定Controller
 */
// @Modules 类似于我们的模块，提供各种实例跟对象
@Module
public class UserModule {

    private Context mContext;

    public UserModule(Context context) {
        this.mContext = context;
    }

    // @Named 有多个构造方法时使用它标注不同的名字
    // @Provides 在Modules中，我们定义的方法是用这个注解，以此来告诉Dagger2我们想要提供哪些实例和对象
    @Named("debug")
    @Provides
    public OrderController providerOrderController() {
        return new OrderController("lisi");
    }

    @Named("release")
    @Provides
    public OrderController providerUserControllerNameAndAge() {
        return new OrderController("lisi", 15);
    }

    @Provides
    public MoneyController providerMoneyController() {
        return new MoneyController(mContext);
    }
}