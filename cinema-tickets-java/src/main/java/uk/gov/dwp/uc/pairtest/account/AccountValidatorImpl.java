package uk.gov.dwp.uc.pairtest.account;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class AccountValidatorImpl implements AccountValidator {

    final static Logger logger = LogManager.getLogger(AccountValidator.class);

    @Override
    public boolean isAccountValid(Long accountNumber) {
        if (accountNumber == null || accountNumber <= 0) {
            logger.warn("Invalid account number, value is not positive");
            return false;
        }
        return true;
    }

}
