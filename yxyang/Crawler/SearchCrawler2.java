//Search for key words
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;
import javax.swing.*;
import javax.swing.table.*;

public class SearchCrawler2 extends JFrame
{
	
	int i=0;
	int ii=0;
	int[] namedefs1={2,7,11,13,14,20,25,34,37,43,44,51,52,53,58,61,62,64,76,77,81,86,88,95,96,99,100,109,115,116,120,125,126,131,136,139,153,154,155,158,160,163,164,165,167,180,184,189,191,192,196,199,201,202,211,214,216,220,222,224,225,228,229,230,234,233,238,240,244,245,248,255,257,258,259,261,263,264,266,269,271,277,288,290,291,293,294,300,301,302,306,307,311,312,313,314,315,316,317,322,325,329,330,331,333,337,338,341,342,343,348,353,356,357,359,362,364,367,368,369,370,371,376,378,379,383,384,385,388,389,394,396,397,398,405,406,408,410,414,420,425,429,442,447,449,452,453,458,459,461,464,468,470,471,472,476,477,478,489,491,492,493,497,498,500,502,505,509,511,520,521,524,525,526,527,529,531,535,543,544,547,552,558,562,564,568,570,571,579,580,581,582,585,586,587,591,600,603,606,607,608,612,614,621,622,624,625,626,629,633,638,642,643,646,649,648,652,654,658,661,666,667,673,674,675,676,680,681,682,683,693,694,705,706,707,709,710,712,716,718,719,722,725,734,736,742,750,757,758,760,763,766,769,774,780,209,788,791,796,798,800,810,813,814,815,816,817,823,825,826,828,829,835,839,844,847,848,849,851,852,855,858,868,874,875,879,881,889,892,898,899,900,902,905,907,911,912,914,917,920,921,924,926,930,931,932,934,938,939,944,945,947,949,950,952,955,959,960,977,980,982,984,986,989,997,998,999,1004,1005,1007,1009,1015,1017,1020,1021,1027,1028,1031,1037,1046,1047,1053,1056,1060,1064,1065,1067,1071,1073,1082,1085,1096,1101,1102,1109,1110,1111,1112,1113,1117,677,1120,1121,1122,1123,1127,1133,1134,1137,1138,1139,1142,1145,1100,1149,1154,1160,1162,1163,1164,1174,1179,1180,1183,1186,1187,1188,1190,1191,1193,1194,1196,1201,1205,1207,1,1208,1211,1212,1213,1217,1218,1222,1224,1225,1226,1230,1234,1236,1239,1240,1242,1245,1248,1249,1251,1255,1259,1262,1265,1268,1271,1272,1273,1276,1281,1286,1288,1292,1293,1295,1299,1306,1310,1311,1312,1316,1321,1322,1324,1325,1327,1329,1331,1332,1334,1336,1337,1338,1339,1341,1342,1343,1344,1345,1346,1354,1356,1357,1360,1362,1366,1367,1369,1371,1374,1376,1385,1386,1388,1394,1395,1398,1399,1404,1422,1423,1430,1437,1440,4246,1445,1453,1457,2033,1470,1472,1473,1474,1478,1479,1480,1481,1483,1484,1486,1489,1491,1493,1495,1496,1503,1505,1509,1512,1513,1514,1515,1523,1524,1528,1532,1537,1540,1546,1553,1554,1559,1563,1565,1570,1578,1582,1584,1585,1593,1594,1595,1596,1599,1600,1601,1602,1603,1605,1611,1614,1615,1617,1622,1623,1632,1635,1637,1638,1639,1641,1642,1644,1645,1650,1652,1659,1665,1666,1669,1670,1672,1678,1680,1681,1682,1683,1684,1685,1686,1693,1694,1698,1699,1703,1704,1706,1707,1719,1730,1739,1740,1743,1745,1748,1749,1755,1762,1765,1766,1768,1770,1771,1574,1783,1787,1788,1794,1795,1796,1797,1803,1804,1805,1808,1817,1818,1820,1821,1824,1825,1828,1829,1832,1833,1836,1837,1840,1841,1858,1859,1860,1863,1864,1865,1871,1872,1875,1876,1881,1883,1884,1889,1890,1892,1895,1898,1900,1908,1910,1913,1914,1916,1918,1920,1927,1929,1931,1934,1935,1938,1941,1942,1944,1947,1948,1950,1951,1952,1955,1956,1957,1958,1959,1960,1963,1964,1967,1968,5309,1971,1978,1984,1990,1991,1998,2003,2006,2007,2008,2009,2017,2018,2019,2026,2028,2011,1772,1773,1779};
	int[] namedefs2={1780,2039,2040,2042,2044,2048,2049,2050,2051,2054,2059,2062,2063,2070,2072,2078,2087,2088,2089,2090,2091,2098,2099,2102,2112,2119,2120,2122,2126,2127,2130,2131,2132,2133,2134,2141,2142,2143,2146,2147,2148,2149,2150,2152,2155,2156,2158,2162,2167,2169,2171,2174,2181,2183,2186,2188,2189,2197,2198,2199,2200,2201,2203,2205,2213,2214,2215,2216,2217,2218,2220,2226,2227,2232,2233,2235,2239,2246,2248,2250,2255,2258,2259,2264,2265,2268,2270,2275,2277,2278,2279,2280,2283,2288,2289,2295,2299,2300,2304,2305,2307,2308,2309,2312,2313,2314,2316,2319,2323,2325,2329,2331,2333,2337,2339,2341,2343,2344,2353,2355,2357,2361,2367,2369,2372,2373,2377,2380,2387,2388,2391,2392,2397,2399,2405,2406,2407,2408,2409,2411,2412,2415,2416,2417,2420,2423,2425,2432,2434,2435,2437,2442,2443,2451,2452,2453,2454,2462,2463,2472,2473,2478,2479,2486,2487,2488,2035,2037,2489,2494,2500,2508,2509,5307,2515,2517,2518,2519,2521,2523,2531,2533,2535,2540,2545,2546,2548,2549,2551,2552,2553,2555,2560,2564,2566,2567,2570,2575,2581,2584,2586,2587,2588,5308,2592,2593,2594,2599,2600,2601,2603,2605,2608,2615,2618,2620,2621,2514,2644,2650,2653,2654,2656,2657,2658,2659,2660,2664,2674,2679,2680,2681,2684,2685,2687,2689,2690,2691,2693,2696,2697,2703,2705,2707,2710,2713,2714,2716,2723,2727,2729,2732,2735,2736,2739,2741,2742,2749,2750,2751,2754,2755,2757,2758,2759,2760,2762,2626,2629,2776,2777,2779,2790,2792,2795,2802,2806,2809,2810,2811,2812,2814,2815,2839,2841,2842,2844,2848,2857,2858,2866,2868,2870,2873,2878,2883,2885,2886,2893,2901,2903,2911,2912,2916,2926,2932,2936,2937,2938,2944,2946,2947,2948,2952,3,2971,2981,2989,2990,2991,2992,3001,3000,3012,3015,3021,3023,3025,3026,3027,3029,3035,3036,3038,3040,3045,3047,3051,3054,3056,3058,3081,3083,3098,3115,3118,3131,3132,3137,3140,3141,3147,3148,3150,3154,3155,3157,3160,3163,3164,3168,3169,3170,3172,3173,3174,3175,3176,3177,3178,3181,3184,3185,3190,3192,3195,3196,3197,3199,3200,3206,3208,3210,3217,3218,3219,3202,3223,3225,3227,3232,3233,3234,3245,3251,3252,3262,3265,3272,3274,3276,3277,3282,3283,3285,3286,3288,3289,3290,3293,3295,3296,3299,3300,3301,3304,3306,3311,3315,3316,3317,3320,3321,3328,3329,3330,3332,3333,3336,3345,3347,3350,3353,3356,3357,3358,3361,3362,3366,3373,3379,3380,3381,3385,3389,3390,3392,3394,3401,3402,3403,3404,3407,3408,3409,3410,3412,3413,3414,3416,3421,3422,3428,3429,3441,3443,3445,3446,3449,3453,3454,3455,3457,3458,3459,3460,3463,3466,3433,3064,3067,3069,3070,3072,3478,3488,3489,3501,3503,3505,3506,3512,3513,3514,3516,3517,3521,3523,3524,3526,3528,3532,3533,3535,3537,3538,3540,3544,3546,3547,3549,3551,3552,3553,3555,3557,3560,3561,3564,3565,3473,3570,3572,3579,3581,3583,3585,3589,3591,3593,3599,3608,3612,3614,3619,3621,3628,3629,3630,3636,3637,3639,3716,3643,3646,3658,3660,3663,3668,3670,3672,3674,3677,3680,3687,3688,3700,3703,3709,3711,3712,3713,3717,3718,3719,3720,3721,3723,3725,3728,3738,3742,3743,3748,3766,3767,3769,3770,3772,3773,3786,3787,3788,3789,3793,3794,3796,3802,3804,3806,3813,3818,3820,3822,3829,3836,3837,3838,3843,3853,3854,3855,3859,3861,3862,3867,3869,3871,3877,3885,3887,3889,3891,3894,3896};
	int[] namedefs3={3907,3910,3911,3912,3918,3919,3920,3922,5305,3927,3928,3929,3930,3933,3941,3944,3945,3948,3949,3950,3952,3954,3955,3956,3957,3958,3960,3962,3965,3970,3973,3974,3976,3980,3981,3984,3987,3989,3990,3996,3997,3999,4004,4005,4007,4008,4009,4019,4020,1494,4026,4027,4028,4031,4032,4034,4036,4037,4042,4046,4051,4052,4054,4057,4058,4063,4064,4066,4067,4069,4071,4080,4082,4083,4089,4092,4093,4101,4102,4105,4111,4114,4117,4118,4120,4126,4127,4128,4129,4130,4137,3901,3905,4139,4140,4143,4146,4153,4156,4158,4159,4162,4163,4165,4168,4169,4177,4178,4181,4184,4189,4190,4193,4195,4202,4206,4208,4214,4216,4218,4221,4222,4223,4225,4227,4228,4229,4236,4238,4239,4240,4245,4248,4197,4249,4258,4260,4261,4266,4269,4271,4274,4276,4277,4278,4281,4282,5306,4284,4290,4291,4300,4301,4306,4311,4313,4314,4315,4316,4318,4320,4321,4322,4326,4330,4334,4335,4337,4338,4344,4345,4347,4348,4351,4352,4354,4356,4357,4359,4360,4361,4363,4364,4370,4371,4373,4374,4375,4377,4380,4381,4387,4390,4392,4396,4400,4401,4402,4403,4404,4405,4408,4410,4417,4419,4422,4426,4432,4437,4438,4441,4442,4448,4449,4452,4460,4469,4474,4479,4480,4481,4484,4485,4487,4489,4490,4495,4497,4500,4502,4506,4507,4508,4509,4514,4515,4518,4521,4524,4527,4528,4529,4535,4538,4539,4540,4541,4543,4545,4550,4559,4565,4570,4571,4572,4573,4574,4576,4577,4579,4580,4584,4585,4587,4591,4592,4595,4597,4598,4599,4602,4606,4608,4610,4611,4612,4614,4619,4622,4623,4627,4628,4630,4634,4635,4638,4640,4642,4644,4647,4138,4653,4655,4657,4661,4668,4669,4671,4672,4680,4682,4686,4688,4692,4693,4700,4705,4706,4709,4710,4712,4713,4715,4716,4717,4719,4720,4722,5310,4727,4728,4729,4730,4733,4738,4741,4745,4750,4754,4756,4757,4759,4769,4770,4773,4777,4788,4789,4791,4796,4798,4802,4805,4806,4808,4812,4786,4787,4817,4821,4822,4826,4827,4832,4836,4837,4838,4839,4843,4854,4857,4859,4863,4870,4880,4850,4887,4889,4896,4900,4901,4902,4903,4906,4907,4908,4909,4851,4913,4916,4926,4929,4932,4935,4936,4937,4938,4940,4944,4948,4951,4952,4962,4963,4964,4965,4968,4969,4970,4972,4974,4975,4978,4982,4988,4989,4994,4996,5003,5004,5007,5010,5011,5015,5016,5017,5018,5019,5026,5027,5029,5030,5035,5036,5037,5038,5039,5045,5047,5048,5050,5053,5060,5075,5077,5078,5079,5080,5083,5088,5090,5092,5093,5095,5097,5102,5103,5104,5105,5110,5113,5116,5122,5123,5125,5130,5131,5132,5136,5138,5139,5142,5147,5153,5156,5162,5165,5176,5178,5180,5181,5182,5188,5193,5195,5203,5204,5206,5210,5211,5212,5213,5216,5219,5221,5226,5228,5229,5233,5234,5238,5239,5241,5242,5251,5253,5254,5255,5256,5258,5259,5261,5263,5266,5268,5272,4911,5275,5278,5283,5291,5296,5298,5299};
	int[] namedefs=new int[1815];
	double[] receive=new double[1815];
	char[] target=new char[29];
	// Max URLs drop-down values.
	private static final String[] MAX_URLS =
		{"50", "100", "500", "1000"};
	// Cache of robot disallow lists.
	private HashMap disallowListCache = new HashMap();
	// Search GUI controls.
	private JTextField startTextField;
	private JComboBox maxComboBox;
	private JCheckBox limitCheckBox;
	private JTextField logTextField;
	private JTextField searchTextField;
	private JCheckBox caseCheckBox;
	private JButton searchButton;
	// Search stats GUI controls.
	private JLabel crawlingLabel2;
	private JLabel crawledLabel2;
	private JLabel toCrawlLabel2;
	private JProgressBar progressBar;
	private JLabel matchesLabel2;
	// Table listing search matches.
	private JTable table;
	//Flag for whether or not crawling is underway.
	private boolean crawling;
	//Matches log file print writer.
	private PrintWriter logFileWriter;
	//Constructor for Search Web Crawler.
	public SearchCrawler2()
	{
		//yingxiang
		System.arraycopy(namedefs1,0,namedefs,0,namedefs1.length);
		System.arraycopy(namedefs2,0,namedefs,namedefs1.length,namedefs2.length);
		System.arraycopy(namedefs3,0,namedefs,namedefs1.length+namedefs2.length,namedefs3.length);
		//Set application title.
		setTitle("Search Crawler");
		//Set window size.
		setSize(600, 600);
		//Handle window closing events.
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				actionExit();
			}
		});
		//Set up File menu.
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		JMenuItem fileExitMenuItem = new JMenuItem("Exit",
				KeyEvent.VK_X);
		fileExitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionExit();
			}
		});
		fileMenu.add(fileExitMenuItem);
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		//Set up search panel.
		JPanel searchPanel = new JPanel();
		GridBagConstraints constraints;
		GridBagLayout layout = new GridBagLayout();
		searchPanel.setLayout(layout);
		JLabel startLabel = new JLabel("Start URL:");
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets = new Insets(5, 5, 0, 0);
		layout.setConstraints(startLabel, constraints);
		searchPanel.add(startLabel);
		startTextField = new JTextField();
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.insets = new Insets(5, 5, 0, 5);
		layout.setConstraints(startTextField, constraints);
		searchPanel.add(startTextField);
		JLabel maxLabel = new JLabel("Max URLs to Crawl:");
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets = new Insets(5, 5, 0, 0);
		layout.setConstraints(maxLabel, constraints);
		searchPanel.add(maxLabel);
		maxComboBox = new JComboBox(MAX_URLS);
		maxComboBox.setEditable(true);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 0, 0);
		layout.setConstraints(maxComboBox, constraints);
		searchPanel.add(maxComboBox);
		limitCheckBox =
				new JCheckBox("Limit crawling to Start URL site");
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(0, 10, 0, 0);
		layout.setConstraints(limitCheckBox, constraints);
		searchPanel.add(limitCheckBox);
		JLabel blankLabel = new JLabel();
		constraints = new GridBagConstraints();
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		layout.setConstraints(blankLabel, constraints);
		searchPanel.add(blankLabel);
		JLabel logLabel = new JLabel("Matches Log File:");
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets = new Insets(5, 5, 0, 0);
		layout.setConstraints(logLabel, constraints);
		searchPanel.add(logLabel);
		String file =
				System.getProperty("user.dir") +
				System.getProperty("file.separator") +
				"crawler.log";
		logTextField = new JTextField(file);
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.insets = new Insets(5, 5, 0, 5);
		layout.setConstraints(logTextField, constraints);
		searchPanel.add(logTextField);
		JLabel searchLabel = new JLabel("Search String:");
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets = new Insets(5, 5, 0, 0);
		layout.setConstraints(searchLabel, constraints);
		searchPanel.add(searchLabel);
		searchTextField = new JTextField();
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(5, 5, 0, 0);
		constraints.gridwidth= 2;
		constraints.weightx = 1.0d;
		layout.setConstraints(searchTextField, constraints);
		searchPanel.add(searchTextField);
		caseCheckBox = new JCheckBox("Case Sensitive");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 0, 5);
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		layout.setConstraints(caseCheckBox, constraints);
		searchPanel.add(caseCheckBox);
		searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionSearch();
			}
		});
		constraints = new GridBagConstraints();
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(searchButton, constraints);
		searchPanel.add(searchButton);
		JSeparator separator = new JSeparator();
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(separator, constraints);
		searchPanel.add(separator);
		JLabel crawlingLabel1 = new JLabel("Crawling:");
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets = new Insets(5, 5, 0, 0);
		layout.setConstraints(crawlingLabel1, constraints);
		searchPanel.add(crawlingLabel1);
		crawlingLabel2 = new JLabel();
		crawlingLabel2.setFont(
				crawlingLabel2.getFont().deriveFont(Font.PLAIN));
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.insets = new Insets(5, 5, 0, 5);
		layout.setConstraints(crawlingLabel2, constraints);
		searchPanel.add(crawlingLabel2);
		JLabel crawledLabel1 = new JLabel("Crawled URLs:");
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets = new Insets(5, 5, 0, 0);
		layout.setConstraints(crawledLabel1, constraints);
		searchPanel.add(crawledLabel1);
		crawledLabel2 = new JLabel();
		crawledLabel2.setFont(
				crawledLabel2.getFont().deriveFont(Font.PLAIN));
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.insets = new Insets(5, 5, 0, 5);
		layout.setConstraints(crawledLabel2, constraints);
		searchPanel.add(crawledLabel2);
		JLabel toCrawlLabel1 = new JLabel("URLs to Crawl:");
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets = new Insets(5, 5, 0, 0);
		layout.setConstraints(toCrawlLabel1, constraints);
		searchPanel.add(toCrawlLabel1);
		toCrawlLabel2 = new JLabel();
		toCrawlLabel2.setFont(
				toCrawlLabel2.getFont().deriveFont(Font.PLAIN));
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.insets = new Insets(5, 5, 0, 5);
		layout.setConstraints(toCrawlLabel2, constraints);
		searchPanel.add(toCrawlLabel2);
		JLabel progressLabel = new JLabel("Crawling Progress:");
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets = new Insets(5, 5, 0, 0);
		layout.setConstraints(progressLabel, constraints);
		searchPanel.add(progressLabel);
		progressBar = new JProgressBar();
		progressBar.setMinimum(0);
		progressBar.setStringPainted(true);
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.insets = new Insets(5, 5, 0, 5);
		layout.setConstraints(progressBar, constraints);
		searchPanel.add(progressBar);
		JLabel matchesLabel1 = new JLabel("Search Matches:");
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.EAST;
		constraints.insets = new Insets(5, 5, 10, 0);
		layout.setConstraints(matchesLabel1, constraints);
		searchPanel.add(matchesLabel1);
		matchesLabel2 = new JLabel();
		matchesLabel2.setFont(
				matchesLabel2.getFont().deriveFont(Font.PLAIN));
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.insets = new Insets(5, 5, 10, 5);
		layout.setConstraints(matchesLabel2, constraints);
		searchPanel.add(matchesLabel2);
		// Set up matches table.
		table =
				new JTable(new DefaultTableModel(new Object[][]{},
						new String[]{"URL"}) {
					public boolean isCellEditable(int row, int column)
					{
						return false;
					}
				});
		// Set up Matches panel.
		JPanel matchesPanel = new JPanel();
		matchesPanel.setBorder(
				BorderFactory.createTitledBorder("Matches"));
		matchesPanel.setLayout(new BorderLayout());
		matchesPanel.add(new JScrollPane(table),
				BorderLayout.CENTER);
		// Add panels to display.
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(searchPanel, BorderLayout.NORTH);
		getContentPane().add(matchesPanel, BorderLayout.CENTER);
	}
	// Exit this program.
	private void actionExit() {
		System.exit(0);
	}
	// Handle Search/Stop button being clicked.
	private void actionSearch() {
		// If stop button clicked, turn crawling flag off.
		if (crawling) {
			crawling = false;
			return;
		}
		ArrayList errorList = new ArrayList();
		// Validate that start URL has been entered.
		String startUrl = startTextField.getText().trim();
		if (startUrl.length() < 1) {
			errorList.add("Missing Start URL.");
		}
		// Verify start URL.
		else if (verifyUrl(startUrl) == null) {
			errorList.add("Invalid Start URL.");
		}
		// Validate that Max URLs is either empty or is a number.
		int maxUrls = 0;
		String max = ((String) maxComboBox.getSelectedItem()).trim();
		if (max.length() > 0) {
			try {
				maxUrls = Integer.parseInt(max);
			} catch (NumberFormatException e) {
			}
			if (maxUrls < 1) {
				errorList.add("Invalid Max URLs value.");
			}
		}
		// Validate that matches log file has been entered.
		String logFile = logTextField.getText().trim();
		if (logFile.length() < 1) {
			errorList.add("Missing Matches Log File.");
		}
		// Validate that search string has been entered.
		String searchString = searchTextField.getText().trim();
		if (searchString.length() < 1) {
			errorList.add("Missing Search String.");
		}
		// Show errors, if any, and return.
		if (errorList.size() > 0) {
			StringBuffer message = new StringBuffer();
			// Concatenate errors into single message.
			for(int i=0;i< errorList.size(); i++) {
				message.append(errorList.get(i));
				if(i+1< errorList.size()) {
					message.append("\n");
				}
			}
			showError(message.toString());
			return;
		}
		// Remove "www" from start URL if present.
		startUrl = removeWwwFromUrl(startUrl);
		// Start the Search Crawler.
		search(logFile, startUrl, maxUrls, searchString);
	}
	private void search(final String logFile, final String startUrl,
			final int maxUrls, final String searchString)
	{
		// Start the search in a new thread.
		Thread thread = new Thread(new Runnable() {
			public void run() {
				// Show hour glass cursor while crawling is under way.
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				// Disable search controls.
				startTextField.setEnabled(false);
				maxComboBox.setEnabled(false);
				limitCheckBox.setEnabled(false);
				logTextField.setEnabled(false);
				searchTextField.setEnabled(false);
				caseCheckBox.setEnabled(false);
				// Switch Search button to "Stop."
				searchButton.setText("Stop");
				// Reset stats.
				table.setModel(new DefaultTableModel(new Object[][]{},
						new String[]{"URL"}) {
					public boolean isCellEditable(int row, int column)
					{
						return false;
					}
				});
				updateStats(startUrl, 0, 0, maxUrls);
				// Open matches log file.
				try {
					logFileWriter = new PrintWriter(new FileWriter(logFile));
				} catch (Exception e) {
					showError("Unable to open matches log file.");
					return;
				}
				// Turn crawling flag on.
				crawling = true;
				// Perform the actual crawling.
				crawl(startUrl, maxUrls, limitCheckBox.isSelected(),
						searchString, caseCheckBox.isSelected());
				// Turn crawling flag off.
				crawling = false;
				// Close matches log file.
				try {
					logFileWriter.close();
				} catch (Exception e) {
					showError("Unable to close matches log file.");
				}
				// Mark search as done.
				crawlingLabel2.setText("Done");
				// Enable search controls.
				startTextField.setEnabled(true);
				maxComboBox.setEnabled(true);
				limitCheckBox.setEnabled(true);
				logTextField.setEnabled(true);
				searchTextField.setEnabled(true);
				caseCheckBox.setEnabled(true);
				// Switch search button back to "Search."
				searchButton.setText("Search");
				// Return to default cursor.
				setCursor(Cursor.getDefaultCursor());
				// Show message if search string not found.
				if (table.getRowCount() == 0) {
					JOptionPane.showMessageDialog(SearchCrawler2.this,
							"Your Search String was not found. Please try another.",
							"Search String Not Found",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		thread.start();
	}
	// Show dialog box with error message.
	private void showError(String message) {
		JOptionPane.showMessageDialog(this, message, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
	// Update crawling stats.
	private void updateStats(
			String crawling, int crawled, int toCrawl, int maxUrls)
	{
		crawlingLabel2.setText(crawling);
		crawledLabel2.setText("" + crawled);
		toCrawlLabel2.setText("" + toCrawl);
		// Update progress bar.
		if (maxUrls == -1) {
			progressBar.setMaximum(crawled + toCrawl);
		} else {
			progressBar.setMaximum(maxUrls);
		}
		progressBar.setValue(crawled);
		matchesLabel2.setText("" + table.getRowCount());
	}
	// Add match to matches table and log file.
	private void addMatch(String url) {
		// Add URL to matches table.
		DefaultTableModel model =
				(DefaultTableModel) table.getModel();
		model.addRow(new Object[]{url});
		// Add URL to matches log file.
		try {
			logFileWriter.println(url);
		} catch (Exception e) {
			showError("Unable to log match.");
		}
	}
	// Verify URL format.
	private URL verifyUrl(String url) {
		// Only allow HTTP URLs.
		if (!url.toLowerCase().startsWith("http://"))
			return null;
		// Verify format of URL.
		URL verifiedUrl = null;
		try {
			verifiedUrl = new URL(url);
		} catch (Exception e) {
			return null;
		}
		return verifiedUrl;
	}
	// Check if robot is allowed to access the given URL.
	private boolean isRobotAllowed(URL urlToCheck) {
		String host = urlToCheck.getHost().toLowerCase();
		// Retrieve host's disallow list from cache.
		ArrayList disallowList =
				(ArrayList) disallowListCache.get(host);
		// If list is not in the cache, download and cache it.
		if (disallowList == null) {
			disallowList = new ArrayList();
			try {
				URL robotsFileUrl =
						new URL("http://" + host + "/robots.txt");
				// Open connection to robot file URL for reading.
				BufferedReader reader =
						new BufferedReader(new InputStreamReader(
								robotsFileUrl.openStream()));
				// Read robot file, creating list of disallowed paths.
				String line;
				while ((line = reader.readLine()) != null) {
					if (line.indexOf("Disallow:") == 0) {
						String disallowPath =
								line.substring("Disallow:".length());
						// Check disallow path for comments and remove if present.
						int commentIndex = disallowPath.indexOf("#");
						if (commentIndex != - 1) {
							disallowPath =
									disallowPath.substring(0, commentIndex);
						}
						// Remove leading or trailing spaces from disallow path.
						disallowPath = disallowPath.trim();
						// Add disallow path to list.
						disallowList.add(disallowPath);
					}
				}
				// Add new disallow list to cache.
				disallowListCache.put(host, disallowList);
			}
			catch (Exception e) {
				/* Assume robot is allowed since an exception
		is thrown if the robot file doesn't exist. */
				return true;
			}
		}
		/* Loop through disallow list to see if
		crawling is allowed for the given URL. */
		String file = urlToCheck.getFile();
		for(int i=0;i< disallowList.size(); i++) {
			String disallow = (String) disallowList.get(i);
			if (file.startsWith(disallow)) {
				return false;
			}
		}
		return true;
	}
	// Download page at given URL.
	private String downloadPage(URL pageUrl) {
		try {
			// Open connection to URL for reading.
			BufferedReader reader =
					new BufferedReader(new InputStreamReader(
							pageUrl.openStream()));
			// Read page into buffer.
			String line;
			StringBuffer pageBuffer = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				pageBuffer.append(line);
			}
			return pageBuffer.toString();
		} catch (Exception e) {
		}
		return null;
	}
	// Remove leading "www" from a URL's host if present.
	private String removeWwwFromUrl(String url) {
		int index = url.indexOf("://www.");
		if (index != -1) {
			return url.substring(0, index + 3) +
					url.substring(index + 7);
		}
		return (url);
	}
	// Parse through page contents and retrieve links.
	private ArrayList retrieveLinks(
			URL pageUrl, String pageContents, HashSet crawledList,
			boolean limitHost)
	{
		// Compile link matching pattern.
		Pattern p =
				Pattern.compile("<a\\s+href\\s*=\\s*\"?(.*?)[\"|>]",
						Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(pageContents);
		// Create list of link matches.
		ArrayList linkList = new ArrayList();
		while (m.find()) {
			String link = m.group(1).trim();
			// Skip empty links.
			if (link.length() < 1) {
				continue;
			}
			// Skip links that are just page anchors.
			if (link.charAt(0) == '#') {
				continue;
			}
			// Skip mailto links.
			if (link.indexOf("mailto:") != -1) {
				continue;
			}
			// Skip JavaScript links.
			if (link.toLowerCase().indexOf("javascript") != -1) {
				continue;
			}
			// Prefix absolute and relative URLs if necessary.
			if (link.indexOf("://") == -1) {
				// Handle absolute URLs.
				if (link.charAt(0) == '/') {
					link = "http://" + pageUrl.getHost() + link;
					// Handle relative URLs.
				} else {
					String file = pageUrl.getFile();
					if (file.indexOf('/') == -1) {
						link = "http://" + pageUrl.getHost() + "/" + link;
					} else {
						String path =
								file.substring(0, file.lastIndexOf('/') + 1);
						link = "http://" + pageUrl.getHost() + path + link;
					}
				}
			}
			// Remove anchors from link.
			int index = link.indexOf('#');
			if (index != -1) {
				link = link.substring(0, index);
			}
			// Remove leading "www" from URL's host if present.
			link = removeWwwFromUrl(link);
			// Verify link and skip if invalid.
			URL verifiedLink = verifyUrl(link);
			if (verifiedLink == null) {
				continue;
			}
			/* If specified, limit links to those
				having the same host as the start URL. */
			if (limitHost &&
					!pageUrl.getHost().toLowerCase().equals(
							verifiedLink.getHost().toLowerCase()))
			{
				continue;
			}
			// Skip link if it has already been crawled.
			if (crawledList.contains(link)) {
				continue;
			}
			// Add link to list.
			linkList.add(link);
		}
		return (linkList);
	}
	/* Determine whether or not search string is
				matched in the given page contents. */
	//yingxiang
	private void searchStringMatches(
			String pageContents, String searchString,
			boolean caseSensitive)
	{
		String searchContents = pageContents;
		/* If case-sensitive search, lowercase
				page contents for comparison. */
		if (!caseSensitive) {
			searchContents = pageContents.toLowerCase();
		}
		System.out.println(searchContents);
		// Split search string into individual terms.
		int ind1=searchContents.indexOf("Your search returned <strong>");
		int ind2=  searchContents.indexOf("</strong> results:");    
		ii++;
		if (ind1>0&&ind2>0){
			String tempS=searchContents.substring(ind1+29, ind2);
			double numl=Double.parseDouble(tempS);
			System.out.println(numl);
		}

	}
	// Perform the actual crawling, searching for the search string.
	public void crawl(
			String startUrl, int maxUrls, boolean limitHost,
			String searchString, boolean caseSensitive)
	{
		// Set up crawl lists.
		HashSet crawledList = new HashSet();
		LinkedHashSet toCrawlList = new LinkedHashSet();
		// Add start URL to the to crawl list.
		toCrawlList.add(startUrl);
		/* Perform actual crawling by looping
				through the To Crawl list. */
		while (crawling && toCrawlList.size() > 0)
		{
			/* Check to see if the max URL count has
				been reached, if it was specified.*/
			if (maxUrls != -1) {
				if (crawledList.size() == maxUrls) {
					break;
				}
			}
			// Get URL at bottom of the list.
			String url = (String) toCrawlList.iterator().next();
			// Remove URL from the To Crawl list.
			toCrawlList.remove(url);
			// Convert string url to URL object.
			URL verifiedUrl = verifyUrl(url);
			// Skip URL if robots are not allowed to access it.
			if (!isRobotAllowed(verifiedUrl)) {
				continue;
			}
			// Update crawling stats.
			updateStats(url, crawledList.size(), toCrawlList.size(),
					maxUrls);
			// Add page to the crawled list.
			crawledList.add(url);
			// Download the page at the given URL.
			String pageContents = downloadPage(verifiedUrl);
			System.out.println(pageContents);
			/* If the page was downloaded successfully, retrieve all its
				links and then see if it contains the search string. */
			if (pageContents != null && pageContents.length() > 0 && i<1815)
			{
				// Retrieve list of valid links from page.
				//Modified by Yingxiang
				ArrayList links=new  ArrayList();
				i=i+1;
				links.add("http://www.darwinproject.ac.uk/advanced-search?intercept=adv&as-type=letter&as-authorId="+namedefs[i]);

				//Modified by Yingxiang
				//System.out.println(links);
				
				// Add links to the To Crawl list.
				toCrawlList.addAll(links);
				/* Check if search string is present in
				page, and if so, record a match. */
				/*
				if (searchStringMatches(pageContents, searchString,
						caseSensitive))
				{
					addMatch(url);
				}
				*/
			}
			// Update crawling stats.
			updateStats(url, crawledList.size(), toCrawlList.size(),
					maxUrls);
		}
	}
	// Run the Search Crawler.
	public static void main(String[] args) {
		SearchCrawler2 crawler = new SearchCrawler2();
		crawler.show();
	}
}
