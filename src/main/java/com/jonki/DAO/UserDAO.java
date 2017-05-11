package com.jonki.DAO;

public interface UserDAO {
    public boolean checkRepeatedUsername(final String username); // True, if it exists.
    public boolean checkRepeatedEmail(final String email); // True, if it exists.
    public boolean checkActivationCode(final Long id, final int code);
    public boolean checkChangeCode(final Long id, final int code);
    public Long getIDByUsername(final String username);
    public Long getIDByEmail(final String email);
}
