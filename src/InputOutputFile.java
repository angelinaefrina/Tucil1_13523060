import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;

public class InputOutputFile {
    
    // Meminta input file .txt
    public static String inputFile(){
        System.out.println("SELAMAT DATANG DI IQ PUZZLE PRO SOLVER!");

        Scanner scanner = new Scanner(System.in);
        String filename;
        File file;

        while (true) {
            System.out.println("Masukkan nama file .txt (pastikan file ada pada folder test): ");
            filename = scanner.nextLine().trim();
            if (filename.isEmpty()) {
                System.out.println("Nama file tidak boleh kosong! Coba lagi.");
                continue;
            }
            file = new File("test/" + filename + ".txt");
            if (!file.exists()) {
                System.out.println("File tidak ditemukan pada folder test! Coba lagi.");
                continue;
            } else {
                break;
            }
        }
        
        return ("test/" + filename + ".txt");
        
    }
    
    // Membaca masukan file .txt
    public static Puzzle bacaFilePuzzle(String file_name) {
        int N = 0, M = 0, P = 0;
        String S = "";
        HashSet<Character> huruf_blocks = new HashSet<>();
        List<char[][]> puzzle_blocks = new ArrayList<>();


        try {
            BufferedReader reader = new BufferedReader(new FileReader(file_name));
            String line = reader.readLine();
            if (line == null || line.trim().isEmpty()) {
                throw new IOException("File kosong! Coba lagi.");
            }
            // Membaca baris pertama
            String[] first_line = line.trim().split(" ");
            if (first_line.length != 3) {
                throw new IOException("Komponen Puzzle belum sesuai! Coba lagi.");
            }
            N = Integer.parseInt(first_line[0]);
            M = Integer.parseInt(first_line[1]);
            P = Integer.parseInt(first_line[2]);
            // Membaca baris kedua
            line = reader.readLine();
            if (line == null || line.trim().isEmpty()) {
                throw new IOException("Tipe Puzzle belum ada! Coba lagi.");
            }
            S = line.trim();
            if (!(S.equals("DEFAULT") || S.equals("CUSTOM") || S.equals("PYRAMID"))) {
                throw new IOException("Tipe Puzzle belum sesuai! Coba lagi.");
            }
            // Membaca baris-baris selanjutnya/membaca block puzzle dan memvalidasi banyak block puzzle
            List<String> block = new ArrayList<>();
            char current_block = ' ';

            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    char jenis_block = line.trim().charAt(0);
                    huruf_blocks.add(jenis_block);
                    if (block.isEmpty() || jenis_block == current_block) {
                        block.add(line);
                        current_block = jenis_block;
                    } else {
                        puzzle_blocks.add(convertBlockToMatrix(block));
                        block.clear();
                        block.add(line);
                        current_block = jenis_block;
                    }
                }
            }
            if (!block.isEmpty()) {
                puzzle_blocks.add(convertBlockToMatrix(block));
            }
            
            // System.out.println("Huruf Unik: " + huruf_blocks.size());
            // reader.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new Puzzle(N, M, P, S, puzzle_blocks, huruf_blocks);
    }

    public static char[][] convertBlockToMatrix(List<String> block) {
        int baris = block.size();
        int kolom = 0;
        for (String line : block) {
            kolom = Math.max(kolom, line.length());
        }
        char[][] matriks = new char[baris][kolom];
        for (int i = 0; i < baris; i++) {
            String line = block.get(i);
            for (int j = 0; j < kolom; j++) {
                matriks[i][j] = (j < line.length()) ? line.charAt(j) : ' ';
            }
        }
        return matriks;
    }

    public static void outputFile(char[][] papan, String filenames){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filenames))) {
            for (char[] baris : papan) {
                writer.write(baris);
                writer.newLine();
            }
        } catch (IOException e){
            System.out.println("error!");
        }
    }

    public static final String[] WARNA_HEX = {
        "#FFFFFF", // Putih.
        "#A6A6A6", // Abu-Abu.
        "#FF3131", // Merah.
        "#FF914C", // Oranye.
        "#FFDE59", // Kuning.
        "#7ED956", // Hijau Muda.
        "#00BF62", // Hijau.
        "#0CC1E0", // Biru Muda.
        "#004AAD", // Biru Tua.
        "#FF65C3", // Pink.
        "#8C52FF", // Ungu.
        "#FF5757", // Terracotta.
        "#F0FFA2", // Kuning Pucat.
        "#FEBD59", // Kuning Tua.
        "#944912", // Coklat.
        "#C0FF72", // Lime.
        "#00892A", // Hijau Tua.
        "#5CE1E6", // Cyan.
        "#0097B2", // Turqoise.
        "#38B6FF", // Biru Langit.
        "#5271FF", // Indigo.
        "#F539FF", // Magenta.
        "#F0AFFF", // Lavender.
        "#CB6BE6", // Violet.
        "#5D17EB", // Ungu Tua.
        "#800000"  // Maroon.
    };
    
    public static final String WARNA_DEFAULT_HEX = "#000000";
    
    public static String getWarna(char huruf_block) {
        if (huruf_block == ' ') {
                return WARNA_DEFAULT_HEX;
            }
            int indeks = huruf_block - 'A';
            if (indeks >= 0 && indeks < WARNA_HEX.length) {
                return WARNA_HEX[indeks];
            }
            return WARNA_DEFAULT_HEX;
        }
    
    public static void generateGambarSolusi(char[][] papan, String filename) {
        int ukuran = 50;
        int panjang = papan.length * ukuran;
        int lebar = papan[0].length * ukuran;
        BufferedImage image = new BufferedImage(lebar, panjang, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        for (int i = 0; i < papan.length; i++) {
            for (int j = 0; j < papan[i].length; j++) {
                String hexColor = getWarna(papan[i][j]);
                g.setColor(Color.decode(hexColor));
                g.fillRect(j * ukuran, i * ukuran, ukuran, ukuran);
                g.setColor(Color.BLACK); // warna outline
                g.drawRect(j * ukuran, i * ukuran, ukuran, ukuran);
                g.drawString(String.valueOf(papan[i][j]), j * ukuran + 20, i * ukuran + 30);
            }
        }

        g.dispose();
        try {
            ImageIO.write(image, "png", new File(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
