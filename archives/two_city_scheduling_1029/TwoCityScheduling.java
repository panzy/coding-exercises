package two_city_scheduling_1029;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-28.
 */
public class TwoCityScheduling {

    public int twoCitySchedCost(int[][] costs) {
        return new Solution3().twoCitySchedCost(costs);
    }

    @Test
    void testFly() {
        Assertions.assertEquals(110, twoCitySchedCost(new int[][]{
                {10, 20}, {30, 200}, {400, 50}, {30, 20}
        }));

        Assertions.assertEquals(3086, twoCitySchedCost(new int[][]{
                {515, 563}, {451, 713}, {537, 709}, {343, 819}, {855, 779}, {457, 60}, {650, 359}, {631, 42}
        }));

        Assertions.assertEquals(1859, twoCitySchedCost(new int[][]{
                {259, 770}, {448, 54}, {926, 667}, {184, 139}, {840, 118}, {577, 469}
        }));

        Assertions.assertEquals(13752, twoCitySchedCost(new int[][]{
                {403, 578}, {406, 455}, {710, 697}, {155, 861}, {540, 843}, {911, 753}, {477, 453}, {378, 936}, {492, 720}, {915, 382}, {984, 200}, {449, 448}, {525, 964}, {875, 767}, {905, 753}, {18, 84}, {351, 167}, {554, 582}, {175, 794}, {677, 301}, {268, 994}, {631, 627}, {53, 107}, {995, 390}, {540, 406}, {932, 808}, {426, 455}, {997, 735}, {449, 757}, {90, 869}, {640, 396}, {573, 536}
        }));

        Assertions.assertEquals(13215, twoCitySchedCost(new int[][]{
                {60, 749}, {520, 113}, {951, 37}, {143, 789}, {881, 936}, {60, 911}, {531, 261}, {292, 335}, {515, 462}, {839, 555}, {268, 482}, {121, 19}, {75, 942}, {498, 317}, {499, 271}, {351, 322}, {602, 169}, {807, 903}, {154, 539}, {806, 61}, {449, 889}, {637, 954}, {505, 505}, {672, 742}, {752, 782}, {127, 207}, {457, 505}, {773, 357}, {778, 890}, {900, 718}, {692, 483}, {992, 715}, {166, 694}, {615, 618}, {426, 50}, {151, 714}, {151, 434}, {72, 874}
        }));
    }
}
