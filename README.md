# Digital Zoo Manager — UTS Implementasi PBO

Aplikasi desktop Java Swing untuk menyimpan hewan secara sementara di memori.
Proyek ini memenuhi spesifikasi `Animal`, `Mammal`, `Bird`, `ZooFrame`, dan log aktivitas.

## Persyaratan
- JDK 17 atau lebih baru
- IntelliJ IDEA (Community Edition cukup)

## Menjalankan di IntelliJ IDEA
1. Ekstrak folder ZIP proyek.
2. Pilih **Open** di IntelliJ IDEA dan buka folder `DigitalZooManager`.
3. Tunggu indeks dan impor Maven selesai, dan atur Project SDK menjadi JDK 17+ bila diminta.
4. Buka `src/main/java/com/digitalzoo/Main.java`.
5. Klik ikon Run (segitiga hijau) di dekat fungsi `main()`.
6. Isi Name, Age, dan pilih Generic Mammal / Generic Bird.
7. Untuk Mammal, isi Fur Color. Untuk Bird, centang Can Fly? bila bisa terbang.
8. Klik **Add Animal**. Informasi dan suara hewan akan muncul pada Zoo Activity Log.

## Struktur & konsep PBO
- **Animal**: superclass dengan field private name/age, constructor, getter/setter, makeSound(), getInfo().
- **Mammal**: `extends Animal`, field furColor, `super()` dan override dua method.
- **Bird**: `extends Animal`, field canFly, `super()` dan override dua method.
- **ZooFrame**: GUI `JFrame`, form, tombol, `JTextArea`, `ArrayList<Animal>`.
- **Main**: entry point Swing EDT.

**Inheritance**: Mammal dan Bird mewarisi Animal.
**Encapsulation**: atribut private dengan getter dan setter.
**Polymorphism**: `ArrayList<Animal>` menampung Mammal dan Bird, dan memanggil method yang di-override.

## Contoh output
```
Added a new Mammal! Info: Name: Leo, Age: 5, Fur Color: Golden. Sound: The mammal makes a sound.
Added a new Bird! Info: Name: Rio, Age: 2, Can Fly: Yes. Sound: The bird chirps.
```

## Upload ke GitHub (untuk pengumpulan)
1. Login ke github.com dan pilih **New repository**.
2. Nama contoh: `UTS-Digital-Zoo-Manager`. Jangan aktifkan opsi tambahan README saat membuat repo baru.
3. Buat repository, lalu buka Terminal dari folder `DigitalZooManager`.
4. Jalankan perintah (ganti URL `USERNAME`):
   ```bash
   git init
   git add .
   git commit -m "UTS Implementasi PBO - Digital Zoo Manager"
   git branch -M main
   git remote add origin https://github.com/USERNAME/UTS-Digital-Zoo-Manager.git
   git push -u origin main
   ```
5. Kirim URL repository asli yang sudah dibuat ke dosen.

**Catatan**: Semua data hanya disimpan di `ArrayList` selama program berjalan, sesuai permintaan soal.
