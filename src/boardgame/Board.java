package boardgame;

public class Board {
    
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) {
        if(rows < 1 || columns < 1) {
            throw new BoardException("Error creating Board: the board has to have least 1 row and 1 column.");
        }
        this.rows = rows;
        this.columns = columns;
        this.pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Piece piece(int row, int column) {
        if(!this.positionExists(row, column)){
            throw new BoardException("Error picking up piece: position does not exist on the board");
        }
        return this.pieces[row][column];
    }

    public Piece piece(Position position) {
        if(!this.positionExists(position)){
            throw new BoardException("Error picking up piece: position does not exist on the board");
        }
        return this.pieces[position.getRow()][position.getColumn()];
    }
    
    public void placePiece(Piece piece, Position position) {
        if(thereIsAPiece(position)){
            throw new BoardException("there is already a piece in this position ->  " + position);
        }
        this.pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    public Piece removePiece(Position position) {
        if(!thereIsAPiece(position)){
            return null;
        }
        Piece aux = piece(position);
        aux.position = null;
        pieces[position.getRow()][position.getColumn()] = null;
        return aux;
    }

    private boolean positionExists(int row, int column) {
        return ((row >= 0 && row < this.rows) && (column >= 0 && column < this.columns));
    }

    public boolean positionExists(Position position) {
        return this.positionExists(position.getRow(), position.getColumn());
    }

    public boolean thereIsAPiece(Position position) {
        if(!this.positionExists(position)){
            throw new BoardException("Error picking up piece: position does not exist on the board");
        }
        return this.piece(position) != null;
    }
}
