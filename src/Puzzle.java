import java.util.*;

public class Puzzle {
    public int N, M, P;
    public String S;
    public List<char[][]> puzzle_blocks;
    

    public Puzzle(int N, int M, int P, String S, List<char[][]> puzzle_blocks) {
        this.N = N;
        this.M = M;
        this.P = P;
        this.S = S;
        this.puzzle_blocks = puzzle_blocks;
    }
}
