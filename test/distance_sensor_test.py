import unittest
from rocketlauncher.distance_sensor import *


class MainFrameTest(unittest.TestCase):

    def test_that_sensor_can_be_triggered(self):
        trigger_sensor()

    def test_that_measure_time_elapsed_returns_time_span(self):
        GPIO.trigger_sensor()
        span = measure_time_elapsed()
        self.assertAlmostEquals(0.1, span, None, None, 0.015)

    def test_that_distance_returns_almost_seventeen(self):
        distance = distance_in_meters()
        self.assertAlmostEquals(17, distance, None, None, 1.5)
