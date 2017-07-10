package info.androidhive.retrofit.model;

import java.util.Map;


/**
 * Created by Krishna on 09-07-2017.
 */

public class TokenAccessRequest {

    private Map<String, String> _userName ;
    private Map<String, String>  _password ;
    private Map<String, String>  _grantType ;
    private Map<String, String>  _scope ;

    public Map<String, String> get_username() {
        return _userName;
    }

    public void set_username(Map<String, String> _username) {
        this._userName = _username;
    }

    public Map<String, String> get_password() {
        return _password;
    }

    public void set_password(Map<String, String> _password) {
        this._password = _password;
    }

    public Map<String, String> get_grantType() {
        return _grantType;
    }

    public void set_grantType(Map<String, String> _grantType) {
        this._grantType = _grantType;
    }

    public Map<String, String> get_scope() {
        return _scope;
    }

    public void set_scope(Map<String, String> _scope) {
        this._scope = _scope;
    }
}
