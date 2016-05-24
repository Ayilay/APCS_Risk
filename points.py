points = [
[746,  63],
[763,  74],
[763,  79],
[774,  79],
[776,  76],
[780,  76],
[783,  79],
[788,  79],
[799,  89],
[838,  93],
[836,  97],
[840, 101],
[844, 101],
[845,  99],
[847,  99],
[849, 101],
[849, 104],
[844, 108],
[844, 109],
[851, 117],
[826, 101],
[811, 101],
[812, 113],
[768, 112],
[752, 105],
[749, 108],
[752, 111],
[733, 109],
[724, 114],
[721, 108],
[725, 104],
[728, 105],
[732, 105],
[736,  99],
[739,  99],
[739,  85],
[745,  85],
[748,  81],
[754,  81]
]
minx = points[0][0]
miny = points[0][1]

maxx = points[0][0]
maxy = points[0][1]

for p in points:
    if p[0] < minx:
        minx = p[0]
    if p[1] < miny:
        miny = p[1]
    if p[0] > maxx:
        maxx = p[0]
    if p[1] > maxy:
        maxy = p[1]

print "this.setPreferredSize(new Dimension(" + str(maxx - minx) + ", " + str(maxy - miny) + "));"
print "this.setSize(new Dimension(" + str(maxx - minx) + ", " + str(maxy - miny) + "));"
print "this.setLocation(" + str(minx) + ", " + str(miny+5) + ");"

for p in points:
    p[0] -= minx
    p[1] -= miny
    print "shape.addPoint(" + str(p[0]) + ", " + str(p[1]) + ");"

