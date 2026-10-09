import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner pemindai = new Scanner(System.in);

        // tambahkan nasabah awal sebagai contoh
        bank.addCustomer("Aura", "Iftita");
        bank.addCustomer("Pania", "Putri");

        // tambahkan rekening awal untuk nasabah pertama
        bank.getCustomer(0).setAccount(new Account(500000));
        bank.getCustomer(0).setAccount(new Account(1000000));

        boolean berjalan = true;

        while (berjalan) {
            System.out.println("\n========== SISTEM BANK & ATM ==========");
            System.out.println("1. Tampilkan Daftar Nasabah");
            System.out.println("2. Tambah Nasabah Baru");
            System.out.println("3. Buat Rekening Baru untuk Nasabah");
            System.out.println("4. Transaksi ATM (Cek Saldo / Setor / Tarik)");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1-5): ");

            int pilihanUtama = pemindai.nextInt();
            pemindai.nextLine(); // Membaca karakter newline

            switch (pilihanUtama) {
                case 1:
                    System.out.println("\n--- DAFTAR NASABAH ---");
                    if (bank.getNumOfCustomers() == 0) {
                        System.out.println("Belum ada nasabah terdaftar.");
                    } else {
                        for (int i = 0; i < bank.getNumOfCustomers(); i++) {
                            Customer n = bank.getCustomer(i);
                            System.out.println("[" + i + "] " + n.getFirstName() + " " + n.getLastName() +
                                               " (Jumlah Rekening: " + n.getNumOfAccounts() + ")");
                        }
                    }
                    break;

                case 2:
                    System.out.print("Masukkan Nama Depan: ");
                    String namaDepan = pemindai.nextLine();
                    System.out.print("Masukkan Nama Belakang: ");
                    String namaBelakang = pemindai.nextLine();
                    bank.addCustomer(namaDepan, namaBelakang);
                    System.out.println("Nasabah " + namaDepan + " " + namaBelakang + " berhasil ditambahkan!");
                    break;

                case 3:
                    System.out.print("Masukkan Indeks Nasabah: ");
                    int indeksNasabah = pemindai.nextInt();
                    Customer nasabah = bank.getCustomer(indeksNasabah);

                    if (nasabah != null) {
                        System.out.print("Masukkan Saldo Awal Rekening: Rp ");
                        double saldoAwal = pemindai.nextDouble();
                        nasabah.setAccount(new Account(saldoAwal));
                        System.out.println("Rekening baru berhasil dibuat!");
                    } else {
                        System.out.println("Nasabah tidak ditemukan!");
                    }
                    break;

                case 4:
                    System.out.print("Masukkan Indeks Nasabah: ");
                    int idxNasabah = pemindai.nextInt();
                    Customer nasabahAktif = bank.getCustomer(idxNasabah);

                    if (nasabahAktif != null && nasabahAktif.getNumOfAccounts() > 0) {
                        System.out.print("Masukkan Indeks Rekening (0 sampai " + (nasabahAktif.getNumOfAccounts() - 1) + "): ");
                        int idxRekening = pemindai.nextInt();
                        Account rekeningAktif = nasabahAktif.getAccount(idxRekening);

                        if (rekeningAktif != null) {
                            System.out.println("\n--- MENU TRANSAKSI ATM ---");
                            System.out.println("1. Cek Saldo");
                            System.out.println("2. Setor Tunai");
                            System.out.println("3. Tarik Tunai");
                            System.out.print("Pilih opsi transaksi (1-3): ");
                            int pilihanATM = pemindai.nextInt();

                            switch (pilihanATM) {
                                case 1:
                                    System.out.println("Saldo Anda saat ini: Rp " + rekeningAktif.getBalance());
                                    break;

                                case 2:
                                    System.out.print("Masukkan jumlah setor tunai: Rp ");
                                    double jumlahSetor = pemindai.nextDouble();
                                    if (rekeningAktif.deposit(jumlahSetor)) {
                                        System.out.println("Setor tunai berhasil! Saldo baru: Rp " + rekeningAktif.getBalance());
                                    } else {
                                        System.out.println("Setor tunai gagal! Jumlah harus lebih dari 0.");
                                    }
                                    break;

                                case 3:
                                    System.out.print("Masukkan jumlah tarik tunai: Rp ");
                                    double jumlahTarik = pemindai.nextDouble();
                                    if (rekeningAktif.withdraw(jumlahTarik)) {
                                        System.out.println("Tarik tunai berhasil! Saldo sisa: Rp " + rekeningAktif.getBalance());
                                    } else {
                                        System.out.println("Tarik tunai gagal! Saldo tidak mencukupi atau jumlah tidak valid.");
                                    }
                                    break;

                                default:
                                    System.out.println("Opsi transaksi tidak valid!");
                                    break;
                            }
                        } else {
                            System.out.println("Rekening tidak ditemukan!");
                        }
                    } else {
                        System.out.println("Nasabah tidak ditemukan atau belum memiliki rekening!");
                    }
                    break;

                case 5:
                    berjalan = false;
                    System.out.println("Terima kasih telah menggunakan layanan sistem bank!");
                    break;

                default:
                    System.out.println("Pilihan menu tidak valid! Silakan masukkan angka 1-5.");
                    break;
            }
        }

        pemindai.close();
    }
}