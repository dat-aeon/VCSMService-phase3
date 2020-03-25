package mm.aeon.com.zconfig.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * <br/>
 * It is a point-cut configuration for <code>ExceptionHandlerAspect</code> and
 * <code>LoggingAspect</code>
 */
public class PointCutConfig {

	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	protected void controllerBean() {
	}

	@Pointcut("within(@org.springframework.stereotype.Service *)")
	protected void serviceBean() {
	}

	@Pointcut("within(@org.springframework.stereotype.Repository *)")
	protected void daoBean() {
	}

	@Pointcut("execution(* mm.aeon.com.zgen.mapper.*.*(..)) || execution(* mm.aeon.com.custommapper.*.*(..))")
	protected void mapper() {
	}

	@Pointcut("execution(public * *(..))")
	protected void publicMethod() {
	}

	@Pointcut("publicMethod() && controllerBean()")
	protected void publicMethodInsideControllerBean() {
	}

	@Pointcut("publicMethod() && serviceBean()")
	protected void publicMethodInsideServiceBean() {
	}

	@Pointcut("publicMethod() && daoBean()")
	protected void publicMethodInsideDaoBean() {
	}

	@Pointcut("publicMethod() && mapper()")
	protected void publicMethodInsideMapper() {
	}
}
