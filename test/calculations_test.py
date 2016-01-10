import unittest
from rocketlauncher.calculations import *


class CalculationsTest(unittest.TestCase):
    def testHypotenuse(self):
        hypotenuse = calc_hypotenuse(4, 3)
        self.assertEquals(5, hypotenuse,
                          "Expected that hypotenuse(4, 3) returns 5, but was " +
                          "{:.10f}".format(hypotenuse))

    def testSinAlpha(self):
        sin_of_angle_alpha = calc_sin_of_triangle(4, 3)
        self.assertEquals(0.6, sin_of_angle_alpha,
                          "Expect that angle alpha has an sin() value of 0.6, but was " +
                          "{:.10f}".format(sin_of_angle_alpha))

    def testSinAlpha2(self):
        sin_of_angle_alpha = calc_sin_of_triangle(5, 3)
        self.assertAlmostEquals(0.51450, sin_of_angle_alpha, 5,
                                "Expect that angle alpha has an sin() value of 0.51450, but was " +
                                "{:.10f}".format(sin_of_angle_alpha))

    def testAngleAlpha(self):
        value_of_angle_alpha = calc_angle_degrees_of_triangle(4, 3)
        self.assertAlmostEquals(0.64350, value_of_angle_alpha, 5)

    def testDistanceFromTimeSpan(self):
        time_span_that_sound_needs_for_two_meters = 0.005830904
        distance = calc_distance_from_time_span(time_span_that_sound_needs_for_two_meters)
        self.assertAlmostEquals(1, distance, 5)

    def suite(self):
        return unittest.makeSuite(CalculationsTest, 'test')

    if __name__ == "__main__":
        unittest.main()
