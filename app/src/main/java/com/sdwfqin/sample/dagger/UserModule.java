package com.sdwfqin.sample.dagger;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sdwfqin on 2017/4/24.
 */
@Module
public class UserModule {

    private Context mContext;

    public UserModule(Context context) {
        this.mContext = context;
    }

    @Provides
    public OrderController providerOrderController(){
        return new OrderController();
    }

    @Provides
    public MoneyController providerMoneyController() {
        return new MoneyController(mContext);
    }
}
