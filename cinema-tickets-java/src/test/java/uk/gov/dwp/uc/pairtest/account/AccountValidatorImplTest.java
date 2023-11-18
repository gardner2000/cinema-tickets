package uk.gov.dwp.uc.pairtest.account;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AccountValidatorImplTest {

    private AccountValidator accountValidator;

    private static final Long MAXIMUM_ACCOUNT_NUMBER = Long.MAX_VALUE;

    @Before
    public void setUp() {
        accountValidator = new AccountValidatorImpl();
    }

    @Test
    public void nullAccountNumberIsInvalid() {
        Assert.assertFalse(accountValidator.isAccountValid(null));
    }

    @Test
    public void negativeAccountNumberIsInvalid() {
        Assert.assertFalse(accountValidator.isAccountValid(-1L));
    }

    @Test
    public void zeroAccountNumberIsInvalid() {
        Assert.assertFalse(accountValidator.isAccountValid(0L));
    }

    @Test
    public void positiveAccountNumberIsValid() {
        Assert.assertTrue(accountValidator.isAccountValid(1L));
    }

    @Test
    public void maximumAccountNumberIsValid() {
        Assert.assertTrue(accountValidator.isAccountValid(MAXIMUM_ACCOUNT_NUMBER));
    }
}