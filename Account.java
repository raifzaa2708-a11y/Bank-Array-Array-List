public class Account {
    protected double saldo;

    public Account(double saldoAwal) {
        this.saldo = saldoAwal;
    }

    public double getBalance() {
        return saldo;
    }

    public boolean deposit(double jumlah) {
        if (jumlah > 0) {
            saldo += jumlah;
            return true;
        }
        return false;
    }

    public boolean withdraw(double jumlah) {
        if (saldo >= jumlah && jumlah > 0) {
            saldo -= jumlah;
            return true;
        }
        return false;
    }
}