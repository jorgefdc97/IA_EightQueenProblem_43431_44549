import java.util.ArrayList;

public class Queen {

    public static final int BOARD_SIZE = 8;
    private static ArrayList<int[][]> SOLUTIONS = new ArrayList<>();

    public void solve(){
        int board[][] = new int[BOARD_SIZE][BOARD_SIZE];
        search(board, 0);
        printAllSolutions();
    }

    public boolean search(int [][] board, int column){
        //checks if column is not out of bounds
        //if it is, it is added to solutions board
        if (column == BOARD_SIZE){
            int[][]solution = new int[BOARD_SIZE][BOARD_SIZE];
            for (int r = 0; r < board.length; r++){
                for (int c = 0; c < board.length; c++){
                    solution[r][c] = board[r][c];
                }
            }
            SOLUTIONS.add(solution);
            return false;
        }

        boolean verifica = false;
        for (int i=0; i < BOARD_SIZE; i++){
            if (isSafe(board, i, column)){
                board[i][column] = 1;
                if(search(board, column + 1)){
                    verifica = true;
                }
                board[i][column] = 0;
            }
        }
        return verifica;
    }

    public boolean isSafe(int[][] board, int row, int col){
        //check row
        for (int i=0; i<col; i++){
            if (board[row][i] == 1){
                return false;
            }
        }

        //check upper left diagonal
        for (int r=row, c=col; r >= 0 && c >= 0; r--, c--){
            if (board[r][c] == 1){
                return false;
            }
        }

        //check down left diagonal
        for (int r=row, c=col; r < board.length && c >= 0; r++, c--){
            if (board[r][c] == 1){
                return false;
            }
        }
        return true;
    }

    public void printResult(int[][] board){
        for(int r=0; r < BOARD_SIZE; r++){
            for (int c=0; c < BOARD_SIZE; c++){
                if (board[r][c] == 1){
                    System.out.print(" Q ");
                }else{
                    System.out.print(" * ");
                }
            }
            System.out.println();
        }
    }

    public void printAllSolutions(){
        System.out.println("==================== SOLUTIONS TO SOLVE "+ BOARD_SIZE +" QUEEN PUZZLE ====================");
        for (int i=0; i < SOLUTIONS.size(); i++){
            System.out.println("\n== SOLUTION "+ (i+1) + "==\n");
            printResult(SOLUTIONS.get(i));
            System.out.println();
        }
    }
}

