import java.util.*;

public class Solusi {
    public char[][] papan;
    public boolean is_solved;
    public int count;

    public Solusi(char[][] papan, boolean is_solved, int count) {
        this.papan = papan;
        this.is_solved = is_solved;
        this.count = count;
    }

    public static final String[] WARNA = {
        "\u001B[38;2;255;255;255m", // Putih.
        "\u001B[38;2;166;166;166m", // Abu-Abu.
        "\u001B[38;2;255;49;49m", // Merah.
        "\u001B[38;2;255;145;76m", // Oranye.
        "\u001B[38;2;255;222;89m", // Kuning.
        "\u001B[38;2;126;217;86m", // Hijau Muda.
        "\u001B[38;2;0;191;98m", // Hijau.
        "\u001B[38;2;12;193;224m", // Biru Muda.
        "\u001B[38;2;0;74;173m", // Biru Tua.
        "\u001B[38;2;255;101;195m", // Pink.
        "\u001B[38;2;140;82;255m", // Ungu.
        "\u001B[38;2;255;87;87m", // Terracota.
        "\u001B[38;2;240;255;162m", // Kuning Pucat.
        "\u001B[38;2;254;189;89m", // Kuning Tua.
        "\u001B[38;2;148;73;18m", // Coklat.
        "\u001B[38;2;192;255;114m", // Lime.
        "\u001B[38;2;0;137;42m", // Hijau Tua.
        "\u001B[38;2;92;225;230m", // Cyan.
        "\u001B[38;2;0;151;178m", // Turqoise.
        "\u001B[38;2;56;182;255m", // Biru Langit.
        "\u001B[38;2;82;113;255m", // Indigo.
        "\u001B[38;2;245;57;255m", // Magenta.
        "\u001B[38;2;240;175;255m", // Lavender.
        "\u001B[38;2;203;107;230m", // Violet.
        "\u001B[38;2;93;23;235m", // Ungu Tua.
        "\u001B[38;2;128;0;0m" // Maroon.
    }; 

    public static final String WARNA_DEFAULT = "\u001B[0m";

    public static String getWarna(char huruf_block) {
        if (huruf_block == ' ') {
            return WARNA_DEFAULT;
        }
        int indeks = huruf_block - 'A';
        return WARNA[indeks];
    }

    public static void printSolusiBerwarna(char[][] papan, HashSet<Character> huruf_blocks) {
        for (char[] baris : papan) {
            for (char huruf_block : baris) {
                System.out.print(getWarna(huruf_block) + huruf_block + WARNA_DEFAULT);
            }
            System.out.println();
        }
    }

}
