import java.io.*;
import java.util.*;

public class InputOutputFile {
    
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

}
