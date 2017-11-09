package puissance4;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class GridTest {

    Grid grid;

    @Before
    public void setUp() throws Exception {
        grid = new Grid();
    }

    @Test
    public void new_shouldHave7Columns() {
        assertThat(grid.getColumnsCount()).isEqualTo(7);
    }

    @Test
    public void new_shouldHave6Rows() {
        assertThat(grid.getRowsCount()).isEqualTo(Column.ROWS_COUNT);
    }

    @Test
    public void getCellState_shouldReturnEMPTY() {
        assertThat(grid.getCellState(0, 0)).isEqualTo(Column.EMPTY_CELL);
    }

    @Test
    public void getCellState_shouldReturnExpectedColor() throws ColumnFullException {
        grid.addCoin(2, Coin.YELLOW);
        grid.addCoin(2, Coin.RED);

        assertThat(grid.getCellState(2, 0)).isEqualTo(Coin.YELLOW);
        assertThat(grid.getCellState(2, 1)).isEqualTo(Coin.RED);
    }

    @Test
    public void addCoin_shouldModifyExpectedColumnState() throws  ColumnFullException {
        int columnIndex = 0;
        grid.addCoin(columnIndex, Coin.RED);

        assertThat(grid.getColumnFreeSpace(columnIndex)).isEqualTo(5);
    }

    @Test
    public void clear_shouldReturnEmptyGrid() throws  ColumnFullException {
        for (int columnIndex = 0; columnIndex < Grid.COLUMNS_COUNT; columnIndex++) {
            for (int row = 0; row < Column.ROWS_COUNT; row++) {
                grid.addCoin(columnIndex, Coin.RED);
            }
        }

        grid.clear();

        for (int columnIndex = 0; columnIndex < Grid.COLUMNS_COUNT; columnIndex++) {
            assertThat(grid.columns.get(columnIndex).getFreeSpaces()).isEqualTo(Column.ROWS_COUNT);
        }
    }

    @Test
    public void render_shouldRender_whenGridIsEmpty() {
        String view = grid.render();

        assertThat(view).isEqualTo(
                " . . . . . . . \n" +
                " . . . . . . . \n" +
                " . . . . . . . \n" +
                " . . . . . . . \n" +
                " . . . . . . . \n" +
                " . . . . . . . \n"
        );
    }

    @Test
    public void render_shouldRender_whenGridContainsAYellowCoin() throws ColumnFullException {
        grid.addCoin(2, Coin.YELLOW);

        String view = grid.render();

        assertThat(view).isEqualTo(
                " . . . . . . . \n" +
                " . . . . . . . \n" +
                " . . . . . . . \n" +
                " . . . . . . . \n" +
                " . . . . . . . \n" +
                " . . Y . . . . \n"
        );
    }

    @Test
    public void render_shouldRender_whenGridContainsARedCoin() throws ColumnFullException {
        grid.addCoin(4, Coin.RED);

        String view = grid.render();

        assertThat(view).isEqualTo(
                " . . . . . . . \n" +
                " . . . . . . . \n" +
                " . . . . . . . \n" +
                " . . . . . . . \n" +
                " . . . . . . . \n" +
                " . . . . R . . \n"
        );
    }

    @Test
    public void render_shouldRender_whenGridContainsMultipleCoins() throws ColumnFullException {
        grid.addCoin(4, Coin.YELLOW);
        grid.addCoin(3, Coin.RED);
        grid.addCoin(4, Coin.YELLOW);
        grid.addCoin(4, Coin.RED);

        String view = grid.render();

        assertThat(view).isEqualTo(
                " . . . . . . . \n" +
                " . . . . . . . \n" +
                " . . . . . . . \n" +
                " . . . . R . . \n" +
                " . . . . Y . . \n" +
                " . . . R Y . . \n"
        );
    }
}