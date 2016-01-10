import math

SPEED_OF_SOUND = 343  # 343 m /s: constant for speed of sound travelling through air at 20 degrees Celsius


def calc_hypotenuse(adjacent, opposite):
    """
    this function uses Pythagoras' theorem to calculate the length of
     the hypotenuse of a rectangular triangle.
    :param adjacent: the length of one short side of an rectangular triangle
    :param opposite: the length of the other short side of an rectangular triangle
    :return: the length of the hypotenuse
    """
    return math.sqrt(adjacent * adjacent + opposite * opposite)


def calc_sin_of_triangle(adjacent, opposite):
    """
    this function uses the mathematical angle set:
        SIN(ALPHA) = OPPOSITE SIDE / HYPOTENUSE
    to calculate the sinus value of an angle in an rectangular triangle
    :param adjacent: the length the short side adjacent to angle ALPHA
    :param opposite: the length the short side opposite to angle ALPHA
    :return: the sinus value of angle ALPHA
    """
    hypotenuse = calc_hypotenuse(adjacent, opposite)
    return opposite / hypotenuse


def calc_angle_degrees_of_triangle(adjacent, opposite):
    """
    this function calculates the angle ALPHA of and rectangular triangle in degrees.
    :param adjacent: the length the short side adjacent to angle ALPHA
    :param opposite: the length the short side opposite to angle ALPHA
    :return: the value of hte angle ALPHA in degrees
    """
    sin_of_triangle = calc_sin_of_triangle(adjacent, opposite)
    return math.asin(sin_of_triangle)


def calc_distance_from_time_span(time_span_there_and_back):
    """
    this function calculates the distance of an object by the time a sound signal needs to travel there and back.
    :param time_span_there_and_back: the time in seconds
    :return: the distance in meters
    """
    time_span = time_span_there_and_back / 2
    distance = time_span * SPEED_OF_SOUND
    return distance
