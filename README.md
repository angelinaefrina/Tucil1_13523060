# Tucil1_13523060
Tugas Kecil 1 Strategi Algoritma

# Penyelesaian IQ Puzzler Pro dengan Algoritma Brute Force
Oleh: Angelina Efrina Prahastaputri - 13523060

## **Deskripsi Program**
Program ini merupakan salah satu tugas kecil dari mata kuliah IF2211 Strategi Algoritma. Tucil ini dibuat dengan bahasa Java. Program ini bertujuan untuk mencari penyelesaikan dari permainan IQ Puzzler Pro dengan menggunakan pendekatan algoritma *brute force*. IQ Puzzler Pro adalah permainan teka-teki logika yang dibuat oleh SmartGames, yaitu sebuah perusahaan yang terkenal dengan berbagai permainan puzzle logika yang menantang. Pengguna akan dimintai input oleh program berupa file *.txt* yang berisi bagian-bagian puzzle yang harus diselesaikan. Kemudian, program akan mencari solusi menggunakan algoritma *brute force* dan menampilkan hasilnya.

## Requirement Program dan Instalasi Tertentu:
**1.** Terminal lokal(cmd) atau VSCode.  
**2.** Java sudah terinstall di sistem. Anda dapat memeriksa di terminal lokal dengan cara
```
java -version
```

## Cara Menjalankan dan Menggunakan Program:
  **1.** Buka link repository GitHub, lalu salin URL di bagian "Code" untuk melakukan git clone.  
  **2.** Buka terminal di Visual Studio Code atau CMD.  
  **3.** Arahkan direktori ke folder tempat Anda ingin melakukan git clone.  
  **4.** Gunakan perintah git clone <URL> untuk menyalin repositori.
  ```
  git clone https://github.com/angelinaefrina/Tucil1_13523060.git 
  ```
  **5.** Setelah proses cloning repositori selesai, pastikan Anda sekarang berada pada direktori folder Tucil1_13523060
  ```
  cd Tucil1_13523060
  ```
  **6.** Jalankan program menggunakan perintah
  ```
  javac -d bin src/InputOutputFile.java src/PapanPuzzle.java src/Puzzle.java src/PuzzleSolver.java src/Solusi.java

  java -cp bin PuzzleSolver
  ```
  **7.** Ikuti prompt yang muncul untuk memasukkan input nama file yang berisi konfigurasi permainan.   
  **Notes :** Jangan lupa untuk memasukkan file .txt tersebut ke dalam folder test.

  **8.** Program akan menampilkan solusi atau penyelesaian. Pengguna dapat menyimpan hasil solusi berupa *.txt* dan gambar *.png*. 
  
  **9.** Ikuti prompt yang muncul untuk memasukkan input nama file tempat hasil akan disimpan. Hasil solusi berupa *.txt* disimpan pada path test/solution. Hasil solusi berupa gambar *.png* disimpan pada path test/solution/pic.

