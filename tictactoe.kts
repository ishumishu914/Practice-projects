import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val board = arrayOf(
        arrayOf(" ", " ", " "),
        arrayOf(" ", " ", " "),
        arrayOf(" ", " ", " ")
    )
    var currentPlayer = "X"
    var isGameOver = false

    while (!isGameOver) {
        // Print the board
        for (row in board) {
            for (cell in row) {
                print("$cell|")
            }
            println()
        }

        // Get the current player's move
        print("$currentPlayer's turn. Enter row (0-2): ")
        val row = scanner.nextInt()
        print("$currentPlayer's turn. Enter column (0-2): ")
        val col = scanner.nextInt()

        // Check if the move is valid
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            println("Invalid move. Try again.")
            continue
        }
        if (board[row][col] != " ") {
            println("Cell already taken. Try again.")
            continue
        }

        // Make the move
        board[row][col] = currentPlayer

        // Check if the game is over
        if (isWin(board, currentPlayer)) {
            println("$currentPlayer wins!")
            isGameOver = true
        } else if (isDraw(board)) {
            println("It's a draw!")
            isGameOver = true
        }

        // Switch to the other player
        currentPlayer = if (currentPlayer == "X") "O" else "X"
    }
}

fun isWin(board: Array<Array<String>>, player: String): Boolean {
    // Check rows
    for (row in board) {
        if (row.all { it == player }) {
            return true
        }
    }

    // Check columns
    for (col in 0..2) {
        if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
            return true
        }
    }

    // Check diagonals
    if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
        return true
    }
    if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
        return true
    }

    return false
}

fun isDraw(board: Array<Array<String>>): Boolean {
    for (row in board) {
        for (cell in row) {
            if (cell == " ") {
                return false
            }
        }
    }
    return true
}
