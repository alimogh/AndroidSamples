package com.sdwfqin.mvpseed.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 描述：自定义注解，区分不同的BaseUrl
 *
 * @author sdwfqin
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface TestUrl {

}
