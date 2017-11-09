package puissance4;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    public static final int COLUMNS_COUNT = 7;

    List<Column> columns;

    public Grid() {
        columns = new ArrayList<Column>();

        for (int i = 0; i < COLUMNS_COUNT; i++) {
            columns.add(new Column());
        }
    }

    public int getColumnsCount() {
        return COLUMNS_COUNT;
    }

    public int getRowsCount() {
        return Column.ROWS_COUNT;
    }

    public String getCellState(int column, int row) {
        return this.columns.get(column).getState(row);
    }

    public void addCoin(int column, String color) throws ColumnFullException {
        this.columns.get(column).addCoin(new Coin(color));
    }

    public int getColumnFreeSpace(int columnIndex) {
        return this.columns.get(columnIndex).getFreeSpaces();
    }

    public void clear() {
        columns.stream().forEach(Column::clear);
    }

    public String render() {
        StringBuilder renderBuf = new StringBuilder();

        for (int row = getRowsCount() - 1; row >= 0; row--) {
            for (int column = 0; column < getColumnsCount(); column++) {
                if (getCellState(column, row).equals(Coin.YELLOW))
                    renderBuf.append(" Y");
                else if (getCellState(column, row).equals(Coin.RED))
                    renderBuf.append(" R");
                else
                    renderBuf.append(" .");
            }
            renderBuf.append(" \n");

        }

        return renderBuf.toString();
    }
}
