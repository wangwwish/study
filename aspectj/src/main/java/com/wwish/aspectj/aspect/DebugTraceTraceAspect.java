
package com.wwish.aspectj.aspect;

import android.os.Build;
import android.os.Looper;
import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.concurrent.TimeUnit;

/**
 * 跟踪被DebugTrace注解标记的方法和构造函数
 */
@Aspect
public class DebugTraceTraceAspect {

  private static volatile boolean enabled = true;

  private static final String POINTCUT_CLASS =
          "within(@com.wwish.aspectj.annotation.DebugTrace *)";

  private static final String POINTCUT_METHOD =
      "execution(@com.wwish.aspectj.annotation.DebugTrace * *(..))";

  private static final String POINTCUT_CONSTRUCTOR =
      "execution(@com.wwish.aspectj.annotation.DebugTrace *.new(..))";

  @Pointcut(POINTCUT_CLASS)
  public void withinAnnotatedClass() {}

  @Pointcut(POINTCUT_METHOD)
  public void methodAnnotatedWithDebugTrace() {}

  @Pointcut(POINTCUT_CONSTRUCTOR)
  public void constructorAnnotatedDebugTrace() {}

  @Around("methodAnnotatedWithDebugTrace() || constructorAnnotatedDebugTrace()")
  public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    String className = methodSignature.getDeclaringType().getSimpleName();
    String methodName = methodSignature.getName();

    enterMethod(joinPoint);

    long startNanos = System.nanoTime();
    Object result = joinPoint.proceed();
    long stopNanos = System.nanoTime();
    long lengthMillis = TimeUnit.NANOSECONDS.toMillis(stopNanos - startNanos);

    exitMethod(joinPoint, result, lengthMillis);

    return result;
  }

  private static void enterMethod(ProceedingJoinPoint joinPoint) {
    if (!enabled) return;

    CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();

    Class<?> cls = codeSignature.getDeclaringType();
    String methodName = codeSignature.getName();
    String[] parameterNames = codeSignature.getParameterNames();
    Object[] parameterValues = joinPoint.getArgs();

    StringBuilder builder = new StringBuilder("\u21E2 ");
    builder.append(methodName).append('(');
    for (int i = 0; i < parameterValues.length; i++) {
      if (i > 0) {
        builder.append(", ");
      }
      builder.append(parameterNames[i]).append('=');
      builder.append(Strings.toString(parameterValues[i]));
    }
    builder.append(')');

    if (Looper.myLooper() != Looper.getMainLooper()) {
      builder.append(" [Thread:\"").append(Thread.currentThread().getName()).append("\"]");
    }

    Log.v(asTag(cls), builder.toString());

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {

    }
  }

  private static void exitMethod(ProceedingJoinPoint joinPoint, Object result, long lengthMillis) {
    if (!enabled) return;

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {

    }

    Signature signature = joinPoint.getSignature();

    Class<?> cls = signature.getDeclaringType();
    String methodName = signature.getName();
    boolean hasReturnType = signature instanceof MethodSignature
            && ((MethodSignature) signature).getReturnType() != void.class;

    StringBuilder builder = new StringBuilder("\u21E0 ")
            .append(methodName)
            .append(" [")
            .append(lengthMillis)
            .append("ms]");

    if (hasReturnType) {
      builder.append(" = ");
      builder.append(Strings.toString(result));
    }

    Log.v(asTag(cls), builder.toString());
  }

  private static String asTag(Class<?> cls) {
    if (cls.isAnonymousClass()) {
      return asTag(cls.getEnclosingClass());
    }
    return cls.getSimpleName();
  }


}
