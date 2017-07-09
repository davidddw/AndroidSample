package org.cloud.demo0baseframework.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author d05660ddw
 * @version 1.0 2017/6/20
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface ZhihuUrl {
}
