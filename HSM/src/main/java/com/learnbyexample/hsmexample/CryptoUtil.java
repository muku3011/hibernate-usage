package com.learnbyexample.hsmexample;

import iaik.pkcs.pkcs11.*;
import iaik.pkcs.pkcs11.wrapper.PKCS11Exception;

import java.io.IOException;

class CryptoUtil {

    private static final int SLOT_NUMBER = 0;
    private static final String TOKEN_LOGIN_PIN = "1234";

    private static Token token;
    private static Module module;

    /**
     * Initialize module in the beginning by providing crypto library
     *
     * @throws IOException,    if there are errors finding crypto library
     * @throws TokenException, if there are errors while loading crypto library
     */
    static void initializeModule() throws IOException, TokenException {
        // Initialize module
        module = Module.getInstance("C:\\SoftHSM2\\lib\\softhsm2-x64.dll");
        module.initialize(null);
    }

    /**
     * When all crypto operations are performed, it's advised to finalize module.
     * Initialize module before extracting token.
     *
     * @throws TokenException, if there are any errors while finalizing module
     */
    static void finalizeModule() throws TokenException {
        if (module != null) {
            module.finalize(null);
        }
    }

    /**
     * Initialize or extract to token from slot, slot which is extracted from the module
     *
     * @throws TokenException, if there are errors while getting token from module
     */
    static void initializeToken() throws TokenException {
        if (token == null) {
            // Select slot and get token
            Slot slot = module.getSlotList(Module.SlotRequirement.TOKEN_PRESENT)[SLOT_NUMBER];
            token = slot.getToken();
        }
    }

    /**
     * Open session from token object and login into session with token pin
     *
     * @return Session, session object to perform crypto operations
     * @throws TokenException, if there is any exception while opening or logging in into session
     */
    static Session openSessionAndLogin() throws TokenException {
        Session session = token.openSession(Token.SessionType.SERIAL_SESSION, Token.SessionReadWriteBehavior.RW_SESSION, null, null);
        session.login(Session.UserType.USER, TOKEN_LOGIN_PIN.toCharArray());
        return session;
    }

    /**
     * Close opened session after performing crypto operations
     *
     * @param session, session object to be closed
     * @throws TokenException, if there is any exception while closing session
     */
    static void closeSession(Session session) throws TokenException {
        try {
            session.findObjectsFinal();
        } catch (TokenException e) {
            if (e instanceof PKCS11Exception && ((PKCS11Exception) e).getErrorCode() == 145) {
                session.closeSession();
                return;
            }
        }
        session.closeSession();
    }
}
