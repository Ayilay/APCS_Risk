points = [
[826, 102],
[856, 122],
[863, 119],
[866, 126],
[861, 131],
[865, 147],
[861, 150],
[875, 165],
[865, 169],
[852, 152],
[823, 138],
[802, 156],
[722, 117],
[734, 109],
[748, 113],
[813, 114]
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

