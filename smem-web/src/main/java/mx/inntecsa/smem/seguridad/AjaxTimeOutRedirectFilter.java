package mx.inntecsa.smem.seguridad;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.security.web.util.ThrowableCauseExtractor;
import org.springframework.web.filter.GenericFilterBean;

public class AjaxTimeOutRedirectFilter extends GenericFilterBean
{
 
	private Logger logger = Logger.getLogger(AjaxTimeOutRedirectFilter.class);
 
    private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();
    private AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();
 
    private int customSessionExpiredErrorCode = 901;
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        try
        {
            chain.doFilter(request, response);
        }
        catch (IOException ex)
        {
            throw ex;
        }
        catch (Exception ex)
        {
            Throwable[] causeChain = throwableAnalyzer.determineCauseChain(ex);
            RuntimeException ase = (AuthenticationException) throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class, causeChain);
 
            if (ase == null)
            {
                ase = (AccessDeniedException) throwableAnalyzer.getFirstThrowableOfType(AccessDeniedException.class, causeChain);
            }
 
            if (ase != null)
            {
                if (ase instanceof AuthenticationException)
                {
                    throw ase;
                }
                else if (ase instanceof AccessDeniedException)
                {
 
                    if (authenticationTrustResolver.isAnonymous(SecurityContextHolder.getContext().getAuthentication()))
                    {
                        logger.info("User session expired or not logged in yet");
                        
                        String ajaxHeader = ((HttpServletRequest) request).getHeader("faces-request");
 
                        if ("partial/ajax".equals(ajaxHeader))
                        {
                            HttpServletResponse resp = (HttpServletResponse) response;
                            resp.sendError(this.customSessionExpiredErrorCode);
                        }
                        else
                        {
                            throw ase;
                        }
                    }
                    else
                    {
                        throw ase;
                    }
                }
            }
 
        }
    }
 
    private static final class DefaultThrowableAnalyzer extends ThrowableAnalyzer
    {
        /**
         * @see org.springframework.security.web.util.ThrowableAnalyzer#initExtractorMap()
         */
        protected void initExtractorMap()
        {
            super.initExtractorMap();
 
            registerExtractor(ServletException.class, new ThrowableCauseExtractor()
            {
                public Throwable extractCause(Throwable throwable)
                {
                    ThrowableAnalyzer.verifyThrowableHierarchy(throwable, ServletException.class);
                    return ((ServletException) throwable).getRootCause();
                }
            });
        }
 
    }
 
    public void setCustomSessionExpiredErrorCode(int customSessionExpiredErrorCode)
    {
        this.customSessionExpiredErrorCode = customSessionExpiredErrorCode;
    }
}
