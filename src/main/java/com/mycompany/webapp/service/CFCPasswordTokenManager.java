package com.mycompany.webapp.service;

import org.appfuse.service.impl.PasswordTokenManager;

import com.mycompany.webapp.model.CFCUser;

public interface CFCPasswordTokenManager  {
    
	
	/**
     * {@inheritDoc}
     */
    String generateRecoveryToken(CFCUser user);

    /**
     * {@inheritDoc}
     */
    boolean isRecoveryTokenValid(CFCUser user, String token);

    void invalidateRecoveryToken(CFCUser user, String token);
	
}
