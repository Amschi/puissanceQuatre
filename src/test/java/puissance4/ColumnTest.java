package puissance4;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ColumnTest {

    Column column;

    @Before
    public void setUp() throws Exception {
        column = new Column();

    }

    @Test
    public void new_shouldHave6Rows() throws Exception {
        assertThat(column.getSize()).isEqualTo(Column.ROWS_COUNT);
    }

    @Test
    public void getFreeSpaces_shouldReturnEmptySpacesInColumn_whenNoCoins() throws Exception {
        assertThat(column.getFreeSpaces()).isEqualTo(6);
    }

    @Test
    public void getFreeSpaces_shouldReturnEmptySpacesInColumn_withOneCoin() throws ColumnFullException {
        Coin coin = new Coin(Coin.RED);

        column.addCoin(coin);

        assertThat(column.getFreeSpaces()).isEqualTo(5);
    }

    @Test(expected = ColumnFullException.class)
    public void addCoin_shouldThrowException_whenRowsCountExceeded() throws ColumnFullException {
        for (int i = 0; i < Column.ROWS_COUNT; i++) {
            column.addCoin(new Coin(Coin.RED));
        }

        column.addCoin(new Coin(Coin.RED));
    }

    @Test
    public void getState_shouldReturnCoinState() throws ColumnFullException {
        Coin coin1 = new Coin(Coin.YELLOW);
        Coin coin2 = new Coin(Coin.RED);
        Coin coin3 = new Coin(Coin.YELLOW);

        column.addCoin(coin1);
        column.addCoin(coin2);
        column.addCoin(coin3);

        assertThat(column.getState(0)).isEqualTo(Coin.YELLOW);
        assertThat(column.getState(1)).isEqualTo(Coin.RED);
        assertThat(column.getState(2)).isEqualTo(Coin.YELLOW);
    }

    @Test
    public void clear_shouldLeaveTheColumnEmpty() throws ColumnFullException {
        Coin coin1 = new Coin(Coin.YELLOW);
        column.addCoin(coin1);

        column.clear();

        assertThat(column.getFreeSpaces()).isEqualTo(Column.ROWS_COUNT);
    }
}