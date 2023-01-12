import matplotlib.pyplot as plt
import sys


def draw_number(n):
    x = []
    y = []
    while n > 1:
        if n % 2 == 0:
            n = n / 2
            y.append(n)
        else:
            n = (3 * n) + 1
            y.append(n)
    for i in range(len(y)):
        x.append(i)
    plt.plot(x, y)


if __name__ == '__main__':
    draw_number(int(sys.argv[1]))
    plt.show()