package com.sdwfqin.mvpseed.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 描述：作用域注解
 *
 * @author sdwfqin
 */
@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}
