package domain;

public class RegisteredDetails {
    private String username;
    private String password;
    private Integer bankAccount;

    public RegisteredDetails(String username, String password, Integer bankAccount) {
        this.username = username;
        this.password = password;
        this.bankAccount = bankAccount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Integer bankAccount) {
        this.bankAccount = bankAccount;
    }
}
