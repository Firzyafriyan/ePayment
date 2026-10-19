<div align="center">

<img src="assets/banner_epayment.png" alt="ePayment Banner" width="100%">

<br>

[![Java](https://img.shields.io/badge/Java-Swing-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-Database-005C84?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![NetBeans](https://img.shields.io/badge/IDE-Apache%20NetBeans-1B6AC9?style=for-the-badge&logo=apache-netbeans-ide&logoColor=white)](https://netbeans.apache.org/)
[![Ant](https://img.shields.io/badge/Build-Apache%20Ant-A81C7D?style=for-the-badge&logo=apache-ant&logoColor=white)](https://ant.apache.org/)
[![JasperReports](https://img.shields.io/badge/Report-JasperReports-FF6B35?style=for-the-badge)](https://community.jaspersoft.com/)
[![License](https://img.shields.io/badge/License-MIT-F7DF1E?style=for-the-badge)](LICENSE)

[![Stars](https://img.shields.io/github/stars/Firzyafriyan/ePayment?style=social)](https://github.com/Firzyafriyan/ePayment/stargazers)
[![Forks](https://img.shields.io/github/forks/Firzyafriyan/ePayment?style=social)](https://github.com/Firzyafriyan/ePayment/network/members)

**Aplikasi desktop Point of Sale (POS) lengkap untuk toko retail — transaksi, inventori, karyawan, supplier, member, hingga laporan keuangan bergrafik.**

[🚀 Mulai Cepat](#-cara-pakai) • [📊 Analisis Proyek](docs/analisis_epayment.md) • [🐛 Laporkan Bug](https://github.com/Firzyafriyan/ePayment/issues) • [💡 Request Fitur](https://github.com/Firzyafriyan/ePayment/issues)

</div>

---

## ✨ Kenapa Pakai Ini?

<div align="center">

| 📝 **Kasir Manual** | ✅ **ePayment** |
|:---:|:---:|
| Nota ditulis tangan, rawan hilang | Nota jual & beli tercetak otomatis |
| Stok dihitung manual | Stok ter-update otomatis + deteksi restock |
| Laporan harian direkap sendiri | Laporan harian/mingguan/bulanan + bar chart |
| Siapa saja bisa buka data | Login terpisah: 👑 Owner vs 👤 Karyawan |

</div>

**ePayment** adalah sistem kasir untuk **Toko Moedah** yang mengotomasi seluruh alur toko — dari autentikasi multi-metode (password & kartu **RFID**), transaksi jual-beli dengan diskon member, manajemen produk/stok, sampai laporan keuangan yang bisa **di-export ke Excel**. Semua dalam satu aplikasi desktop Java Swing yang berjalan offline. 🎯

---

## 🚀 Fitur

### 🔐 Autentikasi & Keamanan Akses
- Login **username & password** dengan toggle *show/hide password*
- Login **kartu RFID** — tinggal tap, langsung masuk
- Fitur **lupa password**
- **Role-based access** — menu Owner dan Karyawan terpisah otomatis

### 🛒 Transaksi
- **Transaksi Jual** ke pelanggan — dukungan member & non-member
- **Transaksi Beli** dari supplier
- **Cetak nota/struk** otomatis via **JasperReports**
- **Barcode** produk untuk pencarian cepat

### 📦 Manajemen Data (CRUD)
- 👕 Data **Produk** + kontrol stok
- 👥 Data **Karyawan**
- 🚚 Data **Supplier**
- 💳 Data **Member**

### 📈 Laporan & Analitik
- 📊 Laporan **Pendapatan**: harian / mingguan / bulanan
- 📉 Laporan **Pengeluaran**: harian / mingguan / bulanan
- 📊 **Bar chart** visual: pendapatan, pengeluaran, produk terjual
- 📤 **Export ke Excel (CSV)**
- ⚠️ Deteksi otomatis **barang perlu restock** (stok < 5)

### 🧰 Utilitas
- 💰 Format mata uang **Rupiah** (Locale Indonesia)
- 🗓️ Manajemen waktu & tanggal Bahasa Indonesia (pakai **JCalendar**)
- ⏳ Progress bar saat loading aplikasi

---

## 📋 Persyaratan

| Kebutuhan | Versi / Keterangan |
|---|---|
| ☕ **JDK** | Java 8 atau lebih baru |
| 🛠️ **IDE** | [Apache NetBeans](https://netbeans.apache.org/) (direkomendasikan — project format NetBeans) |
| 🗄️ **Database** | MySQL dengan database `toko_moedah` |
| 📚 **Library** | MySQL Connector/J, JasperReports, JFreeChart, JCalendar, iText |

> 💡 Library (JAR) dikonfigurasi lewat *Project Properties → Libraries* di NetBeans. Detail versi ada di `nbproject/project.properties`.

---

## 🎮 Cara Pakai

### 1️⃣ Siapkan Database

1. Jalankan **MySQL**, lalu buat database `toko_moedah`
2. Buat tabel sesuai skema: `karyawan`, `produk`, `transaksi_jual`, `detail_transaksi_jual`, `member`, `supplier` *(detail kolom ada di [📊 analisis proyek](docs/analisis_epayment.md))*
3. Isi minimal satu akun di tabel `karyawan` dengan `level_user` = `owner` atau `karyawan`

### 2️⃣ Sesuaikan Koneksi Database

Buka `src/Main/Koneksi.java`, lalu sesuaikan kredensial MySQL-mu:

```java
String url  = "jdbc:mysql://localhost:3306/toko_moedah";
String user = "root";      // sesuaikan
String pass = "";          // sesuaikan
```

### 3️⃣ Buka & Jalankan di NetBeans

1. *File → Open Project* → pilih folder `ePayment`
2. Pastikan semua library JAR sudah ter-load (tanda seru merah = library belum ketemu)
3. Tekan **F6** (Run) — jendela **Login** akan terbuka

### 4️⃣ Login & Mulai Berjualan 🎉

- Masuk dengan username/password **atau** tap kartu **RFID** 🪪
- Dashboard menyesuaikan otomatis: 👑 Owner mendapat menu lengkap laporan & CRUD data, 👤 Karyawan fokus ke transaksi & stok

---

## 🗂️ Struktur Proyek

```text
ePayment/
├── src/
│   ├── Frame/       🔐 Autentikasi — Login, Login RFID, Lupa Password, Progress Bar
│   ├── Karyawan/    👤 Dashboard & fitur role Karyawan (Transaksi, Stok, Supplier)
│   ├── Owner/       👑 Dashboard & fitur role Owner (CRUD, Laporan, Transaksi)
│   ├── Main/        ⚙️ Utilitas — Koneksi DB, Format Rupiah, Waktu, Bar Chart
│   ├── PopUp/       💬 23 dialog konfirmasi & notifikasi
│   ├── Informasi/   🪟 17 dialog CRUD (Daftar, Update, Detail, Profile)
│   ├── Cetak/       🧾 Template nota JasperReports (Jual & Beli)
│   ├── Barcode/     🏷️ Gambar barcode produk
│   └── Image/       🖼️ Aset gambar UI
├── docs/
│   └── analisis_epayment.md   📊 Analisis arsitektur, skema DB & rekomendasi
├── nbproject/       🛠️ Konfigurasi project NetBeans
├── build.xml        🔨 Build script Apache Ant
└── manifest.mf      📦 Manifest JAR
```

---

## ⚠️ Catatan

> 🎓 Proyek ini dibuat untuk kebutuhan **pembelajaran & UAS**. Beberapa praktik di dalamnya (query string concatenation, password plain text, kredensial hardcoded) **belum layak untuk production**. Analisis lengkap — beserta langkah perbaikan prioritasnya — tersedia di 👉 [`docs/analisis_epayment.md`](docs/analisis_epayment.md).

---

## 🛠️ Kustomisasi Lanjutan

- 🗄️ Ganti nama database / kredensial → `src/Main/Koneksi.java`
- 🎨 Ubah tampilan UI → edit file `.form` di NetBeans GUI Builder
- 🧾 Ubah format nota → edit `src/Cetak/*.jrxml` dengan Jaspersoft Studio

---

## 🗺️ Pengembangan Selanjutnya

- [ ] 🛡️ Migrasi semua query ke **PreparedStatement** (cegah SQL Injection)
- [ ] 🔑 **Hashing password** dengan BCrypt
- [ ] 📄 Konfigurasi database pindah ke file `config.properties`
- [ ] 🏗️ Refaktor ke arsitektur **MVC + DAO** & hapus duplikasi kode Owner/Karyawan
- [ ] 📦 Migrasi build ke **Maven/Gradle**
- [ ] 🧪 Tambahkan unit test

---

<div align="center">

### 🌈 💚 Dibuat dengan ❤️ dan ☕ Java untuk toko yang lebih rapi 💚 🌈

⭐ **Star repo ini kalau bermanfaat!** ⭐

</div>
