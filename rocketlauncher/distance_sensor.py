import RPi.GPIO as GPIO
import time
from calculations import calc_distance_from_time_span

# GPIO Mode (BOARD / BCM)
GPIO.setmode(GPIO.BCM)

# output GPIO pin to trigger the sensor
GPIO_TRIGGER = 18
GPIO.setup(GPIO_TRIGGER, GPIO.OUT)

# input GPIO pin to get the signal of the sensor
GPIO_ECHO = 24
GPIO.setup(GPIO_ECHO, GPIO.IN)


def distance_in_meters():
    # trigger the sensor and measure the time the signal takes to travel
    trigger_sensor()
    time_span = measure_time_elapsed()

    # calculate distance in meters
    calulated_distance = calc_distance_from_time_span(time_span)

    return calulated_distance


def measure_time_elapsed():
    # get time as long as ECHO is LOW
    while GPIO.input(GPIO_ECHO) == 0:
        start = time.time()

    # get time as long as ECHO is HIGH
    while GPIO.input(GPIO_ECHO) == 1:
        stop = time.time()

    # time elapsed between start of the signal and arrivel of the signal
    time_span = stop - start
    return time_span


def trigger_sensor():
    # set trigger to HIGH
    GPIO.output(GPIO_TRIGGER, True)

    # set trigger to LOW after 1 ms
    time.sleep(0.001)
    GPIO.output(GPIO_TRIGGER, False)


if __name__ == '__main__':
    start_time = time.time()

    last_three_distances = [distance_in_meters(), distance_in_meters(), distance_in_meters()]
    average_distance = 0
    i = 0

    try:
        while True:
            i = (i + 1) % 3
            last_three_distances[i] = distance_in_meters()
            last_average_distance = average_distance
            average_distance = (last_three_distances[0] + last_three_distances[1] + last_three_distances[2]) / 3
            # print ("Average distance during the last three measures = %.3f m" % average_distance)

            if abs(last_average_distance - average_distance) > 0.2:
                elapsed = time.time() - start_time
                print("TRIGGER after " + str(elapsed))
                print("Last average " + str(last_average_distance))
                print("New average " + str(average_distance))

            # next measure in 0.5 seconds
            time.sleep(0.5)

    # abort via CTRL + C
    except KeyboardInterrupt:
        print("User interrupt")
        GPIO.cleanup()
