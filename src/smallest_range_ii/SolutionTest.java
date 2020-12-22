package smallest_range_ii;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest {

    Solution0 solution = new Solution0();

    @Test
    void N1_K0() {
        Assertions.assertEquals(0, solution.smallestRangeII(new int[]{1}, 0));
    }

    @Test
    void N2() {
        Assertions.assertEquals(6, solution.smallestRangeII(new int[]{0, 10}, 2));
    }

    @Test
    void N3() {
        Assertions.assertEquals(3, solution.smallestRangeII(new int[]{1, 3, 6}, 3));
    }

    @Test
    void normal() {
        Assertions.assertEquals(37, solution.smallestRangeII(new int[]{9,35,42,62,74,99,99}, 27));
    }

    @Test
    void normal2() {
        Assertions.assertEquals(78, solution.smallestRangeII(new int[]{3,13,13,36,61,75,76,81}, 63));
    }

    @Test
    void normal3() {
        Assertions.assertEquals(47, solution.smallestRangeII(new int[]{43,78,90}, 42));
    }

    @Test
    void normal4() {
        Assertions.assertEquals(2, solution.smallestRangeII(new int[]{3,1,10}, 4));
    }

    @Test
    void largeK() {
        Assertions.assertEquals(3, solution.smallestRangeII(new int[]{31,34}, 99));
    }

    @Test
    void largeN_100() {
        Assertions.assertEquals(8870, solution.smallestRangeII(new int[]{8038,9111,5458,8483,5052,9161,8368,2094,8366,9164,53,7222,9284,5059,4375,2667,2243,5329,3111,5678,5958,815,6841,1377,2752,8569,1483,9191,4675,6230,1169,9833,5366,502,1591,5113,2706,8515,3710,7272,1596,5114,3620,2911,8378,8012,4586,9610,8361,1646,2025,1323,5176,1832,7321,1900,1926,5518,8801,679,3368,2086,7486,575,9221,2993,421,1202,1845,9767,4533,1505,820,967,2811,5603,574,6920,5493,9490,9303,4648,281,2947,4117,2848,7395,930,1023,1439,8045,5161,2315,5705,7596,5854,1835,6591,2553,8628}, 4643));
    }

    @Test
    void largeN_100_2() {
        Assertions.assertEquals(6394, solution.smallestRangeII(new int[]{6239,5954,7085,8534,7604,6510,1241,5937,8287,6974,9909,1985,275,4137,6995,5311,3938,5356,6408,4776,9252,8551,9909,7465,9922,2356,273,4587,5390,8119,1075,9382,3244,7498,3121,1994,3474,4886,4607,4592,9529,4287,7501,3753,3701,5050,8530,483,1840,2989,7288,6513,153,5467,2244,3189,1286,8523,9927,9499,2381,1602,3981,1371,9449,4310,8706,4621,8569,9062,7369,7216,5113,2893,5584,8837,6070,9313,7808,8638,3979,8891,5735,4719,1248,6199,7270,4470,1496,517,1153,7859,7162,1710,5484,3495,2670,9509,3144,4812}, 1690));
    }

    @Test
    void largeN_1000() {
        Assertions.assertEquals(6632, solution.smallestRangeII(new int[]{4063,5575,6363,2743,9163,8391,6737,5681,8168,4334,4834,4837,8251,7178,1997,1150,9295,9507,1600,8408,8161,798,4649,1727,1781,4230,5929,3371,3615,7327,6942,1759,162,2867,8090,4635,5825,6709,8359,1648,2281,3915,2682,5360,3629,2382,1537,8356,2129,9366,2440,5301,4365,1015,4148,9585,3275,3539,7834,6556,8379,5649,9278,5124,1585,3321,8341,6264,4298,3823,3587,5133,6402,900,9198,1726,162,1195,5129,8618,9879,6867,4435,2874,5286,7745,5196,8662,1114,6442,3669,8882,4557,6511,4363,790,31,1663,2852,9765,9405,5590,650,623,5547,497,8602,6278,9598,4801,4456,5719,6675,1505,5403,4092,9215,4025,292,4943,9237,4621,74,4732,5422,1980,1846,1950,6907,4653,2037,9492,1001,9051,3156,1972,9672,6991,3467,4343,4302,2184,5548,1566,473,2522,507,6983,6561,2490,7710,4561,7089,5138,141,7635,5016,5800,7994,8000,8730,3410,9541,418,41,6060,8366,1973,94,3778,1417,4927,5468,9922,3256,2168,3185,2334,544,8156,1676,7607,5997,1618,7187,4340,2884,5052,2375,1498,5066,9950,2771,3413,3337,8298,4826,765,4434,8113,7100,1924,1857,7506,1213,7090,197,9980,5888,6043,1796,1451,9050,2279,7825,4741,854,2292,7191,5355,2630,5896,2864,3765,9559,2071,2847,2695,8537,3858,6469,209,9945,4719,4530,1847,6155,5887,6999,2274,5759,5579,5267,942,1832,1192,1618,2113,3338,1951,2679,6960,7308,3904,9412,8972,2305,1005,7889,69,3791,2161,299,7149,6931,6436,3824,1955,8666,30,6671,8123,3500,836,3725,1738,1328,6502,7559,5751,9741,905,7377,1084,1271,4055,5297,8425,4915,8553,7806,8288,7003,6641,8502,5134,641,695,172,5485,6668,4592,1383,8222,1539,3995,7753,3829,698,5373,9653,9473,9113,824,7209,8025,9685,7634,9982,9249,9949,3660,7426,8261,4064,6873,5629,6829,5237,9635,9562,5338,1554,1350,2986,5491,3149,8065,5167,3899,5902,5715,5048,679,2171,9781,6077,5803,5819,8190,2905,555,3806,9595,1200,7314,9022,6722,8602,2729,9391,3655,3191,3895,2824,8314,2865,2829,3455,2897,8515,1202,2047,2236,4638,8900,5269,1083,9983,4639,9325,7017,5759,1595,1757,9406,7801,2517,713,3171,9296,4400,6967,5557,4942,5560,4143,7368,4162,9526,2245,2602,4298,3722,794,2158,493,4137,6356,856,6065,5867,2520,5548,6765,7315,5067,4808,7207,9433,9798,8979,9177,4220,6426,4056,2988,1427,1474,2420,1379,3025,2565,6879,7730,3147,1477,9294,1403,9512,832,7542,9388,5631,7485,8049,859,8520,5158,6716,8392,1969,9752,2279,8298,8545,1571,6386,1848,8435,942,5620,857,9531,6242,1564,5737,4237,2151,2510,4120,3317,2742,6965,7301,9541,826,4690,3228,5742,1519,2397,3219,6432,9034,6090,6399,7115,9955,373,3261,7559,9742,2113,9898,8208,7683,43,4524,9146,2292,9449,660,9182,7385,1502,6412,5182,99,4827,1906,2287,7794,4436,2889,7154,1335,2445,8095,3653,8104,5631,355,736,1305,8101,3219,2340,4533,3041,2715,775,2226,7701,5909,6781,8570,36,9643,1912,7619,6488,3287,2806,6982,193,9148,9069,1269,2133,5202,7152,9223,1706,4175,5698,1927,6394,1531,1478,1173,2752,9309,4103,6639,6610,3914,4303,8016,894,7261,507,8598,7560,7875,5594,7771,9038,5370,2022,6139,4472,5973,7646,5866,4044,2348,5292,6842,5658,5286,1983,7692,2381,7214,8769,8327,5956,6285,2481,9258,2226,6512,2578,4253,9247,5712,2168,8099,692,2850,8448,3563,4777,6378,8066,2805,3862,2518,9786,9385,9835,1211,8683,7431,3772,5318,532,661,645,711,7210,831,9844,1111,7172,905,9055,161,8513,864,9700,1616,7867,4994,9300,9601,6905,4788,192,6824,8376,2725,1246,9041,7384,684,4623,4098,1015,5827,5304,4561,3433,9765,6438,6169,6988,6136,4168,2693,5263,2475,5574,6558,5622,2237,974,5268,906,3987,3379,319,2086,3063,8970,2793,3485,9989,9568,3125,3933,5425,1666,4006,5191,6529,3620,8226,9889,6543,9285,2902,2166,3758,1158,7307,8868,4751,8540,5624,4588,8623,5553,4976,1774,1595,1801,8537,8388,3089,712,431,6537,420,2931,6106,6287,6874,8780,6024,85,8716,1657,2170,6221,9847,2180,3775,7064,295,5078,9922,1155,8048,4030,2289,3859,1419,4274,4931,9235,5671,9157,655,5842,8413,4076,5260,1761,242,8907,5033,4154,1807,1327,3781,9204,459,5909,2555,6973,9349,6381,5540,8390,8246,3056,8103,4625,459,2000,4294,397,5632,3224,7980,3255,445,8267,1324,7889,6981,8696,6954,4770,3777,6279,4623,5512,7930,6737,4238,2185,3716,3839,3467,8583,6762,3201,3497,8054,4977,1176,8415,4689,6341,7396,9440,9546,8882,7974,8621,4127,9659,8121,5612,1023,9686,5587,9746,2998,2841,8152,2069,8546,2406,1723,6150,4330,8238,6283,5191,2515,2353,4492,7953,4891,8562,2340,8748,8273,9709,5886,7205,1948,5962,4697,923,7402,9805,7591,7456,8206,2391,6579,3303,4201,6619,5011,8520,1750,9661,6897,9342,3164,4661,3544,3347,8453,8245,5893,3721,5441,7959,6666,4328,5838,2341,1706,1857,4915,1215,3701,5579,6555,5999,9650,485,4052,541,9853,770,6411,9563,1543,8634,3191,2766,676,7953,4922,5742,5458,2381,3132,737,3085,6777,534,7187,8086,1125,2930,4630,831,2920,1745,6423,632,6722,5833,7641,1848,301,847,6280,6114,9074,4061,5253,6877,9292,2624,9769,5803,9758,7679,695,5145,5457,9653,5260,7838,1394,3609,4865,3777,429,9024,8444,6221,5007,751,7002,2397,5586,5077,6184,4945,6004,6553,1736,1791,4514,6040,690,6577,2352,4002,2323,3394,2795,7934,1601,7566,6110,6400,6834,2857,2919,677,2753,5138,9440,6959,3683,100,3682,7338}, 3343));
    }
}
