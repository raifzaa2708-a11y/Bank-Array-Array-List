import java.util.ArrayList;

public class Bank {
    private ArrayList<Customer> daftarNasabah;

    public Bank() {
        daftarNasabah = new ArrayList<>();
    }

    public void addCustomer(String namaDepan, String namaBelakang) {
        daftarNasabah.add(new Customer(namaDepan, namaBelakang));
    }

    public int getNumOfCustomers() {
        return daftarNasabah.size();
    }

    public Customer getCustomer(int indeks) {
        if (indeks >= 0 && indeks < daftarNasabah.size()) {
            return daftarNasabah.get(indeks);
        }
        return null;
    }
}
