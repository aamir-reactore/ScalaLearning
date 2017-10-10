package akfak;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by yadu on 19/10/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Alias {
    String entity();
    boolean canResolveType() default true;
}