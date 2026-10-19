# 📊 Analisis Proyek ePayment

## 1. Ringkasan Proyek

| Aspek | Detail |
|-------|--------|
| **Nama** | ePayment |
| **Tipe** | Aplikasi Desktop (Point of Sale / POS) |
| **Bahasa** | Java (Swing GUI) |
| **IDE** | Apache NetBeans |
| **Database** | MySQL (`toko_moedah`) |
| **Build** | Apache Ant (`build.xml`) |
| **Author** | Tria |

ePayment adalah **sistem kasir / Point of Sale (POS)** untuk toko bernama **"Toko Moedah"**. Aplikasi ini mengelola transaksi jual-beli, data produk, karyawan, supplier, member, serta laporan keuangan — semuanya berbasis GUI desktop Java Swing.

---

## 2. Arsitektur & Struktur Package

```
src/
├── Frame/          → Autentikasi (Login, Login RFID, Lupa Password, Progress Bar)
├── Karyawan/       → Fitur Karyawan (Dashboard, Transaksi Jual/Beli, Stok, Supplier)
├── Owner/          → Fitur Owner (Dashboard, CRUD Data, Laporan, Transaksi)
├── Main/           → Utilitas (Koneksi DB, Format Rupiah, Waktu, BarChart)
├── PopUp/          → 23 Dialog konfirmasi & notifikasi
├── Informasi/      → 17 Dialog CRUD (Daftar, Update, Detail, Profile)
├── Cetak/          → Template laporan JasperReports (Nota Jual & Beli)
├── Barcode/        → Gambar barcode produk (8 file .png)
└── Image/          → Aset gambar UI (142 file .png)
```

### Package Breakdown

| Package | File | Fungsi |
|---------|------|--------|
| **Frame/** | 4 `.java` + 4 `.form` | Autentikasi: Login, Login RFID, Lupa Password, Progress Bar |
| **Karyawan/** | 6 `.java` + 6 `.form` | Fitur untuk role Karyawan: Dashboard, Transaksi Jual/Beli, Stok, Supplier |
| **Owner/** | 12 `.java` + 12 `.form` | Fitur untuk role Owner: Dashboard, CRUD Data, Laporan, Transaksi |
| **Main/** | 7 `.java` | Utilitas: Koneksi DB, Format Rupiah, Waktu, Bar Chart (3 jenis) |
| **PopUp/** | 23 `.java` + 23 `.form` | Dialog konfirmasi & notifikasi (Berhasil, Gagal, Peringatan) |
| **Informasi/** | 17 `.java` + 17 `.form` | Dialog CRUD: Daftar, Update, Detail, Profile, dll |
| **Cetak/** | 2 `.jrxml` + 2 `.jasper` | Template laporan JasperReports (Nota Jual & Nota Beli) |
| **Barcode/** | 8 file `.png` | Gambar barcode produk |
| **Image/** | 142 file `.png` | Aset gambar UI (screenshot, ikon, background) |

---

## 3. Fitur Utama

### 🔐 Autentikasi
- Login dengan **Username & Password**
- Login dengan **Kartu RFID**
- Fitur **Lupa Password**
- Toggle **Show/Hide Password**
- **Role-based access**: Owner vs Karyawan

### 🛒 Transaksi
- **Transaksi Jual** (penjualan ke pelanggan)
- **Transaksi Beli** (pembelian dari supplier)
- Dukungan **Member** (diskon/non-member handling)
- Cetak **Nota/Struk** via JasperReports

### 📦 Manajemen Data (CRUD)
- Data **Produk** (termasuk stok)
- Data **Karyawan**
- Data **Supplier**
- Data **Member**

### 📈 Laporan & Analitik
- **Laporan Pendapatan** (Harian / Mingguan / Bulanan)
- **Laporan Pengeluaran** (Harian / Mingguan / Bulanan)
- **Bar Chart** visualisasi (Pendapatan, Pengeluaran, Produk Terjual)
- **Export ke Excel** (CSV)
- Deteksi **Barang Restock** (stok < 5)

### 🧰 Utilitas
- Format **mata uang Rupiah** (Locale Indonesia)
- Pengelolaan **Waktu** (hari, tanggal, minggu dalam Bahasa Indonesia)
- **Barcode** produk
- **Progress Bar** saat loading

---

## 4. Teknologi & Library

| Library | Versi | Fungsi |
|---------|-------|--------|
| **MySQL Connector** | 8.0.32 / 8.0.27 / 5.1.5 | Koneksi database MySQL |
| **JasperReports** | 5.5.0 / 6.20.0 | Cetak laporan/nota |
| **JFreeChart** | 1.0.19 / 1.5.0 | Visualisasi bar chart |
| **JCalendar** | 1.3.3 / 1.4 | Date picker GUI |
| **Barcode Library** | custom | Generate/scan barcode |
| **iText** | 2.1.7 | PDF generation |
| **Jackson** | 2.13.3 | JSON/XML processing |
| **Bouncy Castle** | 1.70 | Cryptography |
| **AbsoluteLayout** | NetBeans | Layout manager |
| **Groovy** | 2.0.x | Scripting (JasperReports) |

---

## 5. Skema Database (Tabel yang Teridentifikasi)

Berdasarkan query SQL dalam kode, tabel-tabel yang teridentifikasi:

### Tabel `karyawan`
| Kolom | Keterangan |
|-------|------------|
| `kode_karyawan` | Primary Key |
| `nama_karyawan` | Nama lengkap karyawan |
| `username` | Username login |
| `password` | Password (plain text) |
| `level_user` | Role: "owner" atau "karyawan" |

### Tabel `produk`
| Kolom | Keterangan |
|-------|------------|
| `kode_produk` | Primary Key |
| `nama_produk` | Nama produk |
| `stok` | Jumlah stok |
| `harga` | Harga produk |

### Tabel `transaksi_jual`
| Kolom | Keterangan |
|-------|------------|
| `kode_tr_jual` | Primary Key |
| `tanggal` | Tanggal transaksi |
| `total_item` | Total item terjual |
| `bayar` | Total pembayaran |
| `kode_karyawan` | Foreign Key ke karyawan |

### Tabel `detail_transaksi_jual`
| Kolom | Keterangan |
|-------|------------|
| `kode_tr_jual` | Foreign Key ke transaksi_jual |
| `kode_produk` | Foreign Key ke produk |
| `jumlah` | Jumlah item |

### Relasi
- `karyawan` → `transaksi_jual` (1:N)
- `transaksi_jual` → `detail_transaksi_jual` (1:N)
- `produk` → `detail_transaksi_jual` (1:N)

---

## 6. Alur Aplikasi (User Flow)

```
[Buka Aplikasi]
    │
    ▼
[Halaman Login] ──→ [Login RFID]
    │                     │
    │ (username/password)  │
    ▼                     ▼
[Autentikasi] ◄───────────┘
    │
    ├── Gagal ──→ [PopUp: Gagal Login]
    │
    └── Berhasil ──→ [PopUp: Berhasil Login]
                          │
                          ▼
                    [Progress Bar Loading]
                          │
            ┌─────────────┴─────────────┐
            ▼                           ▼
    [👑 Dashboard Owner]        [👤 Dashboard Karyawan]
            │                           │
            ▼                           ▼
    ┌───────────────┐           ┌───────────────┐
    │ Data Karyawan │           │ Transaksi Jual│
    │ Data Member   │           │ Transaksi Beli│
    │ Data Produk   │           │ Stok Produk   │
    │ Data Supplier │           │ Hubungi Suppl.│
    │ Transaksi J/B │           └───────────────┘
    │ Laporan Pend. │
    │ Laporan Peng. │
    │ Hubungi Suppl.│
    └───────────────┘
```

---

## 7. ⚠️ Temuan Masalah & Kerentanan

### 🔴 KRITIS — Keamanan

#### 1. SQL Injection
Seluruh query menggunakan **string concatenation**, sangat rentan terhadap SQL Injection.

**Contoh kode bermasalah** (`src/Frame/Login.java`, baris 206):
```java
// ❌ RENTAN SQL INJECTION
String sql = "SELECT * FROM karyawan WHERE username='" + Username + "' AND password='" + Password + "'";
```

**Perbaikan yang disarankan:**
```java
// ✅ AMAN dengan PreparedStatement
String sql = "SELECT * FROM karyawan WHERE username=? AND password=?";
PreparedStatement pst = conn.prepareStatement(sql);
pst.setString(1, Username);
pst.setString(2, Password);
ResultSet r = pst.executeQuery();
```

**File yang terdampak:** Hampir seluruh file `.java` yang melakukan query database.

#### 2. Password Plain Text
Password disimpan dan dibandingkan **tanpa hashing**.

**Masalah** (`src/Frame/Login.java`, baris 98-100):
```java
// ❌ Password plain text
return res.getString("password");
```

**Rekomendasi:** Gunakan **BCrypt** atau **SHA-256 + salt** untuk hashing password.

#### 3. Kredensial Database Hardcoded

**Masalah** (`src/Main/Koneksi.java`, baris 25-27):
```java
// ❌ Hardcoded credentials
String url = "jdbc:mysql://localhost:3306/toko_moedah";
String user = "root";
String pass = "";
```

**Rekomendasi:** Gunakan file konfigurasi eksternal (`config.properties`) atau environment variables.

---

### 🟡 SEDANG — Kualitas Kode

#### 4. Duplikasi Kode Masif
- Logika login di-copy paste antara `btn_masukActionPerformed` dan `txt_passwordKeyPressed` di `Login.java`
- Package `Owner/` dan `Karyawan/` memiliki file **identik**: `TransaksiJual`, `TransaksiBeli`, `StokProduk`, `MenuTransaksi`, `MenghubungiSupplier`
- 23 PopUp class yang hampir identik, hanya berbeda teks pesan

#### 5. Tidak Ada Connection Pooling
Setiap operasi database membuat koneksi baru tanpa menutupnya:
```java
// ❌ Koneksi baru terus, tidak pernah close
Connection conn = (Connection) Koneksi.configDB();
```

Ini menyebabkan **connection leak** dan berpotensi crash saat banyak operasi. Gunakan **HikariCP** atau minimal `try-with-resources`.

#### 6. Resource Leak (Statement & ResultSet)
`Statement` dan `ResultSet` **tidak pernah di-close** di seluruh aplikasi.

#### 7. Exception Handling Buruk
```java
// ❌ Error ditelan tanpa log
} catch (SQLException ex) {
    // kosong
}
```

#### 8. Penamaan Tidak Konsisten
- Package: `Frame`, `Karyawan`, `Owner` (PascalCase) — tidak mengikuti konvensi Java (lowercase)
- Variable: campuran bahasa Indonesia & Inggris (`namaUser`, `admin`, `kode_karyawan`)
- File duplikat: `MenghubungiSupplier.java` vs `MenghubungiSupplier1.java`

---

### 🟢 MINOR — Arsitektur

#### 9. Tidak Ada Arsitektur MVC
Semua logic (UI, bisnis, database) ada **dalam satu file** JFrame. Tidak ada pemisahan layer.

#### 10. Dependency JAR Duplikat
`nbproject/project.properties` memiliki **3+ versi berbeda** untuk library yang sama (MySQL Connector, JFreeChart, JasperReports) dari path berbagai user.

#### 11. AbsoluteLayout
Menggunakan `AbsoluteLayout` (pixel-based) sehingga UI **tidak responsif** dan berantakan di resolusi layar berbeda.

---

## 8. Statistik Proyek

| Metrik | Jumlah |
|--------|--------|
| Total file `.java` | **~65 file** |
| Total file `.form` (GUI) | **~55 file** |
| Total file gambar | **~150 file** |
| File terbesar | `LaporanPendapatan.java` (~66 KB, 1525 baris) |
| Package terbanyak file | `PopUp/` (46 file) |
| Template laporan | 2 (Nota Jual & Nota Beli) |

---

## 9. Rekomendasi Perbaikan

### Prioritas Tinggi 🔴
1. **Ganti semua query ke PreparedStatement** — mencegah SQL Injection
2. **Implementasi password hashing** (BCrypt)
3. **Eksternalisasi konfigurasi database** ke file `.properties`
4. **Tutup semua koneksi, statement, dan resultset** (gunakan try-with-resources)

### Prioritas Sedang 🟡
5. **Refaktor duplikasi kode** — gabungkan `TransaksiJual` Owner & Karyawan menjadi satu, kontrol akses via flag
6. **Buat class DAO (Data Access Object)** — pisahkan logika database dari UI
7. **Konsolidasi PopUp** — buat satu class dialog generik dengan parameter pesan
8. **Gunakan connection pool** (HikariCP)

### Prioritas Rendah 🟢
9. **Migrasi ke layout manager yang responsif** (MigLayout / GridBagLayout)
10. **Standarisasi penamaan** mengikuti Java conventions
11. **Bersihkan dependency duplikat** di project.properties
12. **Tambahkan unit test** di folder `test/`
13. **Gunakan build tool modern** (Maven/Gradle) untuk dependency management

---

## 10. Kesimpulan

ePayment adalah proyek POS yang **fungsionalitasnya cukup lengkap** untuk kebutuhan toko retail kecil — mulai dari autentikasi multi-metode (password + RFID), transaksi jual-beli, manajemen inventori, hingga laporan keuangan dengan chart dan export Excel.

Namun, proyek ini memiliki **kerentanan keamanan serius** (SQL Injection, plain text password) dan **technical debt tinggi** akibat duplikasi kode masif serta tidak adanya pemisahan arsitektur. Perbaikan keamanan harus menjadi prioritas utama sebelum aplikasi ini digunakan di lingkungan produksi.
