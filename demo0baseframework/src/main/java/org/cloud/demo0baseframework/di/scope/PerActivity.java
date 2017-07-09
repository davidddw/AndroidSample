package org.cloud.demo0baseframework.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author d05660ddw
 * @version 1.0 2017/6/19
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
