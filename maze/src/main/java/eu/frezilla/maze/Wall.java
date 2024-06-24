package eu.frezilla.maze;

public final class Wall {
    
    private boolean closed;
    
    public Wall() {
        closed = true;
    }
    
    public void close() {
        closed = true;
    }
    
    public boolean isClosed() {
        return closed;
    }
    
    public boolean isOpened() {
        return !isClosed();
    }
    
    protected void open() {
        closed = false;
    }
    
}
