package com.mulodo.fiveneed.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import com.mulodo.fiveneed.constant.LogFormat;

/**
 * AOP: Log Advisor<br>
 *
 * Method logger.
 *
 * @author thomc
 *
 */
@Aspect
@Component
public class LogAdvisor extends BaseAdvisor {

	/*-----------------------------------------------
	* Public
	*-----------------------------------------------*/
	/**
	 * Controller Logger
	 *
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.mulodo.fiveneed..*Controller.*(..))")
	public Object invoke(ProceedingJoinPoint pjp) throws Throwable {
		Object ret = null;
		try {
			super.logger.info(String.format(LogFormat.AOP_BEFORE.getPattern(),
					pjp.getTarget().getClass(), pjp.getSignature().getName(),
					pjp.getArgs()));

			ret = pjp.proceed();
			return ret;
		} finally {
			super.logger.info(String.format(LogFormat.AOP_AFTER.getPattern(),
					pjp.getTarget().getClass(), pjp.getSignature().getName(),
					ret));
		}
	}
}
