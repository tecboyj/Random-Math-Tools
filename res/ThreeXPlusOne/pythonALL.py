import matplotlib.pyplot as plt

fig, ax = plt.subplots(1, 2)

level = 9234
criticalMax = []
critical = []
avarageLength = []

def draw_numberAll(max):
    i = 2
    for n in range(2, max):
        x = []
        y = []

        while n > 1:
            if n % 2 == 0:
                n = n / 2
                y.append(n)
            else:
                n = (3 * n) + 1
                y.append(n)

        y2 = whatever.remove(y.copy())

        for j in range(len(y)):
            x.append(j)

        if 9232 in y:
            critical.append(i)


        #ax.plot(x, y, label=str(i))
        ax[0].plot(x, y, label=str(i))
        #name(x, y, i, max)
        avarageLength.append(x[-1])

        i += 1

def name(x, y, i, max):

    labelY = 0
    for j in y:
        if (j > labelY):
            labelY = j

    if (labelY == 9232):
        criticalMax.append(i + 1)

    if (labelY < 50000 or max != 1000):
        return

    labelX = x[y.index(labelY)]

    string = str(i) + " (" + str(int(labelY)) + ")"
    ax[0].text(labelX, labelY, string)
    #ax.text(labelX, labelY, string)

class whatever:
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

        ax[1].plot(x, y)

    def remove(y):
        for i in range(len(y)):
            if (y[i] > 9232):
                y[i] = 0
        return y

if __name__ == '__main__':

    draw_numberAll(1000)
    whatever.draw_number(9232)
    whatever.draw_number(16)


    plt.title("3x+1", size=40)
    plt.show()