package mm.aeon.com.zconfig.aop;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <br/>
 * It is a intercepter class. Which is used to log for all DAO and Service
 * classes.<br/>
 * A method before start, this class automatically will log method name and
 * parameter as JSON format.<br/>
 * A method before end, this class automatically will log method name and result
 * as JSON format.<br/>
 * Note : A method before end will not be hit if there is occurred an
 * exception.<br/>
 */
@Aspect
@Component
public class LoggingAspect extends PointCutConfig {

	private Logger logger = LogManager.getLogger(LoggingAspect.class);

	@Before("publicMethodInsideServiceBean() || publicMethodInsideDaoBean() || publicMethodInsideControllerBean()")
	public void beforeServiceDao(JoinPoint joinPoint) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String logMessage = String.format("%s.%s() method has been started.", joinPoint.getSignature().getDeclaringType(), joinPoint.getSignature().getName());
			for (Object obj : joinPoint.getArgs()) {

				if (obj instanceof Collection<?>) {
					String parameter = mapper.writeValueAsString(((Collection) obj).size());
					logger.debug(logMessage + " Parameter => " + parameter);
				} else if (obj instanceof MultipartFile) {
					logger.debug(logMessage + " Parameter => " + obj.getClass());
				} else {
					String parameter = mapper.writeValueAsString(obj);
					logger.debug(logMessage + " Parameter => " + parameter);
				}

			}

		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	// only hit without throwing exception
	@AfterReturning(pointcut = "publicMethodInsideServiceBean() || publicMethodInsideDaoBean() || publicMethodInsideControllerBean()", returning = "result")
	public void afterReturningServiceDao(JoinPoint joinPoint, Object result) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String logMessage = String.format("%s.%s() method has been finished.", joinPoint.getSignature().getDeclaringType(), joinPoint.getSignature().getName());
			String returnResult = mapper.writeValueAsString(result);
			logger.debug(logMessage + " Return Result => " + returnResult);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

}
