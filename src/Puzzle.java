import java.util.*;

public class Puzzle {
    public int N, M, P;
    public String S;
    public List<char[][]> puzzle_blocks;
    public HashSet<Character> huruf_blocks;
    public char[][] papan;
    

    public Puzzle(int N, int M, int P, String S, List<char[][]> puzzle_blocks, HashSet<Character> huruf_blocks) {
        this.N = N;
        this.M = M;
        this.P = P;
        this.S = S;
        this.puzzle_blocks = puzzle_blocks;
        this.huruf_blocks = huruf_blocks;
        this.papan = new char[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                this.papan[i][j] = ' ';
            }
        }
    }
}
