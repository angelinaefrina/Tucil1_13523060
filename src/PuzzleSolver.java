import java.util.*;

public class PuzzleSolver {
    
    // Mengecek apakah block puzzle dapat ditaruh pada papan
    public static boolean canBlockFit(char[][] papan, char[][] block, int x, int y) {
        // System.out.println("nyoba blok di (" + x + ", " + y + ")");
        int panjang_block = block.length;
        int lebar_block = block[0].length;
        int N = papan.length;
        int M = papan[0].length;

        if (x + panjang_block > N || y + lebar_block > M) {
            return false;
        }

        for (int i = 0; i < panjang_block; i++) {
            for (int j = 0; j < lebar_block; j++) {
                if ((papan[i + x][j + y] != ' ') && (block[i][j] != ' ')) {
                    // System.out.println("hayo");
                    return false;
                }
            }
        }

        return true;
    }

    // Merotasi block puzzle sebanyak 90 derajat searah jarum jam
    public static char[][] rotateBlock(char[][] block) {
        int panjang_block = block.length;
        int lebar_block = block[0].length;
        char[][] rotated = new char[lebar_block][panjang_block]; // Swap dimensions
        
        for (int i = 0; i < lebar_block; i++) {
            for (int j = 0; j < panjang_block; j++) {
                rotated[i][j] = ' ';
            }
        }
        for (int i = 0; i < panjang_block; i++) {
            for (int j = 0; j < lebar_block; j++) {
                rotated[j][panjang_block - 1 - i] = block[i][j];
            }
        }
    
        return rotated;
    }    

    // Mencerminkan block puzzle
    public static char[][] mirrorBlock(char[][] block) {
        int panjang_block = block.length;
        int lebar_block = block[0] .length;
        char[][] mirrored = new char[panjang_block][lebar_block];

        for (int i = 0; i < panjang_block; i++) {
            for (int j = 0; j < lebar_block; j++) {
                mirrored[i][j] = block[i][lebar_block - 1 - j];
            }
        }
        return mirrored;
    }

    // Menaruh block puzzle pada papan
    public static char[][] placeBlock(char[][] papan, char[][] block, int x, int y) {
        // System.out.println("naruh blok di (" + x + ", " + y + ")");
        int panjang_block = block.length;
        int lebar_block = block[0].length;
        int N = papan.length;
        int M = papan[0].length;
        char [][] new_papan = new char[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                new_papan[i][j] = papan[i][j];
            }
        }

        for (int i = 0; i < panjang_block; i++) {
            for (int j = 0; j < lebar_block; j++) {
                if (block[i][j] != ' ') {
                    new_papan[x + i][y + j] = block[i][j];
                }
            }
        }

        return new_papan;
    }

    // Menghapus block puzzle dari papan
    public static char[][] removeBlock(char[][] papan, char[][] block, int x, int y) {
        int panjang_block = block.length;
        int lebar_block = block[0].length;
        int N = papan.length;
        int M = papan[0].length;
        char [][] new_papan = new char[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                new_papan[i][j] = papan[i][j];
            }
        }

        for (int i = 0; i < panjang_block; i++) {
            for (int j = 0; j < lebar_block; j++) {
                if (block[i][j] != '.') {
                    new_papan[x + i][y + j] = ' ';
                }
            }
        }

        return new_papan;
    }

    // Mengecek apakah semua block puzzle sudah ditaruh pada papan
    public static boolean isAllBlockPlaced(HashSet<Character> huruf_blocks, char[][] papan) {
        for (char huruf : huruf_blocks) {
            boolean found = false;
            for (char[] baris : papan) {
                for (char c : baris) {
                    if (c == huruf) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    // Menampilkan papan
    public static void printPapan(char[][] papan) {
        for (char[] baris : papan) {
            for (char c : baris) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    // Mengecek apakah papan sudah terisi penuh
    public static boolean isPapanPenuh(char[][] papan) {
        for (char[] baris : papan) {
            for (char c : baris) {
                if (c == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Mencari solusi dari puzzle
    public static Solusi solvePuzzleByBruteForce(char[][] papan, List<char[][]> puzzle_blocks, HashSet<Character> huruf_blocks) {
        Stack<PapanPuzzle> stack = new Stack<>();
        stack.push(new PapanPuzzle(papan, 0, 0));
        int cases = 0;

        while (!stack.isEmpty()) {
            PapanPuzzle current = stack.pop();
            char[][] current_papan = current.papan;
            int indeks = current.indeks;
            int count = current.count;
            cases++;

            if (indeks >= puzzle_blocks.size()) {
                if (isPapanPenuh(current_papan)) {
                    return new Solusi(current_papan, true, cases);
                }
                continue;
            }

            char[][] original_block = puzzle_blocks.get(indeks);
            char[][] mirrored_block = mirrorBlock(original_block);
            int N = current_papan.length;
            int M = current_papan[0].length;

            for (int i = 0; i < N; i++) {
                for (int j= 0; j < M; j++) {
                    char[][] current_block = original_block;
                    for (int r = 0; r < 4; r++) {
                        if (canBlockFit(current_papan, current_block, i, j)) {
                            char[][] new_papan = placeBlock(current_papan, current_block, i, j);
                            // printPapan(new_papan);
                            // System.out.println();
                            stack.push(new PapanPuzzle(new_papan, indeks + 1, cases));
                        }
                        current_block = rotateBlock(current_block);
                        cases++;
                    }

                    current_block = mirrored_block;
                    for (int r = 0; r < 4; r++) {
                        if (canBlockFit(current_papan, current_block, i, j)) {
                            char[][] new_papan = placeBlock(current_papan, current_block, i, j);
                            stack.push(new PapanPuzzle(new_papan, indeks + 1, cases));
                    }
                        current_block = rotateBlock(current_block);
                        cases++;
                    }
                }
            }
        }
        return new Solusi(papan, false, cases);
    
    }
    


    public static void main(String[] args) {
        long start_time = System.currentTimeMillis();

        String input_filename = InputOutputFile.inputFile();
        Puzzle puzzle = InputOutputFile.bacaFilePuzzle(input_filename);
        // System.out.println("N = " + puzzle.N);
        // System.out.println("M = " + puzzle.M);
        // System.out.println("P = " + puzzle.P);
        // System.out.println("S = " + puzzle.S);
        
        // System.out.println("Puzzle Blocks:");
        // for (char[][] block : puzzle.puzzle_blocks) {
        //     for (char[] baris : block) {
        //         System.out.println(new String(baris));
        //     }
        //     System.out.println();
        // }

        // char[][] papan = new char[puzzle.N][puzzle.M];
        // for (int i = 0; i < puzzle.N; i++) {
        //     for (int j = 0; j < puzzle.M; j++) {
        //         papan[i][j] = ' ';
        //     }
        // }

        Solusi solusi = solvePuzzleByBruteForce(puzzle.papan, puzzle.puzzle_blocks, puzzle.huruf_blocks);
        long end_time = System.currentTimeMillis();
        System.out.println();
        System.out.println("Waktu pencarian: " + (end_time - start_time) + " ms");
        System.out.println();

        if (solusi.is_solved) {
            System.out.println("Puzzle solved! Yay :D");
            // printPapan(solusi.papan);
            System.out.println();
            Solusi.printSolusiBerwarna(solusi.papan, puzzle.huruf_blocks);
            System.out.println();

            System.out.println("Banyak kasus yang ditinjau: " + solusi.count);
            System.out.println();

            // Prompt menyimpan hasil
            System.out.println("Apakah anda ingin menyimpan hasilnya? (ya/tidak)");
            Scanner scanner = new Scanner(System.in);
            String save_solusi = scanner.nextLine();
            if (save_solusi.equals("ya")) {
                System.out.println("Masukkan nama file untuk menyimpan hasil: ");
                String output_filename = scanner.nextLine();
                InputOutputFile.outputFile(solusi.papan, "test/solution/" + output_filename + ".txt");
                System.out.println("Solusi berhasil disimpan pada test/solution!");
            }
            System.out.println();

            // Prompt menyimpan gambar
            System.out.println("Apakah anda ingin menyimpan hasilnya sebagai gambar? (ya/tidak)");
            String save_gambar = scanner.nextLine();
            if (save_gambar.equals("ya")) {
                System.out.println("Masukkan nama file untuk menyimpan gambar: ");
                String output_filename = scanner.nextLine();
                InputOutputFile.generateGambarSolusi(solusi.papan, "test/solution/pic/" + output_filename + ".png");
                System.out.println("Gambar berhasil disimpan pada test/solution/pic!");
            }
            
        } else {
            System.out.println("Puzzle cannot be solved! Good luck next time.");
        }

    }
    
}
