/**
 * 
 */
package com.mycompany.webapp.service.impl;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.appfuse.model.User;
import org.appfuse.service.impl.PasswordTokenManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Component;

import com.mycompany.webapp.model.CFCUser;
import com.mycompany.webapp.service.CFCPasswordTokenManager;

/**
 * @author Administrator
 *
 */
@Component("cfcPasswordTokenManager")
public class CFCPasswordTokenManagerImpl implements CFCPasswordTokenManager {
	
	 private final Log log = LogFactory.getLog(PasswordTokenManagerImpl.class);

	    private final SimpleDateFormat expirationTimeFormat = new SimpleDateFormat("yyyyMMddHHmm");
	    private final int expirationTimeTokenLength = expirationTimeFormat.toPattern().length();

	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    @Autowired(required = false)
	    private SaltSource saltSource;


	

	/* (non-Javadoc)
	 * @see com.mycompany.webapp.service.CFCPasswordTokenManager#generateRecoveryToken(com.mycompany.webapp.model.CFCUser)
	 */
	@Override
	public String generateRecoveryToken(CFCUser user) {
		// TODO Auto-generated method stub
		   if (user != null) {
	            final String tokenSource = getTokenSource(user);
	            final String expirationTimeStamp = expirationTimeFormat.format(getExpirationTime());
	            final Object salt = saltSource != null ? saltSource.getSalt(user) : null;
	            return expirationTimeStamp + passwordEncoder.encode(expirationTimeStamp + tokenSource);
	        }
	        return null;
	}

	/* (non-Javadoc)
	 * @see com.mycompany.webapp.service.CFCPasswordTokenManager#isRecoveryTokenValid(com.mycompany.webapp.model.CFCUser, java.lang.String)
	 */
	@Override
	public boolean isRecoveryTokenValid(CFCUser user, String token) {
		// TODO Auto-generated method stub
		if (user != null && token != null) {
            final String expirationTimeStamp = getTimestamp(token);
            final String tokenWithoutTimestamp = getTokenWithoutTimestamp(token);
            final String tokenSource = expirationTimeStamp + getTokenSource(user);
            final Object salt = saltSource != null ? saltSource.getSalt(user) : null;
            final Date expirationTime = parseTimestamp(expirationTimeStamp);

            return expirationTime != null && expirationTime.after(new Date())
                    && passwordEncoder.matches(tokenWithoutTimestamp, tokenSource);
        }
        return false;
	}

	/* (non-Javadoc)
	 * @see com.mycompany.webapp.service.CFCPasswordTokenManager#invalidateRecoveryToken(com.mycompany.webapp.model.CFCUser, java.lang.String)
	 */
	@Override
	public void invalidateRecoveryToken(CFCUser user, String token) {
		// TODO Auto-generated method stub

	}
	
	/**
     * Return tokens expiration time, now + 1 day.
     * 
     * @return
     */
    private Date getExpirationTime() {
        return DateUtils.addDays(new Date(), 1);
    }

    private String getTimestamp(final String token) {
        return StringUtils.substring(token, 0, expirationTimeTokenLength);
    }

    /**
     * 
     * @param user
     * @return
     */
    private String getTokenSource(final CFCUser user) {
        return user.getEmail() + user.getVersion() + user.getPassword();
    }

    private String getTokenWithoutTimestamp(final String token) {
        return StringUtils.substring(token, expirationTimeTokenLength, token.length());
    }

    private Date parseTimestamp(final String timestamp) {
        try {
            return expirationTimeFormat.parse(timestamp);
        } catch (final ParseException e) {
            return null;
        }
    }

}
