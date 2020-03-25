package mm.aeon.com.zconfig.aop;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import mm.aeon.com.zconfig.MessageCode;
import mm.aeon.com.zconfig.dto.ApiError;
import mm.aeon.com.zconfig.dto.ApiStatus;
import mm.aeon.com.zconfig.dto.Result;

/**
 * <br/>
 * It is like a intercepter class. Which is used to make customize response
 * object for client response.<br/>
 * If the system is thrown any exception or occurred error, this class
 * automatically response <code>ApiError</code> object to client.<br/>
 * Otherwise, this class automatically response <code>Result</code> object to
 * client.<br/>
 */
@ControllerAdvice
public class ResponseEntityHandler implements ResponseBodyAdvice<Object> {

	private Logger logger = LogManager.getLogger(LoggingAspect.class);

	@Autowired
	private MessageSource messageSource;

	@Override
	public boolean supports(final MethodParameter returnType, final Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(final Object body, final MethodParameter returnType, final MediaType selectedContentType,
			final Class<? extends HttpMessageConverter<?>> selectedConverterType, final ServerHttpRequest request, final ServerHttpResponse response) {
		response.setStatusCode(HttpStatus.OK);
		if (body instanceof RuntimeException) {
			RuntimeException re = (RuntimeException) body;
			ApiError apiError = new ApiError(ApiStatus.FAILED);
			apiError.setMessage(re.getMessage());
			if (apiError.getMessage().equals(messageSource.getMessage(MessageCode.ACCOUNT_LOCKED, null, null))) {
				apiError.setMessageCode(MessageCode.ACCOUNT_LOCKED);
			} else {
				apiError.setMessageCode(MessageCode.UNEXPECTED_ERROR);
			}

			apiError.setTimestamp(LocalDateTime.now());
			logger.debug(apiError);
			return apiError;
		}
		if (body instanceof ApiError) {
			logger.debug(body);
			return body;
		}
		if (body instanceof String) {
			logger.debug(body);
			return body;
		}
		if (returnType.getParameterType().isAssignableFrom(void.class)) {
			Result result = new Result();
			result.setData(body);
			result.setStatus(ApiStatus.SUCCESS);
			logger.debug(result);
			return result;
		}
		String versionOneCheckUpdateMethodName = returnType.getExecutable().getName();
		if (versionOneCheckUpdateMethodName.equals("checkUpdateStatus")) {
			return body;
		} else if (body instanceof byte[]) {
			logger.debug(body);
			return body;
		} else {
			Result result = new Result();
			result.setData(body);
			result.setStatus(ApiStatus.SUCCESS);
			logger.debug(result);
			return result;
		}

	}
}