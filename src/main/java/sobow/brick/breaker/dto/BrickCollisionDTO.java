package sobow.brick.breaker.dto;

public class BrickCollisionDTO
{
    private boolean collisionDetected;
    private int rowIndex;
    private int columnIndex;

    public BrickCollisionDTO(boolean collisionDetected, int rowIndex, int columnIndex)
    {
        this.collisionDetected = collisionDetected;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public boolean isCollisionDetected()
    {
        return collisionDetected;
    }

    public int getRowIndex()
    {
        return rowIndex;
    }

    public int getColumnIndex()
    {
        return columnIndex;
    }

    public void setCollisionDetected(boolean collisionDetected)
    {
        this.collisionDetected = collisionDetected;
    }

    public void setRowIndex(int rowIndex)
    {
        this.rowIndex = rowIndex;
    }

    public void setColumnIndex(int columnIndex)
    {
        this.columnIndex = columnIndex;
    }
}
